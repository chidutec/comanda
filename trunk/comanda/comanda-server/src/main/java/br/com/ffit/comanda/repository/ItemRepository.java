package br.com.ffit.comanda.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.ffit.comanda.model.Conta;
import br.com.ffit.comanda.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {
	List<Item> findByConta(Conta conta);
}
