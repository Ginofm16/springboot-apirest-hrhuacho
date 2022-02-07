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
	 * hacia los recursos de nuestra aplicacion.
	 * Por el lado de oauth2. Tamabien se tiene que hace algo como esto por el lado de Spring (SpringSecutiryConfig)*/
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET,
						"/api/historias",
						"/api/historias/page/**").permitAll()
		.antMatchers(HttpMethod.GET,"/api/historias/filtrar-historias/{term}").permitAll()
				.antMatchers(HttpMethod.GET,
						"/api/personal/personal-correo/{correo}",
						"/api/personal/personal-usuario/{usuario}").permitAll()
				.antMatchers(HttpMethod.POST, "/api/personal/actualizar-usuario").permitAll()
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
	}

	/*para manejar los errores que salen en Angular al acceder a rutas donde el usuaio no tiene permiso*/
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	/*configurar un filtro, registrar un filtro, un beans, es prioritario entre los filtros de pring,
	 * tambien para registrar esta configuracion (corsConfigurationSource) y se aplique tanto al servidor
	 *  de autorizacion cuando se acceda a las rutas o enpoints para autenticarnos y generar el token el OAuth y 
	 *  cada ves que se quiera validar el token*/
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
	
}
