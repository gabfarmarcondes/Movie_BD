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

    @Query("SELECT af.avaliacao FROM Avaliacao_Filme af WHERE af.filme.ID_Filme = :idFilme")
    List<Avaliacao> findAvaliacoesByFilmeId(@Param("idFilme") Long ID_Filme);

    @Query("SELECT AVG(af.avaliacao.Nota) FROM Avaliacao_Filme af WHERE af.filme.ID_Filme = :idFilme")
    int findMediaDeNotasPorFilme(@Param("idFilme") Long ID_Filme);

    long countByFilme_ID_Filme(Long idFilme);
}
