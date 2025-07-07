package com.movie_bd.services;

import com.movie_bd.model.Pessoa;
import com.movie_bd.model.Pessoa_Atua_Serie;
import com.movie_bd.model.Serie;
import com.movie_bd.model.keys.PKs_Pessoa_Serie;
import com.movie_bd.repository.Pessoa_Atua_SerieRepository;
import com.movie_bd.repository.auxiliar.ElencoProjecao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Pessoa_Atua_SerieServices {

    private final Pessoa_Atua_SerieRepository pasRepository;
    private final EntityManager entityManager;

    @Autowired
    public Pessoa_Atua_SerieServices(Pessoa_Atua_SerieRepository pasRepository, EntityManager entityManager) {
        this.pasRepository = pasRepository;
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Pessoa_Atua_Serie> getByIds(Long idPessoa, Long idSerie) {
        PKs_Pessoa_Serie id = new PKs_Pessoa_Serie(idPessoa, idSerie);
        return pasRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<Serie>> getSeriesByPessoaId(Long idPessoa) {
        List<Serie> series = pasRepository.findSeriesByPessoaId(idPessoa);
        return ResponseEntity.ok(series);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<ElencoProjecao>> getElencoDaSerie(Long idSerie) {
        List<ElencoProjecao> elenco = pasRepository.findElencoDaSerie(idSerie);
        return ResponseEntity.ok(elenco);
    }

    @Transactional
    public ResponseEntity<Pessoa_Atua_Serie> createPessoaAtuaSerie(Pessoa_Atua_Serie pas) {
        Pessoa pessoaRef = entityManager.getReference(Pessoa.class, pas.getPessoa().getIdPessoa());
        Serie serieRef = entityManager.getReference(Serie.class, pas.getSerie().getIdSerie());

        pas.setPessoa(pessoaRef);
        pas.setSerie(serieRef);

        Pessoa_Atua_Serie novoPaf = pasRepository.save(pas);
        return new ResponseEntity<>(novoPaf, HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<Pessoa_Atua_Serie> updatePessoaAtuaSerie(Long idSerie, Long idPessoa, String funcao) {
        PKs_Pessoa_Serie id = new PKs_Pessoa_Serie(idPessoa, idSerie);

        Pessoa_Atua_Serie pasExistente = pasRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Atuação não encontrada com ID: " + id));

        pasExistente.setFuncao(funcao);
        Pessoa_Atua_Serie pasAtualizada = pasRepository.save(pasExistente);
        return ResponseEntity.ok(pasAtualizada);
    }

    @Transactional
    public ResponseEntity<Void> deletePessoaAtuaSerie(Long idSerie, Long idPessoa) {
        PKs_Pessoa_Serie id = new PKs_Pessoa_Serie(idPessoa, idSerie);
        if (!pasRepository.existsById(id)) {
            throw new EntityNotFoundException("Atuação não encontrada com ID: " + id);
        }
        pasRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}