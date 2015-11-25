package br.com.ffit.comanda.activity.fragment;


import android.app.Fragment;
import android.widget.TextView;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import ffit.com.br.comanda.R;


@EFragment(R.layout.fragment_mesa)
public class MesaFragment extends Fragment {

    @ViewById(R.id.mesa)
    TextView mesa;


}
