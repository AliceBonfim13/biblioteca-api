package com.alice.libraryapi.exceptions;

import lombok.Getter;

public class CampoInvalidoException extends RuntimeException{

    @Getter
    private String campo;

    public CampoInvalidoException(String nome, String mensagem) {
        super(mensagem);
        this.campo = campo;
    }
}
