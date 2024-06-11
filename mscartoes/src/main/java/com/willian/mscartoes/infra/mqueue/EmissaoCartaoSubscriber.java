package com.willian.mscartoes.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.willian.mscartoes.domain.Cartao;
import com.willian.mscartoes.domain.ClienteCartao;
import com.willian.mscartoes.domain.DadosSolicitacaoEmissaoCartao;
import com.willian.mscartoes.infra.repository.CartaoRepository;
import com.willian.mscartoes.infra.repository.ClienteCartaoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmissaoCartaoSubscriber {
    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ClienteCartaoRepository clienteCartaoRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String payload) {
        try{

            ObjectMapper mapper = new ObjectMapper();
            DadosSolicitacaoEmissaoCartao dadosCartao = mapper.readValue(payload, DadosSolicitacaoEmissaoCartao.class);

            Cartao cartao = cartaoRepository.findById(dadosCartao.getIdCartao()).orElseThrow();

            ClienteCartao clienteCartao = new ClienteCartao(dadosCartao.getCpf(), cartao, dadosCartao.getLimiteLiberado());

            clienteCartaoRepository.save(clienteCartao);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
