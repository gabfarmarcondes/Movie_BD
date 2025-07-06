package com.movie_bd.model;

import com.movie_bd.model.keys.PKs_Avaliacao_Filme;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(PKs_Avaliacao_Filme.class)
public class Avaliacao_Filme {

    @Id
    private Long idFilme;

    @Id
    private Long idAvaliacao;

    @MapsId("idFilme")
    @ManyToOne
    @JoinColumn(name = "idFilme", nullable = false)
    private Filme filme;

    @MapsId("idAvaliacao")
    @ManyToOne
    @JoinColumn(name = "idAvaliacao", nullable = false)
    private Avaliacao avaliacao;
}
