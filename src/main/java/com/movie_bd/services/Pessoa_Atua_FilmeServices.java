package com.movie_bd.services;

import com.movie_bd.model.Filme;
import com.movie_bd.model.Pessoa;
import com.movie_bd.model.Pessoa_Atua_Filme;
import com.movie_bd.model.keys.PKs_Pessoa_Filme;
import com.movie_bd.repository.Pessoa_Atua_FilmeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Pessoa_Atua_FilmeServices {

    private final Pessoa_Atua_FilmeRepository pafRepository;

    @Autowired
    public Pessoa_Atua_FilmeServices(Pessoa_Atua_FilmeRepository pafRepository) {
        this.pafRepository = pafRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<Filme>> getFilmesByPessoaId(Long idPessoa) {
        List<Filme> filmes = pafRepository.findFilmeByPessoaId(idPessoa);
        return ResponseEntity.ok(filmes);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<Pessoa>> getPessoasByFuncao(String funcao) {
        List<Pessoa> pessoas = pafRepository.findPessoaByFuncao(funcao);
        return ResponseEntity.ok(pessoas);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Long> getCountFilmesPorPessoa(Long idPessoa) {
        Long contagem = pafRepository.countFilmesPorPessoa(idPessoa);
        return ResponseEntity.ok(contagem);
    }

    @Transactional
    public ResponseEntity<Pessoa_Atua_Filme> createPessoaAtuaFilme(Pessoa_Atua_Filme paf) {
        Pessoa_Atua_Filme novoPaf = pafRepository.save(paf);
        return new ResponseEntity<>(novoPaf, HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<Pessoa_Atua_Filme> updatePessoaAtuaFilme(Long idFilme, Long idPessoa, String funcao) {
        PKs_Pessoa_Filme id = new PKs_Pessoa_Filme(idPessoa, idFilme);

        Pessoa_Atua_Filme pafExistente = pafRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Atuação não encontrada com ID: " + id));

        pafExistente.setFuncao(funcao);
        Pessoa_Atua_Filme pafAtualizado = pafRepository.save(pafExistente);
        return ResponseEntity.ok(pafAtualizado);
    }

    @Transactional
    public ResponseEntity<Void> deletePessoaAtuaFilme(Long idFilme, Long idPessoa) {
        PKs_Pessoa_Filme id = new PKs_Pessoa_Filme(idPessoa, idFilme);
        if (!pafRepository.existsById(id)) {
            throw new EntityNotFoundException("Atuação não encontrada com ID: " + id);
        }
        pafRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}