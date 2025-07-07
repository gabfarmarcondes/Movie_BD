package com.movie_bd.controller;

import com.movie_bd.model.Usuario;
import com.movie_bd.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioServices usuarioServices;

    @Autowired
    public UsuarioController(UsuarioServices usuarioServices) {
        this.usuarioServices = usuarioServices;
    }

    @GetMapping("/byNome/{nomeUsuario}")
    public ResponseEntity<Usuario> getUsuarioByNomeUsuario(@PathVariable String nomeUsuario) {
        return usuarioServices.getUsuarioByNomeUsuario(nomeUsuario);
    }

    @GetMapping("/statusCritico/{status}")
    public ResponseEntity<List<Usuario>> getUsuarioStatusCritico(@PathVariable Boolean status) {
        return usuarioServices.getUsuarioByStatusSolicitacaoCritico(status);
    }

    @GetMapping("/avaliacao")
    public ResponseEntity<List<String>> getNomesDeUsuariosComAvaliacoes(){
        return usuarioServices.getNomesDeUsuariosComAvaliacoes();
    }

    @PostMapping("/criar")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        return usuarioServices.createUsuario(usuario);
    }

    @PutMapping("/atualizar/{idUsuario}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long idUsuario, @RequestBody Usuario usuario) {
        return usuarioServices.updateUsuario(idUsuario, usuario);
    }

    @DeleteMapping("/deletar/{idUsuario}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long idUsuario) {
        return usuarioServices.deleteUsuario(idUsuario);
    }
}
