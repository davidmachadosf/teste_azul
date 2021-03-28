package br.com.davidmachadosf.test_azul.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb02_clientes")
@Data
public class Cliente {
	
    @Id
    String cpf;
	String nome;
    String logradouro;
    String bairro;
    String cidade;
    String estado;
    String cep;
}
