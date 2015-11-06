package br.com.ffit.comanda.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Conta {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private List<Item> itens;
	private List<Usuario> usuarios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}
