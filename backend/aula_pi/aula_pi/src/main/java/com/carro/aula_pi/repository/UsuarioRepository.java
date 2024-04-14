package com.carro.aula_pi.repository;

import com.carro.aula_pi.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @Override
    Usuario getReferenceById(String id);

    Optional<Usuario> getReferenceByEmailAndSenha (String email, String senha);


}
