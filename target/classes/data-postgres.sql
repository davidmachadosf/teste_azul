-- administrador com capacidade de edição e visualização
INSERT INTO tb01_usuarios(login, roles, hash) VALUES('god','ADMIN,EDIT,VIEW','6jOp0qs0q+h3pGq7yQGNrA==:ChYIe0KpYRY+/sQgB29kfwHaL6F39uwvYWs8q9243xfK3OE1Do43SBW4osEYeUY/0ciOkZAxBYKCcMK6Z7g0lg==');
-- administrador de usuarios
INSERT INTO tb01_usuarios(login, roles, hash) VALUES('admin',  'ADMIN','6jOp0qs0q+h3pGq7yQGNrA==:ChYIe0KpYRY+/sQgB29kfwHaL6F39uwvYWs8q9243xfK3OE1Do43SBW4osEYeUY/0ciOkZAxBYKCcMK6Z7g0lg==');
-- cadastradores de clientes
INSERT INTO tb01_usuarios(login, roles, hash) VALUES('edit01', 'EDIT', '6jOp0qs0q+h3pGq7yQGNrA==:ChYIe0KpYRY+/sQgB29kfwHaL6F39uwvYWs8q9243xfK3OE1Do43SBW4osEYeUY/0ciOkZAxBYKCcMK6Z7g0lg==');
-- usuários com permissão de consultar clientes
INSERT INTO tb01_usuarios(login, roles, hash) VALUES('user01', 'VIEW', '6jOp0qs0q+h3pGq7yQGNrA==:ChYIe0KpYRY+/sQgB29kfwHaL6F39uwvYWs8q9243xfK3OE1Do43SBW4osEYeUY/0ciOkZAxBYKCcMK6Z7g0lg==');
INSERT INTO tb01_usuarios(login, roles, hash) VALUES('user02', 'VIEW', '6jOp0qs0q+h3pGq7yQGNrA==:ChYIe0KpYRY+/sQgB29kfwHaL6F39uwvYWs8q9243xfK3OE1Do43SBW4osEYeUY/0ciOkZAxBYKCcMK6Z7g0lg==');
-- OBS.: o hash default corresponde à senha incial "abracadabra", que pode ser alterada posteriormente



-- insere alguns clientes iniciais para testes de desenvolvimento
INSERT INTO tb02_clientes(cpf,nome,logradouro,bairro,cidade,estado,cep) VALUES('00000000001','Zé 01','Rua Comprida, 100000','Vila Sézamo',    'São Paulo', 'SP','05000-001');
INSERT INTO tb02_clientes(cpf,nome,logradouro,bairro,cidade,estado,cep) VALUES('00000000002','Zé 02','Rua Comprida, 150000','Vila Sézamo',    'São Paulo', 'SP','05000-001');
INSERT INTO tb02_clientes(cpf,nome,logradouro,bairro,cidade,estado,cep) VALUES('00000000003','Zé 03','Rua Comprida, 200000','Vila Sézamo',    'Campinas',  'SP','05000-001');
INSERT INTO tb02_clientes(cpf,nome,logradouro,bairro,cidade,estado,cep) VALUES('00000000004','Zé 04','Rua Comprida, 250000','Vila Sézamo',    'Taubaté',   'SP','05000-002');
INSERT INTO tb02_clientes(cpf,nome,logradouro,bairro,cidade,estado,cep) VALUES('00000000005','Zé 05','Rua Comprida, 300000','Vila Sézamo',    'Taubaté',   'SP','05000-002');
INSERT INTO tb02_clientes(cpf,nome,logradouro,bairro,cidade,estado,cep) VALUES('00000000006','Chica','Rua Estreita, 30','Vila do Sossego',    'Rio Branco','AC','09000-999');
INSERT INTO tb02_clientes(cpf,nome,logradouro,bairro,cidade,estado,cep) VALUES('00000000007','Chica','Rua Larga, 222', 'Bairro das Tormentas','Manaus',    'AM','07000-989');
INSERT INTO tb02_clientes(cpf,nome,logradouro,bairro,cidade,estado,cep) VALUES('00000000008','João' ,'Alameda Verde, 30','Jardim das Flores', 'Belém',     'PA','08000-979');