package br.com.davidmachadosf.test_azul.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.rest.core.annotation.RestResource;

import lombok.Data;

@Entity
@Table(name = "tb02_clientes")
@Data
public class Cliente {
	
    
    //public Cliente() {
	//	this.endereco = new Endereco();;
	//}

	@Id
    String cpf;
    String nome;
    String email;
    
    String cep;
	String logradouro;
	String bairro;
	String localidade;
	String uf;
    
	/*
	 * @OneToOne(cascade=CascadeType.PERSIST)
	 * 
	 * @JoinColumn(name = "endereco_id")
	 * 
	 * @RestResource(path = "endereco", rel="endereco") Endereco endereco;
	 */    
}
