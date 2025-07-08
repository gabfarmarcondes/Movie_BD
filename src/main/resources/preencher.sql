-- Deixe o banco de dados gerar os IDs para 'pessoa'
INSERT INTO pessoa (pnome, minicial, unome, nacionalidade, data_nascimento)
VALUES
    ('Ana', 'M', 'Silva', 'Brasileira', '1990-05-15'),
    ('João', 'L', 'Souza', 'Brasileiro', '1985-03-22'),
    ('Emily', 'R', 'Johnson', 'Americana', '1992-11-30');

-- Deixe o banco de dados gerar os IDs para 'serie'
INSERT INTO serie (titulo, sinopse, qtde_temporadas, genero, ano_inicio, ano_fim)
VALUES
    ('Mundo Virtual', 'Uma série sobre realidades alternativas digitais.', 3, 'Ficção', 2018, 2021),
    ('Segredos Urbanos', 'Drama policial ambientado em grandes cidades.', 5, 'Drama', 2015, 2020);

-- Deixe o banco de dados gerar os IDs para 'filme'
INSERT INTO filme (titulo, sinopse, genero, duracao, data_lancamento)
VALUES
    ('O Código Final', 'Suspense sobre um hacker em busca da verdade.', 'Suspense', '02:10:00', '2019-08-10'),
    ('Sol Nascente', 'História romântica entre culturas diferentes.', 'Romance', '01:45:00', '2021-02-14');

-- Nas tabelas de junção, os IDs continuam, pois são chaves estrangeiras
INSERT INTO pessoa_atua_serie (id_pessoa, id_serie, funcao)
VALUES
    (1, 1, 'Ator Principal'),
    (2, 2, 'Diretor');

INSERT INTO pessoa_atua_filme (id_pessoa, id_filme, funcao)
VALUES (3, 1, 'Roteirista');

-- Deixe o banco de dados gerar os IDs para 'usuario'
INSERT INTO usuario (nome_usuario, email, senha, status_solicitacao_critico, data_cadastro, tipo_usuario)
VALUES
    ('usuario_ana', 'ana@email.com', 'senha123', FALSE, '2024-01-10', 'Critico'),
    ('joao_cine', 'joao@email.com', 'senha456', TRUE, '2023-12-05', 'Comum');

-- Deixe o banco de dados gerar os IDs para 'avaliacao'
INSERT INTO avaliacao (id_usuario, nota, comentario, tipo_conteudo_avaliacao, data_avaliacao, id_conteudo_avaliacao)
VALUES
    (1, 9, 'Excelente narrativa!', 'Serie', '2024-01-15', 1),
    (2, 7, 'Bom filme, mas previsível.', 'Filme', '2024-02-20', 1);

-- Nas tabelas de junção, os IDs continuam
INSERT INTO avaliacao_serie (id_serie, id_avaliacao)
VALUES (1, 1);

INSERT INTO avaliacao_filme (id_filme, id_avaliacao)
VALUES (1, 2);

SELECT setval(pg_get_serial_sequence('pessoa', 'id_pessoa'), (SELECT MAX(id_pessoa) FROM pessoa));
SELECT setval(pg_get_serial_sequence('serie', 'id_serie'), (SELECT MAX(id_serie) FROM serie));
SELECT setval(pg_get_serial_sequence('filme', 'id_filme'), (SELECT MAX(id_filme) FROM filme));
SELECT setval(pg_get_serial_sequence('usuario', 'id_usuario'), (SELECT MAX(id_usuario) FROM usuario));
SELECT setval(pg_get_serial_sequence('avaliacao', 'id_avaliacao'), (SELECT MAX(id_avaliacao) FROM avaliacao));