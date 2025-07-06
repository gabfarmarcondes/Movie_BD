package com.movie_bd.services;

import com.movie_bd.model.Avaliacao;
import com.movie_bd.model.Avaliacao_Serie;
import com.movie_bd.model.keys.PKs_Pessoa_Serie;
import com.movie_bd.repository.Avaliacao_SerieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class Avaliacao_SerieServices {

    private final Avaliacao_SerieRepository asRepository;

    @Autowired
    public Avaliacao_SerieServices(Avaliacao_SerieRepository asRepository) {
        this.asRepository = asRepository;
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
    public Avaliacao_Serie criarAvaliacao(Avaliacao_Serie avaliacaoSerie) {
        if (avaliacaoSerie.getAvaliacao() != null) {
            avaliacaoSerie.getAvaliacao().setIdAvaliacao(Date.valueOf(LocalDate.now()).getTime());
        }
        return asRepository.save(avaliacaoSerie);
    }

    @Transactional
    public Avaliacao_Serie atualizarAvaliacao(PKs_Pessoa_Serie id, Avaliacao dadosAtualizacao) {
        Avaliacao_Serie avaliacaoExistente = asRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada com o ID: " + id));

        Avaliacao avaliacaoParaAtualizar = avaliacaoExistente.getAvaliacao();
        avaliacaoParaAtualizar.setNota(dadosAtualizacao.getNota());
        avaliacaoParaAtualizar.setComentario(dadosAtualizacao.getComentario());
        avaliacaoParaAtualizar.setDataAvaliacao(Date.valueOf(LocalDate.now()));

        return asRepository.save(avaliacaoExistente);
    }

    @Transactional
    public void deletarAvaliacaoPorId(PKs_Pessoa_Serie id) {
        if (!asRepository.existsById(id)) {
            throw new EntityNotFoundException("Não é possível deletar. Avaliação não encontrada com o ID: " + id);
        }
        asRepository.deleteById(id);
    }
}