package br.com.ffit.comanda.rest;

import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.UsuarioTO;

@Rest(rootUrl = "http://192.168.1.102:8080/comanda-server", converters = {MappingJackson2HttpMessageConverter.class})
public interface RestClient {

    @Post("/usuario/fazerLogin")
    JSONResponse<UsuarioTO> fazerLogin(UsuarioTO usuarioTO);

}
