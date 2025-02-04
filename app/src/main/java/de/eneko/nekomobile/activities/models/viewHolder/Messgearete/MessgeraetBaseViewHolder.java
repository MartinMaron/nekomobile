package de.eneko.nekomobile.activities.models.viewHolder.Messgearete;


import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.InputDialogClass;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.models.MessgeraetModel;
import de.eneko.nekomobile.activities.models.viewHolder.BaseViewHolder;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.beans.hlpta.Webes_Grundparameter;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.controllers.Dict;
import de.eneko.nekomobile.controllers.MessgeraeteConroller;
import de.eneko.nekomobile.controllers.PhotoHandler;

public abstract class MessgeraetBaseViewHolder extends BaseViewHolder {

    public final static Integer REQUEST_BT_STICHTAG = 2001;
    public final static Integer REQUEST_BT_AKTUELL = 2002;
    public final static Integer BT_BARCODE_NEW_MODEL = 1001;
    public final static Integer BT_BARCODE_NEW_NUMMER = 1002;
    public final static Integer BT_BARCODE_NEW_FUNKMODEL = 1003;
    public final static Integer BT_BARCODE_NEW_FUNKNUMMER = 1004;
    public final static Integer BT_SPEAKER = 1005;


    /*
     * Objekt Attribute Decl. and init
     */
    private TextView tvRaum = null;
    private AutoCompleteTextView actvRaum = null;
    private TextView tvNummer = null;
    private TextView tvLetzterWert = null;
    private TextView tvLetzterWertDatum = null;
    private TextView tvModel = null;
    private TextView lbStichtag = null;
    private TextView lbDisplayBewertung = null;
    private TextView lbDisplayBewertungHKArt = null;

    private TextView lbDisplayBesonderheiten = null;

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
    private TextView lbNummer = null;
    private TextView lbRaum = null;
    private TextView lbNewModel = null;
    private Spinner spNewModel = null;
    private ImageView ivBarcodeNewModel = null;
    private TextView lbNewNummer = null;
    private EditText tvNewNummer = null;
    private ImageView ivBarcodeNewNummer = null;
    private TextView lbNewFunkModel = null;
    private Spinner spNewFunkModel = null;
    private TextView lbAustauschGrund = null;
    private Spinner spAustauschgrund = null;
    private ImageView ivBarcodeNewFunkModel = null;
    private TextView lbNewFunkNummer = null;
    private EditText tvNewFunkNummer = null;
    private ImageView ivBarcodeNewFunkNummer = null;
    private CheckBox cbDefekt = null;
    private TextView lbBemerkung = null;
    private EditText etBemerkung = null;
    private ImageView ivSpechToText = null;
    private TextView lbUnDoneGrund = null;
    private AutoCompleteTextView acUnDoneGrund = null;
    private TextView lbStartwert = null;
    private EditText tvStartwert = null;
    private TextView lbProcente = null;
    private EditText tvProcente = null;
    private TextView tvOrt = null;
    private TextView tvSontexNeueFunknummer = null;
    private TextView tvSontexRssi = null;


    public MessgeraetBaseViewHolder(View pView, MessgeraetModel pBean) {
        this(pView, pBean,null);
    }
    public MessgeraetBaseViewHolder(View pView, MessgeraetModel pBean, Activity pActivity) {
        super(pView, pBean, pActivity);
    }

    public MessgeraetBaseViewHolder(View pView, MessgeraetModel pBean, Activity pActivity, Boolean reload) {
        super(pView, pBean, pActivity,reload);
    }

    @Override
    public Activity getActivity() {
        return mActivity;
    }
    public Messgeraet getBean() {
        return (Messgeraet) super.getBasemodel().getBean();
    }


    public MessgeraetModel getBasemodel() {
        return (MessgeraetModel) super.getBasemodel();
    }


    @Override
    public void updateView() {

        setLbAktuell(findViewById(R.id.lbAktuell));
        setLbStichtag(findViewById(R.id.lbStichtag));
        setTvAktuell(findViewById(R.id.tvAktuell));
        setTvStichtag(findViewById(R.id.tvStichtag));
        setIvPhoto(findViewById(R.id.ivPhoto));
        setLbUnDoneGrund(findViewById(R.id.lbUnDoneGrund));
        setAcUnDoneGrund(findViewById(R.id.acUnDoneGrund));
        setLbDisplayBewertung(findViewById(R.id.lbDisplayBewertung));
        setLbDisplayBewertungHKArt(findViewById(R.id.lbDisplayBewertungHKArt));
        setLbDisplayBesonderheiten(findViewById(R.id.lbDisplayBesonderheiten));
        setTvOrt(findViewById(R.id.tvOrt));
        setTvSontexNeueFunknummer(findViewById(R.id.tvSontexNeueFunknummer));
        setTvSontexRssi(findViewById(R.id.tvSontexRssi));

        if(getIvPhoto() != null) {
            getIvPhoto().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDataToModel();
                    String relativePath = getBasemodel().getTodo().getNutzer().getLiegenschaft().getAdresseOneLine();
                    relativePath = relativePath + "@" + getBasemodel().getTodo().getNutzer().getBaseModel().getWohnungsnummerMitLage().replace('.','_');
                    String filename = "#" + getBasemodel().getArt();

                    //ist es nur ein Photo oder oder wird der zähler ausgetauscht
                    if (!getBasemodel().getAustauschGrund().equals("X") || !getBasemodel().getUnDoneGrundGrund().equals("")){
                        filename = filename + "@_WECHSEL";
                    }else{
                        filename = filename + "@_PHOTO";
                    }

                    //ist das Photo von ursprünglichen Zähler oder von neu getauschten
                    if (!getBasemodel().getNeueNummer().equals("")){
                        filename = filename + "@NEU@";
                        // nach welchen Zählernumern soll in neuen zähler gesucht werden
                        if (!getBasemodel().getNeueFunkNummer().trim().equals("")){
                            filename = filename  + getBasemodel().getNeueFunkNummer().trim()+ "@";
                        }else if (!getBasemodel().getNeueNummer().trim().equals("")){
                            filename = filename  + getBasemodel().getNeueNummer().trim()+ "@";
                        }
                    }else{
                        filename = filename + "@ALT@";
                        filename = filename  + getBasemodel().getNummer().trim()+ "@";

                    }
                    // ID
                    if (getBasemodel().getTodo().getNutzer() != null){
                        filename = filename  +  getBasemodel().getTodo().getNutzer().getLiegenschaft().getBud_guid()+ "@";
                    }else {
                        filename = filename  +  getBasemodel().getTodo().getLiegenschaft().getBud_guid()+ "@";
                    }
                    // ID
                    if (!getBasemodel().getNekoId().equals("")){
                        filename = filename  +  getBasemodel().getNekoId()+ "@";
                    }
                    PhotoHandler.getInstance().openCameraIntent(relativePath,filename,getActivity());
                }

            });
        }
        if(getBean().getDatum() != null) {
            if(getLbAktuell() != null) getLbAktuell().setText("Aktuell: " + new SimpleDateFormat(GlobalConst.dayMonthDateFormat).format(getBean().getDatum()));
        }else {
            if(getLbAktuell() != null) getLbAktuell().setText("Aktuell: " + new SimpleDateFormat(GlobalConst.dayMonthDateFormat).format(Calendar.getInstance().getTime()));
        }
        if(getLbStichtag() != null && getBasemodel().getStichtagsdatum() != null) getLbStichtag().setText("Stichtag: " + new SimpleDateFormat(GlobalConst.dayMonthDateFormat).format(getBasemodel().getStichtagsdatum()));
        if(getTvAktuell() != null ){
            getTvAktuell().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setDataToModel();
                    CurrentObjectNavigation.getInstance().setMessgeraet(getBean());
                    if (MessgeraeteConroller.getInstance().getEingabeArt() == MessgeraeteConroller.EingabeArt.SPRACHE) {
                        showInputDialog(); // startSpracheingabe(REQUEST_BT_AKTUELL);
                    } else {
                        showInputDialog();
                    }
                }
            });
        }
        if(getTvAktuell() != null ) {
            getTvStichtag().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setDataToModel();
                    CurrentObjectNavigation.getInstance().setMessgeraet(getBean());
                    if (MessgeraeteConroller.getInstance().getEingabeArt() == MessgeraeteConroller.EingabeArt.SPRACHE) {
                        showInputDialog(); //startSpracheingabe(REQUEST_BT_STICHTAG);
                    } else {
                        showInputDialog();
                    }
                }
            });
        }
             if(getLbDisplayBewertung() != null) {
            getLbDisplayBewertung().setText(getBasemodel().getDisplayBewertung());
            if (getBasemodel().getDisplayBewertung().contains("-1")){
                getLbDisplayBewertung().setTextColor(ContextCompat.getColor(getActivity(), R.color.red));
                if(getLbDisplayBewertungHKArt() != null) { getLbDisplayBewertungHKArt().setTextColor(ContextCompat.getColor(getActivity(), R.color.red));}
            }
        }

        if(getTvSontexRssi() != null ) getTvSontexRssi().setText(getBean().getSonexaRSSI().toString());
        if(getTvSontexNeueFunknummer() != null )
        {
            if (!getBean().getNeueFunkNummer().equals("")) getTvSontexNeueFunknummer().setText(getBean().getNeueFunkNummer());
            if (!getBean().getNeueNummer().equals("")) getTvSontexNeueFunknummer().setText(getBean().getNeueNummer());
        }
        if(getTvOrt() != null ) {
            if (getBean().getTodo().getNutzer() != null)
            {
                getTvOrt().setText("Whg: " + getBean().getTodo().getNutzer().getWohnungsnummer()
                                    + " (" + getBean().getTodo().getNutzer().getLage() + ")"
                                    + " " + getBean().getTodo().getNutzer().getNutzerName());
            }
            if (getBean().getTodo().getLiegenschaft() != null)
            {
                getTvOrt().setText("Gesamtzähler");
            }
        }




        if(getLbDisplayBewertungHKArt() != null) {
            Webes_Grundparameter param = Dict.getInstance().getWebesGrundparameter(getBasemodel().getGrundparameter());
            if (param != null)
            {
                getLbDisplayBewertungHKArt().setText(param.getId().toString() + " - " +  param.getBezeichnung());
            }else
            {
                getLbDisplayBewertungHKArt().setText("");
            }

        }


        if(getLbDisplayBesonderheiten() != null) {
            String procenteString = "";
            if (getBasemodel().getProcent() !=100){
                procenteString =  getBasemodel().getProcent().toString() + " % - ";
            }
            getLbDisplayBesonderheiten().setText(procenteString + getBasemodel().getBemerkung());
        }
    }

    public void setDataToModel() {

    }


    protected void loadData(){

    }

    protected abstract void createLayout();

    public void showInputDialog(){
        new InputDialogClass(getActivity(), getBasemodel()){
            @Override
            public void OnDialogSubmit(double pValueAktuell, double pValueStichtag) {
                getBasemodel().setAktuellValue(pValueAktuell);
                getBasemodel().setStichtagValue(pValueStichtag);
                getBasemodel().save();
                loadData();
            }
        }.show();
    }







    // region properties
    public TextView getTvRaum() {
        return tvRaum;
    }

    public void setTvRaum(TextView tvRaum) {
        this.tvRaum = tvRaum;
    }

    public AutoCompleteTextView getActvRaum() {
        return actvRaum;
    }

    public void setActvRaum(AutoCompleteTextView actvRaum) {
        this.actvRaum = actvRaum;
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

    public TextView getLbNummer() {
        return lbNummer;
    }

    public void setLbNummer(TextView lbNummer) {
        this.lbNummer = lbNummer;
    }

    public TextView getLbRaum() {
        return lbRaum;
    }

    public void setLbRaum(TextView lbRaum) {
        this.lbRaum = lbRaum;
    }

    public TextView getLbNewModel() {
        return lbNewModel;
    }

    public void setLbNewModel(TextView lbNewModel) {
        this.lbNewModel = lbNewModel;
    }

    public Spinner getSpNewModel() {
        return spNewModel;
    }

    public void setSpNewModel(Spinner spNewModel) {
        this.spNewModel = spNewModel;
    }

    public ImageView getIvBarcodeNewModel() {
        return ivBarcodeNewModel;
    }

    public void setIvBarcodeNewModel(ImageView ivBarcodeNewModel) {
        this.ivBarcodeNewModel = ivBarcodeNewModel;
    }

    public TextView getLbNewNummer() {
        return lbNewNummer;
    }

    public void setLbNewNummer(TextView lbNewNummer) {
        this.lbNewNummer = lbNewNummer;
    }

    public EditText getTvNewNummer() {
        return tvNewNummer;
    }

    public void setTvNewNummer(EditText tvNewNummer) {
        this.tvNewNummer = tvNewNummer;
    }

    public ImageView getIvBarcodeNewNummer() {
        return ivBarcodeNewNummer;
    }

    public void setIvBarcodeNewNummer(ImageView ivBarcodeNewNummer) {
        this.ivBarcodeNewNummer = ivBarcodeNewNummer;
    }

    public TextView getLbNewFunkModel() {
        return lbNewFunkModel;
    }

    public void setLbNewFunkModel(TextView lbNewFunkModel) {
        this.lbNewFunkModel = lbNewFunkModel;
    }

    public Spinner getSpNewFunkModel() {
        return spNewFunkModel;
    }

    public void setSpNewFunkModel(Spinner spNewFunkModel) {
        this.spNewFunkModel = spNewFunkModel;
    }

    public ImageView getIvBarcodeNewFunkModel() {
        return ivBarcodeNewFunkModel;
    }

    public void setIvBarcodeNewFunkModel(ImageView ivBarcodeNewFunkModel) {
        this.ivBarcodeNewFunkModel = ivBarcodeNewFunkModel;
    }

    public TextView getLbNewFunkNummer() {
        return lbNewFunkNummer;
    }

    public void setLbNewFunkNummer(TextView lbNewFunkNummer) {
        this.lbNewFunkNummer = lbNewFunkNummer;
    }

    public EditText getTvNewFunkNummer() {
        return tvNewFunkNummer;
    }

    public void setTvNewFunkNummer(EditText tvNewFunkNummer) {
        this.tvNewFunkNummer = tvNewFunkNummer;
    }

    public ImageView getIvBarcodeNewFunkNummer() {
        return ivBarcodeNewFunkNummer;
    }

    public void setIvBarcodeNewFunkNummer(ImageView ivBarcodeNewFunkNummer) {
        this.ivBarcodeNewFunkNummer = ivBarcodeNewFunkNummer;
    }

    public CheckBox getCbDefekt() {
        return cbDefekt;
    }

    public void setCbDefekt(CheckBox cbDefekt) {
        this.cbDefekt = cbDefekt;
    }

    public TextView getLbBemerkung() {
        return lbBemerkung;
    }

    public void setLbBemerkung(TextView lbBemerkung) {
        this.lbBemerkung = lbBemerkung;
    }

    public EditText getEtBemerkung() {
        return etBemerkung;
    }

    public void setEtBemerkung(EditText etBemerkung) {
        this.etBemerkung = etBemerkung;
    }

    public ImageView getIvSpechToText() {
        return ivSpechToText;
    }

    public void setIvSpechToText(ImageView ivSpechToText) {
        this.ivSpechToText = ivSpechToText;
    }

    public TextView getLbAustauschGrund() {
        return lbAustauschGrund;
    }

    public void setLbAustauschGrund(TextView lbAustauschGrund) {
        this.lbAustauschGrund = lbAustauschGrund;
    }

    public Spinner getSpAustauschgrund() {
        return spAustauschgrund;
    }

    public void setSpAustauschgrund(Spinner spAustauschgrund) {
        this.spAustauschgrund = spAustauschgrund;
    }

    public TextView getLbUnDoneGrund() {
        return lbUnDoneGrund;
    }

    public void setLbUnDoneGrund(TextView lbKeinAustauschGrund) {
        this.lbUnDoneGrund = lbKeinAustauschGrund;
    }

    public AutoCompleteTextView getAcUnDoneGrund() {
        return acUnDoneGrund;
    }

    public void setAcUnDoneGrund(AutoCompleteTextView acKeinAustauschGrund) {
        this.acUnDoneGrund = acKeinAustauschGrund;
    }



    public EditText getTvStartwert() {
        return tvStartwert;
    }

    public void setTvStartwert(EditText tvStartwert) {
        this.tvStartwert = tvStartwert;
    }

    public EditText getTvProcente() {
        return tvProcente;
    }

    public void setTvProcente(EditText tvProcente) {
        this.tvProcente = tvProcente;
    }

    public TextView getLbStartwert() {
        return lbStartwert;
    }

    public void setLbStartwert(TextView lbStartwert) {
        this.lbStartwert = lbStartwert;
    }

    public TextView getLbProcente() {
        return lbProcente;
    }

    public void setLbProcente(TextView lbProcente) {
        this.lbProcente = lbProcente;
    }

    public TextView getLbDisplayBewertung() {
        return lbDisplayBewertung;
    }

    public void setLbDisplayBewertung(TextView lbDisplayBewertung) {
        this.lbDisplayBewertung = lbDisplayBewertung;
    }

    public TextView getLbDisplayBewertungHKArt() {
        return lbDisplayBewertungHKArt;
    }

    public void setLbDisplayBewertungHKArt(TextView lbDisplayBewertungHKArt) {
        this.lbDisplayBewertungHKArt = lbDisplayBewertungHKArt;
    }


    public TextView getLbDisplayBesonderheiten() {
        return lbDisplayBesonderheiten;
    }

    public void setLbDisplayBesonderheiten(TextView lbDisplayBesonderheiten) {
        this.lbDisplayBesonderheiten = lbDisplayBesonderheiten;
    }

    public TextView getTvOrt() {
        return tvOrt;
    }

    public void setTvOrt(TextView tvOrt) {
        this.tvOrt = tvOrt;
    }

    public TextView getTvSontexNeueFunknummer() {
        return tvSontexNeueFunknummer;
    }

    public void setTvSontexNeueFunknummer(TextView tvSontexNeueFunknummer) {
        this.tvSontexNeueFunknummer = tvSontexNeueFunknummer;
    }

    public TextView getTvSontexRssi() {
        return tvSontexRssi;
    }

    public void setTvSontexRssi(TextView tvSontexRssi) {
        this.tvSontexRssi = tvSontexRssi;
    }
    // endregion properties
}
