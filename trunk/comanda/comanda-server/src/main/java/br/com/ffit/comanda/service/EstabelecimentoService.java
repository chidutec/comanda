package br.com.ffit.comanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ffit.comanda.model.Produto;
import br.com.ffit.comanda.repository.ProdutoRepository;


@Service
public class EstabelecimentoService {

	@Autowired
	ProdutoRepository produtoRepository;

	public Produto inserirProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
}
