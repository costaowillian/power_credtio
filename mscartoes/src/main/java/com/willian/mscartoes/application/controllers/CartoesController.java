package com.willian.mscartoes.application.controllers;

import com.willian.mscartoes.application.dto.CartoesPorClienteResponse;
import com.willian.mscartoes.application.dto.SalvarCartaoDTO;
import com.willian.mscartoes.application.services.CartaoServices;
import com.willian.mscartoes.application.services.ClienteCartaoService;
import com.willian.mscartoes.domain.Cartao;
import com.willian.mscartoes.domain.ClienteCartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cartoes")
public class CartoesController {

    @Autowired
    private CartaoServices cartaoServices;

    @Autowired
    private ClienteCartaoService clienteCartaoService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cartao> salvar(@RequestBody SalvarCartaoDTO request) {
        Cartao cartao = request.toModel();
        cartao = cartaoServices.save(cartao);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(cartao.getId()).toUri();

        return ResponseEntity.created(uri).body(cartao);
    }

    @GetMapping(params = "renda", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cartao>> getCartoesPorRenda(@RequestParam("renda") Long renda) {
        List<Cartao> cartaoList = cartaoServices.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok().body(cartaoList);
    }

    @GetMapping(params = "cpf", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesPorCliente(@RequestParam("cpf") String cpf) {
        List<ClienteCartao> lista = clienteCartaoService.listCartoesPorCpf(cpf);
        List<CartoesPorClienteResponse> responseList = lista.stream().map(CartoesPorClienteResponse::fromModel).toList();
        if(responseList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(responseList);
    }

}
