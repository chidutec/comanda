package br.com.ffit.comanda.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import br.com.ffit.comanda.to.ContaTO;
import ffit.com.br.comanda.R;

@EViewGroup(R.layout.conta_item)
public class ContaTOItemView extends LinearLayout{

    @ViewById
    TextView tvContaRestaurante;

    @ViewById
    TextView tvContaDtAbertura;

    @ViewById
    TextView tvContaValorParcial;

    private ContaTO contaTO;

    public ContaTOItemView(Context context){
        super(context);
    }

    public void bind(ContaTO contaTO) {
        this.setContaTO(contaTO);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        tvContaRestaurante.setText(contaTO.getNomeRestaurante());
        tvContaDtAbertura.setText(sdf.format(contaTO.getDataAberturaUsuario()));
        tvContaValorParcial.setText(NumberFormat.getCurrencyInstance().format(contaTO.getValorParcial()));
    }

    public ContaTO getContaTO() {
        return contaTO;
    }

    public void setContaTO(ContaTO contaTO) {
        this.contaTO = contaTO;
    }

}
