package br.com.ffit.comanda.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue
	private Long id;
	private Integer quantidade;
	
	@OneToOne
	private Produto produto;
	
	@ManyToOne
	private Conta conta;

	@Transient
	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
	private Set<UsuarioItem> usuarioItens;

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<UsuarioItem> getUsuarioItens() {
		return usuarioItens;
	}

	public void setUsuarioItens(Set<UsuarioItem> usuarioItens) {
		this.usuarioItens = usuarioItens;
	}

}
