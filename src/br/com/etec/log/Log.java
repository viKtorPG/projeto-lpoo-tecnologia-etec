/*
 * Sistema de Log do projeto
 */
package br.com.etec.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jose
 *
 * Sistema de log (saído no console), onde podemos acompanhar o andamento do
 * sistema.
 *
 * Sistema de Log Baseado no Android.
 */
public class Log {

    //Debug
    public static void d(String tag, String msg, Class classe) {
        System.out.printf("%s D %s %s %s", getDateTime(), tag, msg, classe.toString());
    }

    public static void d(String tag, String msg) {
        System.out.printf("%s D %s %s", getDateTime(), tag, msg);
    }

    //Error
    public static void e(String tag, String msg, Class classe) {
        System.err.printf("%s E %s %s %s", getDateTime(), tag, msg, classe.toString());
    }

    public static void e(String tag, String msg) {
        System.err.printf("%s D %s %s", getDateTime(), tag, msg);
    }

    //Info
    public static void i(String tag, String msg) {
        System.out.printf("%s I %s %s", getDateTime(), tag, msg);
    }

    //Verbose
    public static void v(String tag, String msg) {
        System.out.printf("%s V %s %s", getDateTime(), tag, msg);
    }

    //Warning
    public static void w(String tag, String msg) {
        System.err.printf("%s D %s %s", getDateTime(), tag, msg);
    }

    
    //Retorna a data e hora do sistema
    private static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
