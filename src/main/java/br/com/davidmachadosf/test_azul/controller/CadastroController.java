package br.com.davidmachadosf.test_azul.controller;

import static br.com.davidmachadosf.test_azul.ConstantesApplication.APP_NOME;
import static br.com.davidmachadosf.test_azul.ConstantesApplication.APP_TITULO;
import static br.com.davidmachadosf.test_azul.ConstantesApplication.APP_VERSAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.davidmachadosf.test_azul.model.Cliente;
import br.com.davidmachadosf.test_azul.repository.ClienteRepository;
import br.com.davidmachadosf.test_azul.repository.UsuarioRepository;

@Controller
public class CadastroController {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	String[][] links = {
		{"pesquisacep.html",    "Testar ViaCEP"},
	    {"listUsuarios",  "Usuários do Sistema"},
		{"listClientes", "Clientes Cadastrados"},
		{"editCliente","Cadastrar Novo Cliente"},
	};

	@RequestMapping("/main")
	public String database(Model model) {
		model.addAttribute("titulo", APP_TITULO);
		model.addAttribute("nomeVersao", APP_NOME + " (" + APP_VERSAO + ")");
		model.addAttribute("links", links);
		return "main";
	}

	@RequestMapping("/listUsuarios")
	public String findUsuarios(Model model) {
		model.addAttribute("usuarios", usuarioRepo.findAll());
		return "listUsuarios";
	}

	@RequestMapping("/listClientes")
	public String findClientes(Model model) {
		model.addAttribute("clientes", clienteRepo.findAll());
		return "listClientes";
	}

	@RequestMapping(value = "/editCliente/{cpf}")
	public String editCliente(Model model, @PathVariable String cpf) {
		if(null!=cpf) {
			model.addAttribute("cliente", clienteRepo.findById(cpf));
		}
		return "editCliente";
	}
	
	@RequestMapping(value = "/deleteCliente/{cpf}")
	public String deleteCliente(@PathVariable String cpf) {
		clienteRepo.deleteById(cpf);
		return "redirect:/listClientes";
	}
	
	@RequestMapping(value = "/saveCliente", method = RequestMethod.POST)
	public String saveCliente(@ModelAttribute("cliente") Cliente cliente) {
		clienteRepo.save(cliente);
		return "redirect:/listClientes";
	}

		
}