package com.movie_bd.model;

import com.movie_bd.model.keys.PKs_Pessoa_Serie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa_Atua_Serie {

    @EmbeddedId
    private PKs_Pessoa_Serie id = new PKs_Pessoa_Serie();

    @MapsId("idPessoa")
    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @MapsId("idSerie")
    @ManyToOne
    @JoinColumn(name = "id_serie")
    private Serie serie;

    @Column(nullable = false, length = 30)
    private String funcao;
}
