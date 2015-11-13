package br.com.ffit.comanda.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

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

    public ProdutoTOItemView(Context context){
        super(context);
    }

    public void bind(ProdutoTO produtoTO){
        produtoNomeView.setText(produtoTO.getNome());
        produtoDescricaoView.setText(produtoTO.getDescricao());
        produtoPrecoView.setText(produtoTO.getPreco().toString());
    }
}
