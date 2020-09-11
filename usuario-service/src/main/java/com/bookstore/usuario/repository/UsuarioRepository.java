package com.bookstore.usuario.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.usuario.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	Usuario findByUsername(String username);
}
