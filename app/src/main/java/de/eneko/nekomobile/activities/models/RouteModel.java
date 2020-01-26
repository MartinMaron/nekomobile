package de.eneko.nekomobile.activities.models;

import java.util.Date;

import de.eneko.nekomobile.beans.Route;

public class RouteModel extends Basemodel {

    private String mBezeichnung;
    private Date mDatum;
    private String mBemerkung;
    private String mNotiz;
    private String mCreateTimestamp;


    public RouteModel(Object bean) {
        super(bean);
    }

    @Override
    public Route getBean() {
        return (Route) super.getBean();
    }

    @Override
    public void save() {
    }

    @Override
    public void load() {
        setDatum(getBean().getDatum());
        setBezeichnung(getBean().getBezeichnung());
        setBemerkung(getBean().getBemerkung());
        setNotiz(getBean().getNotiz());
        setCreateTimestamp(getBean().getCreateTimestamp());
        setDataLoaded(true);
    }

    // region properties

    public String getBezeichnung() {
        return mBezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        mBezeichnung = bezeichnung;
    }

    public Date getDatum() {
        return mDatum;
    }

    public void setDatum(Date datum) {
        mDatum = datum;
    }

    public String getBemerkung() {
        return mBemerkung;
    }

    public void setBemerkung(String bemerkung) {
        mBemerkung = bemerkung;
    }

    public String getNotiz() {
        return mNotiz;
    }

    public void setNotiz(String notiz) {
        mNotiz = notiz;
    }

    public String getCreateTimestamp() {
        return mCreateTimestamp;
    }

    public void setCreateTimestamp(String createTimestamp) {
        mCreateTimestamp = createTimestamp;
    }



    // endregion properties
}
