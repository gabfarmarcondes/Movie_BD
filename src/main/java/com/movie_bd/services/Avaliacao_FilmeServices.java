package com.movie_bd.services;

import com.movie_bd.model.Avaliacao;
import com.movie_bd.model.Avaliacao_Filme;
import com.movie_bd.model.Filme;
import com.movie_bd.model.keys.PKs_Avaliacao_Filme;
import com.movie_bd.repository.Avaliacao_FilmeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Avaliacao_FilmeServices {

    private final Avaliacao_FilmeRepository afRepository;
    private final EntityManager entityManager;

    @Autowired
    public Avaliacao_FilmeServices(Avaliacao_FilmeRepository afRepository, EntityManager entityManager) {
        this.afRepository = afRepository;
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<Avaliacao>> getAvaliacoesByFilmeId(Long idFilme){
        List<Avaliacao> avaliacoes = afRepository.findAvaliacoesByFilmeId(idFilme);
        return ResponseEntity.ok(avaliacoes);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Double> getMediaDeNotasPorFilme(Long idFilme){
        Double media = afRepository.findMediaDeNotasPorFilme(idFilme);
        return ResponseEntity.ok(media != null ? media : 0);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Long> getCountByFilme_ID_Filme(Long idFilme){
        long contagem = afRepository.countByFilmeIdFilme(idFilme);
        return ResponseEntity.ok(contagem);
    }

    @Transactional
    public ResponseEntity<Avaliacao_Filme> createAvaliacaoFilme(Avaliacao_Filme avaliacaoFilme) {
        if (avaliacaoFilme.getAvaliacao() != null && avaliacaoFilme.getAvaliacao().getIdAvaliacao() != null) {
            Avaliacao avaliacaoRef = entityManager.getReference(Avaliacao.class, avaliacaoFilme.getAvaliacao().getIdAvaliacao());
            avaliacaoFilme.setAvaliacao(avaliacaoRef);
        }
        if (avaliacaoFilme.getFilme() != null && avaliacaoFilme.getFilme().getIdFilme() != null) {
            Filme filmeRef = entityManager.getReference(Filme.class, avaliacaoFilme.getFilme().getIdFilme());
            avaliacaoFilme.setFilme(filmeRef);
        }
        Avaliacao_Filme novaAvaliacaoFilme = afRepository.save(avaliacaoFilme);
        return new ResponseEntity<>(novaAvaliacaoFilme, HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<Avaliacao_Filme> update(Long idFilme, Long idAvaliacao, Avaliacao_Filme dadosAtualizados) {
        PKs_Avaliacao_Filme id = new PKs_Avaliacao_Filme(idAvaliacao, idFilme);
        Avaliacao_Filme afExistente = afRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação de filme não encontrada"));

        if (dadosAtualizados.getAvaliacao() != null && dadosAtualizados.getAvaliacao().getIdAvaliacao() != null) {
            Avaliacao avaliacaoRef = entityManager.getReference(Avaliacao.class, dadosAtualizados.getAvaliacao().getIdAvaliacao());
            afExistente.setAvaliacao(avaliacaoRef);
        }

        Avaliacao_Filme afAtualizado = afRepository.save(afExistente);
        return ResponseEntity.ok(afAtualizado);
    }

    @Transactional
    public ResponseEntity<Void> delete(Long idFilme, Long idAvaliacao) {
        PKs_Avaliacao_Filme id = new PKs_Avaliacao_Filme(idAvaliacao, idFilme);
        if (!afRepository.existsById(id)) {
            throw new EntityNotFoundException("Avaliação de filme não encontrada");
        }
        afRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
