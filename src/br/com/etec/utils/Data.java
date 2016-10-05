package br.com.etec.utils;
/**
 * Manipulador de datas.
 */
import br.com.etec.log.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {

    public static String convertSql(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String result = dateFormat.format(date);

        return result;
    }

    public static String convertString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String result = dateFormat.format(date);

        return result;
    }

    public static Date convertDate(String date) {
        try {
            Date data = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            return data;
        } catch (ParseException e) {
            Log.e("Data", e.getMessage());
        }
            Log.w("Data", "retorno null");
        return null;
    }
}
