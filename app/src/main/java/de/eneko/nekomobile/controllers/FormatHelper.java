package de.eneko.nekomobile.controllers;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatHelper {

    public static String formatDouble(Double d){
        Locale fmtLocale = Locale.getDefault(Locale.Category.FORMAT);
        NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        //NumberFormat formatter = new java.text.DecimalFormat("#,###.00");
        String ret_val = formatter.format(d);
//        ret_val = ret_val.replace(",", "#");
//        ret_val = ret_val.replace(".", ",");
//        ret_val = ret_val.replace("#", ".");
        return ret_val;
    }
}
