package br.com.davidmachadosf.test_azul.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.davidmachadosf.test_azul.model.Endereco;

@FeignClient(name = "viacep", url = "${feign.viacep.url}", configuration=FeignConfig.class)
public interface IFeignViaCep{
	
	@GetMapping(value = "/{cep}/json/unicode/")
	Endereco consultaViaCep(@RequestHeader Map<String,String> header, @PathVariable("cep") String cep);
	
}