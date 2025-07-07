package com.movie_bd.services;

import com.movie_bd.model.*;
import com.movie_bd.model.keys.PKs_Avaliacao_Filme;
import com.movie_bd.model.keys.PKs_Avaliacao_Serie;
import com.movie_bd.model.keys.PKs_Pessoa_Serie;
import com.movie_bd.repository.Avaliacao_SerieRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class Avaliacao_SerieServices {

    private final Avaliacao_SerieRepository asRepository;
    private final EntityManager entityManager;

    @Autowired
    public Avaliacao_SerieServices(Avaliacao_SerieRepository asRepository, EntityManager entityManager) {
        this.asRepository = asRepository;
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public List<Avaliacao> findAvaliacoesBySerieId(Long idSerie) {
        return asRepository.findAvaliacoesBySerieId(idSerie);
    }

    @Transactional(readOnly = true)
    public List<Avaliacao> findAvaliacoesRecentesPorSerie(Long idSerie) {
        return asRepository.findAvaliacoesRecentesPorSerie(idSerie);
    }

    @Transactional(readOnly = true)
    public List<Avaliacao> findAvaliacoesComNotaMaiorQue(Long idSerie, int nota) {
        return asRepository.findAvaliacoesComNotaMaiorQue(idSerie, nota);
    }

    @Transactional
    public ResponseEntity<Avaliacao_Serie> criarAvaliacao(Avaliacao_Serie avaliacaoSerie) {
        if (avaliacaoSerie.getAvaliacao() != null && avaliacaoSerie.getAvaliacao().getIdAvaliacao() != null) {
            Avaliacao avaliacaoRef = entityManager.getReference(Avaliacao.class, avaliacaoSerie.getAvaliacao().getIdAvaliacao());
            avaliacaoSerie.setAvaliacao(avaliacaoRef);
        }
        if (avaliacaoSerie.getSerie() != null && avaliacaoSerie.getSerie().getIdSerie() != null) {
            Serie serieRef = entityManager.getReference(Serie.class, avaliacaoSerie.getSerie().getIdSerie());
            avaliacaoSerie.setSerie(serieRef);
        }
        Avaliacao_Serie novaAvaliacaoSerie = asRepository.save(avaliacaoSerie);
        return new ResponseEntity<>(novaAvaliacaoSerie, HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<Avaliacao_Serie> atualizarAvaliacao(Long idSerie, Long idAvaliacao, Avaliacao_Serie dadosAtualizados) {
        PKs_Avaliacao_Serie id = new PKs_Avaliacao_Serie(idAvaliacao, idSerie);
        Avaliacao_Serie asExistente = asRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação de série não encontrada"));

        if (dadosAtualizados.getAvaliacao() != null && dadosAtualizados.getAvaliacao().getIdAvaliacao() != null) {
            Avaliacao avaliacaoRef = entityManager.getReference(Avaliacao.class, dadosAtualizados.getAvaliacao().getIdAvaliacao());
            asExistente.setAvaliacao(avaliacaoRef);
        }

        Avaliacao_Serie afAtualizado = asRepository.save(asExistente);
        return ResponseEntity.ok(afAtualizado);
    }

    @Transactional
    public void deletarAvaliacaoPorId(PKs_Avaliacao_Serie id) {
        if (!asRepository.existsById(id)) {
            throw new EntityNotFoundException("Não é possível deletar. Avaliação não encontrada com o ID: " + id);
        }
        asRepository.deleteById(id);
    }
}