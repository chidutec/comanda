package br.com.ffit.comanda.service;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

import br.com.ffit.comanda.rest.RestClient;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.UsuarioTO;

@EBean
public class UsuarioService {

    @RestService
    RestClient restClient;

    @Bean
    FacebookService facebookService;

    public JSONResponse<UsuarioTO> fazerLogin() {
        UsuarioTO usuarioTO = facebookService.getUserInfo();
        return restClient.fazerLogin(usuarioTO);
    }


}
