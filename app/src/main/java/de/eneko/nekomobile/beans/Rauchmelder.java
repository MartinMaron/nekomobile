package de.eneko.nekomobile.beans;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.activities.models.RauchmelderModel;

public class Rauchmelder extends BaseObject implements InekoId, ItoXmlElement {
    private String nekoId;
    private String mNummer = "";
    private String mRaum = "";
    private Integer mModel = 0;

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
        return  null;
        // TODO: Implement ToXmlString
    }

    public void updateRouteFromXmlElement(Element element) {
        this.nekoId = element.getAttributeNode("nekoId").getValue();
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element propElement = (Element) node;
                switch (propElement.getNodeName()) {
                    case "nummer":
                        mNummer = XmlHelper.getString(propElement);
                        break;
                    case "raum":
                        mRaum = XmlHelper.getString(propElement);
                        break;
                    case "model":
                        mModel = XmlHelper.getInteger(propElement);
                        break;
                    default:
                        System.out.println(propElement.getNodeName() + ": keine bekannte Property");
                }
             }
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
