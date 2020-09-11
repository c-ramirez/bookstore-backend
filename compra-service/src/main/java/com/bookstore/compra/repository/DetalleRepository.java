package com.bookstore.compra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bookstore.compra.model.Detalle;

public interface DetalleRepository extends CrudRepository<Detalle, Long> {
	@Query("SELECT detalle FROM Detalle detalle where detalle.compra.id=?1")
	List<Detalle> obtenerPorCompra(Long id);
	
	@Query("SELECT detalle FROM Detalle detalle WHERE detalle.compra.id=?1 AND detalle.libroId=?2")
	Detalle obtenerDetallePorCompraYLibro(Long idCompra, Long idLibro);
}
