package de.eneko.nekomobile.framework;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatHelper {

    public static String formatDisplayDouble(Double d){
        NumberFormat formatter = NumberFormat.getInstance(Locale.GERMANY);
        formatter.setMaximumFractionDigits(3);
        formatter.setMinimumFractionDigits(3);
        String ret_val = formatter.format(d);
        return ret_val;
    }

    public static String formatInputDouble(Double d){
        NumberFormat formatter = NumberFormat.getInstance(Locale.GERMANY);
        formatter.setMaximumFractionDigits(3);
        formatter.setMinimumFractionDigits(3);
        String ret_val = formatter.format(d);
        ret_val = ret_val.replace(".","");
        ret_val = ret_val.replace(",",".");
        return ret_val;
    }

}
