package br.com.ffit.comanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ffit.comanda.exception.BussinessException;
import br.com.ffit.comanda.model.Estabelecimento;
import br.com.ffit.comanda.model.Produto;
import br.com.ffit.comanda.repository.EstabelecimentoRepository;
import br.com.ffit.comanda.repository.ProdutoRepository;

@Service
public class EstabelecimentoService {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	EstabelecimentoRepository estabelecimentoRepository;

	public Produto inserirProduto(Produto produto) {
		return produtoRepository.save(produto);
	}

	public void inserirEstabelecimento(Estabelecimento estabelecimento)
			throws BussinessException {
		if (estabelecimentoRepository.findByLogin(estabelecimento.getLogin()) == null) {
			estabelecimentoRepository.save(estabelecimento);
		} else {
			throw new BussinessException("já existe restaurante com esse login");
		}
	}

}
