package com.analistajunior.reserva.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.analistajunior.reserva.model.Professor;

public class UsuarioSistema extends User {

	private static final long serialVersionUID = 1L;
	
	private Professor usuario;
	
	public UsuarioSistema(Professor usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	public Professor getUsuario() {
		return usuario;
	}

}
