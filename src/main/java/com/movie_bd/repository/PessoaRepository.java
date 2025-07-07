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

    List<Pessoa> findPessoaByNacionalidade(String nacionalidade);

    List<Pessoa> findPessoaByDataNascimento(Date dataNascimento);

    @Query("SELECT p FROM Pessoa p " +
            "JOIN p.pessoaAtuaFilmes paf " +
            "JOIN paf.filme f " +
            "WHERE f.titulo = :titulo")
    List<Pessoa> findPessoasByTituloDeFilme(
            @Param("titulo")  String titulo);

    @Query("SELECT p FROM Pessoa p " +
            "JOIN p.pessoaAtuaSeries pas " +
            "JOIN pas.serie s " +
            "WHERE s.titulo = :tituloSerie")
    List<Pessoa> findPessoasByTituloDeSerie(
            @Param("tituloSerie")  String titulo);
}
