package com.movie_bd.repository;

import com.movie_bd.model.Pessoa_Atua_Serie;
import com.movie_bd.model.Serie;
import com.movie_bd.repository.auxiliar.ElencoProjecao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Pessoa_Atua_SerieRepository extends JpaRepository<Pessoa_Atua_Serie, Long> {

    Optional<Pessoa_Atua_Serie> findByPessoa_ID_PessoaAndSerie_ID_Serie(Long ID_Pessoa, Long ID_SERIE);


    @Query("SELECT pas.serie FROM Pessoa_Atua_Serie pas WHERE pas.pessoa.ID_Pessoa = :ID_Pessoa")
    List<Serie> findSeriesByPessoaId(@Param("ID_Pessoa") Long ID_Pessoa);

    @Query("SELECT p.Pnome || ' ' || p.Unome AS nomeCompleto, pas.Funcao AS funcao " +
            "FROM Pessoa_Atua_Serie pas JOIN pas.pessoa p " +
            "WHERE pas.serie.ID_SERIE = :ID_SERIE")
    List<ElencoProjecao> findElencoDaSerie(@Param("ID_SERIE") Long ID_SERIE);
}
