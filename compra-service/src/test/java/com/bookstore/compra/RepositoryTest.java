package com.bookstore.compra;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.bookstore.compra.model.Compra;
import com.bookstore.compra.model.Detalle;
import com.bookstore.compra.service.CompraService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RepositoryTest {
	@Autowired
	CompraService compraService;
	
	@Test
	public void compraTest() {
		Compra compra = new Compra();
		compra.setCodigo("221123333");
		compra.setUsuarioId(1L);
		compra.setUsuarioDocumento("22331122");
		compra.setFecha(new Date());
		compra.setTotal(500.5);
		Detalle detalle = new Detalle();
		detalle.setLibroId(1L);
		detalle.setLibroIsbn("100233321");
		detalle.setCantidad(2);
		detalle.setSubtotal(200.5);
		detalle.setPrecioUnidad(200.5);
		compra.setDetalles(new ArrayList<>());
		compra.getDetalles().add(detalle);
		compraService.comprar(compra);
		assertNotNull(compra.getId());
	}
}
