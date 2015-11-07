package br.com.ffit.comanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ffit.comanda.model.Produto;
import br.com.ffit.comanda.service.EstabelecimentoService;

@Controller
@RequestMapping(value = "/estabelecimento")
public class EstabelecimentoController {

	@Autowired
	private EstabelecimentoService estabelecimentoService;

	@RequestMapping(value = "/inserirProduto", method = RequestMethod.POST)
	public @ResponseBody Produto inserirProduto(@RequestBody Produto produto) {
		return estabelecimentoService.inserirProduto(produto);
	}

}
