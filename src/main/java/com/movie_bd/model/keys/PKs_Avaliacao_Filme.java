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
public class PKs_Avaliacao_Filme implements Serializable {
    private Long idFilme;

    private Long idAvaliacao;
}
