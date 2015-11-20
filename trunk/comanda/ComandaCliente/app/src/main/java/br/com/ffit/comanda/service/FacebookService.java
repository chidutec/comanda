package br.com.ffit.comanda.service;

import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;

import org.androidannotations.annotations.EBean;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import br.com.ffit.comanda.to.UsuarioTO;

@EBean
public class FacebookService {

    public UsuarioTO getUserInfo() {
        try {
            Bundle params = new Bundle();
            params.putString("fields", "id,name,email,picture.type(normal)");

            JSONObject jsonObject = new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/me",
                    params,
                    HttpMethod.GET
            ).executeAndWait().getJSONObject();

            UsuarioTO usuarioTO = new UsuarioTO();
            usuarioTO.setIdFacebook(jsonObject.getLong("id"));
            usuarioTO.setNome(jsonObject.getString("name"));
            usuarioTO.setEmail(jsonObject.getString("email"));
            usuarioTO.setFotoUrl(jsonObject.getJSONObject("picture").getJSONObject("data").getString("url"));
            return usuarioTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<UsuarioTO> getUserFriends() {
        Bundle params = new Bundle();
        params.putString("fields", "friends");

        JSONArray jsonArray = new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me",
                params,
                HttpMethod.GET
        ).executeAndWait().getJSONArray();
        return Arrays.asList(new UsuarioTO());
    }

}
