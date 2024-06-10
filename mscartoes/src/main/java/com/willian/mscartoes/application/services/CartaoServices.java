package com.willian.mscartoes.application.services;

import com.willian.mscartoes.domain.Cartao;
import com.willian.mscartoes.infra.repository.CartaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartaoServices {

    @Autowired
    private CartaoRepository repository;

    @Transactional
    public Cartao save(Cartao cartao) {
        return repository.save(cartao);
    }

    public List<Cartao> getCartoesRendaMenorIgual(Long renda) {
        BigDecimal redaBigDecimal = BigDecimal.valueOf(renda);
        return repository.findByRendaLessThanEqual(redaBigDecimal);
    }


}
