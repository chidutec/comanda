package br.com.ffit.comanda.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "estabelecimento")
public class Estabelecimento {
	
	@Id
	@GeneratedValue
	private Long id;
	private String Nome;
	
	@OneToMany(mappedBy = "estabelecimento")
	private Set<Conta> contas;
	
	@OneToMany(mappedBy = "estabelecimento")
	private Set<Produto> produtos;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	
	public Set<Conta> getContas() {
		return contas;
	}
	public void setContas(Set<Conta> contas) {
		this.contas = contas;
	}
	
	public Set<Produto> getProdutos() {
		return produtos;
	}
	
	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}

}
