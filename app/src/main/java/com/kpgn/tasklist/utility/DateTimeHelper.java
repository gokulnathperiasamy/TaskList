package com.kpgn.tasklist.utility;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public abstract class DateTimeHelper {

    private static final String DATE_FORMATTER_STRING = "EEE, d MMM yyyy HH:mm:ss";
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_FORMATTER_STRING, Locale.US);

    public static String getFormattedDateTime(long rowUpdate) {
        try {
            return SIMPLE_DATE_FORMAT.format(new Date(rowUpdate));
        } catch (Exception e) {
            Log.w(DateTimeHelper.class.getCanonicalName(), e.getLocalizedMessage());
        }
        return "";
    }
}
