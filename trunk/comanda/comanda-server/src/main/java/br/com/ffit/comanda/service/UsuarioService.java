package br.com.ffit.comanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ffit.comanda.exception.BusinessException;
import br.com.ffit.comanda.model.Usuario;
import br.com.ffit.comanda.repository.UsuarioRepository;
import br.com.ffit.comanda.to.UsuarioTO;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Transactional
	public UsuarioTO fazerLogin(UsuarioTO usuarioTO) throws BusinessException {
		Usuario usuario = usuarioRepository.findByIdFacebook(usuarioTO.getIdFacebook()); 
		if(usuario == null) {
			usuario = new Usuario();
			usuario.setIdFacebook(usuarioTO.getIdFacebook());
		}
		usuario.setEmail(usuarioTO.getEmail());
		usuario.setFotoUrl(usuarioTO.getFotoUrl());
		usuario.setNome(usuarioTO.getNome());
		usuario = usuarioRepository.save(usuario);
		
		usuarioTO.setId(usuario.getId());
		return usuarioTO;
	}

	public UsuarioTO buscaUsuarioPeloIdFacebook(Long idFacebook) {
		Usuario usuario = usuarioRepository.findByIdFacebook(idFacebook); 
		UsuarioTO usuarioTO = new UsuarioTO();
		usuarioTO.setId(usuario.getId());
		usuarioTO.setEmail(usuario.getEmail());
		usuarioTO.setNome(usuario.getNome());
		usuarioTO.setIdFacebook(usuario.getIdFacebook());
		usuarioTO.setFotoUrl(usuario.getFotoUrl());
		return usuarioTO;
	}

}
