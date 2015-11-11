package br.com.ffit.comanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ffit.comanda.model.Produto;
import br.com.ffit.comanda.service.EstabelecimentoService;
import br.com.ffit.comanda.to.EstabelecimentoTO;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.LoginTO;
import br.com.ffit.comanda.to.ProdutoTO;

@Controller
@RequestMapping(value = "/estabelecimento")
public class EstabelecimentoController {

	@Autowired
	private EstabelecimentoService estabelecimentoService;

	@RequestMapping(value = "/inserirProduto", method = RequestMethod.POST)
	public @ResponseBody Produto inserirProduto(@RequestBody ProdutoTO produtoTO) {
		return estabelecimentoService.inserirProduto(produtoTO);
	}

	@RequestMapping(value = "/cadastraEstabelecimento", method = RequestMethod.POST)
	public @ResponseBody JSONResponse cadastraEstabelecimeto(@RequestBody EstabelecimentoTO estabelecimentoTO) {
		return estabelecimentoService.inserirEstabelecimento(estabelecimentoTO);
	}
	
	@RequestMapping(value="/fazerLogin", method = RequestMethod.POST)
	public @ResponseBody JSONResponse fazerLogin(@RequestBody LoginTO loginTO) {
		return estabelecimentoService.fazerLogin(loginTO);
	}
	
	@RequestMapping(value="/verificaDisponibilidadeLogin", method = RequestMethod.POST)
	public @ResponseBody JSONResponse verificaDisponibilidadeLogin(@RequestBody LoginTO loginTO) {
		return estabelecimentoService.verificaDisponibilidadeLogin(loginTO);
	}

}
