package com.movie_bd.services;

import com.movie_bd.model.*;
import com.movie_bd.repository.PessoaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaServices {

    private final PessoaRepository pessoaRepository;
    private final EntityManager entityManager;

    @Autowired
    public PessoaServices(PessoaRepository pessoaRepository, EntityManager entityManager) {
        this.pessoaRepository = pessoaRepository;
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<Pessoa>> getPessoaByNacionalidade(String nacionalidade) {
        List<Pessoa> pessoas = pessoaRepository.findPessoaByNacionalidade(nacionalidade);
        return ResponseEntity.ok(pessoas);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<Pessoa>> getPessoaByDataNascimento(Date dataNascimento) {
        List<Pessoa> pessoas = pessoaRepository.findPessoaByDataNascimento(dataNascimento);
        return ResponseEntity.ok(pessoas);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<Pessoa>> getPessoasByTituloDeFilme(String titulo) {
        List<Pessoa> pessoas = pessoaRepository.findPessoasByTituloDeFilme(titulo);
        return ResponseEntity.ok(pessoas);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<Pessoa>> getPessoasByTituloDeSerie(String titulo) {
        List<Pessoa> pessoas = pessoaRepository.findPessoasByTituloDeSerie(titulo);
        return ResponseEntity.ok(pessoas);
    }

    @Transactional
    public ResponseEntity<Pessoa> createPessoa(Pessoa pessoa) {
        if (pessoa.getPessoaAtuaFilmes() != null) {
            List<Pessoa_Atua_Filme> atuacoesGerenciadas = new ArrayList<>();
            for (Pessoa_Atua_Filme pafFromJson : pessoa.getPessoaAtuaFilmes()) {
                Pessoa_Atua_Filme novaAtuacao = new Pessoa_Atua_Filme();
                Filme filmeRef = entityManager.getReference(Filme.class, pafFromJson.getFilme().getIdFilme());
                novaAtuacao.setPessoa(pessoa);
                novaAtuacao.setFilme(filmeRef);
                novaAtuacao.setFuncao(pafFromJson.getFuncao());
                atuacoesGerenciadas.add(novaAtuacao);
            }
            pessoa.setPessoaAtuaFilmes(atuacoesGerenciadas);
        }
        if (pessoa.getPessoaAtuaSeries() != null) {
            List<Pessoa_Atua_Serie> atuacoesGerenciadas = new ArrayList<>();
            for (Pessoa_Atua_Serie pasFromJson : pessoa.getPessoaAtuaSeries()) {
                Pessoa_Atua_Serie novaAtuacao = new Pessoa_Atua_Serie();
                Serie serieRef = entityManager.getReference(Serie.class, pasFromJson.getSerie().getIdSerie());
                novaAtuacao.setPessoa(pessoa);
                novaAtuacao.setSerie(serieRef);
                novaAtuacao.setFuncao(pasFromJson.getFuncao());
                atuacoesGerenciadas.add(novaAtuacao);
            }
            pessoa.setPessoaAtuaSeries(atuacoesGerenciadas);
        }

        Pessoa novaPessoa = pessoaRepository.save(pessoa);
        return new ResponseEntity<>(novaPessoa, HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<Pessoa> updatePessoa(Long idPessoa, Pessoa dadosPessoa) {
        Pessoa pessoaExistente = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com ID: " + idPessoa));
        pessoaExistente.setPnome(dadosPessoa.getPnome());
        pessoaExistente.setMinicial(dadosPessoa.getMinicial());
        pessoaExistente.setUnome(dadosPessoa.getUnome());
        pessoaExistente.setNacionalidade(dadosPessoa.getNacionalidade());
        pessoaExistente.setDataNascimento(dadosPessoa.getDataNascimento());

        pessoaExistente.getPessoaAtuaFilmes().clear();
        if (dadosPessoa.getPessoaAtuaFilmes() != null) {
            for (Pessoa_Atua_Filme pafFromJson : dadosPessoa.getPessoaAtuaFilmes()) {
                Pessoa_Atua_Filme novaAtuacao = new Pessoa_Atua_Filme();
                Filme filmeRef = entityManager.getReference(Filme.class, pafFromJson.getFilme().getIdFilme());
                novaAtuacao.setPessoa(pessoaExistente);
                novaAtuacao.setFilme(filmeRef);
                novaAtuacao.setFuncao(pafFromJson.getFuncao());
                pessoaExistente.getPessoaAtuaFilmes().add(novaAtuacao);
            }
        }
        pessoaExistente.getPessoaAtuaSeries().clear();
        if (dadosPessoa.getPessoaAtuaSeries() != null) {
            for (Pessoa_Atua_Serie pasFromJson : dadosPessoa.getPessoaAtuaSeries()) {
                Pessoa_Atua_Serie novaAtuacao = new Pessoa_Atua_Serie();
                Serie serieRef = entityManager.getReference(Serie.class, pasFromJson.getSerie().getIdSerie());
                novaAtuacao.setPessoa(pessoaExistente);
                novaAtuacao.setSerie(serieRef);
                novaAtuacao.setFuncao(pasFromJson.getFuncao());
                pessoaExistente.getPessoaAtuaSeries().add(novaAtuacao);
            }
        }

        Pessoa pessoaAtualizada = pessoaRepository.save(pessoaExistente);
        return ResponseEntity.ok(pessoaAtualizada);
    }

    @Transactional
    public ResponseEntity<Void> deletePessoa(Long idPessoa) {
        if (!pessoaRepository.existsById(idPessoa)) {
            throw new EntityNotFoundException("Pessoa não encontrada com ID: " + idPessoa);
        }
        pessoaRepository.deleteById(idPessoa);
        return ResponseEntity.noContent().build();
    }
}