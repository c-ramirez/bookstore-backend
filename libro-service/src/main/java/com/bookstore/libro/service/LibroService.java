package com.bookstore.libro.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bookstore.libro.model.Autor;
import com.bookstore.libro.model.Libro;
import com.bookstore.libro.model.Tag;

public interface LibroService {
	List<Libro> obtenerLibros();
	Libro obtenerPorIsbn(String isbn);
	Libro obtenerFullPorIsbn(String isbn);
	public Page<Libro> findAll(String tag, Pageable pageable);
	List<Tag> obtenerTags();
	Page<Libro> obtenerLibrosFull(Pageable pageable);
	Page<Autor> obtenerPaginaAutores(Pageable pageable);
	Page<Tag> obtenerCategorias(Pageable pageable);
	List<Autor> obtenerAutores();
	Libro guardarLibro(Libro libro);
}
