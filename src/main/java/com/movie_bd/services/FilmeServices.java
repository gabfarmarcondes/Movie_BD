package com.movie_bd.services;

import com.movie_bd.model.Filme;
import com.movie_bd.repository.FilmeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

@Service
public class FilmeServices {

    private final FilmeRepository filmeRepository;

    @Autowired
    public FilmeServices(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<Filme>> getFilmesByGenero(String genero) {
        List<Filme> filmes = filmeRepository.findFilmesByGenero(genero);
        return ResponseEntity.ok(filmes);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<Filme>> findFilmesEntre2000e2010() {
        List<Filme> filmes = filmeRepository.findFilmesEntre2000e2010();
        return ResponseEntity.ok(filmes);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<Filme>> getFilmesComDuracaoMaiorOuIgualA(LocalTime duracao) {
        List<Filme> filmes = filmeRepository.findFilmesComDuracaoMaiorOuIgualA(duracao);
        return ResponseEntity.ok(filmes);
    }

    @Transactional
    public ResponseEntity<Filme> createFilme(Filme filme) {
        Filme novoFilme = filmeRepository.save(filme);
        return new ResponseEntity<>(novoFilme, HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<Filme> updateFilme(Long idFilme, Filme dadosFilme) {
        Filme filmeExistente = filmeRepository.findById(idFilme)
                .orElseThrow(() -> new EntityNotFoundException("Filme não encontrado com ID: " + idFilme));

        filmeExistente.setTitulo(dadosFilme.getTitulo());
        filmeExistente.setSinopse(dadosFilme.getSinopse());
        filmeExistente.setGenero(dadosFilme.getGenero());
        filmeExistente.setDuracao(dadosFilme.getDuracao());
        filmeExistente.setDataLancamento(dadosFilme.getDataLancamento());

        Filme filmeAtualizado = filmeRepository.save(filmeExistente);
        return ResponseEntity.ok(filmeAtualizado);
    }

    @Transactional
    public ResponseEntity<Void> deleteFilme(Long idFilme) {
        if (!filmeRepository.existsById(idFilme)) {
            throw new EntityNotFoundException("Filme não encontrado com ID: " + idFilme);
        }
        filmeRepository.deleteById(idFilme);
        return ResponseEntity.noContent().build();
    }
}