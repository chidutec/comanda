package br.com.ffit.comanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ffit.comanda.exception.BusinessException;
import br.com.ffit.comanda.model.Usuario;
import br.com.ffit.comanda.repository.UsuarioRepository;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.LoginTO;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	public Usuario inserirUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public JSONResponse fazerLogin(LoginTO loginTO) throws BusinessException{
		Usuario usuario = usuarioRepository.findByLogin(loginTO.getLogin());
		JSONResponse jsonResponse = new JSONResponse();
		if(usuario != null) {
			if(usuario.getSenha().equals(loginTO.getSenha())) {
				jsonResponse.setSuccess(true);
			} else {
				jsonResponse.setSuccess(false);
				jsonResponse.setErrorMessage("Senha invalida");
			}
		} else {
			jsonResponse.setSuccess(false);
			jsonResponse.setErrorMessage("Usuario nao cadastrado");
		}
		return jsonResponse;
	}
	
}
