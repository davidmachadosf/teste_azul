package br.com.davidmachadosf.test_azul;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ConstantesApplication {
	
	public static final String APP_TITULO= "Teste azul";
	public static final String APP_NOME= "Cadastro de Clientes";
	public static final String APP_VERSAO = "v1.1.2";
	
    public static final Charset BASE64_CHARSET = StandardCharsets.UTF_8;
	
	public static final long SEGUNDOS = 1000/*milissegundos*/;
	public static final long MINUTOS = 60*SEGUNDOS;
	public static final long HORAS = 60*MINUTOS;
	public static final long DIAS = 24*HORAS;
	public static final long SEMANAS = 7*DIAS;
	
	// tokens de autenticação expiram em 2 horas e meia
   	public static long EXPIRES_DURATION = 2*HORAS + 30*MINUTOS;		
   	public static String CHAVE_CRIPTOGRAFIA = "secret$key";
   	
   	// atributos de header
   	public static String HEADER_KEY_TOKEN = "Token";
   	public static String HEADER_KEY_INTERCEPTOR = "Interceptor";
}
