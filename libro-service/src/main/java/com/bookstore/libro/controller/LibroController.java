package com.bookstore.libro.controller;

import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.libro.ArchivoUtil;
import com.bookstore.libro.model.Autor;
import com.bookstore.libro.model.Libro;
import com.bookstore.libro.model.Tag;
import com.bookstore.libro.service.LibroService;

@RestController
@RequestMapping("/api")
public class LibroController {
	@Autowired
	LibroService libroService;
	
	@GetMapping("/mant/libro/page/{page}")
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_ADMIN"})
	public Page<Libro> obtenerLibros(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 10);
		return libroService.obtenerLibrosFull(pageable);
	}
	
	@GetMapping("/libro/{isbn}")
	@ResponseStatus(HttpStatus.OK)
	public Libro detalleLibro(@PathVariable("isbn") String isbn) {
		return libroService.obtenerPorIsbn(isbn);
	}
	@GetMapping("/mant/libro/{isbn}")
	@Secured({"ROLE_ADMIN"})
	@ResponseStatus(HttpStatus.OK)
	public Libro detalleLibroFull(@PathVariable("isbn") String isbn) {
		return libroService.obtenerFullPorIsbn(isbn);
	}
	@GetMapping("/libro/page/{page}")
	public Page<Libro> index(@PathVariable Integer page, @RequestParam(required = false) String tag) {
		Pageable pageable = PageRequest.of(page, 6);
		return libroService.findAll(tag, pageable);
	}
	@GetMapping("/mant/autor/page/{page}")
	@Secured({"ROLE_ADMIN"})
	public Page<Autor> obtenerPaginaAutores(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 10);
		return libroService.obtenerPaginaAutores(pageable);
	}
	@GetMapping("/mant/categoria/page/{page}")
	public Page<Tag> obtenerTags(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 10);
		return libroService.obtenerCategorias(pageable);
	}
	@GetMapping("/mant/autor")
	@Secured({"ROLE_ADMIN"})
	public List<Autor> obtenerAutores(){
		return libroService.obtenerAutores();
	}
	@PostMapping("/mant/libro")
	@Secured({"ROLE_ADMIN"})
	public Libro guardarLibro(@RequestBody Libro libro) {
		return libroService.guardarLibro(libro);
	}
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		Resource recurso = null;		
		try {
			recurso = ArchivoUtil.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	@GetMapping("/categoria")
	@ResponseStatus(HttpStatus.OK)
	public List<Tag> obtenerTags(){
		return libroService.obtenerTags();
	}
	
}
