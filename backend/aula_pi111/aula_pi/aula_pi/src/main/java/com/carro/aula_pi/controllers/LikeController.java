package com.carro.aula_pi.controllers;

import com.carro.aula_pi.entity.Like;
import com.carro.aula_pi.repository.LikeRepository;
import com.carro.aula_pi.repository.UsuarioRepository;
import com.carro.aula_pi.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/like")
public class LikeController {

    @Autowired
    LikeRepository likeRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    VideoRepository videoRepository;
//    @PostMapping("/like/{idVideo}/{idUsuario}")
//    public ResponseEntity darLike(@PathVariable String idVideo, @PathVariable String idUsuario) {
//
//   // likeRepository.save(new Like(usuarioRepository., idVideo));
//    }
}
