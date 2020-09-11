package com.bookstore.compra.service;

import java.util.Date;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.compra.model.Compra;
import com.bookstore.compra.model.Detalle;
import com.bookstore.compra.repository.CompraRepository;
import com.bookstore.compra.repository.DetalleRepository;

@Service
@Transactional
public class CompraServiceImpl implements CompraService {
	@Autowired
	CompraRepository compraRepository;
	@Autowired
	DetalleRepository detalleRepository;

	@Override
	public Compra comprar(Compra compra) {
		Compra c = compraRepository.save(compra);
		c.getDetalles().stream().forEach(detalle -> {
			detalle.setCompra(c);
			detalleRepository.save(detalle);
		});
		return c;
	}

	@Override
	public Detalle agregarDetalle(Detalle detalle) {
		Compra compra = compraRepository.obtenerCompraPendiente(detalle.getUsuarioId());
		if (compra != null && compra.getEstado() == 1) {			
			detalle.setCompra(compra);
			Detalle otroDetalle = detalleRepository.obtenerDetallePorCompraYLibro(compra.getId(), detalle.getLibroId());
			if(otroDetalle != null) {
				otroDetalle.setCantidad(otroDetalle.getCantidad()+detalle.getCantidad());
				otroDetalle.setSubtotal(otroDetalle.getCantidad()*otroDetalle.getPrecioUnidad());
				detalleRepository.save(otroDetalle);
			}else {
				detalleRepository.save(detalle);
			}
			compra.setTotal(compra.getTotal() + detalle.getSubtotal());
			compraRepository.save(compra);
		} else {
			Compra nuevaCompra = new Compra();
			nuevaCompra.setCodigo(UUID.randomUUID().toString());
			nuevaCompra.setFecha(new Date());
			nuevaCompra.setUsuarioId(detalle.getUsuarioId());
			nuevaCompra.setEstado(1);
			nuevaCompra.setTotal(detalle.getSubtotal());
			compraRepository.save(nuevaCompra);
			detalle.setCompra(nuevaCompra);
			detalleRepository.save(detalle);
		}
		
		return detalle;
	}

	@Override
	public Compra obtenerCompraPendiente(Long usuarioId) {
		Compra compra = compraRepository.obtenerCompraPendiente(usuarioId);
		if(compra == null) {
			compra = new Compra();
			compra.setCodigo(UUID.randomUUID().toString());
			compra.setFecha(new Date());
			compra.setUsuarioId(usuarioId);
			compra.setEstado(1);
			compra.setTotal(0D);
			compraRepository.save(compra);
		}
		compra.setDetalles(detalleRepository.obtenerPorCompra(compra.getId()));
		return compra;
	}

	@Override
	public void eliminarDetalle(Long id) {
		Detalle detalle = detalleRepository.findById(id).orElse(null);
		if(detalle!=null) {
			Compra compra = compraRepository.obtenerDeDetalleId(id);
			compra.setTotal(compra.getTotal()-detalle.getSubtotal());
			compraRepository.save(compra);
			detalleRepository.deleteById(id);
		}
		
	}

}
