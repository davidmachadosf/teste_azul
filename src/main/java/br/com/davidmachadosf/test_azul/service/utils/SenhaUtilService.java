package br.com.davidmachadosf.test_azul.service.utils;

import static br.com.davidmachadosf.test_azul.model.enums.RoleType.ADMIN;
import static br.com.davidmachadosf.test_azul.model.enums.RoleType.OWNER;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.davidmachadosf.test_azul.interceptor.annotation.RolesAutorizados;
import br.com.davidmachadosf.test_azul.model.Usuario;
import br.com.davidmachadosf.test_azul.model.enums.RoleType;
import br.com.davidmachadosf.test_azul.repository.UsuarioRepository;

@Service
@RestController
public class SenhaUtilService extends SenhaUtil{
	
	 @Autowired
	 private UsuarioRepository repository;
	 
	 public boolean verificaToken(String token, List<RoleType> callerRoles, String loginParam) throws Exception {
		 try {
			verificaToken(token, repository, callerRoles, loginParam);
         }
         catch(Exception ex) {
         	return false;
         }
     	
     	return true;
	 }
	 
    @GetMapping("/auth/{login}/{senha}")
    public ResponseEntity<String> auth(@PathVariable String login, @PathVariable String senha) {
    	try{
	        if(verificaSenha(getHash(login), senha)) {    	
	        	return ok().body(geraToken(login, senha));
			} 
    	}
    	catch (Exception ex) {
		    return status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}        
        return status(HttpStatus.INTERNAL_SERVER_ERROR).body("Autenticação inválida![0]");
    }

	

    @RolesAutorizados({ADMIN,OWNER})
    @PatchMapping("/alterasenha/{login}/{novaSenha}")
    public ResponseEntity<String> alteraSenha(@PathVariable String login, @PathVariable String novaSenha) {        
        try {
            Usuario usuario = getUsuario(login);
            usuario.setHash(geraHashNovoParaSenha(novaSenha));
            repository.save(usuario);        	
			return ok().body("Senha alterada.");
		} 
    	catch (Exception ex) {
    		return status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}        
    }
    
    @RolesAutorizados({ADMIN})
    @GetMapping("/verificasenha/{login}/{senha}")
    public ResponseEntity<String> verificaSenhaService(@PathVariable String login, @PathVariable String senha) {
        try {
            return ok().body(""+verificaSenha(getHash(login), senha));
		} 
    	catch (Exception ex) {
    		return status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
    }
    
    private Usuario getUsuario(String login) throws Exception {
		Usuario usuario = repository.getByLogin(login);
  		if(null==usuario) {
			throw new Exception("Usuário inexistente!");
		}
		return usuario;
	}
    
    private String getHash(String login) throws Exception {
		return getUsuario(login).getHash();
	}
}
