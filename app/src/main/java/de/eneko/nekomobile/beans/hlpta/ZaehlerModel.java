package de.eneko.nekomobile.beans.hlpta;

import de.eneko.nekomobile.MainActivity;

public class ZaehlerModel {
    private Integer mId = 0;
    private String mBezeichnung = "";
    private String mArt = "";
    private Boolean mAustauschmodel = false;

    public  ZaehlerModel(String pId, String pBezeichnung, String pArt, String pAustauschmodel){
        mId = Integer.parseInt(pId);
        mBezeichnung = pBezeichnung;
        mAustauschmodel = pAustauschmodel.equals("True");
        mArt = pArt;
    }


    public Integer getId() {
        return mId;
    }

    public String getBezeichnung() {
        return mBezeichnung;
    }

    public String getArt() {
        return mArt;
    }

    public Boolean getAustauschmodel() {
        return mAustauschmodel;
    }

    @Override
    public String toString() {
        return mBezeichnung;
    }
}
