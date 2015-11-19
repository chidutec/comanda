package br.com.ffit.comanda.service;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

import br.com.ffit.comanda.rest.RestClient;

@EBean
public class EstabelecimentoService {

    @RestService
    RestClient restClient;


}
