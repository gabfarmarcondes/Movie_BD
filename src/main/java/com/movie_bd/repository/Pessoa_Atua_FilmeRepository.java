package com.movie_bd.repository;

import com.movie_bd.model.Filme;
import com.movie_bd.model.Pessoa;
import com.movie_bd.model.Pessoa_Atua_Filme;
import com.movie_bd.model.keys.PKs_Pessoa_Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Pessoa_Atua_FilmeRepository extends JpaRepository<Pessoa_Atua_Filme, PKs_Pessoa_Filme> {

    @Query("SELECT paf.filme FROM Pessoa_Atua_Filme paf WHERE paf.pessoa.idPessoa = :idPessoa")
    List<Filme> findFilmeByPessoaId(@Param("idPessoa") Long idPessoa);

    @Query("SELECT paf.pessoa FROM Pessoa_Atua_Filme paf WHERE paf.funcao = :funcao")
    List<Pessoa> findPessoaByFuncao(@Param("funcao") String funcao);

    @Query("SELECT COUNT(paf) FROM Pessoa_Atua_Filme paf WHERE paf.pessoa.idPessoa = :idPessoa")
    Long countFilmesPorPessoa(@Param("idPessoa") Long idPessoa);
}
