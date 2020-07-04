package com.fivegearszerochill.noted.util.general;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateHelper {
    public static Date getCurrentDate() {
        return new Date();
    }

    public static String convertDateToString(Date date) {
        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern, Locale.US);
        return df.format(date);
    }
}
