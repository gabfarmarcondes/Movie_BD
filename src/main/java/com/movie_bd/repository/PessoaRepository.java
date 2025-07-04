package com.movie_bd.repository;

import com.movie_bd.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    List<Pessoa> findPessoaByNacionalidade(String Nacionalidade);

    List<Pessoa> findPessoaByDataNascimento(Date Data_Nascimento);

    @Query("SELECT p FROM Pessoa p " +
            "JOIN p.pessoa_atua_filmes paf " +
            "JOIN paf.filme f " +
            "WHERE f.Titulo = :Titulo")
    List<Pessoa> findPessoasByTituloDeFilme(
            @Param("tituloFilme")  String Titulo);

    @Query("SELECT p FROM Pessoa p " +
            "JOIN p.pessoa_atua_series pas " +
            "JOIN pas.serie s " +
            "WHERE s.Titulo = :tituloSerie")
    List<Pessoa> findPessoasByTituloDeSerie(
            @Param("tituloSerie")  String Titulo);
}
