package br.com.davidmachadosf.test_azul.controller;

import static br.com.davidmachadosf.test_azul.ConstantesApplication.APP_NOME;
import static br.com.davidmachadosf.test_azul.ConstantesApplication.APP_TITULO;
import static br.com.davidmachadosf.test_azul.ConstantesApplication.APP_VERSAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.davidmachadosf.test_azul.service.IClienteService;
import br.com.davidmachadosf.test_azul.service.IUsuarioService;

@Controller
public class CadastroController {

    @Autowired
    private IUsuarioService usuarioService;
    
    @Autowired
    private IClienteService clienteService;

    @GetMapping("/database")
    public String database(Model model) {
    	model.addAttribute("titulo", APP_TITULO);
    	model.addAttribute("nomeVersao", APP_NOME+" ("+APP_VERSAO+")");
    	return "database";
    }
    
    @GetMapping("/showUsuarios")
    public String findUsuarios(Model model) {
    	model.addAttribute("usuarios", usuarioService.findAll());
        return "showUsuarios";
    }

    @GetMapping("/showClientes")
    public String findClientes(Model model) {
        model.addAttribute("clientes", clienteService.findAll());
        return "showClientes";
    }
}