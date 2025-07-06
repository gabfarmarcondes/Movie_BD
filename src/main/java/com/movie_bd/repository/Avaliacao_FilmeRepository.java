package com.movie_bd.repository;

import com.movie_bd.model.Avaliacao;
import com.movie_bd.model.Avaliacao_Filme;
import com.movie_bd.model.keys.PKs_Avaliacao_Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Avaliacao_FilmeRepository extends JpaRepository<Avaliacao_Filme, PKs_Avaliacao_Filme> {

    @Query("SELECT af.avaliacao FROM Avaliacao_Filme af WHERE af.filme.idFilme = :idFilme")
    List<Avaliacao> findAvaliacoesByFilmeId(@Param("idFilme") Long idFilme);

    @Query("SELECT AVG(af.avaliacao.nota) FROM Avaliacao_Filme af WHERE af.filme.idFilme = :idFilme")
    int findMediaDeNotasPorFilme(@Param("idFilme") Long idFilme);

    long countByFilmeIdFilme(Long idFilme);
}
