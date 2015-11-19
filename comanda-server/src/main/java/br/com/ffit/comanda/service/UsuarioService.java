package br.com.ffit.comanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ffit.comanda.exception.BusinessException;
import br.com.ffit.comanda.model.Usuario;
import br.com.ffit.comanda.repository.UsuarioRepository;
import br.com.ffit.comanda.to.UserTO;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Transactional
	public void fazerLogin(UserTO userTO) throws BusinessException{
		Usuario usuario = usuarioRepository.findByIdFacebook(userTO.getIdFacebook());
		if(usuario == null) {
			usuario = new Usuario();
			usuario.setIdFacebook(userTO.getIdFacebook());
			usuario.setFoto(userTO.getFoto());
			usuario.setName(userTO.getName());
			usuarioRepository.save(usuario);
		}
	}
	
}
