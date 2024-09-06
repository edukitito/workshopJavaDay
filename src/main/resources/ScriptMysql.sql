create database workshopCrud

use workshopCrud

CREATE TABLE usuarios (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    senha VARCHAR(255),
    telefone VARCHAR(30) UNIQUE,
    cpf VARCHAR(30) UNIQUE,
    endereco VARCHAR(255),
    cidade VARCHAR(100),
    estado VARCHAR(100),
    nickname VARCHAR(100) UNIQUE,
    ativo BIT DEFAULT 1
);

CREATE TABLE ongs (
    ongid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) UNIQUE,
    descricao TEXT,
    email VARCHAR(255) UNIQUE,
    telefone VARCHAR(30) UNIQUE,
    endereco VARCHAR(255),
    cidade VARCHAR(100),
    estado VARCHAR(100),
    cnpj VARCHAR(50) UNIQUE,
    pix VARCHAR(100) UNIQUE,
    senha VARCHAR(255),
    ativo BIT DEFAULT 1
);

CREATE TABLE proprietarios (
    id INT NOT NULL,
    tipo VARCHAR(30) NOT NULL,
    PRIMARY KEY (id, tipo)
);

CREATE TABLE animais (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    raca VARCHAR(100),
    descricao TEXT,
    idade INT,
    tipo VARCHAR(50),
    sexo VARCHAR(20),
    imagem LONGBLOB,
    proprietario_Tipo VARCHAR(30), -- Indica se o proprietário é um usuário ou uma ONG
    proprietario_Id INT,
    ativo BIT DEFAULT 1,
    CONSTRAINT fk_proprietarios
        FOREIGN KEY (proprietario_Id, proprietario_Tipo)
        REFERENCES proprietarios(id, tipo)
);

CREATE TABLE adocoes (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    animal_id INT NOT NULL,
    ong_id INT NOT NULL,
    data_adocao DATE NOT NULL,
    etapa_adocao VARCHAR(50),
    status_adocao VARCHAR(40),
    ativo BIT DEFAULT 1,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (animal_id) REFERENCES animais(id),
    FOREIGN KEY (ong_id) REFERENCES ongs(ongid)
);

-- Views para acessar apenas entidades ativas
CREATE VIEW vw_usuarios_ativos AS
SELECT * FROM usuarios
WHERE ativo = 1;

CREATE VIEW vw_ongs_ativas AS
SELECT * FROM ongs
WHERE ativo = 1;

CREATE VIEW vw_animais_ativos AS
SELECT * FROM animais
WHERE ativo = 1;

CREATE VIEW vw_adocoes_ativas AS
SELECT * FROM adocoes
WHERE ativo = 1;

-- Triggers para gerenciar a inativação de entidades

DELIMITER //

CREATE TRIGGER trg_InativarUsuario
    AFTER UPDATE ON usuarios
    FOR EACH ROW
BEGIN
    IF OLD.ativo = 1 AND NEW.ativo = 0 THEN
        -- Inativar adoções do usuário
        UPDATE adocoes
        SET ativo = 0, status_adocao = 'CANCELADA'
        WHERE usuario_id = NEW.id AND status_adocao != 'CONCLUIDA';

        -- Inativar animais do usuário
        UPDATE animais
        SET ativo = 0
        WHERE proprietario_Id = NEW.id AND proprietario_Tipo = 'usuario';
    END IF;
END //

CREATE TRIGGER trg_InativarONG
    AFTER UPDATE ON ongs
    FOR EACH ROW
BEGIN
    IF OLD.ativo = 1 AND NEW.ativo = 0 THEN
        -- Inativar animais da ONG
        UPDATE animais
        SET ativo = 0
        WHERE proprietario_Id = NEW.ongid AND proprietario_Tipo = 'ong';

        -- Inativar adoções da ONG
        UPDATE adocoes
        SET ativo = 0, status_adocao = 'CANCELADA'
        WHERE ong_id = NEW.ongid AND status_adocao != 'CONCLUIDA';
    END IF;
END //

CREATE TRIGGER trg_InativarAnimal
    AFTER UPDATE ON animais
    FOR EACH ROW
BEGIN
    IF OLD.ativo = 1 AND NEW.ativo = 0 THEN
        -- Inativar adoções relacionadas ao animal
        UPDATE adocoes
        SET ativo = 0, status_adocao = 'CANCELADA'
        WHERE animal_id = NEW.id AND status_adocao != 'CONCLUIDA';
    END IF;
END //

CREATE TRIGGER trg_AtualizarProprietarioAdocao
    AFTER UPDATE ON adocoes
    FOR EACH ROW
BEGIN
    IF NEW.status_adocao = 'CONCLUIDA' THEN
        UPDATE animais
        SET proprietario_Id = NEW.usuario_id, proprietario_Tipo = 'usuario'
        WHERE animal_id = NEW.animal_id;
    END IF;
END //

-- Triggers para inserir registros na tabela proprietarios

CREATE TRIGGER trg_InsertProprietarioUsuario
    AFTER INSERT ON usuarios
    FOR EACH ROW
BEGIN
    INSERT INTO proprietarios (id, tipo)
    VALUES (NEW.id, 'usuario');
END //

CREATE TRIGGER trg_InsertProprietarioONG
    AFTER INSERT ON ongs
    FOR EACH ROW
BEGIN
    INSERT INTO proprietarios (id, tipo)
    VALUES (NEW.ongid, 'ong');
END //

DELIMITER ;