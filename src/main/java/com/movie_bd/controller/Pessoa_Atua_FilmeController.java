package com.movie_bd.controller;

import com.movie_bd.model.Filme;
import com.movie_bd.model.Pessoa;
import com.movie_bd.model.Pessoa_Atua_Filme;
import com.movie_bd.services.Pessoa_Atua_FilmeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atuacao/filmes")
public class Pessoa_Atua_FilmeController {

    private final Pessoa_Atua_FilmeServices pafServices;

    @Autowired
    public Pessoa_Atua_FilmeController(Pessoa_Atua_FilmeServices pafServices) {
        this.pafServices = pafServices;
    }

    @GetMapping("/pessoa/{idPessoa}")
    public ResponseEntity<List<Filme>> getFilmesByPessoaId(@PathVariable Long idPessoa) {
        return pafServices.getFilmesByPessoaId(idPessoa);
    }

    @GetMapping("/funcao/{funcao}")
    public ResponseEntity<List<Pessoa>> getPessoasByFuncao(@PathVariable String funcao) {
        return pafServices.getPessoasByFuncao(funcao);
    }

    @GetMapping("/contagem/pessoas/{idPessoa}")
    public ResponseEntity<Long> getCountFilmesPorPessoa(@PathVariable Long idPessoa) {
        return pafServices.getCountFilmesPorPessoa(idPessoa);
    }

    @PostMapping("/criar")
    public ResponseEntity<Pessoa_Atua_Filme> createPessoaAtuaFilme(@RequestBody Pessoa_Atua_Filme paf) {
        return pafServices.createPessoaAtuaFilme(paf);
    }

    @PutMapping("/atualizar/{idFilme}/{idPessoa}")
    public ResponseEntity<Pessoa_Atua_Filme> updatePessoaAtuaFilme(
            @PathVariable Long idFilme, @PathVariable Long idPessoa, @RequestBody Pessoa_Atua_Filme dados) {
        return pafServices.updatePessoaAtuaFilme(idFilme, idPessoa, dados.getFuncao());
    }

    @DeleteMapping("/deletar/{idFilme}/{idPessoa}")
    public ResponseEntity<Void> deletePessoaAtuaFilme(@PathVariable Long idFilme, @PathVariable Long idPessoa) {
        return pafServices.deletePessoaAtuaFilme(idFilme, idPessoa);
    }
}
