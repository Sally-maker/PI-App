package com.carro.aula_pi.controllers;

import com.carro.aula_pi.dto.usuario.LoginDTO;
import com.carro.aula_pi.dto.usuario.UsuarioDTO;
import com.carro.aula_pi.entity.Usuario;
import com.carro.aula_pi.erros.UsuarioNaoEncontradoException;
import com.carro.aula_pi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @CrossOrigin
    @PostMapping("/registrar")
    public ResponseEntity<UsuarioDTO> register(@RequestBody UsuarioDTO usuarioDTO) {

        usuarioRepository.save(new Usuario(usuarioDTO));
        return ResponseEntity.ok(usuarioDTO);
    }

    @CrossOrigin
    @GetMapping("/users")
    public ResponseEntity<List<Usuario>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
    }
    
    @CrossOrigin
    @GetMapping("/user/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
        var usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            throw new UsuarioNaoEncontradoException("Não foi encontrado nenhum usuário com o id " + id);
        }
    }
    @CrossOrigin
    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizar(@RequestBody UsuarioDTO dadosAtualizados, @PathVariable Long id) {
        var usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuario.get().atualizar(dadosAtualizados);
            usuarioRepository.save(usuario.get());
            return ResponseEntity.ok(usuario.toString());
        } else {
            throw new UsuarioNaoEncontradoException("Não foi encontrado nenhum usuário com o id " + id);
        }

    }
    @CrossOrigin
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {

        var usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            usuario.get().desativar();
            usuarioRepository.save(usuario.get());
            return ResponseEntity.ok("O usuario " + usuario.get().getNome() + " foi deletado");
        }

        throw new UsuarioNaoEncontradoException("Não foi encontrado nenhum usuário com o id " + id);
    }
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<Long> logar (@RequestBody LoginDTO loginDTO) {

        var usuario = usuarioRepository.getReferenceByEmailAndSenha(loginDTO.email(), loginDTO.senha());

        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get().getId());
        }

        throw new UsuarioNaoEncontradoException("Credenciais invalidas");

    }



}
