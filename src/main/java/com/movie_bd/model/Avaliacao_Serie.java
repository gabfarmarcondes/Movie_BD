package com.movie_bd.model;

import com.movie_bd.model.keys.PKs_Avaliacao_Serie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Avaliacao_Serie {

    @EmbeddedId
    PKs_Avaliacao_Serie id = new PKs_Avaliacao_Serie();

    @MapsId("idSerie")
    @ManyToOne
    @JoinColumn(name = "id_serie", nullable = false)
    private Serie serie;

    @MapsId("idAvaliacao")
    @ManyToOne
    @JoinColumn(name = "id_avaliacao", nullable = false)
    private Avaliacao avaliacao;
}
