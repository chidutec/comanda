package br.com.ffit.comanda.activity.fragment;

import android.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import br.com.ffit.comanda.service.UsuarioService;
import br.com.ffit.comanda.to.UsuarioTO;
import ffit.com.br.comanda.R;

@EFragment(R.layout.fragment_convidar_amigo)
public class ConvidarAmigoModalFragment extends DialogFragment{

    @ViewById(R.id.convidarAmigoAC)
    AutoCompleteTextView convidarAmigoAC;

    @Bean
    UsuarioService usuarioService;

    @AfterViews
    public void init() {
        convidarAmigoAC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence userInput, int start, int before, int count) {
                getFriendsNames(userInput.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Background
    public void getFriendsNames(String token) {
        List<UsuarioTO> resultado = usuarioService.buscaAmigosPeloToken(token);
        callBackGetFriendsNames(resultado);
    }

    @UiThread
    public void callBackGetFriendsNames(List<UsuarioTO> resultado){
        convidarAmigoAC.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.list_content, resultado));
        convidarAmigoAC.showDropDown();
    }

    @Click
    public void btnConvidarList() {

    }


}
