package com.bookstore.calificacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.calificacion.model.Comentario;
import com.bookstore.calificacion.service.CalificacionService;

@RestController
@RequestMapping("/api")
public class CalificacionController {
	@Autowired
	private CalificacionService calificacionService;
	
	@PostMapping("/comentario")
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_USER"})
	public Comentario guardarComentario(@RequestBody Comentario comentario) {
		return calificacionService.guardarComentario(comentario);
	}
	
	@GetMapping("/comentario/{isbn}")
	@ResponseStatus(HttpStatus.OK)
	public List<Comentario> obtenerComentariosDeLibro(@PathVariable String isbn){
		return calificacionService.obtenerComentarioDeLibro(isbn);
	}
	
	@DeleteMapping("/comentario/{id}")
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_USER"})
	public void eliminarComentario(@PathVariable Long id) {
		calificacionService.eliminarComentario(id);
	}
	
	@PutMapping("/comentario")
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_USER"})
	public Comentario editarComentario(@RequestBody Comentario comentario) {
		return calificacionService.editarComentario(comentario);
	}
}
