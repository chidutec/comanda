package br.com.ffit.comanda.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.ffit.comanda.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {
	
}