package br.com.ffit.comanda.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue
	private Long id;
	private String nome;

	@ManyToMany
	@JoinTable(name = "usuario_conta", joinColumns = { @JoinColumn(name = "usuario_id") }, inverseJoinColumns = { @JoinColumn(name = "conta_id") })
	private Set<Conta> contas;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private Set<UsuarioItem> usuarioItens;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<UsuarioItem> getUsuarioItens() {
		return usuarioItens;
	}

	public void setUsuarioItens(Set<UsuarioItem> usuarioItens) {
		this.usuarioItens = usuarioItens;
	}

}
