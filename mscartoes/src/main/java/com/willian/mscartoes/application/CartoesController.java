package com.willian.mscartoes.application;

import com.willian.mscartoes.application.dto.SalvarCartaoDTO;
import com.willian.mscartoes.domain.Cartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cartoes")
public class CartoesController {

    @Autowired
    private CartaoServices services;

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cartao> salvar(@RequestBody SalvarCartaoDTO request) {
        Cartao cartao = request.toModel();
        cartao = services.save(cartao);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(cartao.getId()).toUri();

        return ResponseEntity.created(uri).body(cartao);
    }

}
