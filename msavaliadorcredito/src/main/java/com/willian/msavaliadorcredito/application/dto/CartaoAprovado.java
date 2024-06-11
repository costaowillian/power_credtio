package com.willian.msavaliadorcredito.application.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartaoAprovado implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cartao;
    private String bandeira;
    private BigDecimal limiteAprovado;

    public CartaoAprovado() {
    }

    public CartaoAprovado(String cartao, String bandeira, BigDecimal limiteAprovado) {
        this.cartao = cartao;
        this.bandeira = bandeira;
        this.limiteAprovado = limiteAprovado;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public BigDecimal getLimiteAprovado() {
        return limiteAprovado;
    }

    public void setLimiteAprovado(BigDecimal limiteAprovado) {
        this.limiteAprovado = limiteAprovado;
    }
}
