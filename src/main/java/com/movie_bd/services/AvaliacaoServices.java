package com.movie_bd.services;

import com.movie_bd.model.Avaliacao;
import com.movie_bd.repository.AvaliacaoRepository;
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

    @Autowired
    public AvaliacaoServices(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
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
        Avaliacao novaAvaliacao = avaliacaoRepository.save(avaliacao);
        return new ResponseEntity<>(novaAvaliacao, HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<Avaliacao> updateAvaliacao(Long idAvaliacao, Avaliacao dadosAvaliacao) {
        Avaliacao avaliacaoExistente = avaliacaoRepository.findById(idAvaliacao)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada com ID: " + idAvaliacao));

        avaliacaoExistente.setNota(dadosAvaliacao.getNota());
        avaliacaoExistente.setComentario(dadosAvaliacao.getComentario());
        avaliacaoExistente.setIdConteudoAvaliacao(dadosAvaliacao.getIdConteudoAvaliacao());
        avaliacaoExistente.setDataAvaliacao(dadosAvaliacao.getDataAvaliacao());
        avaliacaoExistente.setIdConteudoAvaliacao(dadosAvaliacao.getIdConteudoAvaliacao());

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