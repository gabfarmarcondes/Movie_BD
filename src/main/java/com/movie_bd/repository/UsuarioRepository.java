package com.movie_bd.repository;

import com.movie_bd.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNomeUsuario(String nomeUsuario);

    @Query("SELECT u.nomeUsuario, u.email " +
            "FROM Usuario u " +
            "WHERE u.statusSolicitacaoCritico = TRUE ")
    List<Usuario> findUsuarioByStatusSolicitacaoCritico(boolean statusSolicitacaoCritico);

    @Query("SELECT DISTINCT u.nomeUsuario " +
            "FROM Usuario u " +
            "JOIN u.avaliacoes a")
    List<String> findNomesDeUsuariosComAvaliacoes();
}
