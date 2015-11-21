package br.com.ffit.comanda.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.ffit.comanda.model.Estabelecimento;

public interface EstabelecimentoRepository extends CrudRepository<Estabelecimento, Long> {
	
	Estabelecimento findByLogin(String login);
	
	List<Estabelecimento> findAll();
	
	Estabelecimento findById(Long id);
}