package br.com.davidmachadosf.test_azul.model;

import lombok.Data;

//@Entity
//@SequenceGenerator(name = "seq_endereco", sequenceName = "seq01_enderecos", allocationSize = 1, initialValue = 1)
//@Table(name = "tb03_enderecos")
@Data
public class Endereco {
	
	//@Id 
	//@GeneratedValue(generator = "seq_endereco") 
	Long id;
	
	String cep;
	String logradouro;
	String bairro;
	String localidade;
	String uf;
	
	// campos adicionais retornados pelo VIaCEP, mas que não são usados aqui
	//String ibge;
	//String gia;
	//String ddd;
	//String siafi;
}
