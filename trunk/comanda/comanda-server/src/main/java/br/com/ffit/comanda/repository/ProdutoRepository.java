package br.com.ffit.comanda.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.ffit.comanda.model.Estabelecimento;
import br.com.ffit.comanda.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {
	
	Produto findByNomeAndEstabelecimento(String nome, Estabelecimento estabeleciemento);
	
	List<Produto> findByEstabelecimento(Estabelecimento estabelecimento);
	
}