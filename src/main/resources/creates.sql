CREATE TABLE IF NOT EXISTS Pessoa 
(
    ID_Pessoa INT PRIMARY KEY,
    Pnome VARCHAR(50),
    Minicial VARCHAR(1),
    Unome VARCHAR(50),
    Nacionalidade VARCHAR(30),
    Data_Nascimento DATE
);

CREATE TABLE IF NOT EXISTS Serie
(
    ID_SERIE INT PRIMARY KEY,
    Titulo VARCHAR(50),
    Sinopse VARCHAR(255),
    QTDE_Temporadas INT,
    Genero VARCHAR(15),
    Ano_Inicio INTEGER,
    Ano_Fim INTEGER
);

CREATE TABLE IF NOT EXISTS Filme
(
    ID_Filme INT PRIMARY KEY,
    Titulo VARCHAR(50) UNIQUE,
    Sinopse VARCHAR(255) UNIQUE,
    Genero VARCHAR(15),
    Duracao TIME,
    Data_Lancamento DATE
);

CREATE TABLE IF NOT EXISTS Pessoa_Atua_Serie
(
    ID_Pessoa INT,
    ID_Serie INT,
    Funcao VARCHAR(30),
    PRIMARY KEY (ID_Pessoa, ID_Serie),
    FOREIGN KEY (ID_Pessoa) REFERENCES Pessoa(ID_Pessoa),
    FOREIGN KEY (ID_SERIE) REFERENCES Serie(ID_SERIE)
);

CREATE TABLE IF NOT EXISTS Pessoa_Atua_Filme
(
    ID_Pessoa INT,
    ID_Filme INT,
    Funcao VARCHAR(30),
    PRIMARY KEY (ID_Pessoa, ID_Filme),
    FOREIGN KEY (ID_Pessoa) REFERENCES Pessoa(ID_Pessoa),
    FOREIGN KEY (ID_Filme) REFERENCES Filme(ID_Filme)
);

CREATE TABLE IF NOT EXISTS Usuario
(
    ID_USUARIO INT PRIMARY KEY,
    Nome_Usuario VARCHAR(255) UNIQUE,
    Email VARCHAR(255) UNIQUE,
    Senha VARCHAR(255),
    Status_Solicitacao_Critico Boolean,
    Data_Cadastro Date,
    Tipo_Usuario VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS Avaliacao
(
    ID_Avaliacao INT PRIMARY KEY,
    ID_Usuario INT,
    Nota INT,
    Comentario VARCHAR(255),
    Tipo_Conteudo_Avaliacao VARCHAR(10),
    Data_Avaliacao DATE,
    ID_Conteudo_Avaliacao INT,
    FOREIGN KEY (ID_Usuario) REFERENCES Usuario(ID_Usuario)
);

CREATE TABLE IF NOT EXISTS Avaliacao_Serie
(
    ID_Serie INT,
    ID_Avaliacao INT,
    PRIMARY KEY(ID_Serie, ID_Avaliacao),
    FOREIGN KEY (ID_Serie) REFERENCES Serie(ID_Serie),
    FOREIGN KEY (ID_Avaliacao) REFERENCES Avaliacao(ID_Avaliacao)
);

CREATE TABLE IF NOT EXISTS Avaliacao_Filme
(
    ID_Filme INT,
    ID_Avaliacao INT,
    PRIMARY KEY(ID_Filme, ID_Avaliacao),
    FOREIGN KEY (ID_Filme) REFERENCES Filme(ID_Filme),
    FOREIGN KEY (ID_Avaliacao) REFERENCES Avaliacao(ID_Avaliacao)
);
