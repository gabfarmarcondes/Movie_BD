package com.movie_bd.model.keys;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class PKs_Pessoa_Serie implements Serializable {
    private Long idPessoa;

    private Long idSerie;
}
