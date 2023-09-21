package de.eneko.nekomobile.beans.hlpta;

public class ZaehlerModel {
    private final Integer mId;
    private String mBezeichnung;
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
    private final Boolean mIsQundisFunk;
    private final String mQundisGeraeteArt;

    public  ZaehlerModel(String pId, String pBezeichnung, String pAustauschmodel, String pArt,String pFunkadapter, String pArtikelnummer,
                         String pSontexAgent, String pSontexCaption, String pSontexDataToRead,
                         String pIsQundisSimpleBarcode, String pIsQundisFunkSimpleBarcode, String pIsQundisFunk, String pQundisGeraeteArt
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
        mIsQundisFunk = pIsQundisFunk.equals("True");
        mQundisGeraeteArt = pQundisGeraeteArt;
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

    public Boolean getmIsQundisFunk() {
        return mIsQundisFunk;
    }

    public String getmQundisGeraeteArt() {
        return mQundisGeraeteArt;
    }
}
