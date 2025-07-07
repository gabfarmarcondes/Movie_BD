package com.movie_bd.controller;

import com.movie_bd.model.Filme;
import com.movie_bd.services.FilmeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

    private final FilmeServices filmeServices;

    @Autowired
    public FilmeController(FilmeServices filmeServices) {
        this.filmeServices = filmeServices;
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Filme>> getFilmesByGenero(@PathVariable String genero) {
        return filmeServices.getFilmesByGenero(genero);
    }

    @GetMapping("/data/2000-2010")
    public ResponseEntity<List<Filme>> findFilmesEntre2000e2010(){
        return filmeServices.findFilmesEntre2000e2010();
    }

    @GetMapping("/duracao/maiorQue/{duracao}")
    public ResponseEntity<List<Filme>> getFilmesComDuracaoMaiorOuIgualA(@PathVariable LocalTime duracao) {
        return filmeServices.getFilmesComDuracaoMaiorOuIgualA(duracao);
    }

    @PostMapping("/criar")
    public ResponseEntity<Filme> createFilme(@RequestBody Filme filme) {
        return filmeServices.createFilme(filme);
    }

    @PutMapping("/atualizar/{idFilme}")
    public ResponseEntity<Filme> updateFilme(@PathVariable Long idFilme, @RequestBody Filme filme) {
        return filmeServices.updateFilme(idFilme, filme);
    }

    @DeleteMapping("/deletar/{idFilme}")
    public ResponseEntity<Void> deleteFilme(@PathVariable Long idFilme) {
        return filmeServices.deleteFilme(idFilme);
    }
}
