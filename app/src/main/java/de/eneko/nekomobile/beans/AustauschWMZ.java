package de.eneko.nekomobile.beans;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AustauschWMZ extends Austausch {
    private double standStichtag;


    @Override
    public Element toXmlElement(Document document) {
        return  null;
        // TODO: Implement ToXmlString
    }
    @Override
    public String getArtMessgeraet() {
        return "WMZ";
    }

    @Override
    public void setArtMessgeraet(String artMessgeraet) {
        // tu nichts, hier gibt es nur eine Art des Gerätes;
    }
}
