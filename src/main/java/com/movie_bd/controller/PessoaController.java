package com.movie_bd.controller;

import com.movie_bd.model.Pessoa;
import com.movie_bd.services.PessoaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    private final PessoaServices pessoaServices;

    @Autowired
    public PessoaController(PessoaServices pessoaServices) {
        this.pessoaServices = pessoaServices;
    }

    @GetMapping("/nacionalidade/{nacionalidade}")
    public ResponseEntity<List<Pessoa>> getPessoaByNacionalidade(@PathVariable String nacionalidade) {
        return pessoaServices.getPessoaByNacionalidade(nacionalidade);
    }

    @GetMapping("/dataNascimento/{dataNascimento}")
    public ResponseEntity<List<Pessoa>> getPessoaByDataNascimento(@PathVariable Date dataNascimento) {
        return pessoaServices.getPessoaByDataNascimento(dataNascimento);
    }

    @GetMapping("/titloFilme/{titulo}")
    public ResponseEntity<List<Pessoa>> getPessoasByTituloDeFilme(@PathVariable String titulo) {
        return pessoaServices.getPessoasByTituloDeFilme(titulo);
    }

    @GetMapping("/titloSerie/{titulo}")
    public ResponseEntity<List<Pessoa>> getPessoasByTituloDeSerie(@PathVariable String titulo) {
        return pessoaServices.getPessoasByTituloDeSerie(titulo);
    }

    @PostMapping("/criar")
    public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa) {
        return pessoaServices.createPessoa(pessoa);
    }

    @PutMapping("/atualizar/{idPessoa}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long idPessoa, @RequestBody Pessoa pessoa) {
        return pessoaServices.updatePessoa(idPessoa, pessoa);
    }

    @DeleteMapping("/deletar/{idPessoa}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long idPessoa) {
        return pessoaServices.deletePessoa(idPessoa);
    }
}
