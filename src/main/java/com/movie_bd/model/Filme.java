package com.movie_bd.model;
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
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Filme;

    @Column(unique = true, nullable = false, length = 50)
    private String Titulo;

    @Column(unique = true, nullable = false)
    private String Sinopse;

    @Column(nullable = false)
    private String Genero;

    @Column(nullable = false)
    private LocalTime Duracao;

    @Column(nullable = false)
    private Date Data_Lancamento;

    @OneToMany(mappedBy = "ID_Filme", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Pessoa_Atua_Filme> pessoa_atua_filmes = new ArrayList<>();
}
