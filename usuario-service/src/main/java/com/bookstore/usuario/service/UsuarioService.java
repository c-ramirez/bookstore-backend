package com.bookstore.usuario.service;

import java.util.List;

import com.bookstore.usuario.model.Persona;
import com.bookstore.usuario.model.Usuario;

public interface UsuarioService {
	Persona crearPersona(Persona persona);
	List<Persona> obtenerPersonas();
	Usuario crearUsuario(Usuario usuario);
	List<Usuario> obtenerUsuarios();
	Usuario obtenerUsuarioPorUsername(String username);
}
