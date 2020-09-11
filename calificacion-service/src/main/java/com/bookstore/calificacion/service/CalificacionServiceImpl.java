package com.bookstore.calificacion.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.calificacion.model.Comentario;
import com.bookstore.calificacion.repository.ComentarioRepository;

@Service
@Transactional
public class CalificacionServiceImpl implements CalificacionService {
	@Autowired
	private ComentarioRepository comentarioRepository;
	@Override
	public List<Comentario> obtenerComentarioDeLibro(String isbn) {
		return comentarioRepository.obtenerComentarioPorIsbn(isbn);
	}

	@Override
	public Comentario guardarComentario(Comentario comentario) {
		return comentarioRepository.save(comentario);
	}

	@Override
	public void eliminarComentario(Long id) {
		comentarioRepository.deleteById(id);
	}

	@Override
	public Comentario editarComentario(Comentario comentario) {
		Comentario editado = comentarioRepository.findById(comentario.getId()).get();
		editado.setComentario(comentario.getComentario());
		return comentarioRepository.save(editado);
	}

}
