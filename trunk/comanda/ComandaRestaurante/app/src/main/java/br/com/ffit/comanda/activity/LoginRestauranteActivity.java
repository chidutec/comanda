package br.com.ffit.comanda.activity;

import android.app.Activity;
import android.support.annotation.UiThread;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import br.com.ffit.comanda.rest.EstabelecimentoService;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.LoginTO;
import ffit.com.br.comanda.R;

@EActivity(R.layout.activity_login)
public class LoginRestauranteActivity extends Activity {


    @RestService
    EstabelecimentoService estabelecimentoService;

    @ViewById(R.id.inputEmail)
    EditText inputEmail;

    @ViewById(R.id.inputSenha)
    EditText inputSenha;

    @ViewById(R.id.viewMessageLogin)
    TextView viewMessageLogin;


    @Click
    public void btnLogin() {

        String login = inputEmail.getText().toString();
        String senha = inputSenha.getText().toString();

        if (login.isEmpty() && !senha.isEmpty()) {
            Toast.makeText(this, "Preencha o Login", Toast.LENGTH_SHORT).show();
        } else if(!login.isEmpty() && senha.isEmpty()) {
            Toast.makeText(this, "Preencha a Senha", Toast.LENGTH_SHORT).show();
        } else if(login.isEmpty() && senha.isEmpty()) {
            Toast.makeText(this, "Preencha login e senha", Toast.LENGTH_SHORT).show();

        } else {

            LoginTO loginTO = new LoginTO();
            loginTO.setLogin(login);
            loginTO.setSenha(senha);
            fazerLogin(loginTO);
        }
    }

    @Background
    public void fazerLogin(LoginTO loginTO) {
        JSONResponse jsonResponse = estabelecimentoService.fazerLogin(loginTO);
        callBackFazerLogin(jsonResponse);
    }

    @UiThread
    public void callBackFazerLogin(JSONResponse jsonResponse) {
        if(jsonResponse.getSuccess()) {
            //Proxima activity
        } else {
            //Toast.makeText(getApplicationContext(), jsonResponse.getErrorMessage(), Toast.LENGTH_SHORT).show();
            viewMessageLogin.setText(jsonResponse.getErrorMessage());
        }
    }

}
