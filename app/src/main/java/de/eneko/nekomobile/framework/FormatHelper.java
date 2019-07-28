package de.eneko.nekomobile.framework;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatHelper {

    public static String formatDouble(Double d){
        Locale fmtLocale = Locale.getDefault(Locale.Category.FORMAT);
        NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        String ret_val = formatter.format(d);
        return ret_val;
    }
}
