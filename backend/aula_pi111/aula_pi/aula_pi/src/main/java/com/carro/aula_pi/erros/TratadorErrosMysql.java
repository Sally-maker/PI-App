package com.carro.aula_pi.erros;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class TratadorErrosMysql {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
        public ResponseEntity tratarErro500(SQLIntegrityConstraintViolationException e) {
        return ResponseEntity.badRequest().body(new ErroValorInvalidoDTO("Valores invalidos", e.getMessage()));

        }

        private record ErroValorInvalidoDTO (String mensagem, String causa){}



    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<String> tratarErro404(UsuarioNaoEncontradoException e) {
        return ResponseEntity.badRequest().body(e.getMessage());

    }

    @ExceptionHandler(VideoNaoEncontradoException.class)
    public ResponseEntity<String> tratarErro404(VideoNaoEncontradoException e) {
        return ResponseEntity.badRequest().body(e.getMessage());

    }
}
