package br.com.ffit.comanda.service;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

import java.util.List;

import br.com.ffit.comanda.rest.RestClient;
import br.com.ffit.comanda.to.EstabelecimentoTO;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.ProdutoTO;

@EBean
public class EstabelecimentoService {

    @RestService
    RestClient restClient;

   public JSONResponse<List<EstabelecimentoTO>> buscaRestaurantes() {
        return restClient.buscaRestaurantes();
   }

    public JSONResponse<List<ProdutoTO>> buscaProdutos(Long idEstabelecimento) {
        return restClient.buscaProdutos(idEstabelecimento);
    }

}
