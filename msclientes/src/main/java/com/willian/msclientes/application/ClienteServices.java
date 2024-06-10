package com.willian.msclientes.application;

import com.willian.msclientes.domain.Cliente;
import com.willian.msclientes.infra.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServices {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente SalvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> getByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

}
