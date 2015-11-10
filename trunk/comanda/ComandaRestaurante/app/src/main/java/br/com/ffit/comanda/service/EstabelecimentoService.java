package br.com.ffit.comanda.service;


import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

import br.com.ffit.comanda.activity.LoginRestauranteActivity;
import br.com.ffit.comanda.rest.RestClient;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.LoginTO;
import br.com.ffit.comanda.to.ProdutoTO;

@EBean
public class EstabelecimentoService {

    @RestService
    RestClient restClient;

    @Background
    public void inserirProduto(ProdutoTO produtoTO) {
        restClient.inserirProduto(produtoTO);
    }

    @Background
    public void fazerLogin(LoginTO loginTO, LoginRestauranteActivity activity) {
        JSONResponse jsonResponse = restClient.fazerLogin(loginTO);
        activity.setJSONResponse(jsonResponse);
    }



}
