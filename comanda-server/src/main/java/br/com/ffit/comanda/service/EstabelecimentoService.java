package br.com.ffit.comanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ffit.comanda.model.Estabelecimento;
import br.com.ffit.comanda.model.Produto;
import br.com.ffit.comanda.repository.EstabelecimentoRepository;
import br.com.ffit.comanda.repository.ProdutoRepository;
import br.com.ffit.comanda.to.EstabelecimentoTO;
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

	public JSONResponse inserirEstabelecimento(EstabelecimentoTO estabelecimentoTO) {
		JSONResponse jsonResponse = new JSONResponse();
		
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setLogin(estabelecimentoTO.getLogin());
		estabelecimento.setSenha(estabelecimentoTO.getSenha());
		estabelecimento.setNome(estabelecimentoTO.getNome());
		
		estabelecimentoRepository.save(estabelecimento);
		jsonResponse.setSuccess(true);
		jsonResponse.setMessage("Estabelecimento criado");
		return jsonResponse;
	}

	public JSONResponse fazerLogin(LoginTO loginTO) {
		JSONResponse jsonResponse = new JSONResponse();
		Estabelecimento estabelecimento = estabelecimentoRepository
				.findByLogin(loginTO.getLogin());
		if (estabelecimento != null) {
			if (estabelecimento.getSenha().equals(loginTO.getSenha())) {
				jsonResponse.setSuccess(true);
			} else {
				jsonResponse.setSuccess(false);
				jsonResponse.setMessage("Senha invalida");
			}
		} else {
			jsonResponse.setSuccess(false);
			jsonResponse.setMessage("Login nao cadastrado");
		}
		return jsonResponse;
	}

	public JSONResponse verificaDisponibilidadeLogin(LoginTO loginTO) {
		JSONResponse jsonResponse = new JSONResponse();
		Estabelecimento estabelecimento = estabelecimentoRepository
				.findByLogin(loginTO.getLogin());
		if (estabelecimento == null) {
			jsonResponse.setSuccess(true);
		} else {
			jsonResponse.setSuccess(false);
			jsonResponse.setMessage("Login nao disponivel");
		}
		return jsonResponse;
	}

}
