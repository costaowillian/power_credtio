package com.willian.msclientes.application.dto;


import com.willian.msclientes.domain.Cliente;

import java.io.Serializable;

public class SalvarClienteDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cpf;
    private String nome;
    private Integer idade;

    public SalvarClienteDto() {
    }

    public SalvarClienteDto(String cpf, String nome, Integer idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }

    public Cliente toModel() {
        return new Cliente(this.cpf, this.nome, this.idade);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}
