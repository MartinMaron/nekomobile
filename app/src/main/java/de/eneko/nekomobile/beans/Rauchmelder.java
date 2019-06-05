package de.eneko.nekomobile.beans;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Date;

import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.activities.models.RauchmelderModel;

public class Rauchmelder extends BaseObject implements InekoId, ItoXmlElement {
    private String nekoId;
    private String mNummer = "";
    private String mRaum = "";
    private Integer mModel = 0;
    private Date mEingabedatum = null;
    private Boolean mDone = false ;
    private Boolean mWithError = false;
    private Boolean mNew = false;

    private String mBemerkung = "";
    private String mNeueNummer = "";
    private String mAustauschGrund = "XX";

    private ToDo mTodo;

    public Rauchmelder(ToDo todo) {
        mTodo = todo;
    }

    @Override
    protected Basemodel createBaseObject() {
        return new RauchmelderModel(this);
    }

    @Override
    public RauchmelderModel getBaseModel() {
        return (RauchmelderModel) super.getBaseModel();
    }

    // region Xml
    @Override
    public Element toXmlElement(Document document) {
        Element ret_val = document.createElement("RWM_Device");
        try {
            if(this instanceof InekoId)
            {
                Attr attr = document.createAttribute("nekoId");
                attr.setValue(this.getNekoId());
                ret_val.setAttributeNode(attr);
            }

            CreateTextNode(ret_val,"nummer" ,mNummer);
            CreateTextNode(ret_val,"raum" ,mRaum);
            CreateIntegerNode(ret_val,"model",mModel);
            if (mEingabedatum != null) CreateDateTimeNode(ret_val,"eingabedatum",mEingabedatum);
            CreateTextNode(ret_val,"done",mDone.toString());
            CreateTextNode(ret_val,"withError",mWithError.toString());
            CreateTextNode(ret_val,"new",mNew.toString());
            if (mBemerkung != null) CreateTextNode(ret_val,"bemerkung",mBemerkung);
            if (mNeueNummer != null) CreateTextNode(ret_val,"neueNummer",mNeueNummer);
            if (mAustauschGrund != "X") CreateTextNode(ret_val,"austauschgrund",mAustauschGrund);

        } catch (Exception e) {
            e.printStackTrace();
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
                    case "nummer":
                        mNummer = getString(propElement);
                        break;
                    case "raum":
                        mRaum = getString(propElement);
                        break;
                    case "model":
                        mModel = getInteger(propElement);
                        break;
                    case "eingabedatum":
                        mEingabedatum = getSipleLongDate(propElement);
                        break;
                    case "done":
                        mDone = getBoolean(propElement);
                        break;
                    case "withError":
                        mWithError = getBoolean(propElement);
                        break;
                    case "new":
                        mNew = getBoolean(propElement);
                        break;
                    case "bemerkung":
                        mBemerkung = getString(propElement);
                        break;
                    case "neueNummer":
                        mNeueNummer = getString(propElement);
                        break;
                    case "austauschgrund":
                        mAustauschGrund = getString(propElement);
                    default:
                        System.out.println(propElement.getNodeName() + ": keine bekannte Property");
                }
             }
        }
        } catch (Exception e) {
            e.printStackTrace();
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

    public String getNummer() {
        return mNummer;
    }

    public void setNummer(String nummer) {
        mNummer = nummer;
    }

    public String getRaum() {
        return mRaum;
    }

    public void setRaum(String raum) {
        mRaum = raum;
    }

    public Integer getModel() {
        return mModel;
    }

    public void setModel(Integer model) {
        mModel = model;
    }

    public Boolean getDone() {
        return mDone;
    }

    public void setDone(Boolean done) {
        mDone = done;
        if (mEingabedatum == null) mEingabedatum = new Date();
    }

    public Boolean getWithError() {
        return mWithError;
    }

    public void setWithError(Boolean withError) {
        mWithError = withError;
    }

    public Boolean getNew() {
        return mNew;
    }

    public void setNew(Boolean aNew) {
        mNew = aNew;
    }


    public ToDo getTodo() {
        return mTodo;
    }

    public String getBemerkung() {
        return mBemerkung;
    }

    public void setBemerkung(String bemerkung) {
        mBemerkung = bemerkung;
    }

    public String getNeueNummer() {
        return mNeueNummer;
    }

    public void setNeueNummer(String neueNummer) {
        mNeueNummer = neueNummer;
    }

    public String getAustauschGrund() {
        return mAustauschGrund;
    }

    public void setAustauschGrund(String austauschGrund) {
        mAustauschGrund = austauschGrund;
    }

    // endregion properties

}
