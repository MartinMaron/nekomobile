package de.eneko.nekomobile.beans;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class Messgeraet implements InekoId, ItoXmlElement {
    private String nekoId;
    private List<Ablesung> Ablesungen;

    public Messgeraet() {
        Ablesungen = new ArrayList<Ablesung>();
    }

    @Override
    public Element toXmlElement(Document document) {
        return  null;
        // TODO: Implement ToXmlString
    }

    @Override
    public String getNekoId() {
        return nekoId;
    }

    @Override
    public void setNekoId(String nekoId) {
        this.nekoId = nekoId;
    }
}
