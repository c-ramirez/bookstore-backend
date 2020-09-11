package com.bookstore.compra.service;

import com.bookstore.compra.model.Compra;
import com.bookstore.compra.model.Detalle;

public interface CompraService {
	Compra comprar(Compra compra);
	Detalle agregarDetalle(Detalle detalle);
	Compra obtenerCompraPendiente(Long usuarioId);
	void eliminarDetalle(Long id);
}
