# Movie_BD
## Tema: Plataforma de Avaliação de Filmes e Séries
* Justificativa do tema escolhido: Temos interesse por filmes e séries e entendenmos a importância de uma boa crítica e/ou nota para uma possível escolha para assistir algo.

## Atores: usuários, crítico de filmes, administrador do sistema.
* Descrição das Entidades:
  * Usuários: `id_usuario, nome_usuario, email, senha, tipo_usuario (Comum, Crítico), status_solicitacao_critico (Pendante, Aprovado, Rejeitado), data_cadastro`.
  * Filmes: `id_filme, titulo, genero_filme, data_lancamento, sinopse, tempo_duracao`.
  * Série: `id_serie, titulo, genero_filme, ano_inicio, ano_fim, sinopse, qtde_temporadas`.
    * Temporadas: `id_temporada, numero_temporada, id_serie(FK)`.
      * Episódios: `id_episodio, numero_episodio, id_temporada(FK)`.
  * Pessoas: `id_pessoa, nome_pessoa(Pnome, Minicial, Unome), data_nascimento, nacionalidade, funcao (ator, atriz, diretor, escritor, dublador ou outro.)`.
  * Avaliação: `id_avaliacao, id_usuario, nota, comentario, data_avaliacao, tipo_conteudo_avaliacao(filme, serie ou Elenco), id_conteudo_avaliacao (FK),

## Sugestão de Relacionamentos entre as Entidades:
  * Pessoas (N) para Filmes (M): Um filme pode ter muitos atores/diretores/etc, e uma pessoa pode atuar/dirigir em muitos filmes.
    * Representação: Entidade associativa `Papel_Filme` com chaves estrangeiras `id_filme` e `id_pessoa`, e um atributo `tipo_papel`.
    <br>
  * Pessoas (N) para Séries (M): Uma séries pode ter muitos atores/diretores/etc, e uma pessoa pode atuar/dirigir em muitas séries.
    * Representação: Entidade associativa `Papel_Serie` com chaves estrangeiras `id_serie` e `id_pessoa`, e um atributo `tipo_papel`.
    <br>
  * Série (1) para Temporada (N): Uma série pode ter muitas temporadas. Cada temporada pertence a uma única série.
    * Representação: Temporada tem uma chave estrangeira (`id_serie`) referenciando Série.
    <br>
  * Usuario (1) para Avaliação (N): Um usuário pode fazer muitas avaliações. Cada avaliação é feita por um único usuário.
    * Representação: Avaliação tem uma chave estrangeira `id_usuario` referenciando Usuário.
    <br>
  * Filme (1) para Avaliação (N): Um filme pode ter muitas avaliações. Cada avaliação é de um único filme.
    * Representação: Avaliação também terá uma chave estrangeira `id_filme` referenciando Filme.
    <br>
  * Série (1) para Avaliação (N): Uma série pode ter muitas avaliações. Cada avaliação é de um único série.
    * Representação: Avaliação também terá uma chave estrangeira `id_serie` referenciando Série.
    <br>
  * Pessoas(as elenco) (1) para Avaliação (N): Um ator/diretor/etc pode ter muitas avaliações. Cada avaliação é de um único ator/diretor/etc .
    * Representação: Avaliação também terá uma chave estrangeira `id_pessoa` referenciando Pessoas.
    <br>
## Requisitos Funcionais:
  * A plataforma cadastra filmes e seus respectivos atores, atrizes e diretor(a)
  * A plataforma cadastra séries e seus respectivos atores, atrizes, diretor(a), temporadas e seus episódios.
  * Os usuários podem avaliar filmes, séries, atores, atrizes e diretores(as), deixando um comentário e uma nota.
  * usuários críticos de cinema, podem mandar uma solicitação para obter o selo de crítico de cinema.
  * O administrador pode aprovar ou rejeitar solicitações de selo de crítico.
  * O sistema terá duas notas diferentes:
    * Uma nota para os críticos.
    * Uma nota para o público geral.
  * A nota será de 0 a 10. De forma que será caldulada por uma média aritmética (nota de todos os usuários / quantidade de usuários que avaliaram).
