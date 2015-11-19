package br.com.ffit.comanda.util;


import org.apache.commons.io.IOUtils;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class Utils {

    public static byte[] downloadFile(String url) {
            try {
                URL u = new URL(url);
                DataInputStream stream = new DataInputStream(u.openStream());
                return IOUtils.toByteArray(stream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
    }

}
