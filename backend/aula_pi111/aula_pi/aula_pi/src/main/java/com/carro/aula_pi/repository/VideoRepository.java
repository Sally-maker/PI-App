package com.carro.aula_pi.repository;

import com.carro.aula_pi.entity.Usuario;
import com.carro.aula_pi.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {

    Page<Video> findAllByIdUsuarioAndAtivoTrue (Usuario usuario, Pageable pagina);
    Page<Video> findAllByAtivoTrue(Pageable pagina);
    Optional<Video> findByIdAndAtivoTrue(Long id);
    Page<Video> findByTituloContainingAndAtivoTrue(String titulo, Pageable pagina);
}
