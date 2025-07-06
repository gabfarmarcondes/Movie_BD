package com.movie_bd.model;

import com.movie_bd.model.keys.PKs_Pessoa_Serie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@IdClass(PKs_Pessoa_Serie.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa_Atua_Serie {

    @Id
    private Long idPessoa;

    @MapsId("idPessoa")
    @ManyToOne
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoa;

    @Id
    private Long idSerie;

    @MapsId("idSerie")
    @ManyToOne
    @JoinColumn(name = "idSerie")
    private Serie serie;

    @Column(nullable = false, length = 30)
    private String funcao;
}
