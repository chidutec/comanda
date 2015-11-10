package br.com.ffit.comanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ffit.comanda.exception.BusinessException;
import br.com.ffit.comanda.model.Estabelecimento;
import br.com.ffit.comanda.model.Produto;
import br.com.ffit.comanda.model.Usuario;
import br.com.ffit.comanda.repository.EstabelecimentoRepository;
import br.com.ffit.comanda.repository.ProdutoRepository;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.LoginTO;
import br.com.ffit.comanda.to.ProdutoTO;

@Service
public class EstabelecimentoService {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	EstabelecimentoRepository estabelecimentoRepository;

	public Produto inserirProduto(ProdutoTO produtoTO) {
		Produto produto = new Produto();
		produto.setNome(produtoTO.getNome());
		produto.setDescricao(produtoTO.getDescricao());
		produto.setPreco(produtoTO.getPreco());
		
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setId(produtoTO.getIdEstabelecimento());
		
		produto.setEstabelecimento(estabelecimento);
		return produtoRepository.save(produto);
	}

	public void inserirEstabelecimento(Estabelecimento estabelecimento)
			throws BusinessException {
		if (estabelecimentoRepository.findByLogin(estabelecimento.getLogin()) == null) {
			estabelecimentoRepository.save(estabelecimento);
		} else {
			throw new BusinessException("j� existe restaurante com esse login");
		}
	}
	
	public JSONResponse fazerLogin(LoginTO loginTO) throws BusinessException{
		Estabelecimento estabelecimento = estabelecimentoRepository.findByLogin(loginTO.getLogin());
		JSONResponse jsonResponse = new JSONResponse();
		if(estabelecimento != null) {
			if(estabelecimento.getSenha().equals(loginTO.getSenha())) {
				jsonResponse.setSuccess(true);
			} else {
				jsonResponse.setSuccess(false);
				jsonResponse.setErrorMessage("Senha invalida");
			}
		} else {
			jsonResponse.setSuccess(false);
			jsonResponse.setErrorMessage("EStabelecimento nao cadastrado");
		}
		return jsonResponse;
	}

}
