package de.eneko.nekomobile.beans;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Route implements InekoId, ItoXmlElement {
    private String nekoId;
    private String mBezeichnung;
    private Date mDatum;
    private String mBemerkung;
    private String mNotiz;
    private List<Liegenschaft> mLiegenschaften;
    private String mAndroidFileName;
    private String mCreateTimestamp;

    public Route() {
        mLiegenschaften = new ArrayList<Liegenschaft>();
    }

    public List<Liegenschaft> getLiegenschaften() {
        return mLiegenschaften;
    }

    public void setLiegenschaften(List<Liegenschaft> liegenschaften) {
        mLiegenschaften = liegenschaften;
    }
    // region ItoXmlElement
    @Override
    public Element toXmlElement(Document document) {
        return  null;
        // TODO: Implement ToXmlString
    }
    // endregion ItoXmlElement


    public void updateRouteFromXmlElement(Element element, Boolean withSubElements) {
        this.nekoId = element.getAttributeNode("nekoId").getValue();

        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);


            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element propElement = (Element) node;
                switch (propElement.getNodeName()) {
                    case "bezeichnung":
                        mBezeichnung = XmlHelper.getString(propElement);
                        break;
                    case "datum":
                        mDatum = XmlHelper.getSipleDate(propElement);
                        break;
                    case "bemerkung":
                        mBemerkung = XmlHelper.getString(propElement);
                        break;
                    case "createTimestamp":
                        mCreateTimestamp = XmlHelper.getString(propElement);
                        break;
                    case "Liegenschaft":
                        if (withSubElements) {
                            Liegenschaft liegenschaft = new Liegenschaft(this);
                            liegenschaft.updateRouteFromXmlElement(propElement);
                            mLiegenschaften.add(liegenschaft);
                        }
                        break;
                    default:
                        System.out.println(propElement.getNodeName() + ": keine bekannte Property");
                }
            }
        }
    }

    //region properties





    @Override
    public String getNekoId() {
        return this.nekoId ;
    }

    @Override
    public void setNekoId(String value) {
        this.nekoId = value;
    }

    public String getBezeichnung() {
        return mBezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        mBezeichnung = bezeichnung;
    }

    public Date getDatum() {
        return mDatum;
    }

    public void setDatum(Date datum) {
        mDatum = datum;
    }

    public String getBemerkung() {
        return mBemerkung;
    }

    public void setBemerkung(String bemerkung) {
        mBemerkung = bemerkung;
    }

    public String getAndroidFileName() {
        return mAndroidFileName;
    }

    public void setAndroidFileName(String androidFileName) {
        this.mAndroidFileName = androidFileName;
    }

    public String getNotiz() {
        return mNotiz;
    }

    public void setNotiz(String notiz) {
        this.mNotiz = notiz;
    }

    public String getCreateTimestamp() {
        return mCreateTimestamp;
    }

    public void setCreateTimestamp(String createTimestamp) {
        this.mCreateTimestamp = createTimestamp;
    }


//endregion properties

}
