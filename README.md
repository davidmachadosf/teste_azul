
# Teste azul (v1.1.2)


## Código Fonte (GitHub)

O código fonte da aplicação foi disponibilizado no GitHub pelo link: 

https://github.com/davidmachadosf/test_azul.git

## Deploy (Heroku)

Configurou-se o github para que tono novo commit na branch master automaticamente publice a versão recente da aplicação na plataforma Heroku. A homepage web da aplicação, uma página simples para conferência dos dados gravados na base de dados, pode ser acessada pela url:

https://test-azul.herokuapp.com/

## Checagem de status (Actuator)

Pde ser feita uma checagem básica do status da aplicação pelos seguintes endpoints gerados pela ferramenta *Actuator*:

https://test-azul.herokuapp.com/actuator/health - verifica se a aplicação iniciou sem problemas

https://test-azul.herokuapp.com/actuator/env - exibe todas as variáveis do ambiente onde se encontra a aplicação

## Auxílio de desenvolvimento

Durante o desenvolvimento o conteúdo da base de dados pode ser verificado em:

https://test-azul.herokuapp.com/showUsuarios - exibe o conteúdo da tabesa de usuários do sistema

https://test-azul.herokuapp.com/showClientes - exibe conteúdo da tabela de clientes cadastrados

Optou-se por não se colocar verificações de segurança nesta página por ser apenas uma conferência de dados para a fase de desenvolvimento da aplicvação, Isto pode ser feito posteriormente ou pode-se eliminar completamente tais páginas, que só são úteis nesta fase.


## Base de Dados

Utilizou-se uma base de dados PostGres também hospedada na plataforma Heroku, como a aplicação. Decidiu-se por criar uma base de dados volátil, ou seja, ela é apagada e recriada após cada restart da aplicação, comportamento que pode ser facilmente alterado se necessário.

Neste exemplo simples a base de dados é composta de apenas duas tabelas: 

`tb01_usuarios` 

Login (chave primária) dos usuários cadastrados no sistema, bem como o hash de suas senhas (as senhas não são armazenada como texto legível na base) e uma lista de Autorizações (Roles) de cada usuário, que controla quais os serviços ele pode acessar. A estrutura desta tabela é a seguinte:

campo | tipo | chave 
----- | ---- | ------
login | VARCHAR( 10) | PRIMARY KEY
roles | VARCHAR( 50) |
hash  | VARCHAR(255) |

`tb02_clientes`

Clientes cadastrados no sistema pelos usuários habilitados para isto (com Role=EDIT, vide item seguinte), com número de CPF (chave primária), nome e dados de endereço composto de logradouro, bairro, cidade, estado (sigla) e CEP. A estrutura desta tabela na base é a seguinte:

campo | tipo | chave 
----- | ---- | ------
cpf        | VARCHAR(11) | PRIMARY KEY
nome       | VARCHAR(50) |
logradouro | VARCHAR(50) |
bairro     | VARCHAR(50) |
cidade     | VARCHAR(50) |
estado     | VARCHAR( 2) |
cep        | VARCHAR( 9) |


## Autorizações de acesso aos serviços

As autorizações de acesso que podem ser atribuidas aos usuários são os seguintes:

`ADMIN` tem permissão para:
>* cadastrar usuários novos
>* alterar a senha de qualquer usuário (incluindo sua própria)
>* alterar nome e autorizações de acess de acesso de usuários
>* deletar usuários
>* procurar e visualizar usuarios

`EDIT` tem permissão para:
>* alterar a própria senha 
>* cadastrar clientes novos
>* alterar dados clientes
>* deletar clientes 

`VIEW` tem permissão para:
>* alterar a própria senha 
>* procurar e visualizar  clientes 

`OWNER` tem permissão para:
>* indica que usuário só pode acessar o serviço se estiver alterando seus próprios dados



## Serviço de obtenção de token

Para acessar os serviços que requerem autorizações de acesso é necessário, inicialmente, adquirir um token fornecendo login e senha para o serviço (trocando *login* e *senha* na URI abaixo pelo login e senha do usuário):

>* `GET` https://test-azul.herokuapp.com/auth/LOGIN_DO_USUARIO/SENHA

Será devolvido um token, que deve ser incluido no Header das requisições. Isto será explicado posteriormente, nas instruções de como testar a API Rest da aplicação:
```
{
  "Token": "zgb6FNPeClwKodkIuIRoQvCDVxaC2v0JCysTC6fIlk9/FCvrh7Z8pkkPXIlg4YFf"
}
```

Estão cadastrados inicialmente os seguintes usuários com as seguintes autorizações de acesso:

Login  | Roles	
------ | ------
god    | ADMIN,EDIT,VIEW
admin  | ADMIN
edit01 | EDIT
user01 | VIEW
user02 | VIEW


O hash inicialmente cadastrados para todos estes usuários corresponde à senha **abracadabra**.

Foi criado um usuário de login **god** para testes de desenvolvimento, com permissão cadastrada para todas as Roles possíveis. Na prática, ele está autorizado a chamar tdos os serviços REST do sistema, se gerar um token de autenticação com seu login e senha. 

Os tokens gerados expiram depois de 2 horas e 30 minutos, e depois deste período será necessário solicitar um novo token para continuar usando os serviços.


## Serviços de alteração e verificação de senhas

Deve ser feita uma requisição com o método **PATCH** para anteração de senha. Usuários com autorização de ``ADMIN`` podem alterar a senha de qualquer usuário. Usuários sem o perfil de ``ADMIN`` só podem alterar suas próprias senhas. Dada a natureza do armazenamento das senhas como um *hash* adicionado a um *salt* randômico, a única maneira de se recuperar uma senha esquecida é um usuário com perfil ``ADMIN`` inicialmente redefinindo a senha do usuário com alguma conhecida e este, posteriormente, obter um token com esta senha e alterá-la como desejar. A seguinte chamada é um exemplo de redifição de senha do usuário *edit01*:

>* `POST` https://test-azul.herokuapp.com/alterasenha/edit01/abretesézamo

Também existe um serviço para checar a senha de um usuário, util na fase de desenvolvimento. Este é um exemplo de senha validada corretamente:

>* `GET` https://test-azul.herokuapp.com/verificasenha/edit01/abretesézamo
```
true
```

Exemplo de senha incorreta:

>* `GET` https://test-azul.herokuapp.com/verificasenha/edit01/abracadabra
```
false
```


Exemplo de validação de senha de um usuário inexistente no sistema:

>* `GET` https://test-azul.herokuapp.com/verificasenha/ninguem/xxxxxxx
```
Usuário Inexistente!
```

 



## Serviços de criação/substituição

Inclusão ou substituição de registros, baseados nas respectivas chaves primárias de cada entidade, são feitas com uma chamada REST utilizando o método **POST**. Deve ser preenchido no *body* da chamada uma estrutura json com os dados a serem inseridos ou substituidos. Esta operação substitui integralmente todos os campos, e campos não fornecidos na requisição são enviados como *NULL*.   

`usuarios`
>* `POST` https://test-azul.herokuapp.com/usuarios
```
{
    "login": "ze001",
    "roles": "EDIT,VIEW",
    "hash": "12121212"
}
```

`clientes`
>* `POST` https://test-azul.herokuapp.com/clientes
```
{
    "cpf": "00000000015",
    "nome": "Zé 0555",
    "logradouro": "Rua Comprida, 300000",
    "bairro": "Vila Sézamo",
    "cidade": "Taubaté",
    "estado": "SP",
    "cep": "05000-002"
}    
```

## Serviços de alteração de campos

Alterações em registros que não sejam chaves primárias são feitas com uma chamada REST utilizando o método **PATCH**. Deve ser preenchido no *body* da chamada uma estrutura json com os dados a serem alterados e informado na URI a chave primária do registro. Neste caso não há necessidade de enviar todos os campos: campos que não forem fornecidos na estrutura não serão alterados.   

`usuarios`
>* `PATCH` https://test-azul.herokuapp.com/usuarios/LOGIN_DO_USUARIO
```
{
    "roles": "ADMIN,VIEW"
}
```

`clientes`
>* `PATCH` https://test-azul.herokuapp.com/clientes/CPF_DO_CLIENTE
```
{
    "bairro": "Morro do Macaco",
    "cidade": "Pindamonhangaba",
    "cep": "04900-002"
} 
```

## Serviços de remoção de registros

Remoções de registros são feitas com uma chamada REST utilizando o método **DEL**. A chave primária do registro a ser deletado é passada na URI de chamada.  

`usuarios`
>* `DEL` https://test-azul.herokuapp.com/usuarios/LOGIN_DO_USUARIO

`clientes`
>* `DEL` https://test-azul.herokuapp.com/clientes/CPF_DO_CLIENTE


## Serviços de busca
Alguns dos métodos de pesquisa comuns à interface JPA foram expostos como serviços rest. Podemos obter uma listagem de todos os métodops de busca expostos pela chamada:

>* https://test-azul.herokuapp.com/usuarios/search - para busca de usuários
>* https://test-azul.herokuapp.com/clientes/search - para busca de clientes

**OBS.:** *como todas são requisições usando o método* ``GET`` *elas podem ser testadas normalmente em qualquer browser, não há necessidade de ferramentas específicas como o Postman nestes casos.* 

Estão disponíveis as seguintes buscas de usuários:

>Consulta usuários pelo login
>* ``GET``  https://test-azul.herokuapp.com/usuarios/search/getByLogin?login=admin

>Consulta usuários por autorização de acesso
>* ``GET``  https://test-azul.herokuapp.com/usuarios/search/findByRolesContainingIgnoreCase?role=view

Estão disponíveis as seguintes buscas de clientes:

>Consulta cliente pelo CPF
>* ``GET``  https://test-azul.herokuapp.com/clientes/00000000005

>Consulta nome pelo CPF
>* ``GET``  https://test-azul.herokuapp.com/clientes/search/findNomeByCpf?cpf=00000000003

>Consulta clientes homônimos pelo nome
>* ``GET``  https://test-azul.herokuapp.com/clientes/search/findByNomeIgnoreCase?nome=chica

>Consulta clientes por parte do nome
>* ``GET``  https://test-azul.herokuapp.com/clientes/search/findByNomeContainingIgnoreCaseOrderByNome?busca=zé

>Consulta clientes por parte do logradouro
>* ``GET``  https://test-azul.herokuapp.com/clientes/search/findByLogradouroContainingIgnoreCaseOrderByLogradouro?busca=30

>Consulta clientes por parte do bairro
>* ``GET``  https://test-azul.herokuapp.com/clientes/search/findByBairroContainingIgnoreCaseOrderByBairro?busca=vila

>Consulta clientes por parte da cidade
>* ``GET``  https://test-azul.herokuapp.com/clientes/search/findByCidadeContainingIgnoreCaseOrderByCidade?busca=au

>Consulta clientes por estado
>* ``GET``  https://test-azul.herokuapp.com/clientes/search/findByEstadoIgnoreCase?uf=am

>Consulta clientes por CEP
>* ``GET``  https://test-azul.herokuapp.com/clientes/search/findByCep?cep=05000-002


## Instruções para teste no Postman

* Baixar o arquivo [Testes Azul.postman_collection.json](https://github.com/davidmachadosf/test_azul/blob/master/src/test/postman/Testes%20Azul.postman_collection.json) e importar como uma colection do Postman:

![Imagem 1](Clipboard-1.jpg)

* Criar um ambiente "Heroku" e setar nele a variável **host** com o valor *https://test-azul.herokuapp.com*:

![Imagem 2](Clipboard-2.jpg)

* Obter um token para conseguir utilizar os serviços. Para testes foi criado o usuário *god* de senha *abracadabra* com acesso irrestrito a todos os serviços da aplicação:

![Imagem 3](Clipboard-3.jpg)

* Abrir a aba da colection *Testes azul*. Na área de *Authorization*, escolher o type **API Key** e inserir o token recebido no passo anterior. Este token será enviado em todas as requisições da collection, e expira em 2h30.

![Imagem 4](Clipboard-4.jpg)

* As principais consultas, updates e remoções estão pré-configuradas na collection, separadas por pastas. Escolha o request alterando os parâmetros conforme o teste que se deseja efetuar:

![Imagem 5](Clipboard-5.jpg)
 
 
