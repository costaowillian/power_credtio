package com.willian.msclientes.application;

import com.willian.msclientes.application.dto.SalvarClienteDto;
import com.willian.msclientes.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClienteServices clienteServices;

    @GetMapping
    public String status () {
        return "ok";
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> salvar(@RequestBody SalvarClienteDto clienteDto) {
        Cliente clienteDToModel = clienteDto.toModel();

        clienteDToModel = clienteServices.SalvarCliente(clienteDToModel);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().query("cpf={cpf}")
                .buildAndExpand(clienteDToModel.getCpf()).toUri();

        return ResponseEntity.created(uri).body(clienteDToModel);
    }

    @GetMapping(params = "cpf", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> dadosCliente(@RequestParam("cpf") String cpf) {
        Optional<Cliente> cliente =  clienteServices.getByCpf(cpf);
        if(cliente.isPresent()) {
            return ResponseEntity.ok().body(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

}
