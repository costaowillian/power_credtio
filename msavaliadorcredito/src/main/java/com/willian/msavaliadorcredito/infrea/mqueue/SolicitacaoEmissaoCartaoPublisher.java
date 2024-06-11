package com.willian.msavaliadorcredito.infrea.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.willian.msavaliadorcredito.domain.dto.DadosSolicitacaoEmissaoCartao;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SolicitacaoEmissaoCartaoPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    private Queue queueEmissaoCartoes;

    public void solicitarCartao(DadosSolicitacaoEmissaoCartao dados) throws JsonProcessingException {
        String json = convertIntoJson(dados);
        rabbitTemplate.convertAndSend(queueEmissaoCartoes.getName(), json);
    }

    private String convertIntoJson(DadosSolicitacaoEmissaoCartao dados) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dados);
    }

}
