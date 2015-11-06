package br.com.ffit.comanda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ffit.comanda.model.Item;
import br.com.ffit.comanda.service.ContaService;

@Controller
@RequestMapping("/conta")
public class ContaController {
	
	@Autowired
	private ContaService contaService;
	
	@RequestMapping(value="/getItens/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Item> getItens(@PathVariable Long id) {
		return contaService.getItensById(id);
	}
	
}
