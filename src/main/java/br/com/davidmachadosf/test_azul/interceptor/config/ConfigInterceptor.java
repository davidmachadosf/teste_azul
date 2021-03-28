
package br.com.davidmachadosf.test_azul.interceptor.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.com.davidmachadosf.test_azul.interceptor.TokenInterceptor;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("deprecation")
@RequiredArgsConstructor
@Component
public class ConfigInterceptor 
extends WebMvcConfigurerAdapter {

    private final TokenInterceptor inperceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(inperceptor);
    }
    
}
