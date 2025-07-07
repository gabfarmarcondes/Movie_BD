package com.movie_bd.repository;

import com.movie_bd.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    List<Filme> findFilmesByGenero(String genero);

    @Query("SELECT f " +
            "FROM Filme f " +
            "WHERE YEAR(f.dataLancamento) BETWEEN 2000-01-01 AND 2010-12-31")
    List<Filme> findFilmesEntre2000e2010();


    @Query("SELECT f " +
            "FROM Filme f " +
            "WHERE f.duracao >= :duracao")
    List<Filme> findFilmesComDuracaoMaiorOuIgualA(
            @Param("duracao") LocalTime duracao);
}
