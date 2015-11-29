package br.com.ffit.comanda.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "conta")
public class Conta {

	@Id
	@GeneratedValue
	private Long id;

	private Date dataAbertura;
	private Date dataFechamento;

	@Transient
	@OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
	private Set<Item> itens;

//	@ManyToMany
//	@JoinTable(name = "usuario_conta", joinColumns = { @JoinColumn(name = "conta_id") }, inverseJoinColumns = { @JoinColumn(name = "usuario_id") })
//	private Set<Usuario> usuarios;
	
	@Transient
	@OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
	private Set<UsuarioConta> usuarioContas;

	@ManyToOne
	private Estabelecimento estabelecimento;

	public Long getId() {
		return id;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Set<Item> getItens() {
		return itens;
	}

	public void setItens(Set<Item> itens) {
		this.itens = itens;
	}


	public Set<UsuarioConta> getUsuarioContas() {
		return usuarioContas;
	}

	public void setUsuarioContas(Set<UsuarioConta> usuarioContas) {
		this.usuarioContas = usuarioContas;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
	
	
	@Transient
	public boolean isAtiva() {
		return (this.dataFechamento == null);
	}

}
