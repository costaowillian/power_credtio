package com.willian.mscartoes.application.dto;

import com.willian.mscartoes.domain.Cartao;
import com.willian.mscartoes.domain.enums.BandeiraCartao;

import java.math.BigDecimal;

public class SalvarCartaoDTO {

    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;

    public SalvarCartaoDTO() {
    }

    public SalvarCartaoDTO(String nome, BandeiraCartao bandeira, BigDecimal renda, BigDecimal limiteBasico) {
        this.nome = nome;
        this.bandeira = bandeira;
        this.renda = renda;
        this.limiteBasico = limiteBasico;
    }

    public Cartao toModel() {
        return new Cartao(this.nome, this.bandeira, this.renda, this.limiteBasico);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BandeiraCartao getBandeira() {
        return bandeira;
    }

    public void setBandeira(BandeiraCartao bandeira) {
        this.bandeira = bandeira;
    }

    public BigDecimal getRenda() {
        return renda;
    }

    public void setRenda(BigDecimal renda) {
        this.renda = renda;
    }

    public BigDecimal getLimiteBasico() {
        return limiteBasico;
    }

    public void setLimiteBasico(BigDecimal limiteBasico) {
        this.limiteBasico = limiteBasico;
    }
}
