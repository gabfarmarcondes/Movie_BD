package com.movie_bd.repository;

import com.movie_bd.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @Query("SELECT a " +
            "FROM Avaliacao a " +
            "WHERE a.nota >= :nota")
    List<Avaliacao> findNotasMaioresQue(@Param("nota") int nota);

    List<Avaliacao> findByUsuarioIdUsuario(Long idUsuario);

    @Query("SELECT a " +
            "FROM Avaliacao a " +
            "WHERE a.nota >= :nota O" +
            "RDER BY a.dataAvaliacao DESC")
    List<Avaliacao> findNotasOrdenadas(@Param("nota") int nota);

    @Query("SELECT COUNT(a) FROM Avaliacao a WHERE a.tipoConteudoAvaliacao = :tipoConteudoAvaliacao")
    long countPorTipoDeConteudo(@Param("tipoConteudoAvaliacao") String tipoConteudoAvaliacao);
}
