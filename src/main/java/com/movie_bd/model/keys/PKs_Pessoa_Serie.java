package com.movie_bd.model.keys;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PKs_Pessoa_Serie implements Serializable {
    private Long ID_Pessoa;

    private Long ID_Serie;
}
