# Movie_BD
## Tema: Plataforma de Avaliação de Filmes e Séries
* Justificativa do tema escolhido: Temos interesse por filmes e séries e entendenmos a importância de uma boa crítica e/ou nota para uma possível escolha para assistir algo.

## Atores: usuários, crítico de filmes, administrador do sistema.
* Descrição das Entidades:
  * Usuários: `id_usuario, nome_usuario, email, senha, data_cadastro, tipo_usuario (Comum, Crítico), status_solicitacao_critico (Inativo, Pendante, Aprovado, Rejeitado)`.
  * Filmes: `id_filme, titulo, genero_titulo, data_lancamento, sinopse, tempo_duracao`.
  * Série: `id_serie, titulo, genero_titulo, ano_inicio, ano_fim, sinopse, qtde_temporadas`.
    * Temporadas: `id_temporada, numero_temporada, id_titulo(FK)`.
      * Episódios: `id_episodio, numero_episodio, id_temporada(FK)`.
  * Pessoas: `id_pessoa, nome_pessoa(Pnome, Minicial, Unome), data_nascimento, nacionalidade, funcao (ator, atriz, diretor, escritor, dublador ou outro.)`.
  * Avaliação: `id_avaliacao, id_usuario(FK), id_titulo(FK), nota, comentario, data_avaliacao, tipo_conteudo_avaliacao(filme, serie ou Elenco), id_conteudo_avaliacao (FK)`

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
  * A plataforma cadastra filmes (título) e seus respectivos atores, atrizes e diretor(a)
  * A plataforma cadastra séries (título) e seus respectivos atores, atrizes, diretor(a), temporadas e seus episódios.
  * Os usuários podem avaliar títulos, atores, atrizes e diretores(as); deixando um comentário e uma nota.
  * Usuários podem mandar uma solicitação para obter o selo de crítico de cinema.
  * O administrador pode aprovar ou rejeitar solicitações de selo de crítico.
  * O sistema terá duas notas diferentes:
    * Uma nota para dos críticos.
    * Uma nota para do público geral.
  * A nota será de 0 a 10. De forma que será caldulada por uma média aritmética (nota de todos os usuários / quantidade de usuários que avaliaram).

## Execução do Projeto
### 📌 Pré-Requisitos
- Java 17 ou superior
- Gradle (para gerenciamento de dependências e build)
- PostgreSQL (banco de dados)
- Postman ou curl (para testar os endpoints da API)

### ⚙️ Instalação das Dependências
```bash
# 1. Clone o repositório
git clone <URL_DO_SEU_REPOSITORIO>
cd Movie_BD

# 2. Construa o projeto para baixar as dependências
./gradlew build
```

### 🗃️ Configuração do Banco de Dados
```sql
CREATE DATABASE movie_bd;
```

```bash
# Exemplo de comando para executar os scripts
psql -U postgres -d movie_bd -f src/main/resources/creates.sql
psql -U postgres -d movie_bd -f src/main/resources/preencher.sql
```

### 🔑 Configuração da Conexão
```
spring.datasource.url=jdbc:postgresql://localhost:5432/movie_bd
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA_AQUI

# Durante o desenvolvimento, 'update' ajuda a refletir mudanças nas entidades.
# Para produção, use uma ferramenta de migração como Flyway ou Liquibase.
spring.jpa.hibernate.ddl-auto=update

# Configurações para ver o SQL gerado pelo Hibernate no console
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
server.port=808
```

### ▶️ Rodando a Aplicação
```bash
./gradlew bootRun
```

### ✅ Testando os Endpoints
Alguns exemplos de como interagir com a API.
### 📺 Séries
- **Criar uma Nova Série com Elenco:**
  - (Nota: As pessoas com idPessoa 1 e 3 devem existir no banco de dados) 
```bash
curl -X POST http://localhost:8080/api/series/criar \
-H "Content-Type: application/json" \
-d '{
  "titulo": "A Rede Quântica",
  "sinopse": "Cientistas descobrem como viajar entre universos paralelos.",
  "qtdeTemporadas": 1,
  "genero": "Ficção Científica",
  "anoInicio": 2025,
  "anoFim": 2025,
  "pessoaAtuaSeries": [
    {
      "funcao": "Protagonista",
      "pessoa": { "idPessoa": 1 }
    },
    {
      "funcao": "Roteirista",
      "pessoa": { "idPessoa": 3 }
    }
  ]
}'
```

- **Atualizar uma Série (Ex: ID 1):**
```bash
curl -X PUT http://localhost:8080/api/series/atualizar/1 \
-H "Content-Type: application/json" \
-d '{
  "titulo": "Mundo Virtual - Renascimento",
  "sinopse": "A realidade virtual se torna mais perigosa do que nunca.",
  "qtdeTemporadas": 4,
  "genero": "Ficção Científica / Ação",
  "anoInicio": 2018,
  "anoFim": 2023
}'
```
- **Deletar uma Série(Ex.:ID 2)**
```bash
curl -X DELETE http://localhost:8080/api/series/deletar/2
```
### 🎥 Filmes
- **Criar um Novo Filme**
```bash
curl -X POST http://localhost:8080/api/filmes/criar \
-H "Content-Type: application/json" \
-d '{
    "titulo": "Além do Horizonte",
    "sinopse": "Uma jornada épica em busca de um tesouro perdido.",
    "genero": "Aventura",
    "duracao": "02:25:00",
    "dataLancamento": "2025-12-25"
}'
```

### 👤 Pessoas
- **Criar uma Nova Pessoa**
```bash
curl -X POST http://localhost:8080/api/pessoas/criar \
-H "Content-Type: application/json" \
-d '{
    "pnome": "Maria",
    "minicial": "C",
    "unome": "Costa",
    "nacionalidade": "Brasileira",
    "dataNascimento": "1995-10-08"
}'
```
