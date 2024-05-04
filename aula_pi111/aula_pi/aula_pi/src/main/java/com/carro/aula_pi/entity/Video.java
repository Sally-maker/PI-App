package com.carro.aula_pi.entity;

import com.carro.aula_pi.dto.video.VideoDTO;
import com.carro.aula_pi.dto.video.VideoEditDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity(name = "video")
@Table(name = "video")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;
    private String titulo;
    private String dataDePostagem;
    private boolean restrito;
    private String url;
    private String duracao;
    private String categoria;
    private int visualizacoes;
    private boolean ativo = true;

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public Video(VideoDTO videoDTO, Usuario usuario) {

        this.titulo = videoDTO.titulo();
        this.url = videoDTO.url();
        this.duracao = videoDTO.duracao();
        this.categoria = videoDTO.categoria();
        this.dataDePostagem = LocalDate.now().format(formatter);
        this.idUsuario = usuario;
        this.restrito = videoDTO.restrito();

    }

    public void atualizarVideo(VideoEditDTO videoEditDTO) {
        this.titulo = videoEditDTO.titulo();
        this.categoria = videoEditDTO.categoria();
        this.restrito = videoEditDTO.restrito();
        this.duracao = videoEditDTO.duracao();


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

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDuracao() {
        return duracao;
    }

    public String getDataDePostagem() {
        return dataDePostagem;
    }

    public void setDataDePostagem(String dataDePostagem) {
        this.dataDePostagem = dataDePostagem;
    }

    public boolean isRestrito() {
        return restrito;
    }

    public void setRestrito(boolean restrito) {
        this.restrito = restrito;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getVisualizacoes() {
        return visualizacoes;
    }

    public void setVisualizacoes(int visualizacoes) {
        this.visualizacoes = visualizacoes;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
