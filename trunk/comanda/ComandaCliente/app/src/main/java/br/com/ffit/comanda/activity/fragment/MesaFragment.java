package br.com.ffit.comanda.activity.fragment;


import android.support.v4.app.Fragment;
import android.widget.Toast;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;

import br.com.ffit.comanda.global.GlobalClass;
import br.com.ffit.comanda.service.ContaService;
import br.com.ffit.comanda.to.ContaTO;
import br.com.ffit.comanda.to.FecharContaTO;
import br.com.ffit.comanda.to.JSONResponse;
import ffit.com.br.comanda.R;


@EFragment(R.layout.fragment_mesa)
public class MesaFragment extends Fragment {

    @FragmentArg
    ContaTO contaTO;

    @Bean
    ContaService contaService;

    @App
    GlobalClass globalClass;

    @Click
    public void btnFecharConta() {
        FecharContaTO fecharContaTO = new FecharContaTO();
        fecharContaTO.setIdConta(contaTO.getId());
        fecharContaTO.setIdUsuario(globalClass.getUsuarioLogado().getId());
        fecharConta(fecharContaTO);
    }


    @Background
    public void fecharConta(FecharContaTO fecharContaTO) {
        JSONResponse jsonResponse = contaService.fecharConta(fecharContaTO);
        callBackFecharConta(jsonResponse);
    }

    @UiThread
    public void callBackFecharConta(JSONResponse<ContaTO> jsonResponse) {
        if (jsonResponse.getSuccess()) {
            Toast.makeText(this.getActivity(), "Conta parcial fechada", Toast.LENGTH_SHORT).show();
        }
    }


}
