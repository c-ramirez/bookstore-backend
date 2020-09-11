package com.bookstore.compra.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = false)
public class Detalle extends Auditable<String> implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "No puede estar vacio")
	private Long libroId;
	@NotEmpty(message = "No puede estar vacio")
	private String libroIsbn;
	@NotEmpty(message = "No puede estar vacio")
	private String libroTitulo;
	@NotNull(message = "No puede estar vacio")
	private Integer cantidad;
	@NotNull(message = "No puede estar vacio")
	private Double subtotal;
	@NotNull(message = "No puede estar vacio")
	private Double precioUnidad;
	@Transient
	private Long usuarioId;
	@Transient
	private String usuarioDocumento;
	@ManyToOne
	@JoinColumn(name = "compra_id")
	@JsonIgnore
	private Compra compra;
}
