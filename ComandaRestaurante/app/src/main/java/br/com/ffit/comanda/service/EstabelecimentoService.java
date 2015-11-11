package br.com.ffit.comanda.service;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

import br.com.ffit.comanda.rest.RestClient;
import br.com.ffit.comanda.to.EstabelecimentoTO;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.LoginTO;

@EBean
public class EstabelecimentoService {

    @RestService
    RestClient restClient;

    public JSONResponse fazerLogin(LoginTO loginTO) {
        return restClient.fazerLogin(loginTO);
    }

    public JSONResponse verificaDisponibilidadeLogin(LoginTO loginTO) {
        return restClient.verificaDisponibilidadeLogin(loginTO);
    }

    public JSONResponse cadastraEstabelecimento(EstabelecimentoTO estabelecimentoTO) {
        LoginTO loginTO = new LoginTO();
        loginTO.setLogin(estabelecimentoTO.getLogin());
        JSONResponse jsonResponse = verificaDisponibilidadeLogin(loginTO);
        if(jsonResponse.getSuccess()) {
            return restClient.cadastraEstabelecimento(estabelecimentoTO);
        } else {
            return jsonResponse;
        }
    }
}
