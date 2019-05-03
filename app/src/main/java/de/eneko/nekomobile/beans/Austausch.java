package de.eneko.nekomobile.beans;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class Austausch implements ItoXmlElement {
    protected String prevNekoId;
    protected String newNumber;
    protected String artMessgeraet;
    protected int modelId;
    protected String raum;
    protected double standAktuell;


    @Override
    public Element toXmlElement(Document document) {
        // wird in Childs definiert
        return  null;
    }

    public String getPrevNekoId() {
        return prevNekoId;
    }

    public void setPrevNekoId(String prevNekoId) {
        this.prevNekoId = prevNekoId;
    }

    public String getNewNumber() {
        return newNumber;
    }

    public void setNewNumber(String newNumber) {
        this.newNumber = newNumber;
    }

    public String getArtMessgeraet() {
        return artMessgeraet;
    }

    public void setArtMessgeraet(String artMessgeraet) {
        this.artMessgeraet = artMessgeraet;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getRaum() {
        return raum;
    }

    public void setRaum(String raum) {
        this.raum = raum;
    }

    public double getStandAktuell() {
        return standAktuell;
    }

    public void setStandAktuell(double standAktuell) {
        this.standAktuell = standAktuell;
    }


}

