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
    private Long idAvaliacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(nullable = false)
    private int nota;

    @Column(nullable = false)
    private String comentario;

    @Column(nullable = false, length = 10)
    private String tipoConteudoAvaliacao;

    @Column(nullable = false)
    private Date dataAvaliacao;

    @Column(nullable = false)
    private int idConteudoAvaliacao;
}
