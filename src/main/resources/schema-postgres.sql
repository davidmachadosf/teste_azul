DROP TABLE IF EXISTS tb01_usuarios;

CREATE TABLE tb01_usuarios(
	login VARCHAR(10) PRIMARY KEY, 
	roles VARCHAR(50), 
	hash VARCHAR(255));


DROP TABLE IF EXISTS tb02_clientes;

CREATE TABLE tb02_clientes(
	cpf VARCHAR(11) PRIMARY KEY, 
	nome VARCHAR(60), 
	email VARCHAR(60),
    cep VARCHAR(9), 
	logradouro VARCHAR(60), 
	bairro VARCHAR(40), 
	localidade VARCHAR(40), 
	uf VARCHAR(2));


--DROP TABLE IF EXISTS tb03_enderecos;

--CREATE TABLE tb03_enderecos(
--	id INT PRIMARY KEY,
--    cep VARCHAR(9), 
--	logradouro VARCHAR(60), 
--	bairro VARCHAR(40), 
--	localidade VARCHAR(40), 
--	uf VARCHAR(2));
	
	
--DROP SEQUENCE IF EXISTS seq01_enderecos;

--CREATE SEQUENCE seq01_enderecos
--MINVALUE 1
--MAXVALUE 9999999999999999
--START WITH 1
--INCREMENT BY 1;