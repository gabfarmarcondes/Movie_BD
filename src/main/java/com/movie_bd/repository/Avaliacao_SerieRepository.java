package com.movie_bd.repository;

import com.movie_bd.model.Avaliacao;
import com.movie_bd.model.Avaliacao_Serie;
import com.movie_bd.model.keys.PKs_Avaliacao_Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Avaliacao_SerieRepository extends JpaRepository<Avaliacao_Serie, PKs_Avaliacao_Serie> {

    @Query("SELECT asr.avaliacao FROM Avaliacao_Serie asr WHERE asr.serie.idSerie = :idSerie")
    List<Avaliacao> findAvaliacoesBySerieId(@Param("idSerie") Long idSerie);

    @Query("SELECT asr.avaliacao FROM Avaliacao_Serie asr " +
            "WHERE asr.serie.idSerie = :idSerie ORDER BY asr.avaliacao.dataAvaliacao DESC")
    List<Avaliacao> findAvaliacoesRecentesPorSerie(@Param("idSerie") Long idSerie);

    @Query("SELECT asr.avaliacao FROM Avaliacao_Serie asr " +
            "WHERE asr.serie.idSerie = :idSerie AND asr.avaliacao.nota > :notaMinima")
    List<Avaliacao> findAvaliacoesComNotaMaiorQue(
            @Param("idSerie") Long idSerie,
            @Param("notaMinima") int nota
    );
}
