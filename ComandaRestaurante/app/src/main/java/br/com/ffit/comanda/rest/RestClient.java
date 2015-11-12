package br.com.ffit.comanda.rest;

import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import br.com.ffit.comanda.to.EstabelecimentoTO;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.LoginTO;
import br.com.ffit.comanda.to.ProdutoTO;

@Rest(rootUrl = "http://192.168.100.84:8080/comanda-server", converters = {MappingJackson2HttpMessageConverter.class})
public interface RestClient {

    @Post("/estabelecimento/inserirProduto")
    void inserirProduto(ProdutoTO produtoTO);

    @Post("/estabelecimento/fazerLogin")
    JSONResponse fazerLogin(LoginTO loginTO);

    @Post("/estabelecimento/verificaDisponibilidadeLogin")
    JSONResponse verificaDisponibilidadeLogin(LoginTO loginTO);

    @Post("/estabelecimento/cadastraEstabelecimento")
    JSONResponse cadastraEstabelecimento(EstabelecimentoTO estabelecimentoTO);

}
