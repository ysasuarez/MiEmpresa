package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Permitir el acceso a recursos desde dominios cruzados y deshabilitar 
 * medidas de seguridad específicas para desarrollo, como las restricciones 
 * del iframe en aplicaciones que utilizan la consola de H2 Database. Además, 
 * habilita la configuración básica de seguridad web en tu aplicación Spring Boot.
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Deshabilita la protección CSRF (Cross-Site Request Forgery) y 
	 * habilita la configuración de CORS.
	 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable(); 
        http.headers().frameOptions().disable();
    }

    /**
     * Este método configura las reglas CORS. 
     * @return
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Permite solicitudes desde cualquier origen
        configuration.addAllowedOrigin("*"); 
        // Acepta cualquier encabezado.
        configuration.addAllowedHeader("*"); 
        // Permite cualquier método HTTP.
        configuration.addAllowedMethod("*"); 

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
