package br.com.davidmachadosf.test_azul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import br.com.davidmachadosf.test_azul.model.Cliente;
import br.com.davidmachadosf.test_azul.model.Endereco;
import br.com.davidmachadosf.test_azul.model.Usuario;



@SpringBootApplication
@EnableFeignClients
public class StartApplication implements RepositoryRestConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}
	
	// configura JPA Data REST para exibir os ids das entidades nos responses
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Usuario.class);
        config.exposeIdsFor(Cliente.class);
        config.exposeIdsFor(Endereco.class);
    }

}