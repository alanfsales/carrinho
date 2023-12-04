package com.carrinho.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntidadeNaoExisteException extends RuntimeException{

    public EntidadeNaoExisteException(String mensagem){
        super(mensagem);
    }
}
