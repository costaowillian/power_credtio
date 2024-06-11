package com.willian.mscartoes.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class DadosSolicitacaoEmissaoCartao implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long idCartao;
        private String cpf;
        private String endereco;
        private BigDecimal limiteLiberado;

        public DadosSolicitacaoEmissaoCartao() {
        }

        public DadosSolicitacaoEmissaoCartao(Long idCartao, String cpf, String endereco, BigDecimal limiteLiberado) {
            this.idCartao = idCartao;
            this.cpf = cpf;
            this.endereco = endereco;
            this.limiteLiberado = limiteLiberado;
        }

        public Long getIdCartao() {
            return idCartao;
        }

        public void setIdCartao(Long idCartao) {
            this.idCartao = idCartao;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public String getEndereco() {
            return endereco;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public BigDecimal getLimiteLiberado() {
            return limiteLiberado;
        }

        public void setLimiteLiberado(BigDecimal limiteLiberado) {
            this.limiteLiberado = limiteLiberado;
        }

}
