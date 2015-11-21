package br.com.ffit.comanda.global;

import android.app.Application;

import org.androidannotations.annotations.EApplication;

import br.com.ffit.comanda.to.UsuarioTO;

@EApplication
public class GlobalClass extends Application{

    UsuarioTO usuarioLogado;

    public UsuarioTO getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(UsuarioTO usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }


}
