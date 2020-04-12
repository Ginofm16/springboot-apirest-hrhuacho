package com.hregional.springboot.backend.apirest.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	/*metodo que va permitir implementar todas la reglas de seguridad de nuestros empoints, de nuestras rutas
	 * hacia los recursos de nuestra aplicacion, por ejemplo que si se quide dar permiso a todos al listado
	 * del clientes o que solo los admin pueda realizar el crear, eliminar y modificar*/
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/historias","/api/historias/page/**").permitAll()
		.antMatchers(HttpMethod.GET,"/api/historias/filtrar-historias/{term}").permitAll()
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
	}

	/*para manejar los errores que salen en Angular al acceder a rutas donde el usuaio no tiene permiso*/
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		/*OPTIONS, es porque ne algunos navegadores cuando se envia una solictud para autentificar 
		 * y solictar el tokena la ruta OAuthToken ese request, esa petecion, lo envia como OPTIONS;
		 * o tambien de parametro del asList podria ir con un solo astisco ("*")*/
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
		
		/*Ahora se tiene que registrar esta configuracion Cors para todas las rutas, todos los endpoints del
		 * backend*/
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		/* "/**", se indica que sera para todas la rutas del backend*/
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	/*configurar un filtro, registrar un filtro, un beans, es prioritario entre los filtros de pring,
	 * tambien para registrar esta configuracion (corsConfigurationSource) y se aplique tanto al servidor
	 *  de autorizacion cuando se acceda a las rutas o enpoints para autenticarnos y generar el token el OAuth y 
	 *  cada ves que se quiera validar el token*/
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		/*basicamente, se creo un filtro de cors, que a ese filtro se le pasa toda la configuracion 
		 * realizada en corsConfigurationSource(). CON ESTO YA QUEDA CONFIGURADO COMPLETAMENTE TANTO
		 * POR EL LADO DE SPRING SECURITY COMO TAMBIEN PO EL LADO DE OAUTH2*/
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
	
}
