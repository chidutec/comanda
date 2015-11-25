package br.com.ffit.comanda.activity.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.widget.AbsListView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import br.com.ffit.comanda.adapter.EstabelecimentoTOListAdapter;
import br.com.ffit.comanda.service.EstabelecimentoService;
import br.com.ffit.comanda.to.EstabelecimentoTO;
import br.com.ffit.comanda.to.JSONResponse;
import ffit.com.br.comanda.R;


@EFragment(R.layout.fragment_restaurante)
public class RestauranteFragment extends Fragment {

    @ViewById(android.R.id.list)
    AbsListView mListView;

    @Bean
    EstabelecimentoService estabelecimentoService;

    @Bean
    EstabelecimentoTOListAdapter estabelecimentoTOListAdapter;

    ProgressDialog progressDialog;

    @AfterInject
    public void afterInject() {
        progressDialog = ProgressDialog.show(getActivity(), "Buscando Restaurante", "Aguarde", true);
        buscaRestaurantes();
    }

    @Background
    public void buscaRestaurantes() {
        JSONResponse<List<EstabelecimentoTO>> jsonResponse = estabelecimentoService.buscaRestaurantes();
        callBackBuscaRestaurantes(jsonResponse);
    }

    @UiThread
    public void callBackBuscaRestaurantes(JSONResponse<List<EstabelecimentoTO>> jsonResponse) {
        estabelecimentoTOListAdapter.setEstabelecimentoTOs(jsonResponse.getObj());
        mListView.setAdapter(estabelecimentoTOListAdapter);
        progressDialog.dismiss();
    }

}
