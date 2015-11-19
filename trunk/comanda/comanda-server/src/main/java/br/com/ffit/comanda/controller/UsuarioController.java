package br.com.ffit.comanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ffit.comanda.service.UsuarioService;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.UserTO;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(value="/fazerLogin", method = RequestMethod.POST)
	public @ResponseBody JSONResponse fazerLogin(@RequestBody UserTO userTO) {
		usuarioService.fazerLogin(userTO);
		JSONResponse jsonResponse = new JSONResponse();
		jsonResponse.setSuccess(true);
		return jsonResponse;
	}
	
}
