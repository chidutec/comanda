package br.com.ffit.comanda.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "estabelecimento")
public class Estabelecimento {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String login;
	private String senha;
	private String nome;
	
	@OneToMany(mappedBy = "estabelecimento", cascade = CascadeType.ALL)
	private Set<Conta> contas;
	
	@OneToMany(mappedBy = "estabelecimento", cascade = CascadeType.ALL)
	private Set<Produto> produtos;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
