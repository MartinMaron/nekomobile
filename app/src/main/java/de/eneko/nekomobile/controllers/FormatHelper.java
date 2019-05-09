package de.eneko.nekomobile.controllers;

import java.text.NumberFormat;

public class FormatHelper {

    public static String formatDouble(Double d){
        NumberFormat formatter = new java.text.DecimalFormat("#,###.00");
        String ret_val = formatter.format(d);
        ret_val = ret_val.replace(",", "#");
        ret_val = ret_val.replace(".", ",");
        ret_val = ret_val.replace("#", ".");
        return ret_val;
    }
}
