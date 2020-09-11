package com.bookstore.libro.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bookstore.libro.model.Autor;
import com.bookstore.libro.model.Libro;
import com.bookstore.libro.model.Tag;
import com.bookstore.libro.repository.AutorRepository;
import com.bookstore.libro.repository.LibroRepository;
import com.bookstore.libro.repository.TagRepository;

@Service
@Transactional
public class LibroServiceImplm implements LibroService {
	@Autowired
	LibroRepository libroRepository;
	@Autowired
	TagRepository tagRepository;
	@Autowired
	AutorRepository autorRepository;
	@Override
	public List<Libro> obtenerLibros() {
		return libroRepository.obtenerLibros();
	}
	
	public Libro obtenerPorIsbn(String isbn) {
		return libroRepository.findByIsbn(isbn);
	}

	@Override
	public Page<Libro> findAll(String tag, Pageable pageable) {
		if(tag!=null) {
			Tag categoria = tagRepository.findByNombre(tag);
			return libroRepository.obtenerLibrosPorTag(categoria, pageable);
		}else
			return libroRepository.findAll(pageable);
		
	}

	@Override
	public List<Tag> obtenerTags() {
		return (List<Tag>) tagRepository.findAll();
	}

	@Override
	public Page<Libro> obtenerLibrosFull(Pageable pageable) {
		return libroRepository.obtenerLibrosFull(pageable);
	}

	@Override
	public Libro obtenerFullPorIsbn(String isbn) {
		return libroRepository.obtenerLibroFull(isbn);
	}

	@Override
	public Page<Autor> obtenerPaginaAutores(Pageable pageable) {
		return autorRepository.obtenerAutores(pageable);
	}

	@Override
	public List<Autor> obtenerAutores() {
		return autorRepository.findAll();
	}

	@Override
	public Libro guardarLibro(Libro libro) {
		return libroRepository.save(libro);
	}

	@Override
	public Page<Tag> obtenerCategorias(Pageable pageable) {
		return tagRepository.obtenerCategorias(pageable);
	}

}
