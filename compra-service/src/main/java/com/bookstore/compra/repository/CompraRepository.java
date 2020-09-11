package com.bookstore.compra.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bookstore.compra.model.Compra;

public interface CompraRepository extends CrudRepository<Compra, Long>{
	@Query("SELECT c FROM Compra c WHERE c.usuarioId = ?1 AND c.estado = 1")
	Compra obtenerCompraPendiente(Long id);
	
	@Query("SELECT d.compra FROM Detalle d WHERE d.id = ?1")
	Compra obtenerDeDetalleId(Long id);
}
