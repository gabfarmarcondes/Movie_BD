package com.movie_bd.services;

import com.movie_bd.model.Pessoa;
import com.movie_bd.model.Pessoa_Atua_Serie;
import com.movie_bd.model.Serie;
import com.movie_bd.repository.PessoaRepository;
import com.movie_bd.repository.SerieRepository;
import com.movie_bd.repository.auxiliar.SerieDuracao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SerieServices {

    private final SerieRepository serieRepository;
    private final EntityManager entityManager;

    @Autowired
    public SerieServices(SerieRepository serieRepository, EntityManager entityManager) {
        this.serieRepository = serieRepository;
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Serie> getSerieByTitulo(String titulo) {
        return serieRepository.findSerieByTitulo(titulo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<Serie>> getSeriesComMaisTemporadasQue(int qtdeTemporadas) {
        List<Serie> series = serieRepository.findSeriesComMaisTemporadasQue(qtdeTemporadas);
        return ResponseEntity.ok(series);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<SerieDuracao>> getTempoParaFazerSerie(int anoInicio, int anoFim) {
        List<SerieDuracao> series = serieRepository.findTempoParaFazerSerie(anoInicio, anoFim);
        return ResponseEntity.ok(series);
    }

    @Transactional
    public ResponseEntity<Serie> createSerie(Serie serie) {
        List<Pessoa_Atua_Serie> atuacoesGerenciadas = new ArrayList<>();
        if (serie.getPessoaAtuaSeries() != null && !serie.getPessoaAtuaSeries().isEmpty()) {
            for (Pessoa_Atua_Serie pasFromJson : serie.getPessoaAtuaSeries()) {
                Pessoa_Atua_Serie novaAtuacao = new Pessoa_Atua_Serie();
                Pessoa pessoaReferencia = entityManager.getReference(Pessoa.class, pasFromJson.getPessoa().getIdPessoa());

                novaAtuacao.setSerie(serie);
                novaAtuacao.setPessoa(pessoaReferencia);
                novaAtuacao.setFuncao(pasFromJson.getFuncao());

                atuacoesGerenciadas.add(novaAtuacao);
            }
        }
        serie.setPessoaAtuaSeries(atuacoesGerenciadas);
        Serie novaSerie = serieRepository.save(serie);
        return new ResponseEntity<>(novaSerie, HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<Serie> updateSerie(Long idSerie, Serie dadosSerie) {
        Serie serieExistente = serieRepository.findById(idSerie)
                .orElseThrow(() -> new EntityNotFoundException("Série não encontrada com ID: " + idSerie));

        serieExistente.setTitulo(dadosSerie.getTitulo());
        serieExistente.setSinopse(dadosSerie.getSinopse());
        serieExistente.setQtdeTemporadas(dadosSerie.getQtdeTemporadas());
        serieExistente.setGenero(dadosSerie.getGenero());
        serieExistente.setAnoInicio(dadosSerie.getAnoInicio());
        serieExistente.setAnoFim(dadosSerie.getAnoFim());

        serieExistente.getPessoaAtuaSeries().clear();
        if (dadosSerie.getPessoaAtuaSeries() != null) {
            for (Pessoa_Atua_Serie pasFromJson : dadosSerie.getPessoaAtuaSeries()) {
                Pessoa pessoaReferencia = entityManager.getReference(Pessoa.class, pasFromJson.getPessoa().getIdPessoa());
                Pessoa_Atua_Serie novaAtuacao = new Pessoa_Atua_Serie();

                novaAtuacao.setSerie(serieExistente);
                novaAtuacao.setPessoa(pessoaReferencia);
                novaAtuacao.setFuncao(pasFromJson.getFuncao());

                serieExistente.getPessoaAtuaSeries().add(novaAtuacao);
            }
        }

        Serie serieAtualizada = serieRepository.save(serieExistente);
        return ResponseEntity.ok(serieAtualizada);
    }

    @Transactional
    public ResponseEntity<Void> deleteSerie(Long idSerie) {
        if (!serieRepository.existsById(idSerie)) {
            throw new EntityNotFoundException("Série não encontrada com ID: " + idSerie);
        }
        serieRepository.deleteById(idSerie);
        return ResponseEntity.noContent().build();
    }
}