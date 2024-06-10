package com.willian.msavaliadorcredito.infra.repository;

import com.willian.msavaliadorcredito.application.dto.CartaoCliente;
import com.willian.msavaliadorcredito.application.dto.DadosCliente;
import com.willian.msavaliadorcredito.application.dto.SituacaoCliente;
import com.willian.msavaliadorcredito.application.exceptions.ErroComunicacaoMicroserviceException;
import com.willian.msavaliadorcredito.infra.repository.clients.CartoesResourceClient;
import com.willian.msavaliadorcredito.infra.repository.clients.ClienteResourceClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliadorCreditoService {

    @Autowired
    private ClienteResourceClient resourceClient;

    @Autowired
    private CartoesResourceClient resourceCartoes;


    public SituacaoCliente obterSituacaoCliente(String cpf) throws ErroComunicacaoMicroserviceException {

        SituacaoCliente situacaoCliente = new SituacaoCliente();
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = resourceClient.dadosCliente(cpf);
            ResponseEntity<List<CartaoCliente>> dadosCartoesResponse = resourceCartoes.getCartoesPorCliente(cpf);

            situacaoCliente.setCartoes(dadosCartoesResponse.getBody());
            situacaoCliente.setCliente(dadosClienteResponse.getBody());
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            String requestUrl = e.request().url();

            if (HttpStatus.NOT_FOUND.value() == status) {
                if (requestUrl.contains("/cartoes")) {
                    throw new NullPointerException("Cartões não encontrados para o CPF informado");
                } else if (requestUrl.contains("/dadosCliente")) {
                    throw new NullPointerException("Nenhum cartão encontrado para o CPF informado");
                }
            } else {
                throw new ErroComunicacaoMicroserviceException("Erro ao realizar requisição: " + e.getMessage(), e.status());
            }
        }

        return situacaoCliente;
    }

}
