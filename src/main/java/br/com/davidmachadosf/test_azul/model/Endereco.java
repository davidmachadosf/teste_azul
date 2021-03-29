package br.com.davidmachadosf.test_azul.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb03_enderecos")
@Data
public class Endereco {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	int id;
	
	String cep;
	String logradouro;
	String complemento;
	String bairro;
	String localidade;
	String uf;
	String ibge;
	String gia;
	String ddd;
	String siafi;
}
