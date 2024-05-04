package com.carro.aula_pi.entity;

import com.carro.aula_pi.dto.usuario.UsuarioDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String dataDeNascimento;
    private String dataDeRegistro;
    private String email;
    private String senha;
    private boolean ativo;

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Usuario(UsuarioDTO usuarioDTO) {




        this.nome = usuarioDTO.nome();
        this.senha = usuarioDTO.senha();
        this.email = usuarioDTO.email();
        this.dataDeRegistro = LocalDate.now().format(formatter);
        this.dataDeNascimento = String.format(usuarioDTO.dataNascimento(), formatter);

    }

    //ta feito com boolean pq futuramente alguns atributos nao poderao ser repetidos, entao o usuario nao sera atualizado
    public boolean atualizar(UsuarioDTO usuarioDTO) {
        this.senha = usuarioDTO.senha();
        this.email = usuarioDTO.email();
        this.nome = usuarioDTO.nome();
      //  this.dataDeNascimento = LocalDate.parse(usuarioDTO.dataNascimento(), formatter);

        return true;
    }

    public void desativar() {
        this.ativo = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
