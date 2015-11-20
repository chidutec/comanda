package br.com.ffit.comanda.activity.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import br.com.ffit.comanda.adapter.ProdutoTOListAdapter;
import br.com.ffit.comanda.service.EstabelecimentoService;
import br.com.ffit.comanda.to.EstabelecimentoTO;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.ProdutoTO;
import ffit.com.br.comanda.R;


@EFragment(R.layout.fragment_produto)
public class ProdutoFragment extends Fragment implements AbsListView.OnItemClickListener {

    @ViewById(android.R.id.list)
    AbsListView mListView;

    @FragmentArg
    EstabelecimentoTO estabelecimentoTO;

    @Bean
    EstabelecimentoService estabelecimentoService;

    @Bean
    ProdutoTOListAdapter produtoTOListAdapter;

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

    @AfterViews
    public void afterViews() {
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        if (null != mListener) {
//            // Notify the active callbacks interface (the activity, if the
//            // fragment is attached to one) that an item has been selected.
//            mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
//        }
    }

    //Interagir com outros fragmentos
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String id);
    }

}
