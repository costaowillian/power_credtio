package com.willian.msavaliadorcredito.domain.dto.exceptions;

public class ErrorSolicitacaoCartaoException extends RuntimeException{
    public ErrorSolicitacaoCartaoException(String msg) {
        super(msg);
    }
}
