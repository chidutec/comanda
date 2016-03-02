package br.com.ffit.comanda.service;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

import java.util.ArrayList;
import java.util.List;

import br.com.ffit.comanda.global.GlobalClass;
import br.com.ffit.comanda.rest.RestClient;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.UsuarioTO;

@EBean
public class UsuarioService {

    @RestService
    RestClient restClient;

    @Bean
    FacebookService facebookService;

    @App
    GlobalClass globalClass;

    public JSONResponse<UsuarioTO> fazerLogin() {
        UsuarioTO usuarioTO = facebookService.getUserInfo();
        JSONResponse<UsuarioTO> jsonResponse = restClient.fazerLogin(usuarioTO);
        globalClass.setUsuarioLogado(jsonResponse.getObj());
        return jsonResponse;
    }

    public UsuarioTO getUsuarioLogado() {
        UsuarioTO usuarioTO = facebookService.getUserInfo();
        return restClient.buscaUsuarioPeloIdFacebook(usuarioTO.getIdFacebook()).getObj();
    }

    public List<UsuarioTO> buscaAmigosPeloToken(String token) {
        List<UsuarioTO> todosAmigos = facebookService.getUserFriends();

        List<UsuarioTO> amigosComToken = new ArrayList<>();
        for(UsuarioTO usuarioTO : todosAmigos) {
            if(usuarioTO.getNome().equals(token)) {
                amigosComToken.add(usuarioTO);
            }
        }

        return amigosComToken;
    }


}
