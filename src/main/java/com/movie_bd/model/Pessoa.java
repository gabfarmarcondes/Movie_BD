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
    private Long ID_Pessoa;

    @Column(nullable = false, length = 50)
    private String Pnome;

    @Column(nullable = false, length = 1)
    private String Minicial;

    @Column(nullable = false, length = 50)
    private String Unome;

    @Column(nullable = false, length = 30)
    private String Nacionalidade;

    @Column(nullable = false)
    private Date Data_Nascimento;

    @OneToMany(mappedBy = "ID_Pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Pessoa_Atua_Filme> pessoa_atua_filmes = new ArrayList<>();

    @OneToMany(mappedBy = "ID_Pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Pessoa_Atua_Serie> pessoa_atua_series = new ArrayList<>();
}
