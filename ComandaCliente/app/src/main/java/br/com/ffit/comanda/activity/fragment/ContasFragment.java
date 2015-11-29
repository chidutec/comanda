package br.com.ffit.comanda.activity.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.widget.AbsListView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import br.com.ffit.comanda.adapter.ContaTOListAdapter;
import br.com.ffit.comanda.global.GlobalClass;
import br.com.ffit.comanda.service.ContaService;
import br.com.ffit.comanda.to.ContaTO;
import br.com.ffit.comanda.to.JSONResponse;
import ffit.com.br.comanda.R;


@EFragment(R.layout.fragment_contas)
public class ContasFragment extends Fragment {

    @ViewById(android.R.id.list)
    AbsListView mListView;

    @Bean
    ContaService contaService;

    @Bean
    ContaTOListAdapter contaTOListAdapter;

    @App
    GlobalClass globalClass;

    ProgressDialog progressDialog;

    @AfterInject
    public void afterInject() {
        progressDialog = ProgressDialog.show(getActivity(), "Buscando Contas", "Aguarde", true);
        buscaContas();
    }

    @Background
    public void buscaContas() {
        JSONResponse<List<ContaTO>> jsonResponse = contaService.buscaContas(globalClass.getUsuarioLogado().getId());
        callBackBuscaRestaurantes(jsonResponse);
    }

    @UiThread
    public void callBackBuscaRestaurantes(JSONResponse<List<ContaTO>> jsonResponse) {
        contaTOListAdapter.setContaTOs(jsonResponse.getObj());
        mListView.setAdapter(contaTOListAdapter);
        progressDialog.dismiss();
    }

}
