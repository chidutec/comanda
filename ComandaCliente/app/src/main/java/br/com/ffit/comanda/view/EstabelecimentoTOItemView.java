package br.com.ffit.comanda.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import br.com.ffit.comanda.to.EstabelecimentoTO;
import ffit.com.br.comanda.R;

@EViewGroup(R.layout.restaurante_item)
public class EstabelecimentoTOItemView extends LinearLayout{

    @ViewById
    TextView restauranteNomeView;

    private EstabelecimentoTO estabelecimentoTO;

    public EstabelecimentoTOItemView(Context context){
        super(context);
    }

    public void bind(EstabelecimentoTO estabelecimentoTO) {
        this.setEstabelecimentoTO(estabelecimentoTO);
        restauranteNomeView.setText(estabelecimentoTO.getNome());
    }

    public EstabelecimentoTO getEstabelecimentoTO() {
        return estabelecimentoTO;
    }

    public void setEstabelecimentoTO(EstabelecimentoTO estabelecimentoTO) {
        this.estabelecimentoTO = estabelecimentoTO;
    }

}
