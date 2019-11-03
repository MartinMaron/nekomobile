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
import de.eneko.nekomobile.activities.models.NutzerModel;


public class Nutzer extends BaseObject implements InekoId, ItoXmlElement {
    private static final String TAG = Nutzer.class.getName();
    private Date mStart;
    private Date mEnde;
    private String nekoId;
    private String mLage = "";
    private Integer mWohnungsnummer = 0;
    private Integer mNutzernummer = 0;
    private Boolean mAbwesend = false;
    private Boolean mRwmPflicht = false;
    private Boolean mRwmSelbst = false ;
    private String mRwmNeuerNutzer = "";
    private String mBemerkung ="";
    private String mNutzerName ="";
    private String telNummer ="";
    private String mZwischenablesungNeuerNutzer ="";
    private String mZwischenablesungKontakt ="";
    private String mNutzerNameNeuerInNeko = "";
    private final Liegenschaft mLiegenschaft;
    private List<ToDo> mToDos;

    public Nutzer(Liegenschaft liegenschaft) {
        super();
        mLiegenschaft = liegenschaft;
        mToDos = new ArrayList<ToDo>();
    }

    @Override
    protected Basemodel createBaseObject() {
        return new NutzerModel(this);
    }

    @Override
    public NutzerModel getBaseModel() {
        return (NutzerModel) super.getBaseModel();
    }

    // region Xml

    @Override
    public Element toXmlElement(Document document) {

        Element ret_val = document.createElement("Nutzer");
        try{
            if(this instanceof InekoId)
            {
                Attr attr = document.createAttribute("nekoId");
                attr.setValue(this.getNekoId());
                ret_val.setAttributeNode(attr);
            }

            CreateDateTimeNode(ret_val,"start" ,mStart);
            CreateDateTimeNode(ret_val,"ende" ,mEnde);
            CreateTextNode(ret_val,"lage",mLage);
            CreateIntegerNode(ret_val,"wohnungsNummer",mWohnungsnummer);
            CreateIntegerNode(ret_val,"nutzerNummer",mNutzernummer);
            CreateTextNode(ret_val,"abwesend",mAbwesend.toString());
            CreateTextNode(ret_val,"rwmPflicht",mRwmPflicht.toString());
            CreateTextNode(ret_val,"rwmSelbst",mRwmSelbst.toString());
            CreateTextNode(ret_val,"rwmNeuerNutzer",mRwmNeuerNutzer);
            CreateTextNode(ret_val,"bemerkung",mBemerkung.toString());
            CreateTextNode(ret_val,"nutzerName",mNutzerName.toString());
            CreateTextNode(ret_val,"ZwischenablesungNeuerNutzer",mZwischenablesungNeuerNutzer.toString());
            CreateTextNode(ret_val,"ZwischenablesungKontakt",mZwischenablesungKontakt.toString());
            CreateTextNode(ret_val,"nutzerNameNeuerInNeko",mNutzerNameNeuerInNeko.toString());
            CreateTextNode(ret_val,"telNummer",telNummer.toString());
            Element element = document.createElement("todos");
            mToDos.forEach(item -> element.appendChild(item.toXmlElement(document)));
            ret_val.appendChild(element);
        } catch (Exception e) {
            Log.e(TAG, "export error.", e);
        }
        return  ret_val;
    }

    public void updateRouteFromXmlElement(Element element) {
     try{
        this.nekoId = element.getAttributeNode("nekoId").getValue();
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element propElement = (Element) node;
                switch (propElement.getNodeName()) {
                    case "start":
                        mStart = getSipleLongDate(propElement);
                        break;
                    case "ende":
                        mEnde = getSipleLongDate(propElement);
                        break;
                    case "lage":
                        mLage = getString(propElement).replace("\n","").replace(" ","");
                        break;
                    case "wohnungsNummer":
                        mWohnungsnummer = getInteger(propElement);
                        break;
                    case "abwesend":
                        mAbwesend = getBoolean(propElement);
                        break;
                    case "nutzerNummer":
                        mNutzernummer = getInteger(propElement);
                        break;
                    case "rwmPflicht":
                        mRwmPflicht = getBoolean(propElement);
                        break;
                    case "rwmSelbst":
                        mRwmSelbst = getBoolean(propElement);
                        break;
                    case "rwmNeuerNutzer":
                        mRwmNeuerNutzer = getString(propElement);
                        break;
                    case "bemerkung":
                        mBemerkung = getString(propElement);
                        break;
                    case "nutzerName":
                        mNutzerName = getString(propElement);
                        break;
                    case "telNummer":
                        telNummer = getString(propElement);
                        break;
                    case "ZwischenablesungNeuerNutzer":
                        mZwischenablesungNeuerNutzer = getString(propElement);
                        break;
                    case "ZwischenablesungKontakt":
                        mZwischenablesungKontakt = getString(propElement);
                        break;
                    case "nutzerNameNeuerInNeko":
                        mNutzerNameNeuerInNeko = getString(propElement);
                        break;
                    case "todos":
                        NodeList todoNodeList = propElement.getChildNodes();
                        for (int j = 0; j < todoNodeList.getLength(); j++)
                        {
                            Node todoNode = todoNodeList.item(j);
                            if (todoNode.getNodeType() == Node.ELEMENT_NODE)
                            {
                                Element todoPropElement = (Element) todoNode;
                                ToDo todo = new ToDo(this);
                                todo.updateRouteFromXmlElement(todoPropElement);
                                mToDos.add(todo);
                            }
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

    // endregion Xml







        // region properties
    @Override
    public String getNekoId() {
        return nekoId;
    }

    @Override
    public void setNekoId(String nekoId) {
        this.nekoId = nekoId;
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

    public String getLage() {
        return mLage;
    }

    public void setLage(String lage) {
        mLage = lage;
    }

    public Integer getWohnungsnummer() {
        return mWohnungsnummer;
    }

    public void setWohnungsnummer(Integer wohnungsnummer) {
        mWohnungsnummer = wohnungsnummer;
    }

    public Integer getNutzernummer() {
        return mNutzernummer;
    }

    public void setNutzernummer(Integer nutzernummer) {
        mNutzernummer = nutzernummer;
    }

    public Boolean getAbwesend() {
        return mAbwesend;
    }

    public void setAbwesend(Boolean abwesend) {
        mAbwesend = abwesend;
    }

    public Boolean getRwmPflicht() {
        return mRwmPflicht;
    }

    public void setRwmPflicht(Boolean rwmPflicht) {
        mRwmPflicht = rwmPflicht;
    }

    public Boolean getRwmSelbst() {
        return mRwmSelbst;
    }

    public void setRwmSelbst(Boolean rwmSelbst) {
        mRwmSelbst = rwmSelbst;
    }

    public String getRwmNeuerNutzer() {
        return mRwmNeuerNutzer;
    }

    public void setRwmNeuerNutzer(String rwmNeuerNutzer) {
        mRwmNeuerNutzer = rwmNeuerNutzer;
    }

    public String getBemerkung() {
        return mBemerkung;
    }

    public void setBemerkung(String bemerkung) {
        mBemerkung = bemerkung;
    }

    public String getNutzerName() {
        return mNutzerName;
    }

    public void setNutzerName(String nutzerName) {
        mNutzerName = nutzerName;
    }

    public String getTelNummer() {
        return telNummer;
    }

    public void setTelNummer(String telNummer) {
        this.telNummer = telNummer;
    }


    public List<ToDo> getToDos() {
        return mToDos;
    }

    public void setToDos(List<ToDo> toDos) {
        mToDos = toDos;
    }

    public Liegenschaft getLiegenschaft() {
        return mLiegenschaft;
    }

    public String getZwischenablesungNeuerNutzer() {
        return mZwischenablesungNeuerNutzer;
    }

    public void setZwischenablesungNeuerNutzer(String zwischenablesungNeuerNutzer) {
        mZwischenablesungNeuerNutzer = zwischenablesungNeuerNutzer;
    }

    public String getZwischenablesungKontakt() {
        return mZwischenablesungKontakt;
    }

    public void setZwischenablesungKontakt(String zwischenablesungKontakt) {
        mZwischenablesungKontakt = zwischenablesungKontakt;
    }

    public String getNutzerNameNeuerInNeko() {
        return mNutzerNameNeuerInNeko;
    }

    public void setNutzerNameNeuerInNeko(String nutzerNameNeuerInNeko) {
        mNutzerNameNeuerInNeko = nutzerNameNeuerInNeko;
    }

    // endregion properties
}
