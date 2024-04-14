package com.carro.aula_pi.entity;

import com.carro.aula_pi.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "usuario")
@Table(name = "usuario")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private String senha;
    private String email;


    public Usuario(UsuarioDTO usuarioDTO) {

        this.nome = usuarioDTO.nome();
        this.senha = usuarioDTO.senha();
        this.email = usuarioDTO.email();

    }

    //ta feito com boolean pq futuramente alguns atributos nao poderao ser repetidos, entao o usuario nao sera atualizado
    public boolean atualizar(UsuarioDTO usuarioDTO) {
        this.senha = usuarioDTO.senha();
        this.email = usuarioDTO.email();
        this.nome = usuarioDTO.nome();

        return true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
