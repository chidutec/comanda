package br.com.ffit.comanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ffit.comanda.dto.JSONResponse;
import br.com.ffit.comanda.dto.ProdutoTO;
import br.com.ffit.comanda.exception.BussinessException;
import br.com.ffit.comanda.model.Estabelecimento;
import br.com.ffit.comanda.model.Produto;
import br.com.ffit.comanda.service.EstabelecimentoService;

@Controller
@RequestMapping(value = "/estabelecimento")
public class EstabelecimentoController {

	@Autowired
	private EstabelecimentoService estabelecimentoService;

	@RequestMapping(value = "/inserirProduto", method = RequestMethod.POST)
	public @ResponseBody Produto inserirProduto(@RequestBody ProdutoTO produtoTO) {
		return estabelecimentoService.inserirProduto(produtoTO);
	}

	@RequestMapping(value = "/inserirEstabelecimento", method = RequestMethod.POST)
	public @ResponseBody JSONResponse inserirEstabelecimento(@RequestBody Estabelecimento estabelecimento) {
		JSONResponse jsonResponse = new JSONResponse();
		try {
			estabelecimentoService.inserirEstabelecimento(estabelecimento);
			jsonResponse.setSuccess(true);
		} catch (BussinessException e) {
			jsonResponse.setErrorMessage(e.getMessage());
			jsonResponse.setSuccess(false);
		}

		return jsonResponse;
	}

}
