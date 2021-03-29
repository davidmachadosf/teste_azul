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
