package com.carro.aula_pi.dto;

import com.carro.aula_pi.entity.Usuario;

public record UsuarioDTO(String nome, String senha, String email) {

    // Construtor personalizado para criar um objeto UsuarioDTO a partir de um objeto Usuario
    public UsuarioDTO(Usuario usuario) {
        this(usuario.getNome(), usuario.getSenha(), usuario.getEmail());
    }
}
