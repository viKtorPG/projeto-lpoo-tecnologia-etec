package br.com.etec.utils;

import br.com.etec.log.Log;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValidaData {

    public static boolean validarData(String data) {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        try {
            df.parse(data);
            return true;
        } catch (ParseException ex) {
            Log.e("validarData", ex.getMessage());
        }
        return false;
    }
}
