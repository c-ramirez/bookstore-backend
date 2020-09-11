package com.bookstore.libro.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookstore.libro.model.Libro;
import com.bookstore.libro.model.Tag;

public interface LibroRepository extends JpaRepository<Libro, Long> {
	@Query("SELECT new Libro(libro.id,libro.isbn,libro.titulo,libro.precio,libro.autor,libro.editorial) FROM Libro libro")
	public List<Libro> obtenerLibros();
	
	public Libro findByIsbn(String isbn);
	
	@Query("SELECT new Libro(libro.id,libro.isbn,libro.titulo,libro.precio,libro.autor,libro.editorial) FROM Libro libro "
			+ "WHERE :tag MEMBER OF libro.tags")
	Page<Libro> obtenerLibrosPorTag(@Param("tag")Tag tag, Pageable pageable);
	
	@Query("SELECT new Libro(libro.id,libro.isbn,libro.titulo,libro.precio,libro.autor,libro.editorial) FROM Libro libro")
	Page<Libro> obtenerPaginaLibros(Pageable pageable);
	
	@Query("SELECT libro FROM Libro libro")
	Page<Libro> obtenerLibrosFull(Pageable pageable);
	
	@Query("SELECT new Libro(libro, libro.createdDate) FROM Libro libro WHERE libro.isbn = ?1")
	Libro obtenerLibroFull(String isbn);
}
