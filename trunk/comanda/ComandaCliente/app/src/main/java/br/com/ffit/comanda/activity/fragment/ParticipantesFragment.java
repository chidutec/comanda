package br.com.ffit.comanda.activity.fragment;


import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import br.com.ffit.comanda.adapter.ParticipantesTOAdapter;
import br.com.ffit.comanda.service.ContaService;
import br.com.ffit.comanda.to.ContaTO;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.ParticipanteTO;
import ffit.com.br.comanda.R;


@EFragment(R.layout.fragment_participantes)
public class ParticipantesFragment extends Fragment {

    @ViewById(R.id.listParticipantesConta)
    ListView listParticipantesConta;

    @Bean
    ContaService contaService;

    @Bean
    ParticipantesTOAdapter participantesTOAdapter;

    @FragmentArg
    ContaTO contaTO;

    ProgressDialog progressDialog;

    @AfterViews
    public void afterViews() {
        buscaParticipantes();
    }

    @Background
    public void buscaParticipantes() {
        JSONResponse<List<ParticipanteTO>> jsonResponse = contaService.buscaParticipantes(contaTO.getId());
        callBackBuscaAmigos(jsonResponse);
    }


    @UiThread
    public void callBackBuscaAmigos(JSONResponse<List<ParticipanteTO>> jsonResponse) {
        participantesTOAdapter.setParticipanteTOs(jsonResponse.getObj());
        listParticipantesConta.setAdapter(participantesTOAdapter);
    }

    @Click
    public void btnRecarregar() {
        buscaParticipantes();
    }


}
