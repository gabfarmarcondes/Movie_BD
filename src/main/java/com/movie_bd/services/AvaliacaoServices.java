package com.movie_bd.services;

import com.movie_bd.model.*;
import com.movie_bd.repository.AvaliacaoRepository;
import com.movie_bd.repository.Avaliacao_FilmeRepository;
import com.movie_bd.repository.Avaliacao_SerieRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AvaliacaoServices {

    private final AvaliacaoRepository avaliacaoRepository;
    private final Avaliacao_FilmeRepository avaliacaoFilmeRepository;
    private final Avaliacao_SerieRepository avaliacaoSerieRepository;
    private final EntityManager entityManager;

    @Autowired
    public AvaliacaoServices(AvaliacaoRepository avaliacaoRepository, Avaliacao_FilmeRepository avaliacaoFilmeRepository, Avaliacao_SerieRepository avaliacaoSerieRepository, EntityManager entityManager) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.avaliacaoFilmeRepository = avaliacaoFilmeRepository;
        this.avaliacaoSerieRepository = avaliacaoSerieRepository;
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<Avaliacao>> getNotasMaioresQue(int nota) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findNotasMaioresQue(nota);
        return ResponseEntity.ok(avaliacoes);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<Avaliacao>> getByUsuarioId(Long idUsuario) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByUsuarioIdUsuario(idUsuario);
        return ResponseEntity.ok(avaliacoes);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<Avaliacao>> getNotasOrdenadas(int nota) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findNotasOrdenadas(nota);
        return ResponseEntity.ok(avaliacoes);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Long> countPorTipoDeConteudo(String tipoConteudo) {
        long contagem = avaliacaoRepository.countPorTipoDeConteudo(tipoConteudo);
        return ResponseEntity.ok(contagem);
    }

    @Transactional
    public ResponseEntity<Avaliacao> createAvaliacao(Avaliacao avaliacao) {
        Usuario usuarioRef = entityManager.getReference(Usuario.class, avaliacao.getUsuario().getIdUsuario());
        avaliacao.setUsuario(usuarioRef);

        Avaliacao novaAvaliacao = avaliacaoRepository.save(avaliacao);

        if ("Serie".equalsIgnoreCase(avaliacao.getTipoConteudoAvaliacao())) {
            Serie serieRef = entityManager.getReference(Serie.class, (long) avaliacao.getIdConteudoAvaliacao());
            Avaliacao_Serie avaliacaoSerie = new Avaliacao_Serie();
            avaliacaoSerie.setSerie(serieRef);
            avaliacaoSerie.setAvaliacao(novaAvaliacao);
            avaliacaoSerieRepository.save(avaliacaoSerie);

        } else if ("Filme".equalsIgnoreCase(avaliacao.getTipoConteudoAvaliacao())) {
            Filme filmeRef = entityManager.getReference(Filme.class, (long) avaliacao.getIdConteudoAvaliacao());
            Avaliacao_Filme avaliacaoFilme = new Avaliacao_Filme();
            avaliacaoFilme.setFilme(filmeRef);
            avaliacaoFilme.setAvaliacao(novaAvaliacao);
            avaliacaoFilmeRepository.save(avaliacaoFilme);
        } else {
            throw new IllegalArgumentException("Tipo de conteúdo de avaliação inválido: " + avaliacao.getTipoConteudoAvaliacao());
        }

        return new ResponseEntity<>(novaAvaliacao, HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<Avaliacao> updateAvaliacao(Long idAvaliacao, Avaliacao dadosAvaliacao) {
        Avaliacao avaliacaoExistente = avaliacaoRepository.findById(idAvaliacao)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada com ID: " + idAvaliacao));

        avaliacaoExistente.setNota(dadosAvaliacao.getNota());
        avaliacaoExistente.setComentario(dadosAvaliacao.getComentario());

        Avaliacao avaliacaoAtualizada = avaliacaoRepository.save(avaliacaoExistente);
        return ResponseEntity.ok(avaliacaoAtualizada);
    }

    @Transactional
    public ResponseEntity<Void> deleteAvaliacao(Long idAvaliacao) {
        if (!avaliacaoRepository.existsById(idAvaliacao)) {
            throw new EntityNotFoundException("Avaliação não encontrada com ID: " + idAvaliacao);
        }
        avaliacaoRepository.deleteById(idAvaliacao);
        return ResponseEntity.noContent().build();
    }
}