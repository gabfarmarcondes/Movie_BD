package com.movie_bd.model.keys;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PKs_Avaliacao_Serie implements Serializable {
    private Long idSerie;

    private Long idAvaliacao;
}
