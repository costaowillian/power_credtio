package com.willian.mscartoes.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class ClienteCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    @ManyToOne
    @JoinColumn(name = "id_cartao")
    private Cartao cartao;
    private BigDecimal limite;

    public ClienteCartao() {
    }

    public ClienteCartao(Long id, String cpf, Cartao cartao, BigDecimal limite) {
        this.id = id;
        this.cpf = cpf;
        this.cartao = cartao;
        this.limite = limite;
    }

    public ClienteCartao(String cpf, Cartao cartao, BigDecimal limite) {
        this.cpf = cpf;
        this.cartao = cartao;
        this.limite = limite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteCartao that = (ClienteCartao) o;
        return Objects.equals(id, that.id) && Objects.equals(cpf, that.cpf) && Objects.equals(cartao, that.cartao) && Objects.equals(limite, that.limite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, cartao, limite);
    }
}
