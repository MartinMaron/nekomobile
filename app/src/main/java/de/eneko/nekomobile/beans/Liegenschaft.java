package de.eneko.nekomobile.beans;

import android.text.format.DateFormat;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.activities.models.LiegenschaftModel;

public class Liegenschaft extends BaseObject implements ItoXmlElement, InekoId {
    private String nekoId;
    private Date mStart;
    private Date mEnde;
    private int mSortNo;
    private int mZeitInMin;
    private String mArt;
    private String mBemerkung;
    private String mGoogleEventId;
    private double mLatitude;
    private double mLongitude;
    private List<ToDo> mToDos;
    private List<Nutzer> mNutzers;
    private String mAdresse;
    private String mPlZ;
    private final Route route;

    public Route getRoute() {
        return route;
    }

    public Liegenschaft(Route route) {
        super();
        mToDos = new ArrayList<ToDo>();
        mNutzers = new ArrayList<Nutzer>();
        this.route = route;
    }

    @Override
    protected Basemodel createBaseObject() {
        return new LiegenschaftModel(this) ;
    }

    @Override
    public LiegenschaftModel getBaseModel() {
        return (LiegenschaftModel) super.getBaseModel();
    }

    @Override
    public Element toXmlElement(Document document) {
        Element ret_val = document.createElement("Liegenschaft");

        if(this instanceof InekoId)
        {
            Attr attr = document.createAttribute("nekoid");
            attr.setValue(this.getNekoId());
            ret_val.setAttributeNode(attr);
        }

        Element startElement = document.createElement("mStart");
        startElement.appendChild(document.createTextNode(DateFormat.format("dd.MM.yyyy", getStart()).toString()));
        ret_val.appendChild(startElement);




        return ret_val;
    }

    public void updateRouteFromXmlElement(Element element) {
        this.nekoId = element.getAttributeNode("nekoId").getValue();
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element propElement = (Element) node;
                switch (propElement.getNodeName()) {
                    case "start":
                        mStart = XmlHelper.getSipleLongDate(propElement);
                        break;
                    case "ende":
                        mEnde = XmlHelper.getSipleLongDate(propElement);
                        break;
                    case "sortNo":
                        mSortNo = XmlHelper.getInteger(propElement);
                        break;
                    case "zeitInMin":
                        mZeitInMin = XmlHelper.getInteger(propElement);
                        break;
                    case "art":
                        mArt = XmlHelper.getString(propElement);
                        break;
                    case "bemerkung":
                        mBemerkung = XmlHelper.getString(propElement);
                        break;
                    case "googleEventId":
                        mGoogleEventId = XmlHelper.getString(propElement);
                        break;
                    case "latitude":
                        mLatitude = XmlHelper.getDouble(propElement);
                        break;
                    case "longitude":
                        mLongitude = XmlHelper.getDouble(propElement);
                        break;
                    case "todo":
                        ToDo todo = new ToDo(this);
                        todo.updateRouteFromXmlElement(propElement);
                        mToDos.add(todo);
                        break;
                    case "adresse":
                        mAdresse = XmlHelper.getString(propElement);
                        break;
                    case "plz":
                        mPlZ = XmlHelper.getString(propElement);
                        break;
                    case "Nutzerliste":
                        NodeList nutzernodeList = propElement.getChildNodes();
                        for (int j = 0; j < nutzernodeList.getLength(); j++)
                        {
                            Node nutzerNode = nutzernodeList.item(j);
                            if (nutzerNode.getNodeType() == Node.ELEMENT_NODE)
                            {
                                Element nutzerPropElement = (Element) nutzerNode;
                                Nutzer nutzer = new Nutzer(this);
                                nutzer.updateRouteFromXmlElement(nutzerPropElement);
                                mNutzers.add(nutzer);
                            }
                        }
                        break;
                        default:
                            break;
                }
            }
        }

    }


    // region properties
    @Override
    public String getNekoId() {
        return nekoId;
    }

    @Override
    public void setNekoId(String nekoId) {
        this.nekoId = nekoId;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public List<Nutzer> getNutzers() {
        return mNutzers;
    }

    public void setNutzers(List<Nutzer> nutzers) {
        mNutzers = nutzers;
    }

    public Date getStart() {
        return mStart;
    }

    public void setStart(Date start) {
        mStart = start;
    }

    public Date getEnde() {
        return mEnde;
    }

    public void setEnde(Date ende) {
        mEnde = ende;
    }

    public int getSortNo() {
        return mSortNo;
    }

    public void setSortNo(int sortNo) {
        mSortNo = sortNo;
    }

    public int getZeitInMin() {
        return mZeitInMin;
    }

    public void setZeitInMin(int zeitInMin) {
        mZeitInMin = zeitInMin;
    }

    public String getArt() {
        return mArt;
    }

    public void setArt(String art) {
        mArt = art;
    }

    public String getBemerkung() {
        return mBemerkung;
    }

    public void setBemerkung(String bemerkung) {
        mBemerkung = bemerkung;
    }

    public String getGoogleEventId() {
        return mGoogleEventId;
    }

    public void setGoogleEventId(String googleEventId) {
        mGoogleEventId = googleEventId;
    }

    public List<ToDo> getToDos() {
        return mToDos;
    }

    public void setToDos(List<ToDo> toDos) {
        mToDos = toDos;
    }
    public String getAdresse() {
        return mAdresse;
    }
    public String getAdresseOneLine() {
        return mAdresse.replace("\n",",");
    }
    public void setAdresse(String adresse) {
        mAdresse = adresse;
    }

    public String getPlZ() {
        return mPlZ;
    }

    public void setPlZ(String plZ) {
        mPlZ = plZ;
    }



// endregion properties




}
