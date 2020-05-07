package de.eneko.nekomobile.beans.hlpta;

public class Webes_Grundparameter {
    private String mId = "";
    private String mBezeichnung = "";

    public Webes_Grundparameter(String pId, String pBezeichnung){
        mId = pId;
        mBezeichnung = pBezeichnung;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getBezeichnung() {
        return mBezeichnung;
    }



    @Override
    public String toString() {
        return mId + '-' + mBezeichnung;
    }
}
