package com.willian.mscartoes.application.dto;

import com.willian.mscartoes.domain.ClienteCartao;
import com.willian.mscartoes.domain.enums.BandeiraCartao;

import java.math.BigDecimal;

public class CartoesPorClienteResponse {
    private String nome;
    private BandeiraCartao bandeiraCartao;
    private BigDecimal limite;

    public CartoesPorClienteResponse() {
    }

    public CartoesPorClienteResponse(String nome, BandeiraCartao bandeiraCartao, BigDecimal limite) {
        this.nome = nome;
        this.bandeiraCartao = bandeiraCartao;
        this.limite = limite;
    }

    public static CartoesPorClienteResponse fromModel(ClienteCartao model) {
        return new CartoesPorClienteResponse(model.getCartao().getNome(),
                model.getCartao().getBandeira(), model.getLimite());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BandeiraCartao getBandeiraCartao() {
        return bandeiraCartao;
    }

    public void setBandeiraCartao(BandeiraCartao bandeiraCartao) {
        this.bandeiraCartao = bandeiraCartao;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }
}
