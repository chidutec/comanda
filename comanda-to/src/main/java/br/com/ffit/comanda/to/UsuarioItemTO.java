package br.com.ffit.comanda.to;

import java.math.BigDecimal;

public class UsuarioItemTO {

	private BigDecimal preco;
	private Integer qtdTotal;
	private BigDecimal qtdUsuario;

	public UsuarioItemTO(BigDecimal preco, Integer qtdTotal,
			BigDecimal qtdUsuario) {
		this.preco = preco;
		this.qtdTotal = qtdTotal;
		this.qtdUsuario = qtdUsuario;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQtdTotal() {
		return qtdTotal;
	}

	public void setQtdTotal(Integer qtdTotal) {
		this.qtdTotal = qtdTotal;
	}

	public BigDecimal getQtdUsuario() {
		return qtdUsuario;
	}

	public void setQtdUsuario(BigDecimal qtdUsuario) {
		this.qtdUsuario = qtdUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		result = prime * result
				+ ((qtdTotal == null) ? 0 : qtdTotal.hashCode());
		result = prime * result
				+ ((qtdUsuario == null) ? 0 : qtdUsuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioItemTO other = (UsuarioItemTO) obj;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		if (qtdTotal == null) {
			if (other.qtdTotal != null)
				return false;
		} else if (!qtdTotal.equals(other.qtdTotal))
			return false;
		if (qtdUsuario == null) {
			if (other.qtdUsuario != null)
				return false;
		} else if (!qtdUsuario.equals(other.qtdUsuario))
			return false;
		return true;
	}

}
