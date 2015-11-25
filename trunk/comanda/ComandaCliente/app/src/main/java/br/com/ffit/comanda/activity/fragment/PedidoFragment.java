package br.com.ffit.comanda.activity.fragment;


import android.support.v4.app.Fragment;
import android.widget.TextView;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import ffit.com.br.comanda.R;


@EFragment(R.layout.fragment_pedido)
public class PedidoFragment extends Fragment {


    @ViewById(R.id.pedido)
    TextView pedido;


}
