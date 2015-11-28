package br.com.ffit.comanda.service;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

import java.util.List;

import br.com.ffit.comanda.rest.RestClient;
import br.com.ffit.comanda.to.AbrirContaTO;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.ParticipanteTO;

@EBean
public class ContaService {

    @RestService
    RestClient restClient;

    @Bean
    FacebookService facebookService;

    public JSONResponse abrirConta(AbrirContaTO abrirContaTO) {
        return restClient.abrirConta(abrirContaTO);
    }

    public JSONResponse<List<ParticipanteTO>> buscaParticipantes(Long id) {
        return restClient.buscaParticipantes(id);
    }

}
