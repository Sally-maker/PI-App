package com.carro.aula_pi.controllers;

import com.carro.aula_pi.dto.UsuarioDTO;
import com.carro.aula_pi.entity.Usuario;
import com.carro.aula_pi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carro")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/users")
    public List<Usuario> getProducts() {
        return usuarioRepository.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UsuarioDTO usuarioDTO) {

        Usuario usuario = new Usuario(usuarioDTO);

        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuarioDTO);
    }

    @GetMapping("user/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        return ResponseEntity.ok(usuarioRepository.getReferenceById(id).toString());
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity atualizar(@RequestBody UsuarioDTO dadosAtualizados, @PathVariable String id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.atualizar(dadosAtualizados);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok(usuario.toString());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletar(@PathVariable String id) {
        usuarioRepository.delete(usuarioRepository.getReferenceById(id));
        return ResponseEntity.ok("deletado");
    }

    @GetMapping("/login")
    public ResponseEntity logar (@RequestBody UsuarioDTO usuarioDTO) {

        Optional<Usuario> usuario = usuarioRepository.getReferenceByEmailAndSenha(usuarioDTO.email(), usuarioDTO.senha());

        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get().toString());
        }

        return ResponseEntity.notFound().build();

    }



}
