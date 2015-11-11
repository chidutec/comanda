package br.com.ffit.comanda.activity;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import br.com.ffit.comanda.service.EstabelecimentoService;
import br.com.ffit.comanda.to.EstabelecimentoTO;
import br.com.ffit.comanda.to.JSONResponse;
import ffit.com.br.comanda.R;

@EActivity(R.layout.activity_cadastro_restaurante)
public class CadastroRestauranteActivity extends Activity {

    @ViewById(R.id.inputEmailCadastro)
    EditText inputEmailCadastro;

    @ViewById(R.id.inputSenhaCadastro)
    EditText inputSenhaCadastro;

    @ViewById(R.id.inputNomeCadastro)
    EditText inputNomeCadastro;

    @Bean
    EstabelecimentoService estabelecimentoService;

    @AfterViews
    public void loadPassedLogin() {
        inputEmailCadastro.setText(this.getIntent().getStringExtra("login"));
    }


    @Click
    public void btnCadastroCadastrar() {
        String login = inputEmailCadastro.getText().toString();
        String senha = inputSenhaCadastro.getText().toString();
        String nome = inputNomeCadastro.getText().toString();

        if (login.isEmpty()) {
            Toast.makeText(this, "Preencha o Login", Toast.LENGTH_SHORT).show();
            return;
        }

        if (senha.isEmpty()) {
            Toast.makeText(this, "Preencha a Senha", Toast.LENGTH_SHORT).show();
            return;
        }

        if (nome.isEmpty()) {
            Toast.makeText(this, "Preencha a Nome", Toast.LENGTH_SHORT).show();
            return;
        }

        EstabelecimentoTO estabelecimentoTO = new EstabelecimentoTO();
        estabelecimentoTO.setLogin(login);
        estabelecimentoTO.setSenha(senha);
        estabelecimentoTO.setNome(nome);

        cadastraEstabelecimento(estabelecimentoTO);

    }

    @Background
    public void cadastraEstabelecimento(EstabelecimentoTO estabelecimentoTO) {
        JSONResponse jsonResponse = estabelecimentoService.cadastraEstabelecimento(estabelecimentoTO);
        callbackCadastraEstabelecimento(jsonResponse, estabelecimentoTO);
    }

    @UiThread
    public void callbackCadastraEstabelecimento(JSONResponse jsonResponse, EstabelecimentoTO estabelecimentoTO) {
        Toast.makeText(this, jsonResponse.getMessage(), Toast.LENGTH_SHORT).show();
        if(jsonResponse.getSuccess()) {
            // Lanca Atividade Dashboard
        }
    }

}

