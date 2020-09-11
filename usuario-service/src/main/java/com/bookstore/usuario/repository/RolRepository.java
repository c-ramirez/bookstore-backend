package com.bookstore.usuario.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.usuario.model.Rol;

public interface RolRepository extends CrudRepository<Rol, Long>{
	Rol findByNombre(String nombre);
}
