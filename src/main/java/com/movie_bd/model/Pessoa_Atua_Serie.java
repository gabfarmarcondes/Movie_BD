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
    private Long ID_Pessoa;

    @MapsId("ID_Pessoa")
    @ManyToOne
    @JoinColumn(name = "ID_Pessoa")
    private Pessoa pessoa;

    @Id
    private Long ID_Serie;

    @MapsId("ID_Serie")
    @ManyToOne
    @JoinColumn(name = "ID_Serie")
    private Serie serie;

    @Column(nullable = false, length = 30)
    private String Funcao;
}
