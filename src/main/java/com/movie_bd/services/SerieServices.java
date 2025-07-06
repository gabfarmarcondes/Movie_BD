package com.movie_bd.services;

import com.movie_bd.model.Serie;
import com.movie_bd.repository.SerieRepository;
import com.movie_bd.repository.auxiliar.SerieDuracao;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SerieServices {

    private final SerieRepository serieRepository;

    @Autowired
    public SerieServices(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
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
        Serie novaSerie = serieRepository.save(serie);
        return new ResponseEntity<>(novaSerie, HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<Serie> updateSerie(Long idSerie, Serie dadosSerie) {
        Serie serieExistente = serieRepository.findById(idSerie)
                .orElseThrow(() -> new EntityNotFoundException("Série não encontrada com ID: " + idSerie));

        serieExistente.setTitulo(dadosSerie.getTitulo());
        serieExistente.setSinopse(dadosSerie.getSinopse());
        serieExistente.setQTDE_Temporadas(dadosSerie.getQTDE_Temporadas());
        serieExistente.setGenero(dadosSerie.getGenero());
        serieExistente.setAno_Inicio(dadosSerie.getAno_Inicio());
        serieExistente.setAno_Fim(dadosSerie.getAno_Fim());

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