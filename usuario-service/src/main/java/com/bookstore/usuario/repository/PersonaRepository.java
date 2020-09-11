package com.bookstore.usuario.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.usuario.model.Persona;

public interface PersonaRepository extends CrudRepository<Persona, Long>{
	public Persona findByDocumento(String documento);
	public Persona findByEmail(String email);
}
