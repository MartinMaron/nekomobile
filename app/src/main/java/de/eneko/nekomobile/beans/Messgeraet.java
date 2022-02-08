package de.eneko.nekomobile.beans;

import android.util.Log;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.Date;

import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.activities.models.MessgeraetModel;
import de.eneko.nekomobile.controllers.Dict;

public class Messgeraet extends BaseObject implements InekoId, ItoXmlElement, Ibewertung {
    private static final String TAG = Messgeraet.class.getName();
    public static final Integer INTEGER_NULLVALUE = -1;

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
    private Integer mModel = INTEGER_NULLVALUE;
    private Boolean mFunkfehler_offen = false;
    private Boolean mFunkfehler_unerreichbar = false;
    private Boolean mFunkfehler_ignorieren = false;
    private Integer mZielmodel = INTEGER_NULLVALUE;
    private ToDo mTodo = null;
    private Double mAktuellValue = INTEGER_NULLVALUE * 1.00; //zmiana Integer na Double
    private Double mStichtagValue = INTEGER_NULLVALUE * 1.00;
    private Date mDatum = null;
    private Date stichtagsdatum = null;
    private Boolean mDefekt = false;
    private String mBemerkung = "";
    private String mNeueNummer = "";
    private String mNeueFunkNummer = "";
    private String mNeuesFunkModel = "X";
    private String mAustauschGrund = "X";
    private String mUndoneGrund = "";
    private Liegenschaft mLiegenschaft = null;
    private Boolean mNew = false;
    private Double mStartWert = -1.0;
    private Double mProcent = -1.0;


    private String mGrundparameter = "";
    private String mFormparameter = "";
    private Integer mBewertungsfaktor_01 = INTEGER_NULLVALUE;
    private Integer mBewertungsfaktor_02 = INTEGER_NULLVALUE;
    private Integer mBewertungsfaktor_03 = INTEGER_NULLVALUE;
    private Integer mBewertungsfaktor_04 = INTEGER_NULLVALUE;
    private Integer mBewertungsfaktor_05 = INTEGER_NULLVALUE;
    private Integer mBewertungsfaktor_06 = INTEGER_NULLVALUE;
    private Integer mBewertungsfaktor_07 = INTEGER_NULLVALUE;
    private Integer mBewertungsfaktor_08 = INTEGER_NULLVALUE;
    private Integer mBewertungsfaktor_09 = INTEGER_NULLVALUE;
    private Integer mBewertungsfaktor_10 = INTEGER_NULLVALUE;
    private Integer mBewertungsfaktor_11 = INTEGER_NULLVALUE;
    private String mReihenanordnung = "";


    public Boolean isBewertungEquals(Ibewertung value) {
        if (value.getBewertungsfaktor_01().equals(getBewertungsfaktor_01())
            && value.getBewertungsfaktor_02().equals(getBewertungsfaktor_02())
            && value.getBewertungsfaktor_03().equals(getBewertungsfaktor_03())
            && value.getBewertungsfaktor_04().equals(getBewertungsfaktor_04())
            && value.getBewertungsfaktor_05().equals(getBewertungsfaktor_05())
            && value.getBewertungsfaktor_06().equals(getBewertungsfaktor_06())
            && value.getBewertungsfaktor_07().equals(getBewertungsfaktor_07())
            && value.getBewertungsfaktor_08().equals(getBewertungsfaktor_08())
            && value.getBewertungsfaktor_09().equals(getBewertungsfaktor_09())
            && value.getBewertungsfaktor_10().equals(getBewertungsfaktor_10())
            && value.getBewertungsfaktor_11().equals(getBewertungsfaktor_11())
            && value.getReihenanordnung().equals(getReihenanordnung())
        ){
            return true;
        }
        else
            {
                return false;
            }
    }

    public Messgeraet(ToDo pTodo) {
        super();
        mTodo = pTodo;
    }
    public Messgeraet(Liegenschaft pLiegenschaft) {
        super();
        mLiegenschaft = pLiegenschaft;
    }


    public File getSontexFile() {
        if (mTodo != null) {
            return mTodo.getSontexFile();
        }
        if (mLiegenschaft != null) {
            return mLiegenschaft.getSontexFile();
        }
        return null;
    }

    public String getSontexGroupCaption() {
        if (mTodo != null) {
            return mTodo.getSontexGroupCaption();
        }
        if (mLiegenschaft != null) {
            return mLiegenschaft.getSontexGroupCaption();
        }
        return null;
    }

    public String getSontexInfoUser() {
        if (mTodo != null) {
            return mTodo.getSontexInfoUser();
        }
        if (mLiegenschaft != null) {
            return mLiegenschaft.getSontexInfoUser();
        }
        return null;
    }


    @Override
    protected Basemodel createBaseObject() {
        return new MessgeraetModel(this);
    }

    @Override
    public MessgeraetModel getBaseModel() {
        if(super.getBaseModel() == null) baseModel = createBaseObject();
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
                        mDatum = getSipleLongDate(propElement);
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
                    case "unDoneGrund":
                        mUndoneGrund = getString(propElement);
                        break;
                    case "new":
                        mNew = getBoolean(propElement);
                        break;
                    case "grundparameter":
                        mGrundparameter = getString(propElement);
                        break;
                    case "formparameter":
                        mFormparameter = getString(propElement);
                        break;
                    case "bewertungsfaktor_01":
                        mBewertungsfaktor_01 = getInteger(propElement);
                        break;
                    case "bewertungsfaktor_02":
                        mBewertungsfaktor_02 = getInteger(propElement);
                        break;
                    case "bewertungsfaktor_03":
                        mBewertungsfaktor_03 = getInteger(propElement);
                        break;
                    case "bewertungsfaktor_04":
                        mBewertungsfaktor_04 = getInteger(propElement);
                        break;
                    case "bewertungsfaktor_05":
                        mBewertungsfaktor_05 = getInteger(propElement);
                        break;
                    case "bewertungsfaktor_06":
                        mBewertungsfaktor_06 = getInteger(propElement);
                        break;
                    case "bewertungsfaktor_07":
                        mBewertungsfaktor_07 = getInteger(propElement);
                        break;
                    case "bewertungsfaktor_08":
                        mBewertungsfaktor_08 = getInteger(propElement);
                        break;
                    case "bewertungsfaktor_09":
                        mBewertungsfaktor_09 = getInteger(propElement);
                        break;
                    case "bewertungsfaktor_10":
                        mBewertungsfaktor_10 = getInteger(propElement);
                        break;
                    case "bewertungsfaktor_11":
                        mBewertungsfaktor_11 = getInteger(propElement);
                        break;
                    case "reihenanordnung":
                        mReihenanordnung = getString(propElement);
                        break;
                    case "startWert":
                        mStartWert = getDouble(propElement);
                        break;
                    case "procent":
                        mProcent = getDouble(propElement);
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
            CreateTextNode(ret_val,"unDoneGrund" , mUndoneGrund);
            CreateTextNode(ret_val,"new" , mNew.toString());
            CreateTextNode(ret_val,"grundparameter" ,mGrundparameter);
            CreateTextNode(ret_val,"formparameter" ,mFormparameter);
            CreateIntegerNode(ret_val,"bewertungsfaktor_01" ,mBewertungsfaktor_01);
            CreateIntegerNode(ret_val,"bewertungsfaktor_02" ,mBewertungsfaktor_02);
            CreateIntegerNode(ret_val,"bewertungsfaktor_03" ,mBewertungsfaktor_03);
            CreateIntegerNode(ret_val,"bewertungsfaktor_04" ,mBewertungsfaktor_04);
            CreateIntegerNode(ret_val,"bewertungsfaktor_05" ,mBewertungsfaktor_05);
            CreateIntegerNode(ret_val,"bewertungsfaktor_06" ,mBewertungsfaktor_06);
            CreateIntegerNode(ret_val,"bewertungsfaktor_07" ,mBewertungsfaktor_07);
            CreateIntegerNode(ret_val,"bewertungsfaktor_08" ,mBewertungsfaktor_08);
            CreateIntegerNode(ret_val,"bewertungsfaktor_09" ,mBewertungsfaktor_09);
            CreateIntegerNode(ret_val,"bewertungsfaktor_10" ,mBewertungsfaktor_10);
            CreateIntegerNode(ret_val,"bewertungsfaktor_11" ,mBewertungsfaktor_11);
            CreateTextNode(ret_val,"reihenanordnung" ,mReihenanordnung);
            CreateDoubleNode(ret_val,"startWert" ,mStartWert);
            CreateDoubleNode(ret_val,"procent" ,mProcent);
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
            return isForFunkCheck() && (getStichtagValue() >= 0 || getAktuellValue() >= 0 || !getAustauschGrund().equals("X"));}
        if (getTodo().getArt().equals(Dict.TODO_MONTAGE_KWZ) ||
                getTodo().getArt().equals(Dict.TODO_MONTAGE_WMZ) ||
                getTodo().getArt().equals(Dict.TODO_MONTAGE_WWZ)){
                // hier ist der Zähler auf jeden Fall ausgetauscht
                if (!getAustauschGrund().equals("X")) {return true;}
                if (!getNeueNummer().equals("")) {return true;}
                if (!getNeueFunkNummer().equals("")){return true;}
            }
        if (getTodo().getArt().equals(Dict.TODO_MONTAGE_HKV)){
            // hier ist der Zähler auf jeden Fall ausgetauscht
            if (!getAustauschGrund().equals("X")) {return true;}
            if (!getNeueNummer().equals("")) {return true;}
        }
        return false;
    };

    public Boolean isUnDone(){
        if (getTodo().getArt().equals(Dict.TODO_ABLESUNG)){
            return !(getDefekt() || getStichtagValue() >= 0 || getAktuellValue() >= 0) && !getFunk();}
        if (getTodo().getArt().equals(Dict.TODO_ZWISCHENABLESUNG)){
            return !(getDefekt() || getAktuellValue() >= 0) && !getFunk();}
        if (getTodo().getArt().equals(Dict.TODO_FUNK_CHECK)){
            return isForFunkCheck() && !isDone();}
        if (getTodo().getArt().equals(Dict.TODO_MONTAGE_KWZ) ||
                getTodo().getArt().equals(Dict.TODO_MONTAGE_WMZ) ||
                getTodo().getArt().equals(Dict.TODO_MONTAGE_HKV) ||
                getTodo().getArt().equals(Dict.TODO_MONTAGE_WWZ)){
            if (getAustauschGrund().equals("X") && getNeueNummer().equals("") && getNeueFunkNummer().equals("") && getUndoneGrund().equals("")) {return true;}
        }
        return false;
    };

    public Boolean isWithError(){
        if (getTodo().getArt().equals(Dict.TODO_ABLESUNG)){
            return getDefekt();}
        if (getTodo().getArt().equals(Dict.TODO_ZWISCHENABLESUNG)){
            return getDefekt();}
        if (getTodo().getArt().equals(Dict.TODO_FUNK_CHECK)){
            return getDefekt();}
        if (getTodo().getArt().equals(Dict.TODO_MONTAGE_KWZ) ||
                getTodo().getArt().equals(Dict.TODO_MONTAGE_WMZ) ||
                getTodo().getArt().equals(Dict.TODO_MONTAGE_HKV) ||
                getTodo().getArt().equals(Dict.TODO_MONTAGE_WWZ)){
            if (!getUndoneGrund().equals("")) {return true;}
        }
        return false;
    };
    public Boolean isNew(){
        return getNekoId().contains("new");
    };

    public Boolean isForFunkCheck (){
        return !getFunkfehler_ignorieren () && (getFunkfehler_offen() || getFunkfehler_unerreichbar() || getTodo().getArt().equals("FUN_CHK"));
    }
    public Boolean isInfo (){
        return getTodo().getArt().equals("INF_RWM") || getTodo().getArt().equals("INF_GER");
    }
    public Boolean isWork (){
        return !isInfo();
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

    public String getUndoneGrund() {
        return mUndoneGrund;
    }

    public void setUndoneGrund(String undoneGrund) {
        mUndoneGrund = undoneGrund;
    }


    public Boolean getNew() {
        return mNew;
    }

    public void setNew(Boolean aNew) {
        mNew = aNew;
        if (mDatum == null) mDatum = new Date();
    }

    public String getGrundparameter() {
        return mGrundparameter;
    }

    public void setGrundparameter(String grundparameter) {
        mGrundparameter = grundparameter;
    }

    public String getFormparameter() {
        return mFormparameter;
    }

    public void setFormparameter(String formparameter) {
        mFormparameter = formparameter;
    }

    public Integer getBewertungsfaktor_01() {
        return mBewertungsfaktor_01;
    }

    public void setBewertungsfaktor_01(Integer bewertungsfaktor_01) {
        mBewertungsfaktor_01 = bewertungsfaktor_01;
    }

    public Integer getBewertungsfaktor_02() {
        return mBewertungsfaktor_02;
    }

    public void setBewertungsfaktor_02(Integer bewertungsfaktor_02) {
        mBewertungsfaktor_02 = bewertungsfaktor_02;
    }

    public Integer getBewertungsfaktor_03() {
        return mBewertungsfaktor_03;
    }

    public void setBewertungsfaktor_03(Integer bewertungsfaktor_03) {
        mBewertungsfaktor_03 = bewertungsfaktor_03;
    }

    public Integer getBewertungsfaktor_04() {
        return mBewertungsfaktor_04;
    }

    public void setBewertungsfaktor_04(Integer bewertungsfaktor_04) {
        mBewertungsfaktor_04 = bewertungsfaktor_04;
    }

    public Integer getBewertungsfaktor_05() {
        return mBewertungsfaktor_05;
    }

    public void setBewertungsfaktor_05(Integer bewertungsfaktor_05) {
        mBewertungsfaktor_05 = bewertungsfaktor_05;
    }

    public Integer getBewertungsfaktor_06() {
        return mBewertungsfaktor_06;
    }

    public void setBewertungsfaktor_06(Integer bewertungsfaktor_06) {
        mBewertungsfaktor_06 = bewertungsfaktor_06;
    }

    public Integer getBewertungsfaktor_07() {
        return mBewertungsfaktor_07;
    }

    public void setBewertungsfaktor_07(Integer bewertungsfaktor_07) {
        mBewertungsfaktor_07 = bewertungsfaktor_07;
    }

    public Integer getBewertungsfaktor_08() {
        return mBewertungsfaktor_08;
    }

    public void setBewertungsfaktor_08(Integer bewertungsfaktor_08) {
        mBewertungsfaktor_08 = bewertungsfaktor_08;
    }

    public Integer getBewertungsfaktor_09() {
        return mBewertungsfaktor_09;
    }

    public void setBewertungsfaktor_09(Integer bewertungsfaktor_09) {
        mBewertungsfaktor_09 = bewertungsfaktor_09;
    }

    public Integer getBewertungsfaktor_10() {
        return mBewertungsfaktor_10;
    }

    public void setBewertungsfaktor_10(Integer bewertungsfaktor_10) {
        mBewertungsfaktor_10 = bewertungsfaktor_10;
    }

    public Integer getBewertungsfaktor_11() {
        return mBewertungsfaktor_11;
    }

    public void setBewertungsfaktor_11(Integer bewertungsfaktor_11) {
        mBewertungsfaktor_11 = bewertungsfaktor_11;
    }

    public String getReihenanordnung() {
        return mReihenanordnung;
    }

    public void setReihenanordnung(String reihenanordnung) {
        mReihenanordnung = reihenanordnung;
    }

    public Double getStartWert() {
        return mStartWert;
    }

    public void setStartWert(Double startWert) {
        mStartWert = startWert;
    }

    public Double getProcent() {
        return mProcent;
    }

    public void setProcent(Double procent) {
        mProcent = procent;
    }
    // endregion "Properties"
}
