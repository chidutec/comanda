package br.com.ffit.comanda.service;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.androidannotations.annotations.EBean;
import org.json.JSONObject;

import java.math.BigDecimal;

import br.com.ffit.comanda.rest.RestClient;
import cz.msebera.android.httpclient.Header;

@EBean
public class EstabelecimentoService {

    private final String ESTABELECIMENTO = "/estabelecimento";

    public void inserirProduto(String nome, String descricao, BigDecimal preco) {
        try {
            JSONObject produto = new JSONObject();
            produto.put("nome", nome);
            produto.put("descricao", descricao);
            produto.put("preco", preco);

            JSONObject estabelecimento = new JSONObject();
            estabelecimento.put("id", 1);
            produto.put("estabelecimento", estabelecimento);

            RestClient.post(ESTABELECIMENTO + "/inserirProduto", produto, new AsyncHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    System.out.println("sucess");
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    System.out.println("error");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
