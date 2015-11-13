package br.com.ffit.comanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ffit.comanda.exception.BusinessException;
import br.com.ffit.comanda.model.Estabelecimento;
import br.com.ffit.comanda.model.Produto;
import br.com.ffit.comanda.repository.ProdutoRepository;
import br.com.ffit.comanda.to.ProdutoTO;


@Service
public class ProdutoService {
	
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Transactional
	public void inserirProduto(ProdutoTO produtoTO) throws BusinessException {
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setId(produtoTO.getIdEstabelecimento());
		if(produtoRepository.findByNomeAndEstabelecimento(produtoTO.getNome(), estabelecimento) == null) {
			Produto produto = new Produto();
			produto.setNome(produtoTO.getNome());
			produto.setDescricao(produtoTO.getDescricao());
			produto.setPreco(produtoTO.getPreco());
			produto.setEstabelecimento(estabelecimento);
			produtoRepository.save(produto);
		} else {
			throw new BusinessException("Produto ja cadastrado com este nome");
		}
	}
}
