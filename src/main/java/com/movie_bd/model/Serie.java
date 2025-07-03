package com.movie_bd.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_SERIE;

    @Column(unique = true, nullable = false, length = 50)
    private String Titulo;

    @Column(unique = true, nullable = false)
    private String Sinopse;

    @Column(nullable = false)
    private int QTDE_Temporadas;

    @Column(nullable = false)
    private String Genero;

    @Column(nullable = false)
    private int Ano_Inicio;

    @Column(nullable = false)
    private int Ano_Fim;

    @OneToMany(mappedBy = "ID_Serie", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Pessoa_Atua_Serie> pessoa_atua_series = new ArrayList<>();

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Avaliacao_Serie> avaliacao_series = new ArrayList<>();
}
