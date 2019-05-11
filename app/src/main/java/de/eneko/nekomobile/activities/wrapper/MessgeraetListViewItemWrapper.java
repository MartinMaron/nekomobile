package de.eneko.nekomobile.activities.wrapper;


import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MessgeraetListViewItemWrapper {
    /*
     * Objekt Attribute Decl. and init
     */
    private TextView tvRaum = null;
    private TextView tvNummer = null;
    private TextView tvLetzterWert = null;
    private TextView tvLetzterWertDatum = null;
    private TextView tvModel = null;
    private TextView lbStichtag = null;
    private TextView lbAktuell = null;
    private TextView tvAktuell = null;
    private TextView tvStichtag = null;

    private ImageView ivAblesung_andere = null;
    private ImageView ivFortlaufend = null;
    private ImageView ivFunkfehler_offen = null;
    private ImageView ivFunkfehler_unerreichbar = null;
    private ImageView ivStatus = null;
    private ImageView ivPhoto = null;
    private ImageView ivDetail = null;


    // region properties

    public TextView getTvRaum() {
        return tvRaum;
    }

    public void setTvRaum(TextView tvRaum) {
        this.tvRaum = tvRaum;
    }

    public TextView getTvNummer() {
        return tvNummer;
    }

    public void setTvNummer(TextView tvNummer) {
        this.tvNummer = tvNummer;
    }

    public TextView getTvLetzterWert() {
        return tvLetzterWert;
    }

    public void setTvLetzterWert(TextView tvLetzterWert) {
        this.tvLetzterWert = tvLetzterWert;
    }

    public TextView getTvLetzterWertDatum() {
        return tvLetzterWertDatum;
    }

    public void setTvLetzterWertDatum(TextView tvLetzterWertDatum) {
        this.tvLetzterWertDatum = tvLetzterWertDatum;
    }

    public TextView getTvModel() {
        return tvModel;
    }

    public void setTvModel(TextView tvModel) {
        this.tvModel = tvModel;
    }

    public TextView getLbStichtag() {
        return lbStichtag;
    }

    public void setLbStichtag(TextView lbStichtag) {
        this.lbStichtag = lbStichtag;
    }

    public TextView getLbAktuell() {
        return lbAktuell;
    }

    public void setLbAktuell(TextView lbAktuell) {
        this.lbAktuell = lbAktuell;
    }

    public TextView getTvAktuell() {
        return tvAktuell;
    }

    public void setTvAktuell(TextView tvAktuell) {
        this.tvAktuell = tvAktuell;
    }

    public TextView getTvStichtag() {
        return tvStichtag;
    }

    public void setTvStichtag(TextView tvStichtag) {
        this.tvStichtag = tvStichtag;
    }

    public ImageView getIvAblesung_andere() {
        return ivAblesung_andere;
    }

    public void setIvAblesung_andere(ImageView ivAblesung_andere) {
        this.ivAblesung_andere = ivAblesung_andere;
    }

    public ImageView getIvFortlaufend() {
        return ivFortlaufend;
    }

    public void setIvFortlaufend(ImageView ivFortlaufend) {
        this.ivFortlaufend = ivFortlaufend;
    }

    public ImageView getIvFunkfehler_offen() {
        return ivFunkfehler_offen;
    }

    public void setIvFunkfehler_offen(ImageView ivFunkfehler_offen) {
        this.ivFunkfehler_offen = ivFunkfehler_offen;
    }

    public ImageView getIvFunkfehler_unerreichbar() {
        return ivFunkfehler_unerreichbar;
    }

    public void setIvFunkfehler_unerreichbar(ImageView ivFunkfehler_unerreichbar) {
        this.ivFunkfehler_unerreichbar = ivFunkfehler_unerreichbar;
    }

    public ImageView getIvStatus() {
        return ivStatus;
    }

    public void setIvStatus(ImageView ivStatus) {
        this.ivStatus = ivStatus;
    }

    public ImageView getIvPhoto() {
        return ivPhoto;
    }

    public void setIvPhoto(ImageView ivPhoto) {
        this.ivPhoto = ivPhoto;
    }

    public ImageView getIvDetail() {
        return ivDetail;
    }

    public void setIvDetail(ImageView ivDetail) {
        this.ivDetail = ivDetail;
    }

    // endregion properties

}
