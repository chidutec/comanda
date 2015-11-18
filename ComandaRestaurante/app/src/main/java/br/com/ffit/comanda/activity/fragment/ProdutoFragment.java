package br.com.ffit.comanda.activity.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
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

    @ViewById(R.id.inputProdutoIncluirNome)
    EditText inputNome;

    @ViewById(R.id.inputProdutoIncluirDescricao)
    EditText inputDescricao;

    @ViewById(R.id.inputProdutoIncluirPreco)
    EditText inputPreco;

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
        produtoTOListAdapter.setEstabelecimentoTO(estabelecimentoTO);
        mListView.setAdapter(produtoTOListAdapter);
    }

    @AfterViews
    public void afterViews() {
        mListView.setOnItemClickListener(this);
    }

    @Click
    public void btnIncluirProduto() {
        String nome = inputNome.getText().toString();
        String descricao = inputDescricao.getText().toString();
        String preco = inputPreco.getText().toString();

        //Fazer críticas de preenchimento

        if (nome.isEmpty()) {
            Toast.makeText(this.getActivity(), "Preencha o Nome", Toast.LENGTH_SHORT).show();
            return;
        }

        if (descricao.isEmpty()) {
            Toast.makeText(this.getActivity(), "Preencha a Descricao", Toast.LENGTH_SHORT).show();
            return;
        }

        if (preco.isEmpty()) {
            Toast.makeText(this.getActivity(), "Preencha a Preco", Toast.LENGTH_SHORT).show();
            return;
        }

        BigDecimal precoNumber = null;

        try {
            DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getNumberInstance();
            decimalFormat.setParseBigDecimal(true);
            precoNumber = (BigDecimal) decimalFormat.parse(preco);
        } catch(ParseException p) {
            Toast.makeText(this.getActivity(), "Preco Invalido", Toast.LENGTH_SHORT).show();
            return;
        }

        ProdutoTO produtoTO = new ProdutoTO();
        produtoTO.setNome(nome);
        produtoTO.setPreco(precoNumber);
        produtoTO.setDescricao(descricao);
        produtoTO.setIdEstabelecimento(estabelecimentoTO.getId());

        progressDialog = ProgressDialog.show(this.getActivity(), "Cadastrando", "Aguarde", true);
        cadastraProduto(produtoTO);
    }

    @Background
    public void cadastraProduto(ProdutoTO produtoTO) {
        JSONResponse jsonResponse = estabelecimentoService.cadastraProduto(produtoTO);
        callBackCadastraProduto(jsonResponse);
    }

    @UiThread
    public void callBackCadastraProduto(JSONResponse jsonResponse) {
        progressDialog.dismiss();
        Toast.makeText(this.getActivity(), jsonResponse.getMessage(), Toast.LENGTH_SHORT).show();
        if(jsonResponse.getSuccess()) {
            FragmentManager fragmentManager = this.getActivity().getFragmentManager();
            Fragment fragment = ProdutoFragment_.builder().estabelecimentoTO(this.estabelecimentoTO).build();
            fragmentManager.beginTransaction().replace(R.id.dashboardContainer, fragment).commit();
        }
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
