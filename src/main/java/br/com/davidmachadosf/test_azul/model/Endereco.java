package br.com.davidmachadosf.test_azul.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@SequenceGenerator(name = "seq_endereco", sequenceName = "seq01_enderecos", allocationSize = 1, initialValue = 1)
@Table(name = "tb03_enderecos")
@Data
public class Endereco {
	
	@Id 
	@GeneratedValue(generator = "seq_endereco") 
	Long id;
	
	String cep="04";
	String logradouro="05";
	String bairro="06";
	String localidade="07";
	String uf="08";
	//String ibge;
	//String gia;
	//String ddd;
	//String siafi;
}
