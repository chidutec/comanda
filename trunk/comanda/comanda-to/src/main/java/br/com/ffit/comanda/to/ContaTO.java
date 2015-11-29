package br.com.ffit.comanda.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ContaTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nomeRestaurante;
	private Date dataAberturaUsuario;
	private BigDecimal valorParcial;
	private BigDecimal valorTotal;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeRestaurante() {
		return nomeRestaurante;
	}

	public void setNomeRestaurante(String nomeRestaurante) {
		this.nomeRestaurante = nomeRestaurante;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getDataAberturaUsuario() {
		return dataAberturaUsuario;
	}

	public void setDataAberturaUsuario(Date dataAberturaUsuario) {
		this.dataAberturaUsuario = dataAberturaUsuario;
	}

	public BigDecimal getValorParcial() {
		return valorParcial;
	}

	public void setValorParcial(BigDecimal valorParcial) {
		this.valorParcial = valorParcial;
	}
}
