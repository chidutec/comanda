package br.com.ffit.comanda.to;

import java.io.Serializable;

public class EstabelecimentoTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	Long id;
	String login;
    String senha;
    String nome;

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
}
