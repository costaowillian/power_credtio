package com.willian.msavaliadorcredito.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliar-credito")
public class AvaliadorCreditoCOntroller {

    @GetMapping
    public String status() {
        return "ok";
    }
}
