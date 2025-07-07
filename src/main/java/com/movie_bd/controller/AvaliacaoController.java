package com.movie_bd.controller;

import com.movie_bd.model.Avaliacao;
import com.movie_bd.services.AvaliacaoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avaliacao")
public class AvaliacaoController {

    private final AvaliacaoServices avaliacaoServices;

    @Autowired
    public AvaliacaoController(AvaliacaoServices avaliacaoServices) {
        this.avaliacaoServices = avaliacaoServices;
    }

    @GetMapping("/maiorNotaQue/{nota}")
    public ResponseEntity<List<Avaliacao>> getNotasMaioresQue(@PathVariable int nota) {
        return avaliacaoServices.getNotasMaioresQue(nota);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Avaliacao>> getByUsuarioId(@PathVariable Long idUsuario) {
        return avaliacaoServices.getByUsuarioId(idUsuario);
    }

    @GetMapping("/notasOrdenadas/{nota}")
    public ResponseEntity<List<Avaliacao>> getNotasOrdenadas(@PathVariable int nota) {
        return avaliacaoServices.getNotasOrdenadas(nota);
    }

    @GetMapping("/tipoConteudo/{tipoConteudo}")
    public ResponseEntity<Long> countPorTipoDeConteudo(@PathVariable String tipoConteudo) {
        return avaliacaoServices.countPorTipoDeConteudo(tipoConteudo);
    }

    @PostMapping("/criar")
    public ResponseEntity<Avaliacao> createAvaliacao(@RequestBody Avaliacao avaliacao) {
        return avaliacaoServices.createAvaliacao(avaliacao);
    }

    @PutMapping("/atualizar/{idAvaliacao}")
    public ResponseEntity<Avaliacao> updateAvaliacao(@PathVariable Long idAvaliacao, @RequestBody Avaliacao dadosAvaliacao) {
        return     avaliacaoServices.updateAvaliacao(idAvaliacao, dadosAvaliacao);
    }

    @DeleteMapping("/deletar/{idAvaliacao}")
    public ResponseEntity<Void> deleteAvaliacao(@PathVariable Long idAvaliacao) {
        return avaliacaoServices.deleteAvaliacao(idAvaliacao);
    }
}
