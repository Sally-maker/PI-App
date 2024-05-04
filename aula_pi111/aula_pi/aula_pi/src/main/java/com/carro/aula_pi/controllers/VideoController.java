package com.carro.aula_pi.controllers;

import com.carro.aula_pi.dto.video.VideoDTO;
import com.carro.aula_pi.dto.video.VideoEditDTO;
import com.carro.aula_pi.dto.video.VideoRetornoDTO;
import com.carro.aula_pi.entity.Usuario;
import com.carro.aula_pi.entity.Video;
import com.carro.aula_pi.erros.UsuarioNaoEncontradoException;
import com.carro.aula_pi.erros.VideoNaoEncontradoException;
import com.carro.aula_pi.repository.UsuarioRepository;
import com.carro.aula_pi.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    VideoRepository videoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/lista/{numeroDaPagina}")
    public ResponseEntity<Page<VideoRetornoDTO>> getVideos(@PageableDefault(size = 16) Pageable paginaDeVideos, @PathVariable int numeroDaPagina) {
        paginaDeVideos.withPage(numeroDaPagina);
        var paginaDeVideosBusca = videoRepository.findAllByAtivoTrue(PageRequest.of(numeroDaPagina, paginaDeVideos.getPageSize()));
        return ResponseEntity.ok(paginaDeVideosBusca.map(VideoRetornoDTO::new));

    }

    @GetMapping("/lista/{id}/{numeroDaPagina}")
    public ResponseEntity<Page<VideoRetornoDTO>> getVideosPorId (
            @PageableDefault(size = 16) Pageable paginaDeVideos,
            @PathVariable int numeroDaPagina,
            @PathVariable Long id) throws UsuarioNaoEncontradoException {

        final var usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            final var paginaDeVideosBusca = videoRepository.findAllByIdUsuarioAndAtivoTrue(usuario.get(), PageRequest.of(numeroDaPagina, paginaDeVideos.getPageSize()));
            return ResponseEntity.ok(paginaDeVideosBusca.map(VideoRetornoDTO::new));
        } else {
            throw new UsuarioNaoEncontradoException("Não foi encontrado nenhum usuário com o id " + id);
        }

    }

    @PostMapping("/postar/{idUsuario}")
    public ResponseEntity<VideoDTO> postVideo(@PathVariable Long idUsuario, @RequestBody VideoDTO videoDTO) {

        var usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isPresent()) {
            videoRepository.save(new Video(videoDTO, usuario.get()));
            return ResponseEntity.ok(videoDTO);
        }

        throw new UsuarioNaoEncontradoException("Não foi encontrado nenhum usuário com o id " + idUsuario);


    }

    @PutMapping("/editar/{idUsuario}")
    public ResponseEntity<VideoRetornoDTO> editVideo(@PathVariable Long idUsuario, @RequestBody VideoEditDTO videoEditDTO) {

        var usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isPresent()) {

            var video = videoRepository.findById(videoEditDTO.id());

            if (video.isPresent()) {

                video.get().atualizarVideo(videoEditDTO);
                videoRepository.save(video.get());
                return ResponseEntity.ok(new VideoRetornoDTO(video.get()));

            }
            throw new VideoNaoEncontradoException("Não foi encontrado nenhum video com o id " + videoEditDTO.id());
        }

        throw new UsuarioNaoEncontradoException("Não foi encontrado nenhum usuário com o id " + idUsuario);

    }

    @DeleteMapping("/deletar/{idVideo}")
    public ResponseEntity<String> deleteVideo(@PathVariable Long idVideo) {

        var video = videoRepository.findByIdAndAtivoTrue(idVideo);
         if (video.isPresent()) {
             video.get().desativar();
             videoRepository.save(video.get());
             return ResponseEntity.ok("Video " + video.get().getTitulo() + " foi deletado");
         }


        throw new VideoNaoEncontradoException("Não foi encontrado nenhum video com o id " + idVideo);

    }

    @GetMapping("/busca/{titulo}")
    public ResponseEntity<Page<VideoRetornoDTO>> videosPorTitulo (@PathVariable String titulo, @PageableDefault(size = 16) Pageable paginaDeVideos) {
        return ResponseEntity.ok(videoRepository.findByTituloContainingAndAtivoTrue(titulo, paginaDeVideos).map(VideoRetornoDTO::new));

    }



}
