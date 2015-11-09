package br.com.ffit.comanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ffit.comanda.dto.ProdutoTO;
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
			throws BussinessException {
		if (estabelecimentoRepository.findByLogin(estabelecimento.getLogin()) == null) {
			estabelecimentoRepository.save(estabelecimento);
		} else {
			throw new BussinessException("j� existe restaurante com esse login");
		}
	}

}
