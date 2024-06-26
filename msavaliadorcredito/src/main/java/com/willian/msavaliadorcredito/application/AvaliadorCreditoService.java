package com.willian.msavaliadorcredito.application;

import com.willian.msavaliadorcredito.domain.dto.exceptions.ErroComunicacaoMicroserviceException;
import com.willian.msavaliadorcredito.domain.dto.*;
import com.willian.msavaliadorcredito.domain.dto.exceptions.ErrorSolicitacaoCartaoException;
import com.willian.msavaliadorcredito.infra.repository.clients.CartoesResourceClient;
import com.willian.msavaliadorcredito.infra.repository.clients.ClienteResourceClient;
import com.willian.msavaliadorcredito.infrea.mqueue.SolicitacaoEmissaoCartaoPublisher;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AvaliadorCreditoService {

    @Autowired
    private ClienteResourceClient resourceClient;

    @Autowired
    private CartoesResourceClient resourceCartoes;

    @Autowired
    private SolicitacaoEmissaoCartaoPublisher cartaoPublisher;


    public SituacaoCliente obterSituacaoCliente(String cpf) throws ErroComunicacaoMicroserviceException {

        SituacaoCliente situacaoCliente = new SituacaoCliente();
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = resourceClient.dadosCliente(cpf);
            situacaoCliente.setCliente(dadosClienteResponse.getBody());

            ResponseEntity<List<CartaoCliente>> dadosCartoesResponse = resourceCartoes.getCartoesPorCliente(cpf);
            situacaoCliente.setCartoes(dadosCartoesResponse.getBody());

            return situacaoCliente;
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            String requestUrl = e.request().url();

            if (HttpStatus.NOT_FOUND.value() == status) {
                if (requestUrl.contains("/clientes")) {
                    throw new NullPointerException("Nenhum cliente cadastrado para o CPF informado");
                } else if (requestUrl.contains("/cartoes")) {
                    situacaoCliente.setCartoes(List.of());
                    return situacaoCliente;
                }
            }
            throw new ErroComunicacaoMicroserviceException("Erro ao realizar requisição: " + e.getMessage(), e.status());
        }
    }

    public RetornoAvaliacaoCliente realizarAvaliacao (String cpf, long renda) throws ErroComunicacaoMicroserviceException {

        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = resourceClient.dadosCliente(cpf);
            ResponseEntity<List<Cartao>> cartoesResponse = resourceCartoes.getCartoesPorRenda(renda);

            List<Cartao> listCartoes = cartoesResponse.getBody();
            DadosCliente dadosCliente = dadosClienteResponse.getBody();

            assert listCartoes != null;
            List<CartaoAprovado> cartoesAprovados = getCartaoAprovados(listCartoes, dadosCliente);

            return new RetornoAvaliacaoCliente(cartoesAprovados);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            String requestUrl = e.request().url();

            if (HttpStatus.NOT_FOUND.value() == status) {
                if (requestUrl.contains("/clientes")) {
                    throw new NullPointerException("Nenhum cliente cadastrado para o CPF informado");
                } else if (requestUrl.contains("/cartoes")) {
                    throw new NullPointerException("Não há cartões disponíveis para essa renda");
                }
            }
            throw new ErroComunicacaoMicroserviceException("Erro ao realizar requisição: " + e.getMessage(), e.status());
        }
    }

    private static List<CartaoAprovado> getCartaoAprovados(List<Cartao> listCartoes, DadosCliente dadosCliente) {

        List<CartaoAprovado> cartoesAprovados =  listCartoes.stream().map(cartao -> {
            BigDecimal limiteBasico = cartao.getLimiteBasico();
            BigDecimal idadeBd = BigDecimal.valueOf(dadosCliente.getIdade());

            BigDecimal fator = idadeBd.divide(BigDecimal.valueOf(10));

            BigDecimal limiteProvado = fator.multiply(limiteBasico);

            CartaoAprovado aprovado = new CartaoAprovado(
                    cartao.getNome(), cartao.getBandeira(), limiteProvado
            );
            return aprovado;
        }).collect(Collectors.toList());

        return cartoesAprovados;
    }

    public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados) {
        try {
            cartaoPublisher.solicitarCartao(dados);
            String protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartao(protocolo);
        } catch (Exception e) {
            throw new ErrorSolicitacaoCartaoException(e.getMessage());
        }
    }

}
