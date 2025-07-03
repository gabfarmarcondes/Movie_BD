INSERT INTO Pessoa (ID_Pessoa, Pnome, Minicial, Unome, Nacionalidade, Data_Nascimento)
      VALUES
      (1, 'Ana', 'M', 'Silva', 'Brasileira', '1990-05-15'),
      (2, 'João', 'L', 'Souza', 'Brasileiro', '1985-03-22'),
      (3, 'Emily', 'R', 'Johnson', 'Americana', '1992-11-30');

INSERT INTO Serie (ID_SERIE, Titulo, Sinopse, QTDE_Temporadas, Genero, Ano_Inicio, Ano_Fim)
      VALUES
      (1, 'Mundo Virtual', 'Uma série sobre realidades alternativas digitais.', 3, 'Ficção', 2018, 2021),
      (2, 'Segredos Urbanos', 'Drama policial ambientado em grandes cidades.', 5, 'Drama', 2015, 2020);

INSERT INTO Filme (ID_Filme, Titulo, Sinopse, Genero, Duracao, Data_Lancamento)
      VALUES
      (1, 'O Código Final', 'Suspense sobre um hacker em busca da verdade.', 'Suspense', '02:10:00', '2019-08-10'),
      (2, 'Sol Nascente', 'História romântica entre culturas diferentes.', 'Romance', '01:45:00', '2021-02-14');

INSERT INTO Pessoa_Atua_Serie (ID_Pessoa, ID_Serie, Funcao)
      VALUES
      (1, 1, 'Ator Principal'),
      (2, 2, 'Diretor');

INSERT INTO Pessoa_Atua_Filme (ID_Pessoa, ID_Filme, Funcao)
      VALUES (3, 1, 'Roteirista');

INSERT INTO Usuario (ID_USUARIO, Nome_Usuario, Email, Senha, Status_Solicitacao_Critico, Data_Cadastro, Tipo_Usuario)
      VALUES
      (1, 'usuario_ana', 'ana@email.com', 'senha123', FALSE, '2024-01-10', 'Critico'),
      (2, 'joao_cine', 'joao@email.com', 'senha456', TRUE, '2023-12-05', 'Comum');

INSERT INTO Avaliacao (ID_Avaliacao, ID_Usuario, Nota, Comentario, Tipo_Conteudo_Avaliacao, Data_Avalicao, ID_Conteudo_Avalicao)
      VALUES
      (1, 1, 9, 'Excelente narrativa!', 'Serie', '2024-01-15', 1),
      (2, 2, 7, 'Bom filme, mas previsível.', 'Filme', '2024-02-20', 1);

INSERT INTO Avaliacao_Serie (ID_Serie, ID_Avaliacao)
      VALUES (1, 1);

INSERT INTO Avaliacao_Filme (ID_Filme, ID_Avaliacao)
      VALUES (1, 2);


-- mostrar todas as avaliacoes de series feitas por criticos
SELECT U.Nome_Usuario, S.Titulo, A.Nota, A.Comentario
      FROM Avaliacao A
      JOIN Usuario U ON A.ID_Usuario = U.ID_Usuario
      JOIN Avaliacao_Serie AS ON A.ID_Avaliacao = AS.ID_Avaliacao
      JOIN Serie S ON AS.ID_Serie = S.ID_Serie
      WHERE U.Tipo_Usuario = 'Critico';

-- mostrar pessoas que atuaram em filme com genero suspense
SELECT P.Pnome, P.Unome, F.Titulo
      FROM Pessoa P
      JOIN Pessoa_Atua_Filme PF ON P.ID_Pessoa = PF.ID_Pessoa
      JOIN Filme F ON PF.ID_Filme = F.ID_Filme
      WHERE F.Genero = 'Suspense';

-- mostrar usuarios que fizeram avaliacoes com notas maiores que oito
SELECT U.Nome_Usuario, A.Nota, A.Comentario
      FROM Avaliacao A
      JOIN Usuario U ON A.ID_Usuario = U.ID_Usuario
      WHERE A.Nota > 8;
