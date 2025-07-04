package com.movie_bd.repository;

import com.movie_bd.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findUsuarioByNome_Usuario(String Nome_Usuario);

    @Query("SELECT u.Nome_Usuario, u.Email " +
            "FROM Usuario u " +
            "WHERE u.Status_Solicitacao_Critico = TRUE ")
    List<Usuario> findUsuarioByStatusSolicitacaoCritico(boolean Status_Solicitacao_Critico);

    @Query("SELECT DISTINCT u.Nome_Usuario " +
            "FROM Usuario u " +
            "JOIN u.avaliacoes a")
    List<String> findNomesDeUsuariosComAvaliacoes();
}
