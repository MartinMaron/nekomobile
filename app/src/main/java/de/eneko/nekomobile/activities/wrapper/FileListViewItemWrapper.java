package de.eneko.nekomobile.activities.wrapper;

import android.widget.TextView;

import de.eneko.nekomobile.beans.InekoId;

public class FileListViewItemWrapper {
    /*
     * Objekt Attribute Decl. and init
     */
    private TextView txtvRouteName = null;
    private TextView txtvDatum = null;
    private TextView txtvErstellDatum = null;


    public TextView getTxtvRouteName() {
        return txtvRouteName;
    }

    public void setTxtvRouteName(TextView txtvRouteName) {
        this.txtvRouteName = txtvRouteName;
    }

    public TextView getTxtvDatum() {
        return txtvDatum;
    }

    public void setTxtvDatum(TextView txtvDatum) {
        this.txtvDatum = txtvDatum;
    }

    public TextView getTxtvErstellDatum() {
        return txtvErstellDatum;
    }

    public void setTxtvErstellDatum(TextView txtvErstellDatum) {
        this.txtvErstellDatum = txtvErstellDatum;
    }
}
