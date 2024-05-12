package com.carro.aula_pi.erros;

public class VideoNaoEncontradoException extends RuntimeException{

    public VideoNaoEncontradoException(String message) {
        super(message);
    }
}
