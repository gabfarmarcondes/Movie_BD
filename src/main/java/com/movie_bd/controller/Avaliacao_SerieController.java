package com.movie_bd.controller;

import com.movie_bd.model.Avaliacao;
import com.movie_bd.model.Avaliacao_Serie;
import com.movie_bd.model.keys.PKs_Pessoa_Serie;
import com.movie_bd.services.Avaliacao_SerieServices;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Avaliacao_Serie criarAvaliacao(@RequestBody Avaliacao_Serie avaliacaoSerie) {
        return asServices.criarAvaliacao(avaliacaoSerie);
    }

    @PutMapping("/atualizar/{idPessoa}/{idSerie}")
    public Avaliacao_Serie atualizarAvaliacao(
            @PathVariable Long idPessoa, @PathVariable Long idSerie, @RequestBody Avaliacao dadosAtualizacao) {
        PKs_Pessoa_Serie id = new PKs_Pessoa_Serie(idPessoa, idSerie);
        return asServices.atualizarAvaliacao(id, dadosAtualizacao);
    }

    @DeleteMapping("/deletar/{idPessoa}/{idSerie}")
    public void deletarAvaliacaoPorId(@PathVariable Long idPessoa, @PathVariable Long idSerie) {
        PKs_Pessoa_Serie id = new PKs_Pessoa_Serie(idPessoa, idSerie);
        asServices.deletarAvaliacaoPorId(id);
    }
}
