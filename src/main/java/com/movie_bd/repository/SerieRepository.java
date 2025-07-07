package com.movie_bd.repository;

import com.movie_bd.model.Serie;
import com.movie_bd.repository.auxiliar.SerieDuracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {

    Optional<Serie> findSerieByTitulo(String titulo);

    @Query("SELECT s " +
            "FROM Serie s " +
            "WHERE s.qtdeTemporadas >= :temporadas")
    List<Serie> findSeriesComMaisTemporadasQue(@Param("temporadas") int qtdeTemporadas);

    @Query("SELECT s.titulo, s.genero, (s.anoFim - s.anoInicio) as duracaoEmAnos " +
            "FROM Serie s " +
            "WHERE s.anoInicio >= :anoDe " +
            "   AND s.anoFim <= :anoAte " +
            "   AND s.anoFim IS NOT NULL")
    List<SerieDuracao> findTempoParaFazerSerie(
            @Param("anoDe") int anoInicio,
            @Param("anoAte") int anoFim
    );
}
