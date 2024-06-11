package com.willian.msavaliadorcredito.domain.dto;

import java.io.Serializable;

public class ProtocoloSolicitacaoCartao implements Serializable {
    private static final long serialVersionUID = 1L;

    private String protocolo;

    public ProtocoloSolicitacaoCartao() {
    }

    public ProtocoloSolicitacaoCartao(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }
}
