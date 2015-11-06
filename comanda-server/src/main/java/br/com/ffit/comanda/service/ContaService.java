package br.com.ffit.comanda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ffit.comanda.model.Conta;
import br.com.ffit.comanda.model.Item;
import br.com.ffit.comanda.repository.ItemRepository;


@Service
public class ContaService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public List<Item> getItensById(Long id) {
		Conta conta = new Conta();
		conta.setId(id);
		return itemRepository.findByConta(conta);
	}

}
