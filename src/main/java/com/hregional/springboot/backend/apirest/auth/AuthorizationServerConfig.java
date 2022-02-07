package com.hregional.springboot.backend.apirest.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	/*para asegurarnos que sea el opbjeto que esta devolviendo authenticationManager(), lo vamos
	 * seleccionar mediante el nombre del Bean, que por defecto es el nombre del metodo que lo devuelve*/
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private InfoAdiconalToken infoAdiconalToken;

	/*2.3. configurar permisos de los endpoints, de la ruta de accesos de springsecurity oauth2*/
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		/*permitAll() para realizar el login*/
		security.tokenKeyAccess("permitAll()")
		.checkTokenAccess("isAuthenticated()");
		
	}

	/*2.2. configurar el cliente, la aplicaciones, que van acceder al api rest, en este caso
	* solo seria uno, el angular que estoy implementando.
	* Tambien configuracion sobre el token*/
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("angularapp")
		.secret(passwordEncoder.encode("12345"))
		.scopes("read","write")
		.authorizedGrantTypes("password", "refresh_token")
		.accessTokenValiditySeconds(3600)
		.refreshTokenValiditySeconds(3600);
	}

	/*2.1. Se encarga de tdo el proceso de autenticar y validar el token, cada vez que se inicie sesion se envia
	* el usuario y contraseña y si tod sale bien genera el token y se le entrega al usuario*/
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		TokenEnhancerChain tokenEnhacerChain = new TokenEnhancerChain();
		tokenEnhacerChain.setTokenEnhancers(Arrays.asList(infoAdiconalToken, accessTokenConverter()));

		endpoints.authenticationManager(authenticationManager)
		.accessTokenConverter(accessTokenConverter())
		.tokenStore(tokenStore())
		.tokenEnhancer(tokenEnhacerChain);
	}

	/*metodo que por debajo trabaja con toda la implementacion del token JWT para tarducir toda la ifnormacion y
	 * autentificacion con OAuth2 para decodificar los datos y despues para codificar los datos, datos que
	 * generalemnete son el username y roles, datos del usuario, y caulquier informacion extra */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVADA);
		jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLICA);
		return jwtAccessTokenConverter;
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
}
