package de.eneko.nekomobile.beans;

import android.util.Log;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.activities.models.RouteModel;

public class Route extends BaseObject implements InekoId, ItoXmlElement {
    private static final String TAG = Route.class.getName();
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

    @Override
    protected Basemodel createBaseObject() {
        return new RouteModel(this);
    }

    @Override
    public RouteModel getBaseModel() {
        return (RouteModel) super.getBaseModel();
    }

    public List<Liegenschaft> getLiegenschaften() {
        return mLiegenschaften;
    }

    public void setLiegenschaften(List<Liegenschaft> liegenschaften) {
        mLiegenschaften = liegenschaften;
    }
    @Override
    public Element toXmlElement(Document xmldoc) {
            Element retval = xmldoc.createElement("route");
        try {
            Attr attr = xmldoc.createAttribute("nekoId");
            attr.setValue(this.nekoId);
            retval.setAttributeNode(attr);
            CreateTextNode(retval, "bezeichnung", mBezeichnung);
            CreateDateTextNode(retval, "datum", mDatum);
            CreateTextNode(retval, "bemerkung", mBemerkung);
            CreateTextNode(retval, "createTimestamp",mCreateTimestamp);
            this.getLiegenschaften().forEach(item-> retval.appendChild(item.toXmlElement(xmldoc)));
        } catch (Exception e) {
            Log.e(TAG, "export error.", e);
        }
        return  retval;
    }

    public void updateRouteFromXmlElement(Element element, Boolean withSubElements) {
       try {
        this.nekoId = element.getAttributeNode("nekoId").getValue();

        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);


            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element propElement = (Element) node;
                switch (propElement.getNodeName()) {
                    case "bezeichnung":
                        mBezeichnung = getString(propElement);
                        break;
                    case "datum":
                        mDatum = getSipleDate(propElement);
                        break;
                    case "bemerkung":
                        mBemerkung = getString(propElement);
                        break;
                    case "createTimestamp":
                        mCreateTimestamp = getString(propElement);
                        break;
                    case "Liegenschaft":
                        if (withSubElements) {
                            Liegenschaft liegenschaft = new Liegenschaft(this);
                            liegenschaft.updateRouteFromXmlElement(propElement);
                            mLiegenschaften.add(liegenschaft);
                        }
                        break;
                    default:
                        Log.e(TAG, propElement.getNodeName() + ": keine bekannte Property");
                }
            }
        }
       } catch (Exception e) {
           Log.e(TAG, "import error.", e);
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
