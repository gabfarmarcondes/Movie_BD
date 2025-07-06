package com.movie_bd.repository;

import com.movie_bd.model.Pessoa_Atua_Serie;
import com.movie_bd.model.Serie;
import com.movie_bd.model.keys.PKs_Pessoa_Serie;
import com.movie_bd.repository.auxiliar.ElencoProjecao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Pessoa_Atua_SerieRepository extends JpaRepository<Pessoa_Atua_Serie, PKs_Pessoa_Serie> {

    @Query("SELECT pas.serie FROM Pessoa_Atua_Serie pas WHERE pas.pessoa.idPessoa = :idPessoa")
    List<Serie> findByPessoaIdPessoaAndSerieIdSerie(@Param("ID_Pessoa") Long idPessoa);

    @Query("SELECT CONCAT(p.pnome, ' ', p.unome) AS nomeCompleto, pas.funcao AS funcao " +
            "FROM Pessoa_Atua_Serie pas JOIN pas.pessoa p " +
            "WHERE pas.serie.idSerie = :idSerie")
    List<ElencoProjecao> findElencoDaSerie(@Param("idSerie") Long idSerie);
}
