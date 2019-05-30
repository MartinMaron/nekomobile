package de.eneko.nekomobile.activities.models;

import android.text.TextUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.beans.Nutzer;
import de.eneko.nekomobile.beans.ToDo;
import de.eneko.nekomobile.controllers.Dict;

public class NutzerModel extends Basemodel {
    // region privates
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
    // endregion


    public NutzerModel(Nutzer bean) {
        super(bean);
    }

    @Override
    public Nutzer getBean() {
        return (Nutzer) super.getBean();
    }

    @Override
    public void save() {
        getBean().setLage(getLage());
        getBean().setAbwesend(getAbwesend());
        getBean().setRwmNeuerNutzer(getRwmNeuerNutzer());
        getBean().setBemerkung(getBemerkung());
        getBean().setTelNummer(getTelNummer());
    }

    @Override
    public void load() {
        setStart(getBean().getStart());
        setEnde(getBean().getEnde());
        setLage(getBean().getLage());
        setWohnungsnummer(getBean().getWohnungsnummer());
        setNutzernummer(getBean().getNutzernummer());
        setAbwesend(getBean().getAbwesend());
        setRwmPflicht(getBean().getRwmPflicht());
        setRwmNeuerNutzer(getBean().getRwmNeuerNutzer());
        setBemerkung(getBean().getBemerkung());
        setNutzerName(getBean().getNutzerName());
        setTelNummer(getBean().getTelNummer());
    }

    public Boolean hasAblesung(){
        return getBean().getToDos().stream().filter(r -> r.getArt().equals("ABL_ALL"))
                .collect(Collectors.toList()).size() > 0;
    }
    public Boolean hasMontage(){
        return getBean().getToDos().stream().filter(r -> Arrays.asList(new String[]{"MON_HKV", "MON_WMZ","MON_WWZ","MON_KWZ"}).contains(r.getArt()))
                .collect(Collectors.toList()).size() > 0;
    }
    public Boolean hasRwmMontage(){
        return getBean().getToDos().stream().filter(r -> r.getArt().equals("MON_RWM"))
                .collect(Collectors.toList()).size() > 0;
    }
    public Boolean hasRwmWartung(){
        return getBean().getToDos().stream().filter(r -> r.getArt().equals("WAR_RWM"))
                .collect(Collectors.toList()).size() > 0;
    }
    public Boolean hasFunkcheck(){
        return getBean().getToDos().stream().filter(r -> r.getArt().equals("FUN_CHK"))
                .collect(Collectors.toList()).size() > 0;
    }

    public String getDisplay()
    {
        String retval = "";
        if (!this.mNutzerName.equals("")) retval = this.mNutzerName ;
        if (!this.mNutzerName.equals("")) retval = retval + " " + getWohnungsnummerMitLage();
        return  retval;
    }

    public String getWohnungsnummerMitLage() {
        String retval = String.format("%03d" , mWohnungsnummer) + "-" +
                String.format("%03d" , mNutzernummer);
        if(!TextUtils.isEmpty(mLage))
        {
            retval = retval + " " +  mLage;
        }
        return retval;
    }

    public Boolean isCompleted(){
        return false;
    }

    public Integer getStatusImageResourceId() {
        if (mAbwesend) {
            return R.drawable.nutzer_abwesend;
        } else if (isCompleted()) {
            return R.drawable.icon_ok_48;
        } else {
            return R.drawable.set_abwesend;
        }
    }

//    public ToDo getRwmTodo(){
//        return getToDoByArt("WAR_RWM");
//    }

    public ToDo getToDoByArt(String pArt){
        return getBean().getToDos().stream().filter(el -> el.getArt().equals(pArt)).findFirst().orElse(null);
    }

    public Integer getProgressStatusImageResourceId(String todoArt) {
        ToDo todo = getToDoByArt(Dict.TODO_WARTUNG_RWM);
        if (todo == null){return 0;}
        return todo.getBaseModel().getProgressStatusImageResourceId();
   }

    public ToDo getAblesungTodo(){
        return getToDoByArt(Dict.TODO_ABLESUNG);
    }

    public Integer getAblesungImageResourceId(){
        return getAblesungTodo() != null ? getAblesungTodo().getBaseModel().getProgressStatusImageResourceId():0;

    }



    // region properties

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

    public String getNekoId() {
        return nekoId;
    }

    public void setNekoId(String nekoId) {
        this.nekoId = nekoId;
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

    // endregion
}
