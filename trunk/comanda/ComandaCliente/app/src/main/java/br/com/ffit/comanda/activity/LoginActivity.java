package br.com.ffit.comanda.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.UiThread;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
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
import br.com.ffit.comanda.to.UsuarioTO;
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
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @AfterViews
    public void registerLoginButton() {
        btnLogin.setReadPermissions("user_friends","email");
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
        JSONResponse<UsuarioTO> jsonResponse = usuarioService.fazerLogin();
        callBackFazerLogin(jsonResponse);
    }

    @UiThread
    public  void callBackFazerLogin(JSONResponse<UsuarioTO> jsonResponse) {
         if(jsonResponse.getSuccess()) {
            DashBoardClienteActivity_.intent(this).start();
             finish();
         }
    }

}
