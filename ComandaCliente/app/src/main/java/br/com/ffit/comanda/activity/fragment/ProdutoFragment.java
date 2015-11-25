package br.com.ffit.comanda.activity.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.widget.AbsListView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import br.com.ffit.comanda.adapter.ProdutoTOListAdapter;
import br.com.ffit.comanda.global.GlobalClass;
import br.com.ffit.comanda.service.ContaService;
import br.com.ffit.comanda.service.EstabelecimentoService;
import br.com.ffit.comanda.to.AbrirContaTO;
import br.com.ffit.comanda.to.EstabelecimentoTO;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.ProdutoTO;
import ffit.com.br.comanda.R;


@EFragment(R.layout.fragment_produto)
public class ProdutoFragment extends Fragment {

    @ViewById(android.R.id.list)
    AbsListView mListView;

    @FragmentArg
    EstabelecimentoTO estabelecimentoTO;

    @Bean
    EstabelecimentoService estabelecimentoService;

    @Bean
    ProdutoTOListAdapter produtoTOListAdapter;

    @Bean
    ContaService contaService;

    @App
    GlobalClass globalClass;

    private OnFragmentInteractionListener mListener;

    ProgressDialog progressDialog;

    @AfterInject
    public void afterInject() {
        buscaProdutos();
    }

    @Background
    public void buscaProdutos() {
        JSONResponse<List<ProdutoTO>> jsonResponse = estabelecimentoService.buscaProdutos(estabelecimentoTO.getId());
        callBackBuscaProdutos(jsonResponse);
    }

    @UiThread
    public void callBackBuscaProdutos(JSONResponse<List<ProdutoTO>> jsonResponse) {
        produtoTOListAdapter.setProdutoTOs(jsonResponse.getObj());
        mListView.setAdapter(produtoTOListAdapter);
    }

    @Click
    public void btnAbrirConta() {
        AbrirContaTO abrirContaTO = new AbrirContaTO();
        abrirContaTO.setIdUsuario(globalClass.getUsuarioLogado().getId());
        abrirContaTO.setIdEstabelecimento(estabelecimentoTO.getId());
        abrirConta(abrirContaTO);
    }

    @Background
    public void abrirConta(AbrirContaTO abrirContaTO) {
       JSONResponse jsonResponse = contaService.abrirConta(abrirContaTO);
       callBackAbrirConta(jsonResponse);

        FragmentManager fragmentManager = getActivity().getFragmentManager();
        Fragment fragment = ContaFragment_.builder().build();
        fragmentManager.beginTransaction().replace(R.id.dashboardContainer, fragment).commit();
    }

    @UiThread
    public void callBackAbrirConta(JSONResponse jsonResponse) {
        if(jsonResponse.getSuccess()) {
            Toast.makeText(this.getActivity(), "Conta aberta", Toast.LENGTH_SHORT).show();
        }
    }


    //Interagir com outros fragmentos
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String id);
    }

}
