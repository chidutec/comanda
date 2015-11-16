package br.com.ffit.comanda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	EstabelecimentoRepository estabelecimentoRepository;
	

	@Autowired
	ProdutoRepository produtoRepository;


	@Transactional
	public JSONResponse<EstabelecimentoTO> cadatraEstabelecimento(
			EstabelecimentoTO estabelecimentoTO) {
		
		LoginTO loginTO = new LoginTO();
		loginTO.setLogin(estabelecimentoTO.getLogin());
		JSONResponse verificaLoginResponse = verificaDisponibilidadeLogin(loginTO);
		if(verificaLoginResponse.getSuccess()) {
			JSONResponse<EstabelecimentoTO> jsonResponse = new JSONResponse<EstabelecimentoTO>();
			Estabelecimento estabelecimento = new Estabelecimento();
			estabelecimento.setLogin(estabelecimentoTO.getLogin());
			estabelecimento.setSenha(estabelecimentoTO.getSenha());
			estabelecimento.setNome(estabelecimentoTO.getNome());
	
			Estabelecimento aux = estabelecimentoRepository.save(estabelecimento);
			estabelecimentoTO.setId(aux.getId());
			jsonResponse.setSuccess(true);
			jsonResponse.setMessage("Estabelecimento criado");
		
			jsonResponse.setObj(estabelecimentoTO);
		
			return jsonResponse;
		} else {
			return verificaLoginResponse;
		}
	}

	public JSONResponse<EstabelecimentoTO> fazerLogin(LoginTO loginTO) {
		JSONResponse<EstabelecimentoTO> jsonResponse = new JSONResponse<EstabelecimentoTO>();
		Estabelecimento estabelecimento = estabelecimentoRepository
				.findByLogin(loginTO.getLogin());
		if (estabelecimento != null) {
			if (estabelecimento.getSenha().equals(loginTO.getSenha())) {
				EstabelecimentoTO estabelecimentoTO  = new EstabelecimentoTO();
				estabelecimentoTO.setLogin(estabelecimento.getLogin());
				estabelecimentoTO.setNome(estabelecimento.getNome());
				estabelecimentoTO.setSenha(estabelecimento.getSenha());
				estabelecimentoTO.setId(estabelecimento.getId());
				jsonResponse.setObj(estabelecimentoTO);
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

	public List<ProdutoTO> buscaProdutos(Long idEstabelecimento) {
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setId(idEstabelecimento);
		
		List<Produto> produtos = produtoRepository.findByEstabelecimento(estabelecimento);
				
		List<ProdutoTO> produtosTO = new ArrayList<ProdutoTO>();
		for(Produto produto: produtos) {
			ProdutoTO produtoTO = new ProdutoTO();
			produtoTO.setNome(produto.getNome());
			produtoTO.setDescricao(produto.getDescricao());
			produtoTO.setPreco(produto.getPreco());
			produtoTO.setId(produto.getId());
			produtosTO.add(produtoTO);
		}
		
		return produtosTO;
	}

}
