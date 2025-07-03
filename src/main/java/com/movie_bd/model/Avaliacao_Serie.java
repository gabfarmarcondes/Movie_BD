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
    private Long ID_Serie;

    @Id
    private Long ID_Avaliacao;

    @MapsId("ID_Serie")
    @ManyToOne
    @JoinColumn(name = "ID_Serie", nullable = false)
    private Serie serie;

    @MapsId("ID_Avaliacao")
    @ManyToOne
    @JoinColumn(name = "ID_Avaliacao", nullable = false)
    private Avaliacao avaliacao;
}
