package com.movie_bd.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_USUARIO;

    @Column(unique = true, nullable = false)
    private String Nome_Usuario;

    @Column(unique = true, nullable = false)
    private String Email;

    @Column(nullable = false)
    private String Senha;

    @Column(nullable = false)
    private boolean Status_Solicitacao_Critico;

    @Column(nullable = false)
    private Date Data_Cadastro;

    @Column(nullable = false, length = 20)
    private String Tipo_Usuario;

    @OneToMany(mappedBy = "ID_USUARIO", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Avaliacao> avaliacoes = new HashSet<>();
}
