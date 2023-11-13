package de.eneko.nekomobile.activities.models;

import de.eneko.nekomobile.beans.Rauchmelder;

public class RauchmelderModel extends Basemodel {
    private String mNummer = "";
    private String mRaum = "";
    private Integer mModel = 0;

    private Boolean mDone = false ;
    private Boolean mWithError = false;
    private Boolean mNew = false;

    private String mBemerkung = "";
    private String mNeueNummer = "";
    private String mAustauschGrund = "XX";
    private Boolean mDatenAufnahmeFremd = false;


    public RauchmelderModel(Rauchmelder bean) {
        super(bean);
    }

    @Override
    public void save() {
        getBean().setAustauschGrund(mAustauschGrund);
        getBean().setBemerkung(mBemerkung);
        getBean().setDone(mDone);
        getBean().setModel(mModel);
        getBean().setNeueNummer(mNeueNummer);
        getBean().setNew(mNew);
        getBean().setNummer(mNummer);
        getBean().setRaum(mRaum);
        getBean().setWithError(mWithError);
        getBean().setDatenAufnahmeFremd(mDatenAufnahmeFremd);
    }

    @Override
    public void load() {
        mAustauschGrund = getBean().getAustauschGrund();
        mBemerkung = getBean().getBemerkung();
        mDone = getBean().getDone();
        mModel = getBean().getModel();
        mNeueNummer = getBean().getNeueNummer();
        mNew = getBean().getNew();
        mNummer= getBean().getNummer();
        mRaum = getBean().getRaum();
        mWithError = getBean().getWithError();
        mDatenAufnahmeFremd = getBean().getDatenAufnahmeFremd();
        setDataLoaded(true);
    }

    @Override
    public Rauchmelder getBean() {
        return (Rauchmelder) super.getBean();
    }

    public Boolean getUnDone() {
        return !getWithError() && !getDone() && !getBean().getNekoId().contains("new");
    }


    public String getSonexaFunkadress(){
        if ( getNeueNummer() != null && !getNeueNummer().equals("")) {
            return getNeueNummer();
        }else{
            return  getNummer();
        }
    }


    //region properties
    public String getNummer() {
        return mNummer;
    }

    public String getNekoId(){
        return getBean().getNekoId();
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

    public Boolean getmDatenAufnahmeFremd() {
        return mDatenAufnahmeFremd;
    }

    public void setmDatenAufnahmeFremd(Boolean mDatenAufnahmeFremd) {
        this.mDatenAufnahmeFremd = mDatenAufnahmeFremd;
    }

    //endregion
}
