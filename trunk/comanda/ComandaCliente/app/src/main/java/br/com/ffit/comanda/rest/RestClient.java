package br.com.ffit.comanda.rest;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

import br.com.ffit.comanda.to.AbrirContaTO;
import br.com.ffit.comanda.to.EstabelecimentoTO;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.ProdutoTO;
import br.com.ffit.comanda.to.UsuarioTO;

@Rest(rootUrl = "http://192.168.1.102:8080/comanda-server", converters = {MappingJackson2HttpMessageConverter.class})
public interface RestClient {

    @Post("/usuario/fazerLogin")
    JSONResponse<UsuarioTO> fazerLogin(UsuarioTO usuarioTO);

    @Get("/estabelecimento/buscaRestaurantes")
    JSONResponse<List<EstabelecimentoTO>> buscaRestaurantes();

    @Get("/estabelecimento/buscaProdutos/{idEstabelecimento}")
    JSONResponse<List<ProdutoTO>> buscaProdutos(Long idEstabelecimento);

    @Post("/conta/abrirConta")
    JSONResponse abrirConta(AbrirContaTO abrirContaTO);

}
