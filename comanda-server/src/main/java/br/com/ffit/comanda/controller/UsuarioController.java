package br.com.ffit.comanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ffit.comanda.model.Usuario;
import br.com.ffit.comanda.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(value="/inserirUsuario", method = RequestMethod.POST)
	public @ResponseBody Usuario inserirUsuario(@RequestBody Usuario usuario) {
		return usuarioService.inserirUsuario(usuario);
	}
	
}
