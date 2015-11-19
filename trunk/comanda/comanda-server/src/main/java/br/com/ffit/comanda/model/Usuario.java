package br.com.ffit.comanda.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue
	private Long id;
	private Long idFacebook;
	private String name;
	@Column(columnDefinition = "mediumblob")
	private byte[] foto;

	@Transient
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_conta", joinColumns = { @JoinColumn(name = "usuario_id") }, inverseJoinColumns = {
			@JoinColumn(name = "conta_id") })
	private Set<Conta> contas;

	@Transient
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private Set<UsuarioItem> usuarioItens;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdFacebook() {
		return idFacebook;
	}

	public void setIdFacebook(Long idFacebook) {
		this.idFacebook = idFacebook;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
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
