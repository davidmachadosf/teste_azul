package br.com.davidmachadosf.test_azul.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Request;

@Configuration
public class FeignConfig {
	
	private static int SEGUNDOS = 1000000; /* qtdde de microsegundos num segundo */
	private static int CONNECT_TIMEOUT = 60*SEGUNDOS;
	private static int READ_TIMEOUT = 60*SEGUNDOS;
	
	@Bean
	public Request.Options options() {
		return new Request.Options(CONNECT_TIMEOUT, READ_TIMEOUT);
	}
}
