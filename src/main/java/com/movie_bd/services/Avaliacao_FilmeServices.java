package com.movie_bd.services;

import com.movie_bd.config.ResponseWrapper;
import com.movie_bd.model.Avaliacao;
import com.movie_bd.model.Avaliacao_Filme;
import com.movie_bd.model.Avaliacao_Serie;
import com.movie_bd.model.keys.PKs_Avaliacao_Filme;
import com.movie_bd.repository.Avaliacao_FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Avaliacao_FilmeServices {

    private final Avaliacao_FilmeRepository afRepository;

    @Autowired
    public Avaliacao_FilmeServices(Avaliacao_FilmeRepository afRepository) {
        this.afRepository = afRepository;
    }

    public ResponseEntity<Avaliacao_Filme> getAvaliacoesByFilmeId(Long idFilme){
        List<Avaliacao> avaliacoes = afRepository.findAvaliacoesByFilmeId(idFilme);

        return avaliacoes.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Integer> getMediaDeNotasPorFilme(Long idFilme){
        int media = afRepository.findMediaDeNotasPorFilme(idFilme);
        if(media <= 0) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(media, HttpStatus.OK);
    }

    public ResponseEntity<Long> getCountByFilme_ID_Filme(Long idFilme){
        long contagem = afRepository.countByFilmeIdFilme(idFilme);
        if (contagem <= 0) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(contagem, HttpStatus.OK);
    }

    public ResponseEntity<ResponseWrapper<Avaliacao_Serie>> createAvaliacaoFilme(Avaliacao_Filme af){
        try {
            Avaliacao_Serie av = new Avaliacao_Serie();
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseWrapper<>("Avaliacao Serie Criada", av));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseWrapper<>("Internal Server ERROR", null));
        }
    }

    public ResponseEntity<ResponseWrapper<Avaliacao_Filme>> update(Long idFilme, Long idAvaliacao, Avaliacao_Filme dadosAtualizados) {
        PKs_Avaliacao_Filme pk = new PKs_Avaliacao_Filme();
        pk.setIdFilme(idFilme);
        pk.setIdAvaliacao(idAvaliacao);

        Avaliacao_Filme af = afRepository.findById(pk)
                .orElseThrow(() -> new RuntimeException("Avaliacao Filme not found"));

        af.setAvaliacao(dadosAtualizados.getAvaliacao());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseWrapper<>("Avaliacao Atualizada", af));
    }

    public ResponseEntity<ResponseWrapper<Avaliacao_Filme>> delete(Long idFilme, Long idAvaliacao) {
        PKs_Avaliacao_Filme pk = new PKs_Avaliacao_Filme();
        pk.setIdFilme(idFilme);
        pk.setIdAvaliacao(idAvaliacao);

        Avaliacao_Filme af = afRepository.findById(pk)
                .orElseThrow(() -> new RuntimeException("Avaliacao Filme not found"));

        afRepository.deleteById(pk);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ResponseWrapper<>("Avaliacao Deletada", af));
    }

}
