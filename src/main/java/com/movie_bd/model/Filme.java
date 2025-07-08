package com.movie_bd.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idFilme")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFilme;

    @Column(unique = true, nullable = false, length = 50)
    private String titulo;

    @Column(unique = true, nullable = false)
    private String sinopse;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private LocalTime duracao;

    @Column(nullable = false)
    private Date dataLancamento;

    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Pessoa_Atua_Filme> pessoaAtuaFilmes = new ArrayList<>();
}
