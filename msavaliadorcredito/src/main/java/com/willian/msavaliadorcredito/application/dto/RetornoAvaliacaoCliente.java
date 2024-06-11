package com.willian.msavaliadorcredito.application.dto;

import java.io.Serializable;
import java.util.List;

public class RetornoAvaliacaoCliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<CartaoAprovado> cartoesAprovados;

    public RetornoAvaliacaoCliente() {
    }

    public RetornoAvaliacaoCliente(List<CartaoAprovado> cartoesAprovados) {
        this.cartoesAprovados = cartoesAprovados;
    }

    public List<CartaoAprovado> getCartoesAprovados() {
        return cartoesAprovados;
    }

    public void setCartoesAprovados(List<CartaoAprovado> cartoesAprovados) {
        this.cartoesAprovados = cartoesAprovados;
    }
}
