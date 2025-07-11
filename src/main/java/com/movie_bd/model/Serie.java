package com.movie_bd.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idSerie")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSerie;

    @Column(unique = true, nullable = false, length = 100)
    private String titulo;

    @Column(unique = true, nullable = false, length = 500)
    private String sinopse;

    @Column(nullable = false)
    private int qtdeTemporadas;

    @Column(nullable = false, length = 50)
    private String genero;

    @Column(nullable = false)
    private int anoInicio;

    @Column(nullable = false)
    private int anoFim;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Pessoa_Atua_Serie> pessoaAtuaSeries = new ArrayList<>();

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Avaliacao_Serie> avaliacaoSeries = new ArrayList<>();
}
