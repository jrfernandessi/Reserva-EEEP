package com.analistajunior.reserva.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.analistajunior.reserva.model.Professor;
import com.analistajunior.reserva.model.TipoUsuario;
import com.analistajunior.reserva.repository.Professores;
import com.analistajunior.reserva.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Professores usuarios = CDIServiceLocator.getBean(Professores.class);
		Professor usuario = usuarios.porEmail(email);

		UsuarioSistema user = null;

		if (usuario != null) {
			user = new UsuarioSistema(usuario, getGrupos(usuario));
		}

		return user;
	}

	private Collection<? extends GrantedAuthority> getGrupos(Professor usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		authorities.add(new SimpleGrantedAuthority(usuario.getTipoUsuario().getDescricao()));

		return authorities;
	}

	// private TipoUsuario[] getTipos(){
	// return TipoUsuario.values();
	// }

}
