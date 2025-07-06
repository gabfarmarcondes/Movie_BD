package com.movie_bd.repository;

import com.movie_bd.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @Query("SELECT a.tipoConteudoAvaliacao, a.dataAvaliacao, a.comentario " +
            "FROM Avaliacao a " +
            "WHERE a.nota >= :notaMaior")
    List<Avaliacao> findNotasMaioresQue(@Param("notaMaior") int nota);

    List<Avaliacao> findByUsuarioIdUsuario(Long idUsuario);

    @Query("SELECT a.usuario, a.nota " +
            "FROM Avaliacao a " +
            "WHERE a.nota >= :notaMinima O" +
            "RDER BY a.dataAvaliacao DESC")
    List<Avaliacao> findNotasOrdenadas(@Param("notaMinima") int nota);

    @Query("SELECT COUNT(a) FROM Avaliacao a WHERE a.tipoConteudoAvaliacao = :tipoConteudo")
    long countPorTipoDeConteudo(@Param("tipoConteudo") String tipoConteudoAvaliacao);
}
