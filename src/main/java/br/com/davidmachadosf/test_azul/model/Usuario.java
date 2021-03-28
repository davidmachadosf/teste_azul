package br.com.davidmachadosf.test_azul.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb01_usuarios")
@Data
public class Usuario {
	
    @Id
    String login;  // login do usuário (chave)
    String roles;  // permissões de acesso, separadas por vírgula   
	String hash;   // hash da senha de usuário
}
