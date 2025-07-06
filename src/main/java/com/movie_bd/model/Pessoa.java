package com.movie_bd.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoa;

    @Column(nullable = false, length = 50)
    private String pnome;

    @Column(nullable = false, length = 1)
    private String minicial;

    @Column(nullable = false, length = 50)
    private String unome;

    @Column(nullable = false, length = 30)
    private String nacionalidade;

    @Column(nullable = false)
    private Date dataNascimento;

    @OneToMany(mappedBy = "idPessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Pessoa_Atua_Filme> pessoaAtuaFilmes = new ArrayList<>();

    @OneToMany(mappedBy = "idPessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Pessoa_Atua_Serie> pessoaAtuaSeries = new ArrayList<>();
}
