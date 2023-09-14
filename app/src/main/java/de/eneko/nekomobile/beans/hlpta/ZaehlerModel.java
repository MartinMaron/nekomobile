package de.eneko.nekomobile.beans.hlpta;

public class ZaehlerModel {
    private Integer mId = 0;
    private String mBezeichnung = "";
    private String mArt = "";
    private final Boolean mAustauschmodel;
    private final Boolean mFunkintegriert;
    private final String mArtikelnummer;
    private final String mFunkadapter;
    private final String mSontexAgent;
    private final String mSontexCaption;
    private final String mSontexDataToRead;
    private final Boolean mIsQundisSimpleBarcode;
    private final Boolean mIsQundisFunkSimpleBarcode;

    public  ZaehlerModel(String pId, String pBezeichnung, String pAustauschmodel, String pArt,String pFunkadapter, String pArtikelnummer,
                         String pSontexAgent, String pSontexCaption, String pSontexDataToRead, String pIsQundisSimpleBarcode, String pIsQundisFunkSimpleBarcode
    ){
        mId = Integer.parseInt(pId);
        mBezeichnung = pBezeichnung;
        mAustauschmodel = pAustauschmodel.equals("True");
        mArt = pArt;
        mFunkintegriert = !pFunkadapter.equals("");
        mArtikelnummer = pArtikelnummer;
        mFunkadapter = pFunkadapter;
        mSontexAgent = pSontexAgent;
        mSontexCaption = pSontexCaption;
        mSontexDataToRead = pSontexDataToRead;
        mIsQundisSimpleBarcode = pIsQundisSimpleBarcode.equals("True");
        mIsQundisFunkSimpleBarcode = pIsQundisFunkSimpleBarcode.equals("True");
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

    public Boolean getFunkintegriert() {
       return mFunkintegriert;
    }

    public String getArtikelnummer() {
        return mArtikelnummer;
    }

    @Override
    public String toString() {
        return mBezeichnung;
    }

    public String getFunkadapter() {
        return mFunkadapter;
    }

    public String getSontexAgent() {
        return mSontexAgent;
    }

    public String getSontexCaption() {
        return mSontexCaption;
    }

    public String getSontexDataToRead() {
        return mSontexDataToRead;
    }

    public Boolean getmIsQundisSimpleBarcode() {
        return mIsQundisSimpleBarcode;
    }
    public Boolean getmIsQundisFunkSimpleBarcode() {
        return mIsQundisFunkSimpleBarcode;
    }
}
