package br.com.ffit.comanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ffit.comanda.model.Usuario;
import br.com.ffit.comanda.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	public Usuario inserirUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
}
