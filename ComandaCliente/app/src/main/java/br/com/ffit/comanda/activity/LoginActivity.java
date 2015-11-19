package br.com.ffit.comanda.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.UiThread;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import br.com.ffit.comanda.service.FacebookService;
import br.com.ffit.comanda.service.UsuarioService;
import br.com.ffit.comanda.to.JSONResponse;
import ffit.com.br.comanda.R;

@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity {

    @Bean
    UsuarioService usuarioService;

    @Bean
    FacebookService facebookService;

    @ViewById(R.id.btnLogin)
    LoginButton btnLogin;

    CallbackManager callbackManager;

    ProgressDialog progressDialog;

    @AfterInject
    public void init() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @AfterViews
    public void registerLoginButton() {
        btnLogin.setReadPermissions("user_friends");
        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                fazerLogin();
                Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Background
    public void fazerLogin() {
        JSONResponse jsonResponse = usuarioService.fazerLogin();
        callBackFazerLogin(jsonResponse);
    }

    @UiThread
    public  void callBackFazerLogin(JSONResponse jsonResponse) {
         if(jsonResponse.getSuccess()) {
           // DashBoardClienteActivity_.intent(this).extra("userTO", userTO).start();
         }
    }

//    @Click
//    public void btnLogin() {
//
//        String login = inputEmail.getText().toString();
//        String senha = inputSenha.getText().toString();
//
//        if (login.isEmpty()) {
//            Toast.makeText(this, "Preencha o Login", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (senha.isEmpty()) {
//            Toast.makeText(this, "Preencha a Senha", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        LoginTO loginTO = new LoginTO();
//        loginTO.setLogin(login);
//        loginTO.setSenha(senha);
//        progressDialog = ProgressDialog.show(this, "Logando", "Aguarde", true);
//        fazerLogin(loginTO);
//    }

//    @Click
//    public void btnCadastrar() {
//        String login = inputEmail.getText().toString();
//
//        //Caso o campo login esteja preenchido, verificar disponibilidade. Se não apenas abrir a próxima tela
//        if (!login.isEmpty()) {
//            LoginTO loginTO = new LoginTO();
//            loginTO.setLogin(login);
//            progressDialog = ProgressDialog.show(this, "Verificando Disponibilidade do Login", "Aguarde", true);
//            verificaDisponibilidadeLogin(loginTO);
//        } else {
//            CadastroRestauranteActivity_.intent(this).start();
//        }
//    }


}
