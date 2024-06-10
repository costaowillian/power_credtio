package com.willian.msavaliadorcredito.application.dto;

import java.io.Serializable;
import java.util.List;

public class SituacaoCliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private DadosCliente cliente;
    private List<CartaoCliente> cartoes;

    public SituacaoCliente() {
    }

    public SituacaoCliente(DadosCliente cliente, List<CartaoCliente> cartoes) {
        this.cliente = cliente;
        this.cartoes = cartoes;
    }

    public DadosCliente getCliente() {
        return cliente;
    }

    public void setCliente(DadosCliente cliente) {
        this.cliente = cliente;
    }

    public List<CartaoCliente> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<CartaoCliente> cartoes) {
        this.cartoes = cartoes;
    }
}
