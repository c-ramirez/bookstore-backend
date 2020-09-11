package com.bookstore.usuario.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class Persona extends Auditable<String> implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "No puede estar vacio")
	@Size(min = 8, max = 8, message = "El documento tiene que tener 8 digitos")
	private String documento;
	@NotEmpty(message = "No puede estar vacio")
	private String nombres;
	@NotEmpty(message = "No puede estar vacio")
	private String apellidoPaterno;
	private String apellidoMaterno;
	@NotNull(message = "no puede estar vacio")
	private Date fechaNacimiento;
	@NotEmpty(message = "No puede estar vacio")
	@Size(min = 1, max = 1, message = "El genero solo es una letra")
	private String genero;
	@NotEmpty(message = "No puede estar vacio")
	@Email(message = "no es una dirección de correo bien formada")
	@Column(nullable = false, unique = true)
	private String email;
	@JsonIgnore
	@OneToOne(mappedBy = "persona")
	private Usuario usuario;
	
}
