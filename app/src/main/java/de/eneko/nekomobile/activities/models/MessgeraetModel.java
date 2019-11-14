package de.eneko.nekomobile.activities.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.beans.Messgeraet;

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



    public MessgeraetModel(Messgeraet bean) {
        super(bean);
    }

    @Override
    public void save() {
        getBean().setSortNo(mSortNo);
        getBean().setRaum(mRaum);
        getBean().setZielmodel(mZielmodel);
        getBean().setAktuellValue(mAktuellValue);
        getBean().setStichtagValue(mStichtagValue);
        getBean().setDatum(mDatum);
        getBean().setDefekt(mDefekt);
        getBean().setBemerkung(mBemerkung);
        getBean().setNeueNummer(mNeueNummer);
        getBean().setNeueFunkNummer(mNeueFunkNummer);
        getBean().setNeuesFunkModel(mNeuesFunkModel);
        getBean().setAustauschGrund(mAustauschGrund);
        getBean().setUndoneGrund(mUnDoneGrundGrund);
    }

    @Override
    public void load() {
        mSortNo = getBean().getSortNo();
        mRaum = getBean().getRaum();
        mZielmodel = getBean().getZielmodel();
        mAktuellValue = getBean().getAktuellValue();
        mStichtagValue = getBean().getStichtagValue();
        mDatum = getBean().getDatum();
        mDefekt = getBean().getDefekt();
        mBemerkung = getBean().getBemerkung();
        mNeueNummer = getBean().getNeueNummer();
        mNeueFunkNummer = getBean().getNeueFunkNummer();
        mNeuesFunkModel = getBean().getNeuesFunkModel();
        mAustauschGrund = getBean().getAustauschGrund();
        mStichtagsdatum = getBean().getStichtagsdatum();
        mUnDoneGrundGrund = getBean().getUndoneGrund();
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

    //endregion
}
