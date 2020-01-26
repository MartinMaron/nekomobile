package de.eneko.nekomobile.activities.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.beans.ToDo;

public class MessgeraetModel extends Basemodel {
    private Integer mSortNo = 0;
    private String mRaum = "";
    private Integer mZielmodel = -1;
    private Double mAktuellValue = -1.0;
    private Double mStichtagValue = -1.0;
    private Date mDatum = null;
    private Boolean mDefekt = false;
    private String mBemerkung = "";
    private String mNeueNummer = "";
    private String mNeueFunkNummer = "";
    private String mNeuesFunkModel = "X";
    private String mAustauschGrund = "X";
    private Date mStichtagsdatum = null;
    private String mUnDoneGrundGrund = "";

    private String mGrundparameter = "";
    private String mFormparameter = "";
    private Integer mBewertungsfaktor_01 = -1;
    private Integer mBewertungsfaktor_02 = -1;
    private Integer mBewertungsfaktor_03 = -1;
    private Integer mBewertungsfaktor_04 = -1;
    private Integer mBewertungsfaktor_05 = -1;
    private Integer mBewertungsfaktor_06 = -1;
    private Integer mBewertungsfaktor_07 = -1;
    private Integer mBewertungsfaktor_08 = -1;
    private Integer mBewertungsfaktor_09 = -1;
    private Integer mBewertungsfaktor_10 = -1;
    private Integer mBewertungsfaktor_11 = -1;
    private String mReihenanordnung = "";



    public MessgeraetModel(Messgeraet bean) {
        super(bean);
    }

    @Override
    public void save() {
        getBean().setSortNo(getSortNo());
        getBean().setRaum(getRaum());
        getBean().setZielmodel(getZielmodel());
        getBean().setAktuellValue(getAktuellValue());
        getBean().setStichtagValue(getStichtagValue());
        if (getDatum() == null) {getBean().setDatum(new Date());} else { getBean().setDatum(getDatum());}
        getBean().setDefekt(getDefekt());
        getBean().setBemerkung(getBemerkung());
        getBean().setNeueNummer(getNeueNummer());
        getBean().setNeueFunkNummer(getNeueFunkNummer());
        getBean().setNeuesFunkModel(getNeuesFunkModel());
        getBean().setAustauschGrund(getAustauschGrund());
        getBean().setUndoneGrund(getUnDoneGrundGrund());
        getBean().setFormparameter(getFormparameter());
        getBean().setGrundparameter(getGrundparameter());
        getBean().setBewertungsfaktor_01(getBewertungsfaktor_01());
        getBean().setBewertungsfaktor_02(getBewertungsfaktor_02());
        getBean().setBewertungsfaktor_03(getBewertungsfaktor_03());
        getBean().setBewertungsfaktor_04(getBewertungsfaktor_04());
        getBean().setBewertungsfaktor_05(getBewertungsfaktor_05());
        getBean().setBewertungsfaktor_06(getBewertungsfaktor_06());
        getBean().setBewertungsfaktor_07(getBewertungsfaktor_07());
        getBean().setBewertungsfaktor_08(getBewertungsfaktor_08());
        getBean().setBewertungsfaktor_09(getBewertungsfaktor_09());
        getBean().setBewertungsfaktor_10(getBewertungsfaktor_10());
        getBean().setBewertungsfaktor_11(getBewertungsfaktor_11());
        getBean().setReihenanordnung(getReihenanordnung());
    }


    @Override
    public void load() {
        setSortNo ( getBean().getSortNo());
        setRaum ( getBean().getRaum());
        setZielmodel ( getBean().getZielmodel());
        setAktuellValue ( getBean().getAktuellValue());
        setStichtagValue ( getBean().getStichtagValue());
        setDatum ( getBean().getDatum());
        setDefekt ( getBean().getDefekt());
        setBemerkung ( getBean().getBemerkung());
        setNeueNummer ( getBean().getNeueNummer());
        setNeueFunkNummer ( getBean().getNeueFunkNummer());
        setNeuesFunkModel (getBean().getNeuesFunkModel());
        setAustauschGrund ( getBean().getAustauschGrund());
        setStichtagsdatum ( getBean().getStichtagsdatum());
        setUnDoneGrundGrund(getBean().getUndoneGrund());
        setFormparameter(getBean().getFormparameter());
        setGrundparameter(getBean().getGrundparameter());
        setBewertungsfaktor_01(getBean().getBewertungsfaktor_01());
        setBewertungsfaktor_02(getBean().getBewertungsfaktor_02());
        setBewertungsfaktor_03(getBean().getBewertungsfaktor_03());
        setBewertungsfaktor_04(getBean().getBewertungsfaktor_04());
        setBewertungsfaktor_05(getBean().getBewertungsfaktor_05());
        setBewertungsfaktor_06(getBean().getBewertungsfaktor_06());
        setBewertungsfaktor_07(getBean().getBewertungsfaktor_07());
        setBewertungsfaktor_08(getBean().getBewertungsfaktor_08());
        setBewertungsfaktor_09(getBean().getBewertungsfaktor_09());
        setBewertungsfaktor_10(getBean().getBewertungsfaktor_10());
        setBewertungsfaktor_11(getBean().getBewertungsfaktor_11());
        setReihenanordnung(getBean().getReihenanordnung());
        setDataLoaded(true);
    }

    public String getDisplayBewertung() {
        if (!getGrundparameter().equals("")){
            switch (getGrundparameter()){
                case "21":
                    return "BH:" + getBewertungsfaktor_01().toString()
                            + "   BL:" + getBewertungsfaktor_03().toString()
                            + "   BL:" + getBewertungsfaktor_04().toString()
                            + "   BT:" + getBewertungsfaktor_05().toString()
                            + "   T3:" + getBewertungsfaktor_06().toString()
                            + "   PD:" + getBewertungsfaktor_09().toString()
                            + "   LT:" + getBewertungsfaktor_10().toString()
                            + "   LH:" + getBewertungsfaktor_11().toString()
                            + "   " + getReihenanordnung();
                default:
                    return "";
            }
        }


        return "";
    }


    public String getLetzterWertText(){
        String ret_val = "";
        if (getBean().getLetzter_wert_datum() != null) {
            ret_val = "Letzer Wert vom " + new SimpleDateFormat(GlobalConst.dateFormat).format(getBean().getLetzter_wert_datum());
            ret_val = ret_val + ": " + getBean().getLetzter_wert().toString();
        }
        return ret_val;
    }

    public Integer getArtColor() {
        Integer ret_val = 0;
        switch (getBean().getArt()){
            case "HKV":
                ret_val = R.color._light_green;
                break;
            case "WMZ":
                ret_val = R.color.light_goldenrod_yellow;
                break;
            case "KWZ":
                ret_val = R.color.light_blue;
                break;
            case "WWZ":
                ret_val = R.color.light_coral;
                break;
            case "STZ":
                ret_val = R.color.light_slate_gray;
                break;
            default:
                return ret_val ;
        }
        return ret_val;
    }





    // region properties

    @Override
    public Messgeraet getBean() {
        return (Messgeraet) super.getBean();
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

    public Integer getZielmodel() {
        return mZielmodel;
    }

    public void setZielmodel(Integer zielmodel) {
        mZielmodel = zielmodel;
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


    public Date getStichtagsdatum() {
        return mStichtagsdatum;
    }

    public void setStichtagsdatum(Date stichtagsdatum) {
        this.mStichtagsdatum = stichtagsdatum;
    }

    public String getUnDoneGrundGrund() {
        return mUnDoneGrundGrund;
    }

    public void setUnDoneGrundGrund(String unDoneGrundGrund) {
        mUnDoneGrundGrund = unDoneGrundGrund;
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

    public String getArt() {
        return getBean().getArt();
    }
    public String getNekoId() {
        return getBean().getNekoId();
    }
    public ToDo getTodo() {
        return getBean().getTodo();
    }
    public String getNummer() {
        return getBean().getNummer();
    }
    
    public Integer getModel() {
        return getBean().getModel();
    }
   //endregion
}
