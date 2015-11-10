package br.com.ffit.comanda.service;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.SupposeBackground;
import org.androidannotations.annotations.rest.RestService;

import br.com.ffit.comanda.rest.RestClient;
import br.com.ffit.comanda.to.LoginTO;

@EBean
public class UsuarioService {

    @RestService
    RestClient restClient;

    @SupposeBackground
    public void fazerLogin(String login, String senha) {
        LoginTO loginTO = new LoginTO();
        loginTO.setLogin(login);
        loginTO.setSenha(senha);
        restClient.fazerLogin(loginTO);
    }

}
