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
    private Long ID_Filme;

    @Id
    private Long ID_Avaliacao;

    @MapsId("ID_Filme")
    @ManyToOne
    @JoinColumn(name = "ID_Filme", nullable = false)
    private Filme filme;

    @MapsId("ID_Avaliacao")
    @ManyToOne
    @JoinColumn(name = "ID_Avaliacao", nullable = false)
    private Avaliacao avaliacao;
}
