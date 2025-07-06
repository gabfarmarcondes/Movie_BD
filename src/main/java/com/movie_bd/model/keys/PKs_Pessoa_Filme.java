package com.movie_bd.model.keys;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PKs_Pessoa_Filme implements Serializable {
    private Long idPessoa;

    private Long idFilme;
}
