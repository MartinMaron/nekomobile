package de.eneko.nekomobile.beans.hlpta;

public class FunkModel {
    private String mId = "";
    private String mBezeichnung = "";
    private String mArtikelnummer = "";

    public FunkModel(String pId, String pBezeichnung, String pArtikelnummer){
        mId = pId;
        mBezeichnung = pBezeichnung;
        mArtikelnummer = pArtikelnummer;
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

    public String getArtikelnummer() {
        return mArtikelnummer;
    }

    @Override
    public String toString() {
        return mBezeichnung;
    }
}
