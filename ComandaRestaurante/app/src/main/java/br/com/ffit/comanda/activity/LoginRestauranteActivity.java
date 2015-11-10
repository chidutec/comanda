package br.com.ffit.comanda.activity;

import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import br.com.ffit.comanda.rest.RestClient;
import br.com.ffit.comanda.service.EstabelecimentoService;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.LoginTO;
import ffit.com.br.comanda.R;

@EActivity(R.layout.activity_login)
public class LoginRestauranteActivity extends AppCompatActivity {


    private JSONResponse jsonResponse;

    @ViewById(R.id.inputEmail)
    EditText inputEmail;

    @ViewById(R.id.inputSenha)
    EditText inputSenha;

    @ViewById(R.id.txtMessageLogin)
    TextView txtMessageLogin;

    @RestService
    RestClient restClient;

    @Bean
    EstabelecimentoService estabelecimentoService;

    @Click
    public void btnLogin() {

        String login = inputEmail.getText().toString();
        String senha = inputSenha.getText().toString();

        if (login == null && senha != null) {
            txtMessageLogin.setText("Preencha o Login");
        } else if(login != null && senha == null) {
            txtMessageLogin.setText("Preencha a Senha");
        } else if(login != null && senha == null) {
            txtMessageLogin.setText("Preencha login e senha");
        } else {

            LoginTO loginTO = new LoginTO();
            loginTO.setLogin(login);
            loginTO.setSenha(senha);
            estabelecimentoService.fazerLogin(loginTO, this);

        }
    }


    @UiThread
    public void setJSONResponse(JSONResponse jsonResponse) {
        if(jsonResponse.getSuccess()) {
            //Lancar activity
        } else {
            txtMessageLogin.setText("Login/Senha incorreto");
        }
    }
}
