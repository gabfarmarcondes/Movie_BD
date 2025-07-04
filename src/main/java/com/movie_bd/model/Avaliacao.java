package com.movie_bd.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Avaliacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_USUARIO")
    private Usuario ID_USUARIO;

    @Column(nullable = false)
    private int Nota;

    @Column(nullable = false)
    private String Comentario;

    @Column(nullable = false, length = 10)
    private String Tipo_Conteudo_Avaliacao;

    @Column(nullable = false)
    private Date Data_Avaliacao;

    @Column(nullable = false)
    private int ID_Conteudo_Avaliacao;
}
