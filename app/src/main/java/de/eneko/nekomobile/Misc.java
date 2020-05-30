package de.eneko.nekomobile;
import java.util.*;
import java.text.*;

public class Misc {

    public static String getDateAsString() {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(new Date());
    }

}
