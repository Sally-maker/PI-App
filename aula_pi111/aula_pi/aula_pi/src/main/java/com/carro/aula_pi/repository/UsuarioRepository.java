package com.carro.aula_pi.repository;

import com.carro.aula_pi.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    //Usuario getReferenceById(String id);


    Optional<Usuario> findById(Long id);
    Optional<Usuario> getReferenceByEmailAndSenha (String email, String senha);






}
