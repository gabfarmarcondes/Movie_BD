package com.movie_bd.model;

import com.movie_bd.model.keys.PKs_Pessoa_Filme;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa_Atua_Filme {

    @EmbeddedId
    private PKs_Pessoa_Filme id = new PKs_Pessoa_Filme();

    @MapsId("idPessoa")
    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @MapsId("idFilme")
    @ManyToOne
    @JoinColumn(name = "id_filme")
    private Filme filme;

    @Column(nullable = false, length = 30)
    private String funcao;
}
