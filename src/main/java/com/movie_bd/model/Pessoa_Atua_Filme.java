package com.movie_bd.model;

import com.movie_bd.model.keys.PKs_Pessoa_Filme;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@IdClass(PKs_Pessoa_Filme.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa_Atua_Filme {

    @Id
    private Long ID_Pessoa;

    @Id
    private Long ID_Filme;

    @MapsId("ID_Pessoa")
    @ManyToOne
    @JoinColumn(name = "ID_Pessoa")
    private Pessoa pessoa;

    @MapsId("ID_Filme")
    @ManyToOne
    @JoinColumn(name = "ID_Filme")
    private Filme filme;

    @Column(nullable = false, length = 30)
    private String Funcao;
}
