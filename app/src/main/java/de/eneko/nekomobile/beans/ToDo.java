package de.eneko.nekomobile.beans;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.activities.models.LiegenschaftModel;
import de.eneko.nekomobile.activities.models.NutzerModel;
import de.eneko.nekomobile.activities.models.NutzerTodoModel;

public class ToDo extends BaseObject implements ItoXmlElement {
    private String bezeichnung;
    private String art;
    private final Nutzer mNutzer;
    private final Liegenschaft mLiegenschaft;
    private List<Rauchmelder> mRauchmelder;
    private List<Messgeraet> mMessgeraete;

    public ToDo(Nutzer nutzer) {
        super();
        mNutzer = nutzer;
        mLiegenschaft = null;
        mRauchmelder = new ArrayList<Rauchmelder>();
        mMessgeraete = new ArrayList<Messgeraet>();
    }

    public ToDo(Liegenschaft liegenschaft) {
        super();
        mLiegenschaft = liegenschaft;
        mNutzer = null;
        mRauchmelder = new ArrayList<Rauchmelder>();
        mMessgeraete = new ArrayList<Messgeraet>();
    }

    @Override
    protected Basemodel createBaseObject() {
        return new NutzerTodoModel(this);
    }

    @Override
    public NutzerTodoModel getBaseModel() {
        return (NutzerTodoModel) super.getBaseModel();
    }

    //

    public List<Messgeraet> getMessgeraete() {
        return mMessgeraete;
    }

    public List<Rauchmelder> getRauchmelder() {
        return mRauchmelder;
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
                        Rauchmelder rwm = new Rauchmelder(this);
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

    public Liegenschaft getLiegenschaft() {
        return mLiegenschaft;
    }
}
