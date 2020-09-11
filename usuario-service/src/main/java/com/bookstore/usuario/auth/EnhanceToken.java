package com.bookstore.usuario.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.bookstore.usuario.model.Usuario;
import com.bookstore.usuario.service.UsuarioService;

@Component
public class EnhanceToken implements TokenEnhancer{
	@Autowired
	private UsuarioService usuarioService;
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<>();
		Usuario usuario = usuarioService.obtenerUsuarioPorUsername(authentication.getName());
		info.put("nombre", usuario.getPersona().getNombres());
		info.put("apellidoPaterno", usuario.getPersona().getApellidoPaterno());
		info.put("apellidoMaterno", usuario.getPersona().getApellidoMaterno());
		info.put("email", usuario.getPersona().getEmail());
		info.put("id", usuario.getId());
		info.put("documento",usuario.getPersona().getDocumento());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
