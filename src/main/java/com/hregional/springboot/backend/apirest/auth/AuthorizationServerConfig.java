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
/*anotacion para habiltar la configuracion del AuthorizationServer*/
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

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		
		security.tokenKeyAccess("permitAll()")
		.checkTokenAccess("isAuthenticated()");
		
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("angularapp")
		.secret(passwordEncoder.encode("12345"))
		.scopes("read","write")
		.authorizedGrantTypes("password", "refresh_token")
		/*metodo para especificar el tiempo de valides del token, en cuanto tiempo va caducar el token(se mide en segundos)*/
		.accessTokenValiditySeconds(3600)
		/*configurar el tiempo de expiracion del refresh_token.
		 *Entonces agtraves de un refreshToken que es un GrantTypes(tipo de concesion) podemos 
		 *obtener un token de acceso completamente renovado sin tener que volver a iniciar
		 *sesion.*/
		.refreshTokenValiditySeconds(3600);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		/*4to, se registra el componente InfoAdicionalToken, para hacerlo se tiene que crear una instancia
		 del tokenEnhancerChain que es una cadena que va unir tanto la informacion del token por defecto con
		 esta nueva informacion adicional*/
		TokenEnhancerChain tokenEnhacerChain = new TokenEnhancerChain();
		tokenEnhacerChain.setTokenEnhancers(Arrays.asList(infoAdiconalToken, accessTokenConverter()));
		
		/*1ero, registrar el authenticationManager*/
		endpoints.authenticationManager(authenticationManager)
		/*2do paso es registrar accessTokenConverter, es un componente que se tiene que implementar,
		 * es el encargado de manejar varias cosas relacionadas al token, al J2EE, como por ejm
		 * almacenar los datos de autentificacion del usuario, users, roles, cualquier otra informacion, etc.*/
		.accessTokenConverter(accessTokenConverter())
		/*por debajo el endpoint, trabaja con tokenStore, por ende el invocarlo aca es opcional porque
		 * si nos fijamos en el codigo fuente de AuthorizationServerEndpointsConfigurer ya cuenta con 
		 * un metodo que devuelve el TokenStore, que si no lo hemos implementado aca, internamente
		 * evalua que si accessTokenConverter es una instancia de JwtAccessTokenConverter, si es asi
		 * va crear el JwtTokenStore. O tambien lo podemos implementar aca de manera explicita*/
		.tokenStore(tokenStore())
		/*asignar el nuevo token con informacion adicional*/
		.tokenEnhancer(tokenEnhacerChain);
	}
	
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	/*metodo que por debajo trabaja con toda la implementacion del token JWT para tarducir toda la ifnormacion y
	 * autentificacion con OAuth2 para decodificar los datos y despues para codificar los datos, datos que
	 * generalemnete son el username y roles, datos del usuario, y caulquier informacion extra */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		/*SigningKey, es el que firma el token, el jwt, y el que firma es la llave privada*/
		jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVADA);
		/*y el que valida y verifica que el token sea autentico es la publica*/
		jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLICA);
		return jwtAccessTokenConverter;
	}
	
	
}
