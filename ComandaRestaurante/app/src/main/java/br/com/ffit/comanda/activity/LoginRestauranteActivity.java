package br.com.ffit.comanda.activity;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import br.com.ffit.comanda.service.EstabelecimentoService;
import br.com.ffit.comanda.to.JSONResponse;
import br.com.ffit.comanda.to.LoginTO;
import ffit.com.br.comanda.R;

@EActivity(R.layout.activity_login)
public class LoginRestauranteActivity extends Activity {


    @Bean
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

        if (login.isEmpty()) {
            Toast.makeText(this, "Preencha o Login", Toast.LENGTH_SHORT).show();
            return;
        }

        if (senha.isEmpty()) {
            Toast.makeText(this, "Preencha a Senha", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginTO loginTO = new LoginTO();
        loginTO.setLogin(login);
        loginTO.setSenha(senha);
        fazerLogin(loginTO);
    }

    @Click
    public void btnCadastrar() {
        String login = inputEmail.getText().toString();

        //Caso o campo login esteja preenchido, verificar disponibilidade. Se não apenas abrir a próxima tela
        if (!login.isEmpty()) {
            LoginTO loginTO = new LoginTO();
            loginTO.setLogin(login);
            verificaDisponibilidadeLogin(loginTO);
        } else {
            CadastroRestauranteActivity_.intent(this).start();
        }
    }

    @Background
    public void verificaDisponibilidadeLogin(LoginTO loginTO) {
        //Inicia serviço que verifica disponibilidade do login no servidor
        JSONResponse jsonResponse = estabelecimentoService.verificaDisponibilidadeLogin(loginTO);
        callBackVerificaDisponibilidadeLogin(jsonResponse, loginTO);
    }

    @UiThread
    public void callBackVerificaDisponibilidadeLogin(JSONResponse jsonResponse, LoginTO loginTo) {
        //Caso login nao exista, passar para próximo tela com ele de parametro
        if (jsonResponse.getSuccess()) {
            CadastroRestauranteActivity_.intent(this).extra("login", loginTo.getLogin()).start();
        } else {
            Toast.makeText(this, jsonResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Background
    public void fazerLogin(LoginTO loginTO) {
        JSONResponse jsonResponse = estabelecimentoService.fazerLogin(loginTO);
        callBackFazerLogin(jsonResponse);
    }

    @UiThread
    public void callBackFazerLogin(JSONResponse jsonResponse) {
        if (jsonResponse.getSuccess()) {
            DashBoardRestauranteActivity_.intent(this).start();
        } else {
            Toast.makeText(this, jsonResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
