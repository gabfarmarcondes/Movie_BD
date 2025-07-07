package com.movie_bd.controller;

import com.movie_bd.model.Avaliacao_Filme;
import com.movie_bd.services.Avaliacao_FilmeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/avaliacoes/filmes")
public class Avaliacao_FilmeController {

    private final Avaliacao_FilmeServices afServices;

    @Autowired
    public Avaliacao_FilmeController(Avaliacao_FilmeServices afServices) {
        this.afServices = afServices;
    }

    @GetMapping("/avaliacao/{idFilme}")
    public ResponseEntity<Avaliacao_Filme> getAvaliacoesByFilmeId(@PathVariable Long idFilme){
        return afServices.getAvaliacoesByFilmeId(idFilme);
    }

    @GetMapping("/media/{idFilme}")
    public ResponseEntity<Integer> getMediaDeNotasPorFilme(@PathVariable Long idFilme){
        return afServices.getMediaDeNotasPorFilme(idFilme);
    }

    @GetMapping("/contagem/{idFilme}")
    public ResponseEntity<Long> getCountByFilme_ID_Filme(@PathVariable Long idFilme){
        return afServices.getCountByFilme_ID_Filme(idFilme);
    }

    @PostMapping("/criar")
    public ResponseEntity<Avaliacao_Filme> createAvaliacaoFilme(@RequestBody Avaliacao_Filme af){
        return afServices.createAvaliacaoFilme(af);
    }

    @PutMapping("/atualizar/{idFilme}/{idAvaliacao}")
    public ResponseEntity<Avaliacao_Filme> update(
            @PathVariable Long idFilme, @PathVariable Long idAvaliacao, @RequestBody Avaliacao_Filme dadosAtualizados) {
        return afServices.update(idFilme, idAvaliacao, dadosAtualizados);
    }

    @DeleteMapping("/deletar/{idFilme}/{idAvaliacao}")
    public ResponseEntity<Void> delete(
            @PathVariable Long idFilme, @PathVariable Long idAvaliacao) {
        return afServices.delete(idFilme, idAvaliacao);
    }
}
