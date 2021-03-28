package br.com.davidmachadosf.test_azul.interceptor;
import static br.com.davidmachadosf.test_azul.ConstantesApplication.HEADER_KEY_INTERCEPTOR;
import static br.com.davidmachadosf.test_azul.ConstantesApplication.HEADER_KEY_TOKEN;
import static br.com.davidmachadosf.test_azul.model.enums.RoleType.OWNER;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import br.com.davidmachadosf.test_azul.interceptor.annotation.RolesAutorizados;
import br.com.davidmachadosf.test_azul.model.enums.RoleType;
import br.com.davidmachadosf.test_azul.service.utils.SenhaUtil;
import br.com.davidmachadosf.test_azul.service.utils.SenhaUtilService;

@Component
public class TokenInterceptor 
implements HandlerInterceptor {
	
	@Autowired
	SenhaUtilService service;

	// este pre-handle será chamado antes de todos os requests, para checar se usuário tem autorização de acesso
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
    throws Exception {

    	boolean autorizado = true;
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("handler("+handler.getClass().getName()+")->");
    	
    	System.out.println(
    		"\n***************************************"+
    		"\n SCHEME: "+request.getScheme()+
    		"\n METHOD: "+request.getMethod()+
    	    "\n CONTEXT:"+request.getContextPath()+
    	    "\n SERVCTX: "+request.getServletContext() +
    	    "\n PATH:   "+request.getPathInfo()+
    	    "\n REALPATH:"+request.getRealPath(request.getPathInfo())+
    	    "\n SERVPATH:"+request.getServletPath()+
    	    "\n QUERY:  "+request.getQueryString()+
    		"\n HANDLER:"+handler.toString());
    		
        
        if(!(handler instanceof HandlerMethod)) return true;
    	
    	final RolesAutorizados rolesAutorizados = ((HandlerMethod)handler).getMethod().getAnnotation((RolesAutorizados.class));

        // se tem a anotation, processa!
        if(rolesAutorizados != null){
        	
        	List<RoleType> callerRoles = Arrays.asList(rolesAutorizados.value());
        	sb.append("RolesAutorizados("+SenhaUtil.rolesToString(callerRoles)+")->");
        	System.out.println("ROLES: "+SenhaUtil.rolesToString(callerRoles));
        	
        	String token = request.getHeader(HEADER_KEY_TOKEN);
        	sb.append("token("+token+")->");
        	
        	// se Roles do handlre contém o OWNER, preciso descobrir quem é o login sendo consultado/alterado
        	String loginParam = null;
        	if(callerRoles.contains(OWNER)) {
        		loginParam = extractLoginParam(request,sb);
            	sb.append("loginParam("+loginParam+")->");
            	System.out.println("LOGIN: "+loginParam);
        	}
        	
        	// verifica se token dá autorização para acessar o serviço        	
        	autorizado = service.verificaToken(token, callerRoles, loginParam);
        }

        sb.append(autorizado);
        response.addHeader(HEADER_KEY_INTERCEPTOR, sb.toString());
        
        //System.out.	println(sb.toString());
        System.out.println("AUTORIZADO: "+autorizado);
        
        return autorizado;
    }

	private String extractLoginParam(HttpServletRequest request, StringBuilder sb) {
		String loginParam = null;
		
		String method = request.getMethod();
    	String uri = request.getRequestURI();
    	sb.append("(["+method+"]"+uri+")->");
    	
		String call = method + " " + uri + "/";
		for(String start:new String[] {
			"PATCH /usuarios/",	
			"PATCH /alterasenha/",
			"GET /verificasenha/",  }) {
			
			if(call.startsWith(start)) {
				int startLength = start.length();
				int callBar = call.indexOf("/",startLength);
				loginParam = call.substring(startLength, callBar);
			}
		}
    	
    	
    	//Map<String, String[]> paramMap;		
		//String logins;
		//if(null!=paramMap) {
		//	logins = paramMap.get("login");
		//	if(null!=logins && logins.length()>0) {
		//		loginParam = logins[0];
		//	}
		//}
		
		return loginParam;
	}
    
	/*
	 * @Override public ClientHttpResponse intercept( HttpRequest request, byte[]
	 * body, ClientHttpRequestExecution execution) throws IOException {
	 * 
	 * ClientHttpResponse response = execution.execute(request, body);
	 * response.getHeaders().add("Foo", "bar"); return response; }
	 */

}