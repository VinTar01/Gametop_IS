package it.unisa.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility
{
    //stampa su console tutte le stringhe passate come argomento
    public static void print(final String... messages) {
        String message = "";
        for (final String s : messages) {
            message = String.valueOf(message) + s + "\n";
        }
        System.out.printf("%s", message);
    }
    
    public static void print(final Exception exception) {
        print("EXCEPTION: " + exception.getMessage());
        exception.printStackTrace();
    }
    
    public static Date formatStringToDate(final String data) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(data);
        }
        catch (ParseException ex) {
            return null;
        }
    }
    
    public static java.sql.Date toSqlDate(final Date data) {
        final Calendar calendar = new GregorianCalendar();
        calendar.setTime(data);
        calendar.set(10, 1);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return new java.sql.Date(calendar.getTimeInMillis());
    }
}