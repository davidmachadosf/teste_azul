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
	
    
    public Cliente() {
		this.endereco = new Endereco();;
	}

	@Id
    String cpf="1";
    String nome="2";
    String email="3";
    
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "endereco_id")
    @RestResource(path = "endereco", rel="endereco")
    Endereco endereco;    
}
