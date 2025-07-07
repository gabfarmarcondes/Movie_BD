INSERT INTO pessoa (id_pessoa, pnome, minicial, unome, nacionalidade, data_nascimento)
VALUES
    (1, 'Ana', 'M', 'Silva', 'Brasileira', '1990-05-15'),
    (2, 'João', 'L', 'Souza', 'Brasileiro', '1985-03-22'),
    (3, 'Emily', 'R', 'Johnson', 'Americana', '1992-11-30');

INSERT INTO serie (id_serie, titulo, sinopse, qtde_temporadas, genero, ano_inicio, ano_fim)
VALUES
    (1, 'Mundo Virtual', 'Uma série sobre realidades alternativas digitais.', 3, 'Ficção', 2018, 2021),
    (2, 'Segredos Urbanos', 'Drama policial ambientado em grandes cidades.', 5, 'Drama', 2015, 2020);

INSERT INTO filme (id_filme, titulo, sinopse, genero, duracao, data_lancamento)
VALUES
    (1, 'O Código Final', 'Suspense sobre um hacker em busca da verdade.', 'Suspense', '02:10:00', '2019-08-10'),
    (2, 'Sol Nascente', 'História romântica entre culturas diferentes.', 'Romance', '01:45:00', '2021-02-14');

INSERT INTO pessoa_atua_serie (id_pessoa, id_serie, funcao)
VALUES
    (1, 1, 'Ator Principal'),
    (2, 2, 'Diretor');

INSERT INTO pessoa_atua_filme (id_pessoa, id_filme, funcao)
VALUES (3, 1, 'Roteirista');

INSERT INTO usuario (id_usuario, nome_usuario, email, senha, status_solicitacao_critico, data_cadastro, tipo_usuario)
VALUES
    (1, 'usuario_ana', 'ana@email.com', 'senha123', FALSE, '2024-01-10', 'Critico'),
    (2, 'joao_cine', 'joao@email.com', 'senha456', TRUE, '2023-12-05', 'Comum');

INSERT INTO avaliacao (id_avaliacao, id_usuario, nota, comentario, tipo_conteudo_avaliacao, data_avaliacao, id_conteudo_avaliacao)
VALUES
    (1, 1, 9, 'Excelente narrativa!', 'Serie', '2024-01-15', 1),
    (2, 2, 7, 'Bom filme, mas previsível.', 'Filme', '2024-02-20', 1);

INSERT INTO avaliacao_serie (id_serie, id_avaliacao)
VALUES (1, 1);

INSERT INTO avaliacao_filme (id_filme, id_avaliacao)
VALUES (1, 2);


SELECT U.nome_usuario, S.titulo, A.nota, A.comentario
FROM avaliacao A
         JOIN usuario U ON A.id_usuario = U.id_usuario
         JOIN avaliacao_serie AS ON A.id_avaliacao = AS.id_avaliacao
    JOIN serie S ON AS.id_serie = S.id_serie
WHERE U.tipo_usuario = 'Critico';

SELECT P.pnome, P.unome, F.titulo
FROM pessoa P
         JOIN pessoa_atua_filme PF ON P.id_pessoa = PF.id_pessoa
         JOIN filme F ON PF.id_filme = F.id_filme
WHERE F.genero = 'Suspense';

SELECT U.nome_usuario, A.nota, A.comentario
FROM avaliacao A
         JOIN usuario U ON A.id_usuario = U.id_usuario
WHERE A.nota > 8;