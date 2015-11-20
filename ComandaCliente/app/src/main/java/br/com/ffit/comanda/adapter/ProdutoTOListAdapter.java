package br.com.ffit.comanda.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import br.com.ffit.comanda.to.ProdutoTO;
import br.com.ffit.comanda.view.ProdutoTOItemView;
import br.com.ffit.comanda.view.ProdutoTOItemView_;

@EBean
public class ProdutoTOListAdapter extends BaseAdapter {

    private List<ProdutoTO> produtoTOs;

    @RootContext
    Context context;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ProdutoTOItemView produtoItemView;
        if (convertView == null) {
            produtoItemView = ProdutoTOItemView_.build(context);
        } else {
            produtoItemView = (ProdutoTOItemView) convertView;
        }

        produtoItemView.bind(getItem(position));

        return produtoItemView;
    }


    @Override
    public int getCount() {
        return produtoTOs.size();
    }

    @Override
    public ProdutoTO getItem(int position) {
        return produtoTOs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public List<ProdutoTO> getProdutoTOs() {
        return produtoTOs;
    }

    public void setProdutoTOs(List<ProdutoTO> produtoTOs) {
        this.produtoTOs = produtoTOs;
    }

}
