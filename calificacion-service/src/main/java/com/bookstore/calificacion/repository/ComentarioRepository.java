package com.bookstore.calificacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bookstore.calificacion.model.Comentario;

public interface ComentarioRepository extends CrudRepository<Comentario, Long>{
	public List<Comentario> findByLibroIsbnOrderByIdDesc(String isbn);
	
	@Query("SELECT new Comentario(c.id,c.libroId,c.libroNombre,c.libroIsbn,c.usuarioId,c.usuarioNombre,c.comentario,c.createdDate) from Comentario c WHERE c.libroIsbn=?1 ORDER BY c.id DESC")
	public List<Comentario> obtenerComentarioPorIsbn(String isbn);

}
