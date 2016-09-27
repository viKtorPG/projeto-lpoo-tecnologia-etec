package br.com.etec.utils;

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
            e.printStackTrace();
        }

        return null;
    }
}
