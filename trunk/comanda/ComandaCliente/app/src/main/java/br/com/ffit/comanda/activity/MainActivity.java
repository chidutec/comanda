package br.com.ffit.comanda.activity;

import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

import br.com.ffit.comanda.global.GlobalClass;
import br.com.ffit.comanda.service.UsuarioService;
import ffit.com.br.comanda.R;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @App
    GlobalClass globalClass;

    @Bean
    UsuarioService usuarioService;

    @AfterInject
    public void init() {
        FacebookSdk.sdkInitialize(getApplicationContext(), () -> verificaUsuarioLogado());
    }

    @Background
    void verificaUsuarioLogado() {
        if (AccessToken.getCurrentAccessToken() != null) {
            if (globalClass.getUsuarioLogado() == null) {
                globalClass.setUsuarioLogado(usuarioService.getUsuarioLogado());
            }
            DashBoardClienteActivity_.intent(this).start();
        } else {
            LoginActivity_.intent(this).start();
        }
        finish();
    }

}