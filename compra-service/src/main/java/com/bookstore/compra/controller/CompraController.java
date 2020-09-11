package com.bookstore.compra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.compra.model.Compra;
import com.bookstore.compra.model.Detalle;
import com.bookstore.compra.service.CompraService;

@RestController
@RequestMapping("/api")
public class CompraController {
	@Autowired
	private CompraService compraService;
	
	@PostMapping("/compra")
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_USER"})
	public Compra comprar(@RequestBody Compra compra) {
		return compraService.comprar(compra);
	}
	@PostMapping("/detalle")
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_USER"})
	public Detalle agregarDetalle(@RequestBody Detalle detalle) {
		return compraService.agregarDetalle(detalle);
	}
	
	@GetMapping("/compra/{usuarioId}")
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_USER"})
	public Compra obtenerCompraPendiente(@PathVariable Long usuarioId) {
		return compraService.obtenerCompraPendiente(usuarioId);
	}
	
	@DeleteMapping("/detalle/{id}")
	@ResponseStatus(HttpStatus.OK)
	@Secured({"ROLE_USER"})
	public void eliminarDetalle(@PathVariable Long id) {
		compraService.eliminarDetalle(id);
	}
}
