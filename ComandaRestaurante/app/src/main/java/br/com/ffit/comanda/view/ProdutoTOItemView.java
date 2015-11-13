package br.com.ffit.comanda.view;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import br.com.ffit.comanda.service.EstabelecimentoService;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.ProdutoTO;
import ffit.com.br.comanda.R;

@EViewGroup(R.layout.produto_item)
public class ProdutoTOItemView extends LinearLayout{

    @ViewById
    TextView produtoNomeView;

    @ViewById
    TextView produtoDescricaoView;

    @ViewById
    TextView produtoPrecoView;

    @Bean
    EstabelecimentoService estabelecimentoService;

    public ProdutoTOItemView(Context context){
        super(context);
    }

    public void bind(ProdutoTO produtoTO){
        produtoNomeView.setText(produtoTO.getNome());
        produtoDescricaoView.setText(produtoTO.getDescricao());
        produtoPrecoView.setText(produtoTO.getPreco().toString());

        Button btnExcluirProduto = (Button)this.findViewById(R.id.btnExcluirProduto);
        btnExcluirProduto.setOnClickListener((v) -> excluirProduto(produtoTO.getId()));
    }

    @Background
    public void excluirProduto(Long idProduto) {
       JSONResponse jsonResponse = estabelecimentoService.excluirProduto(idProduto);
       callBackExcluirProduto(jsonResponse);
    }

    @UiThread
    public void callBackExcluirProduto(JSONResponse jsonResponse) {
        Toast.makeText(getContext(), jsonResponse.getMessage(), Toast.LENGTH_SHORT).show();
    }

}
