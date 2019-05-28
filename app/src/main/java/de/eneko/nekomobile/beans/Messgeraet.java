package de.eneko.nekomobile.beans;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.text.SimpleDateFormat;
import java.util.Date;
import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.activities.models.MessgeraetModel;

public class Messgeraet extends BaseObject implements InekoId, ItoXmlElement {
    private String nekoId;
    private String mNummer = "";
    private Integer mSortNo = 0;
    private String mRaum = "";
    private Boolean mIsFunk = false ;
    private String mAblesung_andere = "";
    private Double mLetzter_wert = 0.0;
    private Date mLetzter_wert_datum = null;
    private Boolean mFortlaufend = false;
    private Boolean mIsEximFunk = false;
    private Boolean mIsFunkSontex = false;
    private String mArt = "";
    private Integer mModel = -1;
    private Boolean mFunkfehler_offen = false;
    private Boolean mFunkfehler_unerreichbar = false;
    private Boolean mFunkfehler_ignorieren = false;
    private Integer mZielmodel = -1;
    private ToDo mTodo = null;
    private Double mAktuellValue = -1.0;
    private Double mStichtagValue = -1.0;
    private Date mDatum = null;
    private Boolean mDefekt = false;
    private String mBemerkung = "";
    private String mNeueNummer = "";
    private String mNeueFunkNummer = "";
    private String mNeuesFunkModel = "X";
    private String mAustauschGrund = "X";


    public Messgeraet(ToDo pTodo) {
        super();
        mTodo = pTodo;
    }

    @Override
    protected Basemodel createBaseObject() {
        return new MessgeraetModel(this);
    }

    @Override
    public MessgeraetModel getBaseModel() {
        return (MessgeraetModel) super.getBaseModel();
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
                    case "sortNo":
                        mSortNo = XmlHelper.getInteger(propElement);
                        break;
                    case "isFunk":
                        mIsFunk = XmlHelper.getBoolean(propElement);
                        break;
                    case "ablesung_andere":
                        mAblesung_andere = XmlHelper.getString(propElement);
                        break;
                    case "letzter_wert":
                        mLetzter_wert = XmlHelper.getDouble(propElement);
                        break;
                    case "letzter_wert_datum":
                        mLetzter_wert_datum = XmlHelper.getSipleDate(propElement);
                        break;
                    case "fortlaufend":
                        mFortlaufend = XmlHelper.getBoolean(propElement);
                        break;
                    case "isEximFunk":
                        mIsEximFunk = XmlHelper.getBoolean(propElement);
                        break;
                    case "isFunkSontex":
                        mIsFunkSontex = XmlHelper.getBoolean(propElement);
                        break;
                    case "art":
                        mArt = XmlHelper.getString(propElement);
                        break;
                    case "funkfehler_offen":
                        mFunkfehler_offen = XmlHelper.getBoolean(propElement);
                        break;
                    case "funkfehler_unerreichbar":
                        mFunkfehler_unerreichbar = XmlHelper.getBoolean(propElement);
                        break;
                    case "funkfehler_ignorieren":
                        mFunkfehler_ignorieren = XmlHelper.getBoolean(propElement);
                        break;
                    case "zielmodel":
                        mZielmodel = XmlHelper.getInteger(propElement);
                        break;
                    case "aktuellValue":
                        mAktuellValue = XmlHelper.getDouble(propElement);
                        break;
                    case "stichtagValue":
                        mStichtagValue = XmlHelper.getDouble(propElement);
                        break;
                    case "datum":
                        mDatum = XmlHelper.getSipleDate(propElement);
                        break;
                    case "defekt":
                        mDefekt = XmlHelper.getBoolean(propElement);
                        break;
                    case "bemerkung":
                        mBemerkung = XmlHelper.getString(propElement);
                        break;
                    case "neueNummer":
                        mNeueNummer = XmlHelper.getString(propElement);
                        break;
                    case "neueFunkNummer":
                        mNeueFunkNummer = XmlHelper.getString(propElement);
                        break;
                    case "neuesFunkModel":
                        mNeuesFunkModel = XmlHelper.getString(propElement);
                        break;
                    default:
                }
            }
        }

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

    // region "Properties"


    public String getNummer() {
        return mNummer;
    }

    public void setNummer(String nummer) {
        mNummer = nummer;
    }

    public Integer getSortNo() {
        return mSortNo;
    }

    public void setSortNo(Integer sortNo) {
        mSortNo = sortNo;
    }

    public String getRaum() {
        return mRaum;
    }

    public void setRaum(String raum) {
        mRaum = raum;
    }

    public Boolean getFunk() {
        return mIsFunk;
    }

    public void setFunk(Boolean funk) {
        mIsFunk = funk;
    }

    public String getAblesung_andere() {
        return mAblesung_andere;
    }

    public void setAblesung_andere(String ablesung_andere) {
        mAblesung_andere = ablesung_andere;
    }

    public Double getLetzter_wert() {
        return mLetzter_wert;
    }

    public void setLetzter_wert(Double letzter_wert) {
        mLetzter_wert = letzter_wert;
    }

    public Date getLetzter_wert_datum() {
        return mLetzter_wert_datum;
    }

    public void setLetzter_wert_datum(Date letzter_wert_datum) {
        mLetzter_wert_datum = letzter_wert_datum;
    }

    public Boolean getFortlaufend() {
        return mFortlaufend;
    }

    public void setFortlaufend(Boolean fortlaufend) {
        mFortlaufend = fortlaufend;
    }

    public Boolean getEximFunk() {
        return mIsEximFunk;
    }

    public void setEximFunk(Boolean eximFunk) {
        mIsEximFunk = eximFunk;
    }

    public Boolean getFunkSontex() {
        return mIsFunkSontex;
    }

    public void setFunkSontex(Boolean funkSontex) {
        mIsFunkSontex = funkSontex;
    }

    public String getArt() {
        return mArt;
    }

    public void setArt(String art) {
        mArt = art;
    }

    public Integer getModel() {
        return mModel;
    }

    public void setModel(Integer model) {
        mModel = model;
    }

    public Boolean getFunkfehler_offen() {
        return mFunkfehler_offen;
    }

    public void setFunkfehler_offen(Boolean funkfehler_offen) {
        mFunkfehler_offen = funkfehler_offen;
    }

    public Boolean getFunkfehler_unerreichbar() {
        return mFunkfehler_unerreichbar;
    }

    public void setFunkfehler_unerreichbar(Boolean funkfehler_unerreichbar) {
        mFunkfehler_unerreichbar = funkfehler_unerreichbar;
    }

    public Boolean getFunkfehler_ignorieren() {
        return mFunkfehler_ignorieren;
    }

    public void setFunkfehler_ignorieren(Boolean funkfehler_ignorieren) {
        mFunkfehler_ignorieren = funkfehler_ignorieren;
    }

    public Integer getZielmodel() {
        return mZielmodel;
    }

    public void setZielmodel(Integer zielmodel) {
        mZielmodel = zielmodel;
    }

    public ToDo getTodo() {
        return mTodo;
    }

    public Double getAktuellValue() {
        return mAktuellValue;
    }

    public void setAktuellValue(Double aktuellValue) {
        mAktuellValue = aktuellValue;
    }

    public Double getStichtagValue() {
        return mStichtagValue;
    }

    public void setStichtagValue(Double stichtagValue) {
        mStichtagValue = stichtagValue;
    }

    public Date getDatum() {
        return mDatum;
    }

    public void setDatum(Date datum) {
        mDatum = datum;
    }

    public Boolean getDefekt() {
        return mDefekt;
    }

    public void setDefekt(Boolean defekt) {
        mDefekt = defekt;
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

    public String getNeueFunkNummer() {
        return mNeueFunkNummer;
    }

    public void setNeueFunkNummer(String neueFunkNummer) {
        mNeueFunkNummer = neueFunkNummer;
    }

    public String getNeuesFunkModel() {
        return mNeuesFunkModel;
    }

    public void setNeuesFunkModel(String neuesFunkModel) {
        mNeuesFunkModel = neuesFunkModel;
    }

    public String getAustauschGrund() {
        return mAustauschGrund;
    }

    public void setAustauschGrund(String austauschGrund) {
        mAustauschGrund = austauschGrund;
    }

    // endregion "Properties"
}
