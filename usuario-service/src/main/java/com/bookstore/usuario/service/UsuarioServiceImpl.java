package com.bookstore.usuario.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookstore.usuario.model.Persona;
import com.bookstore.usuario.model.Rol;
import com.bookstore.usuario.model.Usuario;
import com.bookstore.usuario.repository.PersonaRepository;
import com.bookstore.usuario.repository.RolRepository;
import com.bookstore.usuario.repository.UsuarioRepository;

@Transactional
@Service
public class UsuarioServiceImpl implements UsuarioService,UserDetailsService {
	@Autowired
	PersonaRepository personaRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	RolRepository rolRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Persona crearPersona(Persona persona) {
		if (personaRepository.findByDocumento(persona.getDocumento()) != null) {
			throw new UsuarioException("La persona ya se encuentra registrada");
		}
		return personaRepository.save(persona);
	}

	@Override
	public List<Persona> obtenerPersonas() {
		return (List<Persona>) personaRepository.findAll();
	}
	
	public List<Usuario> obtenerUsuarios() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	public Usuario crearUsuario(Usuario usuario) {
		if(usuarioRepository.findByUsername(usuario.getUsername()) != null)
			throw new UsuarioException("El usuario ya esta registrado");
		if (personaRepository.findByDocumento(usuario.getPersona().getDocumento()) != null) {
			throw new UsuarioException("La persona ya se encuentra registrada");
		}
		if(personaRepository.findByEmail(usuario.getPersona().getEmail()) != null) {
			throw new UsuarioException("El email ya se encuentra registrado");
		}
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		for(Rol rol: usuario.getRoles()) {
			Rol rolDb = rolRepository.findByNombre(rol.getNombre());
			rol.setId(rolDb.getId());
		}
		return usuarioRepository.save(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username);
		if(usuario == null){
			throw new UsernameNotFoundException("Error en el login: no existe el usuario : "+username);
		}
		List<GrantedAuthority> authorities = usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())
				).collect(Collectors.toList());
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getActivo(), true, true, true, authorities);
	}

	@Override
	public Usuario obtenerUsuarioPorUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}

}
