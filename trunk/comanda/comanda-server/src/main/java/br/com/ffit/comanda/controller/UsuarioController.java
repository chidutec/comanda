package br.com.ffit.comanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ffit.comanda.service.UsuarioService;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.UsuarioTO;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(value="/fazerLogin", method = RequestMethod.POST)
	public @ResponseBody JSONResponse<UsuarioTO> fazerLogin(@RequestBody UsuarioTO usuarioTO) {
		usuarioTO = usuarioService.fazerLogin(usuarioTO);
		JSONResponse<UsuarioTO> jsonResponse = new JSONResponse<UsuarioTO>();
		jsonResponse.setSuccess(true);
		jsonResponse.setObj(usuarioTO);
		return jsonResponse;
	}
	
	@RequestMapping(value="/buscaUsuarioPeloIdFacebook/{idFacebook}", method = RequestMethod.GET)
	public @ResponseBody JSONResponse<UsuarioTO> buscaUsuarioPeloIdFacebook(@PathVariable Long idFacebook) {
		UsuarioTO usuarioTO = usuarioService.buscaUsuarioPeloIdFacebook(idFacebook);
		JSONResponse<UsuarioTO> jsonResponse = new JSONResponse<UsuarioTO>();
		jsonResponse.setSuccess(true);
		jsonResponse.setObj(usuarioTO);
		return jsonResponse;
	}
	
}
