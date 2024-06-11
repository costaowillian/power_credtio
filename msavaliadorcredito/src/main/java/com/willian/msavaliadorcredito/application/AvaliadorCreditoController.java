package com.willian.msavaliadorcredito.application;

import com.willian.msavaliadorcredito.domain.dto.DadosAvaliacao;
import com.willian.msavaliadorcredito.domain.dto.RetornoAvaliacaoCliente;
import com.willian.msavaliadorcredito.domain.dto.SituacaoCliente;
import com.willian.msavaliadorcredito.domain.dto.exceptions.ErroComunicacaoMicroserviceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliar-credito")
public class AvaliadorCreditoController {

    @Autowired
    private AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @GetMapping(value = "/situacao-cliente", params = "cpf", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consulteSituacaoCliente(@RequestParam("cpf") String cpf) {
        try {
            SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok().body(situacaoCliente);
        } catch (ErroComunicacaoMicroserviceException e) {
            assert HttpStatus.resolve(e.getStatus()) != null;
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dados) throws ErroComunicacaoMicroserviceException {
        avaliadorCreditoService.realizarAvaliacao(dados.getCpf(), dados.getRenda());
        try {
            RetornoAvaliacaoCliente retorno = avaliadorCreditoService.realizarAvaliacao(dados.getCpf(), dados.getRenda());
            return ResponseEntity.ok().body(retorno);
        } catch (ErroComunicacaoMicroserviceException e) {
            assert HttpStatus.resolve(e.getStatus()) != null;
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
