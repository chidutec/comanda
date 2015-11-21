package br.com.ffit.comanda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ffit.comanda.model.Item;
import br.com.ffit.comanda.service.ContaService;
import br.com.ffit.comanda.to.AbrirContaTO;
import br.com.ffit.comanda.to.JSONResponse;

@Controller
@RequestMapping("/conta")
public class ContaController {
	
	@Autowired
	private ContaService contaService;
	
	@RequestMapping(value="/abrirConta", method = RequestMethod.POST) 
	public @ResponseBody JSONResponse abrirConta(@RequestBody AbrirContaTO abrirContaTO) {
		JSONResponse jsonResponse = new JSONResponse();
		contaService.abrirConta(abrirContaTO);
		jsonResponse.setSuccess(true);
		jsonResponse.setMessage("Conta Criada");
		return jsonResponse;
	}
	
	@RequestMapping(value="{id}/getItens", method = RequestMethod.GET)
	public @ResponseBody List<Item> getItens(@PathVariable Long id) {
		return contaService.findItensById(id);
	}
	
}
