package com.movie_bd.controller;

import com.movie_bd.model.Serie;
import com.movie_bd.repository.auxiliar.SerieDuracao;
import com.movie_bd.services.SerieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series")
public class SerieController {

    private final SerieServices serieServices;

    @Autowired
    public SerieController(SerieServices serieServices) {
        this.serieServices = serieServices;
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Serie> getSerieByTitulo(@PathVariable String titulo) {
        return serieServices.getSerieByTitulo(titulo);
    }

    @GetMapping("/maiorTemporada/{qtdeTemporadas}")
    public ResponseEntity<List<Serie>> getSeriesComMaisTemporadasQue(@PathVariable int qtdeTemporadas) {
        return serieServices.getSeriesComMaisTemporadasQue(qtdeTemporadas);
    }

    @GetMapping("/tempo/{anoInicio}/{anoFim}")
    public ResponseEntity<List<SerieDuracao>> getTempoParaFazerSerie(@PathVariable int anoInicio, @PathVariable int anoFim) {
        return serieServices.getTempoParaFazerSerie(anoInicio, anoFim);
    }

    @PostMapping("/criar")
    public ResponseEntity<Serie> createSerie(@RequestBody Serie serie) {
        return serieServices.createSerie(serie);
    }

    @PutMapping("/atualizar/{idSerie}")
    public ResponseEntity<Serie> updateSerie(@PathVariable Long idSerie, @RequestBody Serie serie) {
        return serieServices.updateSerie(idSerie, serie);
    }

    @DeleteMapping("/deletar/{idSerie}")
    public ResponseEntity<Void> deleteSerie(@PathVariable Long idSerie) {
        return serieServices.deleteSerie(idSerie);
    }
}
