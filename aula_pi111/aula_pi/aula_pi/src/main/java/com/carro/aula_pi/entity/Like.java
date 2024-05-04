package com.carro.aula_pi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Entity(name = "like")
@Table(name = "gostei")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Like {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_video")
    private Video idVideo;

    public Like(Usuario idUsuario, Video idVideo) {
        this.idUsuario = idUsuario;
        this.idVideo = idVideo;
    }
}
