package de.eneko.nekomobile.beans;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.eneko.nekomobile.R;

public class ToDo implements ItoXmlElement {
    private String bezeichnung;
    private String art;
    private final Nutzer mNutzer;
    private List<Rauchwarnmelder> mRauchmelder;
    private List<Messgeraet> mMessgeraete;

    public ToDo(Nutzer nutzer) {
        mNutzer = nutzer;
        mRauchmelder = new ArrayList<Rauchwarnmelder>();
        mMessgeraete = new ArrayList<Messgeraet>();
    }

    public ToDo() {
        mRauchmelder = new ArrayList<Rauchwarnmelder>();
        mMessgeraete = new ArrayList<Messgeraet>();
        mNutzer = null;
    }


    @Override
    public Element toXmlElement(Document document) {
        return  null;
        // TODO: Implement ToXmlString
    }

    public void updateRouteFromXmlElement(Element element) {
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element propElement = (Element) node;
                switch (propElement.getNodeName()) {
                    case "bezeichnung":
                        bezeichnung = XmlHelper.getString(propElement);
                        break;
                    case "art":
                        art = XmlHelper.getString(propElement);
                        break;
                    case "RWM_Device":
                        Rauchwarnmelder rwm = new Rauchwarnmelder(this);
                        rwm.updateRouteFromXmlElement(propElement);
                        mRauchmelder.add(rwm);
                        break;
                    case "zaehler":
                        Messgeraet zaehler = new Messgeraet(this);
                        zaehler.updateRouteFromXmlElement(propElement);
                        mMessgeraete.add(zaehler);
                        break;
                    default: System.out.println(propElement.getNodeName() + ": keine bekannte Property");
                }
                System.err.print("W");
            }
        }

    }


    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public Nutzer getNutzer() {
        return mNutzer;
    }

    //region Rauchmelder
    /*
    noch zu prüfende Rauchmelder
     */
    public Integer getRwmWartungUndoneCount ()
    {
        return mRauchmelder.stream().filter(r -> r.getUnDone())
                .collect(Collectors.toList()).size();
    }
    /*
    bereits geprüfte Rauchmelder
     */
    public Integer getRwmWartungDoneCount ()
    {
        return mRauchmelder.stream().filter(r -> r.getDone())
                .collect(Collectors.toList()).size();
    }
    /*
    fehlende oder zu ersetztende rwm
     */
    public Integer getRwmWartungWithErrorCount ()
    {
        return mRauchmelder.stream().filter(r -> r.getWithError())
                .collect(Collectors.toList()).size();
    }
    /*
    neu instalierte rwm
     */
    public Integer getRwmWartungNewCount ()
    {
        return mRauchmelder.stream().filter(r -> r.getNekoId().contains("new"))
                .collect(Collectors.toList()).size();
    }
    /*
    Anzahl der Racuhmelder, welche zu prüfen waren
     */
    public Integer getRwmToDoCount ()
    {
        return getRwmWartungDoneCount() + getRwmWartungUndoneCount() + getRwmWartungWithErrorCount();
    }

    public Boolean isRwmCompleted(){
        if(getRwmToDoCount()== 0) {return false;}
        return getRwmToDoCount() == (getRwmWartungWithErrorCount()+ getRwmWartungDoneCount());
    }
    public Integer getRwmStatusImageResourceId(){
         if (isRwmCompleted()) {
             return R.drawable.icon_smoke_detector_green_ok;
         }else {
             return R.drawable.icon_smoke_detector_b;
         }
    }

    public List<Rauchwarnmelder> getRauchmelder() {
        return mRauchmelder;
    }

    // endregion Rauchmelder
}
