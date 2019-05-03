package de.eneko.nekomobile.beans;

import org.w3c.dom.Element;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.eneko.nekomobile.GlobalConst;


public class XmlHelper {



    static Date getSipleLongDate(Element propElement)
    {
        Date retval = null;
        try {
            if (propElement.hasChildNodes()) {
                retval =  new SimpleDateFormat(GlobalConst.longDateTimeFormat).parse(propElement.getFirstChild().getNodeValue());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  retval;
    }

    static Date getSipleDate(Element propElement)
    {
        Date retval = null;
        try {
            if (propElement.hasChildNodes()) {
                retval =  new SimpleDateFormat(GlobalConst.dateFormat).parse(propElement.getFirstChild().getNodeValue());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  retval;
    }



    static Integer getInteger(Element propElement)
    {
        Integer retval = null;
        if (propElement.hasChildNodes()) {
            retval = Integer.parseInt(propElement.getFirstChild().getNodeValue());
        }
        return retval;
    }
    static Double getDouble(Element propElement)
    {
        Double retval = null;
        if (propElement.hasChildNodes()) {
            retval = Double.parseDouble(propElement.getFirstChild().getNodeValue().replace(",","."));
        }
        return retval;
    }

    static String getString(Element propElement)
    {
        String retval = null;
        if (propElement.hasChildNodes()) {
            retval = propElement.getFirstChild().getNodeValue();
        }
        return retval;
    }

    static Boolean getBoolean(Element propElement)
    {
        Boolean retval = false;
        if (propElement.hasChildNodes()) {
          if (propElement.getFirstChild().getNodeValue() == "True")
          {
              retval = true;
          }
        }
        return retval;
    }

}
