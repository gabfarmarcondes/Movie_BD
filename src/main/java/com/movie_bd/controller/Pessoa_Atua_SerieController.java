package com.movie_bd.controller;

import com.movie_bd.model.Pessoa_Atua_Serie;
import com.movie_bd.model.Serie;
import com.movie_bd.repository.auxiliar.ElencoProjecao;
import com.movie_bd.services.Pessoa_Atua_SerieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atuacao/series")
public class Pessoa_Atua_SerieController {

    private final Pessoa_Atua_SerieServices pasSerieServices;

    @Autowired
    public Pessoa_Atua_SerieController(Pessoa_Atua_SerieServices pasSerieServices) {
        this.pasSerieServices = pasSerieServices;
    }

    @GetMapping("/{idPessoa}/{idSerie}")
    public ResponseEntity<Pessoa_Atua_Serie> getByIds(
            @PathVariable Long idPessoa, @PathVariable Long idSerie) {
        return pasSerieServices.getByIds(idPessoa, idSerie);
    }

    @GetMapping("/pessoa/{idPessoa}")
    public ResponseEntity<List<Serie>> getSeriesByPessoaId(@PathVariable Long idPessoa) {
        return pasSerieServices.getSeriesByPessoaId(idPessoa);
    }

    @GetMapping("/elenco/{idSerie}")
    public ResponseEntity<List<ElencoProjecao>> getElencoDaSerie(@PathVariable Long idSerie) {
        return pasSerieServices.getElencoDaSerie(idSerie);
    }

    @PostMapping("/criar")
    public ResponseEntity<Pessoa_Atua_Serie> createPessoaAtuaSerie(
            @RequestBody Pessoa_Atua_Serie pas) {
        return pasSerieServices.createPessoaAtuaSerie(pas);
    }

    @PutMapping("/atualizar/{idSerie}/{idPessoa}")
    public ResponseEntity<Pessoa_Atua_Serie> updatePessoaAtuaSerie(
            @PathVariable Long idSerie, @PathVariable Long idPessoa, @RequestBody Pessoa_Atua_Serie dados) {
        return pasSerieServices.updatePessoaAtuaSerie(idSerie, idPessoa, dados.getFuncao());
    }

    @DeleteMapping("/deletar/{idSerie}/{idPessoa}")
    public ResponseEntity<Void> deletePessoaAtuaSerie(
            @PathVariable Long idSerie, @PathVariable Long idPessoa) {
        return pasSerieServices.deletePessoaAtuaSerie(idSerie, idPessoa);
    }

}
