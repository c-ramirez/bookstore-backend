package com.bookstore.usuario.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = false)
public class Usuario extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "No puede estar vacio")
	@Size(min = 5, message = "El nombre de usuario debe tener como minimo 5 caracteres")
	private String username;
	@NotEmpty(message = "No puede estar vacio")
	@Size(min = 5, message = "La clave debe tener como minimo 5 caracteres")
	private String password;
	@NotNull(message = "No hay persona asociada al usuario")
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "persona_id", referencedColumnName = "id")
	private Persona persona;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"),
	uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id","rol_id"}))
	private Set<Rol> roles;
	private Boolean recordar;
	@NotNull(message = "El usuario debe tener estado")
	private Boolean activo;

}
