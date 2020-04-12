package com.hregional.springboot.backend.apirest.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.hregional.springboot.backend.apirest.models.entity.Personal;
import com.hregional.springboot.backend.apirest.models.entity.Role;
import com.hregional.springboot.backend.apirest.models.entity.Usuario;
import com.hregional.springboot.backend.apirest.models.services.IUsuarioService;

@Component
public class InfoAdiconalToken implements TokenEnhancer{

	//@Autowired
	//private IPersonalService personalService;
	@Autowired
	private IUsuarioService usuarioService;
	
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		//Personal personal = personalService.findByUsername(authentication.getName());
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		
		Personal personal = usuarioService.findByUsuario(usuario.getUsu_codigo());
		
		Role role = usuarioService.findByUsuarioRole(usuario.getUsu_codigo());
		
		Map<String, Object> info = new HashMap<>();
		info.put("info_adicional", "Hola que tal, ".concat(authentication.getName()));
		
		info.put("cod_user", usuario.getUsu_codigo());
		info.put("username", authentication.getName());
		info.put("nombre", personal.getPer_nombre());
		info.put("apellido_paterno", personal.getPer_ape_paterno());
		info.put("apellido_materno", personal.getPer_ape_materno());
		info.put("dni", personal.getPer_dni());
		info.put("rol_codigo", role.getRol_codigo());
		info.put("rol_nombre", role.getRol_nombre());
		
		/*asignando el info al accessToken*/
		/*accessToken, aca es del tipo de la interface, y esta interface no tiene el metodo que
		 * se requiere, para ello realizamos un cast a la clase DefaultOAuth2AccessToken que
		 * implementa OAuth2AccessToken*/
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}
	 

}
