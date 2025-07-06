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
@IdClass(PKs_Avaliacao_Serie.class)
public class Avaliacao_Serie {

    @Id
    private Long idSerie;

    @Id
    private Long idAvaliacao;

    @MapsId("idSerie")
    @ManyToOne
    @JoinColumn(name = "idSerie", nullable = false)
    private Serie serie;

    @MapsId("idAvaliacao")
    @ManyToOne
    @JoinColumn(name = "idAvaliacao", nullable = false)
    private Avaliacao avaliacao;
}
