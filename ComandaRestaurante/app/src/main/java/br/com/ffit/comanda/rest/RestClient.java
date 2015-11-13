package br.com.ffit.comanda.rest;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

import br.com.ffit.comanda.to.EstabelecimentoTO;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.LoginTO;
import br.com.ffit.comanda.to.ProdutoTO;

@Rest(rootUrl = "http://192.168.1.102:8080/comanda-server", converters = {MappingJackson2HttpMessageConverter.class})
public interface RestClient {

    @Post("/estabelecimento/inserirProduto")
    void inserirProduto(ProdutoTO produtoTO);

    @Post("/estabelecimento/fazerLogin")
    JSONResponse<EstabelecimentoTO> fazerLogin(LoginTO loginTO);

    @Post("/estabelecimento/verificaDisponibilidadeLogin")
    JSONResponse<LoginTO> verificaDisponibilidadeLogin(LoginTO loginTO);

    @Post("/estabelecimento/cadastraEstabelecimento")
    JSONResponse<EstabelecimentoTO> cadastraEstabelecimento(EstabelecimentoTO estabelecimentoTO);

    @Get("/estabelecimento/buscaProdutos/{idEstabelecimento}")
    JSONResponse<List<ProdutoTO>> buscaProdutos(Long idEstabelecimento);

}
