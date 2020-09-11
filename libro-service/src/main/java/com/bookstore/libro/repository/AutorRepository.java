package com.bookstore.libro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookstore.libro.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{
	
	@Query("SELECT autor FROM Autor autor ORDER BY autor.nombres DESC")
	Page<Autor> obtenerAutores(Pageable pageable);
}
