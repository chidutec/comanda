package br.com.ffit.comanda.service;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

import java.util.List;

import br.com.ffit.comanda.rest.RestClient;
import br.com.ffit.comanda.to.EstabelecimentoTO;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.LoginTO;
import br.com.ffit.comanda.to.ProdutoTO;

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

    public JSONResponse<EstabelecimentoTO> cadastraEstabelecimento(EstabelecimentoTO estabelecimentoTO) {
        return restClient.cadastraEstabelecimento(estabelecimentoTO);
    }

    public JSONResponse<List<ProdutoTO>> buscaProdutos(Long idEstabelecimento) {
        return restClient.buscaProdutos(idEstabelecimento);
    }

    public JSONResponse<EstabelecimentoTO> excluirProduto(Long idProduto) {
        return restClient.excluirProduto(idProduto);
    }
}
