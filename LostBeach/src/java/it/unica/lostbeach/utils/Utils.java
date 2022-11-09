/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.lostbeach.utils;

import it.unica.lostbeach.exceptions.InvalidParamException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.TimeZone;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Utils {

    /*controlla che la stringa in input abbia una lunghezza tra due interi passati in input*/
    public static void checkString(String param, int min, int max) throws InvalidParamException {

        if (param == null) {
            throw new InvalidParamException("Parametro nullo");
        }

        if (param.length() < min || param.length() > max) {
            throw new InvalidParamException("Stringa non valida: "
                    + "deve avere una dimensione compresa tra "
                    + min + " e " + max + " caratteri.");
        }
    }

    /*controlla che la stringa in input abbia una dimensione tra due interi passati in input*/
    public static void checkInteger(String param, int min, int max) throws InvalidParamException {

        try {
            int valore = Integer.valueOf(param); //recupera il valore sotto forma di intero dalla stringa param
            if (valore < min || valore > max) {
                throw new InvalidParamException("Numero non valido: "
                        + "deve essere compreso tra " + min + " e " + max);
            }
        } catch (NumberFormatException e) {
            throw new InvalidParamException("La stringa " + e + " non rappresenta un numero intero");
        }
    }

    //formatta data e ora
    public static String convertTime(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.setTimeInMillis(time);
        return (cal.get(Calendar.YEAR) + "_" + (cal.get(Calendar.MONTH) + 1) + "_"
                + cal.get(Calendar.DAY_OF_MONTH) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":"
                + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND));
    }

    /* formatta la data*/
    public static String convertDate(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.setTimeInMillis(time);
        return (cal.get(Calendar.YEAR) + "_" + (cal.get(Calendar.MONTH) + 1) + "_"
                + cal.get(Calendar.DAY_OF_MONTH));
    }

    /*controlla che i posti da prenotare non superino quelli disponibili*/
    public static void checkPosti(int postiDisponibili, String postiPrenotati) throws InvalidParamException {

        try {
            int pP = Integer.valueOf(postiPrenotati);
            if (postiDisponibili < pP) {
                throw new InvalidParamException("Posti insufficienti: "
                        + "sono disponibili " + postiDisponibili + " posti! ");
            }
        } catch (NumberFormatException e) {
            throw new InvalidParamException("La stringa non rappresenta un numero intero");
        }
    }

}
