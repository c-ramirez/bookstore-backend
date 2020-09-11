package com.bookstore.usuario.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode
public class Rol implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	@NotEmpty(message = "No puede estar vacio")
	private String nombre;
}
