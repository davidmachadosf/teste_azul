DROP TABLE IF EXISTS tb01_usuarios;

CREATE TABLE tb01_usuarios(
	login VARCHAR(10) PRIMARY KEY, 
	roles VARCHAR(50), 
	hash VARCHAR(255));


DROP TABLE IF EXISTS tb02_clientes;

CREATE TABLE tb02_clientes(
	cpf VARCHAR(11) PRIMARY KEY, 
	nome VARCHAR(50), 
	logradouro VARCHAR(50), 
	email VARCHAR(50),
	endereco_id INT);


DROP TABLE IF EXISTS tb03_enderecos;

CREATE TABLE tb03_enderecos(
	id INT GENERATED ALWAYS AS IDENTITY,
    cep VARCHAR(9), 
	logradouro VARCHAR(50), 
	complemento VARCHAR(50), 
	bairro VARCHAR(50), 
	localidade VARCHAR(50), 
	uf VARCHAR(2), 
	ibge VARCHAR(10), 
	gia VARCHAR(10), 
	ddd VARCHAR(3), 
	siafi VARCHAR(10));