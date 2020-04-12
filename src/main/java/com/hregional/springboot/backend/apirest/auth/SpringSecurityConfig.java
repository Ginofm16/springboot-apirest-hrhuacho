package com.hregional.springboot.backend.apirest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService usuarioService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		//retornamos la instancia
		return new BCryptPasswordEncoder();
	}

	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder());
	}

	/*@Bean, registra el objeto en el contenedor de spring con el nombre authenticationManager*/
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		/*esta linea siempre se pone al final de todas las reglas, para todas esas rutas(endpoints)
		 * que tenemos pero no hemos asignado una regla aun.*/
		.anyRequest().authenticated()
		.and()
		.csrf().disable()
		/*deshabilitar el manejor de sesion en la autentificacion por el lado de spring security, lo
		 * dejaremos sin estado, sin stateless, ya que se va trabajar con token, a diferencia cuando
		 * se trabaja con aplicacion spring mvc donde sta todo incluido el frontend y el backend dentro
		 * del mismo poryecto con spring donde toda la autentificacion de los usuarios es atraves de sesiones*/
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}
