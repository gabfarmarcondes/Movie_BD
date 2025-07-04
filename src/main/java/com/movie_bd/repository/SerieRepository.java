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

    Optional<Serie> findSerieByTitulo(String Titulo);

    @Query("SELECT s.Titulo, s.Genero, s.QTDE_Temporadas " +
            "FROM Serie s " +
            "WHERE s.QTDE_Temporadas >= :temporadas")
    List<Serie> findSeriesComMaisTemporadasQue(@Param("temporadas") int QTDE_Temporadas);

    @Query("SELECT s.Titulo, s.Genero, (s.Ano_Fim - s.Ano_Inicio) as duracaoEmAnos " +
            "FROM Serie s " +
            "WHERE s.Ano_Inicio >= :anoDe " +
            "   AND s.Ano_Fim <= :anoAte " +
            "   AND s.Ano_Fim IS NOT NULL")
    List<SerieDuracao> findTempoParaFazerSerie(
            @Param("anoDe") int Ano_Inicio,
            @Param("anoAte") int Ano_Fim
    );
}
