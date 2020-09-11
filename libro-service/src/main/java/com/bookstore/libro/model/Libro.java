package com.bookstore.libro.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
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
public class Libro extends Auditable<String> implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "No puede estar vacio")
	private String isbn;
	@NotEmpty(message = "No puede estar vacio")
	private String titulo;
	@NotNull(message = "No puede estar vacio")
	private Date publicacion;
	@NotEmpty(message = "No puede estar vacio")
	private String sinopsis;
	@NotNull(message = "No puede estar vacio")
	private Double precio;
	@NotNull(message = "No puede estar vacio")
	private Integer cantidad;
	@NotNull(message = "No puede estar vacio")
	private Integer paginas;
	@NotNull(message = "No puede estar vacio")
	@ManyToOne
	@JoinColumn(name = "autor_id")
	private Autor autor;
	@NotNull(message = "No puede estar vacio")
	@ManyToOne
	@JoinColumn(name = "editorial_id")
	private Editorial editorial;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "libro_tag", joinColumns = @JoinColumn(name = "libro_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"), uniqueConstraints = @UniqueConstraint(columnNames = {
			"libro_id", "tag_id" }))
	@ElementCollection
	private List<Tag> tags;
	@Transient
	private Date fechaAgregado;

	public Libro(Long id, String isbn, String titulo, Double precio, Autor autor, Editorial editorial) {
		this.id = id;
		this.isbn = isbn;
		this.titulo = titulo;
		this.precio = precio;
		this.autor = autor;
		this.editorial = editorial;
	}

	public Libro(Libro libro, Date fechaAgregado) {
		super();
		this.id = libro.id;
		this.isbn = libro.isbn;
		this.titulo = libro.titulo;
		this.publicacion = libro.publicacion;
		this.sinopsis = libro.sinopsis;
		this.precio = libro.precio;
		this.cantidad = libro.cantidad;
		this.paginas = libro.paginas;
		this.autor = libro.autor;
		this.editorial = libro.editorial;
		this.tags = libro.tags;
		this.fechaAgregado = fechaAgregado;
	}
	
	
}
