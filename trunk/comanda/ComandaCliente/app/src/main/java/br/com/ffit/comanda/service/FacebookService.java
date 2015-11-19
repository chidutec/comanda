package br.com.ffit.comanda.service;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.androidannotations.annotations.EBean;
import org.json.JSONObject;

import br.com.ffit.comanda.to.UserTO;
import br.com.ffit.comanda.util.Utils;

@EBean
public class FacebookService {

    public UserTO getUserInfo() {
        try {

            JSONObject jsonObject = new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/me",
                    null,
                    HttpMethod.GET
            ).executeAndWait().getJSONObject();

            String url = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/me/picture",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            // Insert your code here
                        }
                    }).executeAndWait().getJSONObject().getJSONObject("data").getString("url");

            byte[] foto = Utils.downloadFile(url);

            UserTO userTO = new UserTO();
            userTO.setIdFacebook(jsonObject.getLong("id"));
            userTO.setName(jsonObject.getString("name"));
            userTO.setFoto(foto);
            return userTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
