package com.movie_bd.repository;

import com.movie_bd.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    List<Filme> findFilmesByGenero(String Genero);

    @Query("SELECT f.Titulo, f.Genero, f.Genero " +
            "FROM Filme f " +
            "WHERE f.Data_Lancamento BETWEEN 2000-01-01 AND 2010-12-31")
    List<Filme> findFilmesEntre2000e2010(Date Data_Lancamento);


    @Query("SELECT f.Titulo, f.Duracao, f.Genero " +
            "FROM Filme f " +
            "WHERE f.Duracao >= :duracaoMinima")
    List<Filme> findFilmesComDuracaoMaiorOuIgualA(
            @Param("duracaoMinima") LocalTime Duracao);
}
