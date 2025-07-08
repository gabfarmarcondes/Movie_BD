package com.movie_bd.controller;

import com.movie_bd.model.Avaliacao;
import com.movie_bd.model.Avaliacao_Serie;
import com.movie_bd.model.keys.PKs_Avaliacao_Serie;
import com.movie_bd.model.keys.PKs_Pessoa_Serie;
import com.movie_bd.services.Avaliacao_SerieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avaliacoes/series")
public class Avaliacao_SerieController {

    private final Avaliacao_SerieServices asServices;

    @Autowired
    public Avaliacao_SerieController(Avaliacao_SerieServices asServices) {
        this.asServices = asServices;
    }

    @GetMapping("/avaliacao/{idSerie}")
    public List<Avaliacao> findAvaliacoesBySerieId(@PathVariable Long idSerie) {
        return asServices.findAvaliacoesBySerieId(idSerie);
    }

    @GetMapping("/recentes/{idSerie}")
    public List<Avaliacao> findAvaliacoesRecentesPorSerie(@PathVariable Long idSerie) {
        return asServices.findAvaliacoesRecentesPorSerie(idSerie);
    }

    @GetMapping("/maiorNota/{idSerie}/{nota}")
    public List<Avaliacao> findAvaliacoesComNotaMaiorQue(
            @PathVariable Long idSerie, @PathVariable int nota) {
        return asServices.findAvaliacoesComNotaMaiorQue(idSerie, nota);
    }

    @PostMapping("/criar")
    public ResponseEntity<Avaliacao_Serie> criarAvaliacao(@RequestBody Avaliacao_Serie avaliacaoSerie) {
        return asServices.criarAvaliacao(avaliacaoSerie);
    }

    @PutMapping("/atualizar/{idAvaliacao}/{idSerie}")
    public ResponseEntity<Avaliacao_Serie> atualizarAvaliacao(
            @PathVariable Long idAvaliacao,
            @PathVariable Long idSerie,
            @RequestBody Avaliacao_Serie dadosAtualizados) {
        return asServices.atualizarAvaliacao(idSerie, idAvaliacao, dadosAtualizados);
    }

    @DeleteMapping("/deletar/{idAvaliacao}/{idSerie}")
    public ResponseEntity<Void> deletarAvaliacaoPorId(@PathVariable Long idAvaliacao, @PathVariable Long idSerie) {
        PKs_Avaliacao_Serie id = new PKs_Avaliacao_Serie(idAvaliacao, idSerie);
        asServices.deletarAvaliacaoPorId(id);
        return ResponseEntity.noContent().build();
    }
}
