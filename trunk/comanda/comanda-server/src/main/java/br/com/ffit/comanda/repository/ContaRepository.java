package br.com.ffit.comanda.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.ffit.comanda.model.Conta;

public interface ContaRepository extends CrudRepository<Conta, Long> {
	
}
