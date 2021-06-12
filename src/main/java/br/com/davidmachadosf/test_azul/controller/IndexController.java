package br.com.davidmachadosf.test_azul.controller;


import static br.com.davidmachadosf.test_azul.ConstantesApplication.APP_NOME;
import static br.com.davidmachadosf.test_azul.ConstantesApplication.APP_VERSAO;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.websocket.server.PathParam;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.davidmachadosf.test_azul.model.cv.Curriculum;
import br.com.davidmachadosf.test_azul.service.utils.CurriculumUtil;

@RestController
@RequestMapping(value ="")
public class IndexController {

	@GetMapping
	@RequestMapping(value ="/hello")
	public String hello() {
    	return "Hello world!! I am <"+APP_NOME+"> "+APP_VERSAO+
    		   " by David Machado Santos Filho (davidmachadosf@gmail.com).";
	}
	
	@GetMapping
	@RequestMapping(value ="/bye")
	public String bye() {
    	return "Bye bye, cruel world... :-(";
	}
	
	// alguns servicinhos uteis...
	@GetMapping
	@RequestMapping(value ="/now")
	public String now() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return "Datetime on appserver is: "+ formatter.format(new Date());
	}
	
	@GetMapping
	@RequestMapping(value ="/add")
	public String add(@PathParam("a") Long a, @PathParam("b") Long b) {
    	return a+"+"+b+"="+(a+b);
	}
	
	@GetMapping
	@RequestMapping(value ="/mult")
	public String mult(@PathParam("a") Long a, @PathParam("b") Long b) {
    	return a+"x"+b+"="+(a*b);
	}
	
	@GetMapping
	@RequestMapping(value ="/cv")
	public Curriculum curriculum() {
 		Curriculum cvDavid = new Curriculum();
 		return CurriculumUtil.populateDavidMachadoSantosFilhoData(cvDavid); 
	}
}