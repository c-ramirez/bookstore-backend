package com.bookstore.calificacion.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class Comentario extends Auditable<String> implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "No puede estar vacio")
	private Long libroId;
	@NotEmpty(message = "No puede estar vacio")
	private String libroNombre;
	@NotEmpty(message = "No puede estar vacio")
	private String libroIsbn;
	@NotNull(message = "No puede estar vacio")
	private Long usuarioId;
	@NotEmpty(message = "No puede estar vacio")
	private String usuarioNombre;
	@NotEmpty(message = "No puede estar vacio")
	private String comentario;
	@Transient
	private Date publicacion;

}
