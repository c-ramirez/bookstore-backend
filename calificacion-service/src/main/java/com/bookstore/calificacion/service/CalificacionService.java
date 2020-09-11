package com.bookstore.calificacion.service;

import java.util.List;

import com.bookstore.calificacion.model.Comentario;

public interface CalificacionService {
	public List<Comentario> obtenerComentarioDeLibro(String isbn);
	public Comentario guardarComentario(Comentario comentario);
	public void eliminarComentario(Long id);
	public Comentario editarComentario(Comentario comentario);
}
