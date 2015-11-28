package br.com.ffit.comanda.to;

import java.math.BigDecimal;

public class ParticipanteTO {
	
	Long idUsuario;
	String nome;
	String fotoUrl;
	BigDecimal parcial;
	
	public ParticipanteTO() {
		
	}

	public ParticipanteTO(Long idUsuario, String nome, String fotoUrl) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.fotoUrl = fotoUrl;
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFotoUrl() {
		return fotoUrl;
	}
	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}
	public BigDecimal getParcial() {
		return parcial;
	}
	public void setParcial(BigDecimal parcial) {
		this.parcial = parcial;
	}
	
	
}
