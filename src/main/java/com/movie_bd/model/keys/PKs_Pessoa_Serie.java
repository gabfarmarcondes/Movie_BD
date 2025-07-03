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
public class PKs_Pessoa_Serie implements Serializable {
    private Long ID_Pessoa;

    private Long ID_Serie;
}
