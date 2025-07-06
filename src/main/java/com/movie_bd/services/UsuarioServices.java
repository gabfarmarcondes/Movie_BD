package com.movie_bd.services;

import com.movie_bd.model.Usuario;
import com.movie_bd.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServices {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServices(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Usuario> getUsuarioByNomeUsuario(String nomeUsuario) {
        return usuarioRepository.findUsuarioByNome_Usuario(nomeUsuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<Usuario>> getUsuarioByStatusSolicitacaoCritico(boolean status) {
        List<Usuario> usuarios = usuarioRepository.findUsuarioByStatusSolicitacaoCritico(status);
        return ResponseEntity.ok(usuarios);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<String>> getNomesDeUsuariosComAvaliacoes() {
        List<String> nomesDeUsuarios = usuarioRepository.findNomesDeUsuariosComAvaliacoes();
        return ResponseEntity.ok(nomesDeUsuarios);
    }

    @Transactional
    public ResponseEntity<Usuario> createUsuario(Usuario usuario) {
        Usuario novoUsuario = usuarioRepository.save(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<Usuario> updateUsuario(Long idUsuario, Usuario dadosUsuario) {
        Usuario usuarioExistente = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + idUsuario));

        usuarioExistente.setNome_Usuario(dadosUsuario.getNome_Usuario());
        usuarioExistente.setEmail(dadosUsuario.getEmail());
        usuarioExistente.setSenha(dadosUsuario.getSenha());
        usuarioExistente.setStatus_Solicitacao_Critico(dadosUsuario.isStatus_Solicitacao_Critico());
        usuarioExistente.setData_Cadastro(dadosUsuario.getData_Cadastro());

        Usuario usuarioAtualizado = usuarioRepository.save(usuarioExistente);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @Transactional
    public ResponseEntity<Void> deleteUsuario(Long idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new EntityNotFoundException("Usuário não encontrado com ID: " + idUsuario);
        }
        usuarioRepository.deleteById(idUsuario);
        return ResponseEntity.noContent().build();
    }
}