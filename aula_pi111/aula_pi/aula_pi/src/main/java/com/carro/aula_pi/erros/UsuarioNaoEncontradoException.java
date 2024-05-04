package com.carro.aula_pi.erros;

public class UsuarioNaoEncontradoException extends RuntimeException{

    public UsuarioNaoEncontradoException(String message) {
        super(message);
    }
}
