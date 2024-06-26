package com.willian.mscartoes.application.services;

import com.willian.mscartoes.domain.ClienteCartao;
import com.willian.mscartoes.infra.repository.ClienteCartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteCartaoService {

    @Autowired
    private ClienteCartaoRepository repository;

    public List<ClienteCartao> listCartoesPorCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
