package de.eneko.nekomobile.beans;

import org.w3c.dom.Element;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.activities.models.Basemodel;

public abstract class BaseObject {
    protected Basemodel baseModel;

    public BaseObject() {
        this.baseModel = createBaseObject();
    }

    protected abstract Basemodel createBaseObject();

    public Basemodel getBaseModel() {
        return baseModel;
    }

    public void CreateTextNode(Element pXmlElement , String elementName , String pValue ){
        if (pValue == null) return;
        Element _Element = pXmlElement.getOwnerDocument().createElement(elementName);
        _Element.appendChild(pXmlElement.getOwnerDocument().createTextNode(pValue));
        pXmlElement.appendChild(_Element);
    }

    public void CreateIntegerNode(Element pXmlElement , String elementName , Integer pValue ){
        if (pValue == null) return;
        Element _Element = pXmlElement.getOwnerDocument().createElement(elementName);
        _Element.appendChild(pXmlElement.getOwnerDocument().createTextNode(Integer.toString(pValue)));
        pXmlElement.appendChild(_Element);
    }

    public void CreateDoubleNode(Element pXmlElement , String elementName , Double pValue ){
        if (pValue == null) return;
        Element _Element = pXmlElement.getOwnerDocument().createElement(elementName);
        _Element.appendChild(pXmlElement.getOwnerDocument().createTextNode(Double.toString(pValue)));
        pXmlElement.appendChild(_Element);
    }

    public void CreateDateTextNode(Element pXmlElement , String elementName , Date pValue ){
        if (pValue == null) return;
        Element _Element = pXmlElement.getOwnerDocument().createElement(elementName);
        _Element.appendChild(pXmlElement.getOwnerDocument().createTextNode(new SimpleDateFormat(GlobalConst.dateFormat).format(pValue)));
        pXmlElement.appendChild(_Element);
    }

    public void CreateDateTimeNode(Element pXmlElement , String elementName , Date pValue ){
        if (pValue == null) return;
        Element _Element = pXmlElement.getOwnerDocument().createElement(elementName);
        _Element.appendChild(pXmlElement.getOwnerDocument().createTextNode(new SimpleDateFormat(GlobalConst.longDateTimeFormat).format(pValue)));
        pXmlElement.appendChild(_Element);
    }

    public void CreateSontexDateTimeNode(Element pXmlElement , String elementName , Date pValue ){
        if (pValue == null) return;
        Element _Element = pXmlElement.getOwnerDocument().createElement(elementName);
        _Element.appendChild(pXmlElement.getOwnerDocument().createTextNode(new SimpleDateFormat(GlobalConst.SONTEX_longDateTimeFormat).format(pValue)));
        pXmlElement.appendChild(_Element);
    }

//    new SimpleDateFormat(GlobalConst.dateFormat).format(getBean().getLetzter_wert_datum())


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

    static Date getSontexSipleLongDate(Element propElement)
    {
        Date retval = null;
        try {
            if (propElement.hasChildNodes()) {
                retval =  new SimpleDateFormat(GlobalConst.SONTEX_longDateTimeFormat).parse(propElement.getFirstChild().getNodeValue());
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

    protected static String getString(Element propElement)
    {
        String retval = "";
        if (propElement.hasChildNodes()) {
            retval = propElement.getFirstChild().getNodeValue();
        }
        return retval;
    }

    static Boolean getBoolean(Element propElement)
    {
        Boolean retval = false;
        if (propElement.hasChildNodes()) {
            if (propElement.getFirstChild().getNodeValue().toLowerCase().equals("True".toLowerCase()))
            {
                retval = true;
            }
        }
        return retval;
    }




}
