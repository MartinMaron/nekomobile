package de.eneko.nekomobile.beans;

import android.util.Log;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.Date;
import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.activities.models.MessgeraetModel;
import de.eneko.nekomobile.controllers.Dict;

public class Messgeraet extends BaseObject implements InekoId, ItoXmlElement {
    private static final String TAG = Messgeraet.class.getName();
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
    private Date stichtagsdatum = null;
    private Boolean mDefekt = false;
    private String mBemerkung = "";
    private String mNeueNummer = "";
    private String mNeueFunkNummer = "";
    private String mNeuesFunkModel = "X";
    private String mAustauschGrund = "X";
    private Liegenschaft mLiegenschaft = null;
    public Messgeraet(ToDo pTodo) {
        super();
        mTodo = pTodo;
    }
    public Messgeraet(Liegenschaft pLiegenschaft) {
        super();
        mLiegenschaft = pLiegenschaft;
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
                    case "sortNo":
                        mSortNo = getInteger(propElement);
                        break;
                    case "raum":
                        mRaum = getString(propElement);
                        break;
                    case "isFunk":
                        mIsFunk = getBoolean(propElement);
                        break;
                    case "ablesung_andere":
                        mAblesung_andere = getString(propElement);
                        break;
                    case "letzter_wert":
                        mLetzter_wert = getDouble(propElement);
                        break;
                    case "letzter_wert_datum":
                        mLetzter_wert_datum = getSipleDate(propElement);
                        break;
                    case "stichtagsdatum":
                        stichtagsdatum = getSipleDate(propElement);
                        break;
                    case "fortlaufend":
                        mFortlaufend = getBoolean(propElement);
                        break;
                    case "isEximFunk":
                        mIsEximFunk = getBoolean(propElement);
                        break;
                    case "isFunkSontex":
                        mIsFunkSontex = getBoolean(propElement);
                        break;
                    case "art":
                        mArt = getString(propElement);
                        break;
                    case "model":
                        mModel = getInteger(propElement);
                        break;
                    case "funkfehler_offen":
                        mFunkfehler_offen = getBoolean(propElement);
                        break;
                    case "funkfehler_unerreichbar":
                        mFunkfehler_unerreichbar = getBoolean(propElement);
                        break;
                    case "funkfehler_ignorieren":
                        mFunkfehler_ignorieren = getBoolean(propElement);
                        break;
                    case "zielmodel":
                        mZielmodel = getInteger(propElement);
                        break;
                    case "aktuellValue":
                        mAktuellValue = getDouble(propElement);
                        break;
                    case "stichtagValue":
                        mStichtagValue = getDouble(propElement);
                        break;
                    case "datum":
                        mDatum = getSipleDate(propElement);
                        break;
                    case "defekt":
                        mDefekt = getBoolean(propElement);
                        break;
                    case "bemerkung":
                        mBemerkung = getString(propElement);
                        break;
                    case "neueNummer":
                        mNeueNummer = getString(propElement);
                        break;
                    case "neueFunkNummer":
                        mNeueFunkNummer = getString(propElement);
                        break;
                    case "neuesFunkModel":
                        mNeuesFunkModel = getString(propElement);
                        break;
                    case "austauschGrund":
                        mAustauschGrund = getString(propElement);
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

    @Override
    public Element toXmlElement(Document document) {
        Element ret_val = document.createElement("zaehler");

        try {

            if(this instanceof InekoId)
            {
                Attr attr = document.createAttribute("nekoId");
                attr.setValue(this.getNekoId());
                ret_val.setAttributeNode(attr);
            }
            CreateTextNode(ret_val,"nummer" ,mNummer);
            CreateIntegerNode(ret_val,"sortNo" ,mSortNo);
            CreateTextNode(ret_val,"raum" ,mRaum);
            CreateTextNode(ret_val,"isFunk" ,mIsFunk.toString());
            CreateTextNode(ret_val,"ablesung_andere" ,mAblesung_andere);
            CreateDoubleNode(ret_val,"letzter_wert" ,mLetzter_wert);
            CreateDateTextNode(ret_val,"letzter_wert_datum" ,mLetzter_wert_datum);
            CreateDateTextNode(ret_val,"stichtagsdatum" ,stichtagsdatum);
            CreateTextNode(ret_val,"fortlaufend" ,mFortlaufend.toString());
            CreateTextNode(ret_val,"isEximFunk" ,mIsEximFunk.toString());
            CreateTextNode(ret_val,"isFunkSontex" ,mIsFunkSontex.toString());
            CreateTextNode(ret_val,"art" ,mArt);
            CreateIntegerNode(ret_val,"model" ,mModel);
            CreateTextNode(ret_val,"funkfehler_offen" ,mFunkfehler_offen.toString());
            CreateTextNode(ret_val,"funkfehler_unerreichbar" ,mFunkfehler_unerreichbar.toString());
            CreateTextNode(ret_val,"funkfehler_ignorieren" ,mFunkfehler_ignorieren.toString());
            CreateIntegerNode(ret_val,"zielmodel" ,mZielmodel);
            CreateDoubleNode(ret_val,"aktuellValue" ,mAktuellValue);
            CreateDoubleNode(ret_val,"stichtagValue" ,mStichtagValue);
            if (mDatum != null) CreateDateTimeNode(ret_val,"datum" ,mDatum);
            CreateTextNode(ret_val,"defekt" ,mDefekt.toString());
            CreateTextNode(ret_val,"bemerkung" ,mBemerkung);
            CreateTextNode(ret_val,"neueNummer" ,mNeueNummer);
            CreateTextNode(ret_val,"neueFunkNummer" ,mNeueFunkNummer);
            CreateTextNode(ret_val,"neuesFunkModel" ,mNeuesFunkModel);
            CreateTextNode(ret_val,"austauschGrund" ,mAustauschGrund);
        } catch (Exception e) {
            Log.e(TAG, "export error.", e);
        }
        return  ret_val;
    }

    @Override
    public String getNekoId() {
        return nekoId;
    }

    @Override
    public void setNekoId(String nekoId) {
        this.nekoId = nekoId;
    }

    public Boolean isDone(){
        if (getTodo().getArt().equals(Dict.TODO_ABLESUNG)){
        return (getStichtagValue() >= 0 || getAktuellValue() >= 0) && !getFunk() && !getDefekt();}
        if (getTodo().getArt().equals(Dict.TODO_ZWISCHENABLESUNG)){
            return (getAktuellValue() >= 0) && !getFunk() && !getDefekt();}
        if (getTodo().getArt().equals(Dict.TODO_FUNK_CHECK)){
            return getStichtagValue() >= 0 || getAktuellValue() >= 0 || !getAustauschGrund().equals("X");}
        return false;
    };

    public Boolean isUnDone(){
        if (getTodo().getArt().equals(Dict.TODO_ABLESUNG)){
            return !(getDefekt() || getStichtagValue() >= 0 || getAktuellValue() >= 0) && !getFunk();}
        if (getTodo().getArt().equals(Dict.TODO_ZWISCHENABLESUNG)){
            return !(getDefekt() || getAktuellValue() >= 0) && !getFunk();}
        if (getTodo().getArt().equals(Dict.TODO_FUNK_CHECK)){
            return !isDone();}
        return false;
    };

    public Boolean isWithError(){
        if (getTodo().getArt().equals(Dict.TODO_ABLESUNG)){
            return getDefekt();}
        if (getTodo().getArt().equals(Dict.TODO_ZWISCHENABLESUNG)){
            return getDefekt();}
        if (getTodo().getArt().equals(Dict.TODO_FUNK_CHECK)){
            return false;}
        return false;
    };
    public Boolean isNew(){
        return getNekoId().contains("new");
    };



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
        if (mDatum == null) mDatum = new Date();
    }

    public Double getStichtagValue() {
        return mStichtagValue;
    }

    public void setStichtagValue(Double stichtagValue) {
        mStichtagValue = stichtagValue;
        if (mDatum == null) mDatum = new Date();
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
        if (mDatum == null) mDatum = new Date();
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

    public Date getStichtagsdatum() {
        return stichtagsdatum;
    }

    public void setStichtagsdatum(Date stichtagsdatum) {
        this.stichtagsdatum = stichtagsdatum;
    }

    // endregion "Properties"
}
