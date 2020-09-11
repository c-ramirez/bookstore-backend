package com.bookstore.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.usuario.model.Persona;
import com.bookstore.usuario.model.Usuario;
import com.bookstore.usuario.service.UsuarioService;

@CrossOrigin(origins = { "http://localhost:4200" ,"*"})
@RestController
@RequestMapping("/api")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/usuario/persona")
	@ResponseStatus(HttpStatus.OK)
	public Persona crearPersona(@RequestBody Persona persona) {
		return usuarioService.crearPersona(persona);
	}
	@PostMapping("/usuario")
	@ResponseStatus(HttpStatus.OK)
	public Usuario crearUsuario(@RequestBody Usuario usuario) {
		return usuarioService.crearUsuario(usuario);
	}
	
	@GetMapping("/usuario")
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_ADMIN"})
	public List<Usuario> listaUsuarios(){
		return usuarioService.obtenerUsuarios();
	}
	
	
}
