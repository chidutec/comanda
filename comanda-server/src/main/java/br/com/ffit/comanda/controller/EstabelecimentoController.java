package br.com.ffit.comanda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ffit.comanda.exception.BusinessException;
import br.com.ffit.comanda.service.EstabelecimentoService;
import br.com.ffit.comanda.service.ProdutoService;
import br.com.ffit.comanda.to.EstabelecimentoTO;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.LoginTO;
import br.com.ffit.comanda.to.ProdutoTO;

@Controller
@RequestMapping(value = "/estabelecimento")
public class EstabelecimentoController {

	@Autowired
	private EstabelecimentoService estabelecimentoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value = "/buscaRestaurantes")
	public @ResponseBody JSONResponse<List<EstabelecimentoTO>> buscaRestaurantes() {
		JSONResponse<List<EstabelecimentoTO>> jsonResponse = new JSONResponse<List<EstabelecimentoTO>>();
		jsonResponse.setObj(estabelecimentoService.buscaRestaurantes());
		jsonResponse.setSuccess(true);
		return jsonResponse;
	}
	
	@RequestMapping(value = "/buscaProdutos/{idEstabelecimento}",  method = RequestMethod.GET)
    public @ResponseBody JSONResponse<List<ProdutoTO>> buscaProdutos(@PathVariable Long idEstabelecimento) {
		JSONResponse<List<ProdutoTO>> jsonResponse = new JSONResponse<List<ProdutoTO>>();
		jsonResponse.setObj(estabelecimentoService.buscaProdutos(idEstabelecimento));
		jsonResponse.setSuccess(true);
		return jsonResponse;
	}

	@RequestMapping(value = "/cadastraEstabelecimento", method = RequestMethod.POST)
	public @ResponseBody JSONResponse<EstabelecimentoTO> cadastraEstabelecimeto(@RequestBody EstabelecimentoTO estabelecimentoTO) {
		return estabelecimentoService.cadatraEstabelecimento(estabelecimentoTO);
	}
	
	@RequestMapping(value="/fazerLogin", method = RequestMethod.POST)
	public @ResponseBody JSONResponse<EstabelecimentoTO> fazerLogin(@RequestBody LoginTO loginTO) {
		return estabelecimentoService.fazerLogin(loginTO);
	}
	
	@RequestMapping(value="/verificaDisponibilidadeLogin", method = RequestMethod.POST)
	public @ResponseBody JSONResponse verificaDisponibilidadeLogin(@RequestBody LoginTO loginTO) {
		return estabelecimentoService.verificaDisponibilidadeLogin(loginTO);
	}
	
	@RequestMapping(value="/excluirProduto/{idProduto}", method = RequestMethod.DELETE)
	public @ResponseBody JSONResponse excluirProduto(@PathVariable Long idProduto) {
		JSONResponse<EstabelecimentoTO> jsonResponse = new JSONResponse<EstabelecimentoTO>();
		try{
			produtoService.excluirProduto(idProduto);
			jsonResponse.setSuccess(true);
			jsonResponse.setMessage("Produto excluido com sucesso");
		} catch (Exception e) {
			jsonResponse.setSuccess(false);
			jsonResponse.setMessage("Erro ao excluir o produto");
		}
		return jsonResponse;
	}
	
	@RequestMapping(value="/cadastraProduto", method = RequestMethod.POST)
	public @ResponseBody JSONResponse cadastraProduto(@RequestBody ProdutoTO produtoTO) {
		JSONResponse jsonResponse = new JSONResponse();
		try {			
			produtoService.cadastraProduto(produtoTO);
			jsonResponse.setSuccess(true);
			jsonResponse.setMessage("Produto cadastrado com sucesso");
		} catch (BusinessException e) {
			jsonResponse.setSuccess(false);
			jsonResponse.setMessage(e.getMessage());
		}
		return jsonResponse;
	}
}
