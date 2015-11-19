package br.com.ffit.comanda.service;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

import br.com.ffit.comanda.rest.RestClient;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.UserTO;

@EBean
public class UsuarioService {

    @RestService
    RestClient restClient;

    @Bean
    FacebookService facebookService;

    public JSONResponse fazerLogin() {
        UserTO userTO = facebookService.getUserInfo();
        return restClient.fazerLogin(userTO);
    }


}
