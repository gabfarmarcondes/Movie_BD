package com.movie_bd.services;

import com.movie_bd.model.Pessoa;
import com.movie_bd.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class PessoaServices {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaServices(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
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
        pessoaExistente.setData_Nascimento(dadosPessoa.getData_Nascimento());

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