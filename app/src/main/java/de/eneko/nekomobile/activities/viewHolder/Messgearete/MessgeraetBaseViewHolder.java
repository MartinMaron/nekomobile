package de.eneko.nekomobile.activities.viewHolder.Messgearete;


import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.InputDialogClass;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.models.MessgeraetModel;
import de.eneko.nekomobile.activities.viewHolder.BaseViewHolder;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
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


    public MessgeraetBaseViewHolder(View pView, MessgeraetModel pBean) {
        this(pView, pBean,null);
    }

    public MessgeraetBaseViewHolder(View pView, MessgeraetModel pBean, Activity pActivity) {
        super(pView, pBean, pActivity);
    }

    @Override
    public Activity getActivity() {
        return mActivity;
    }

    public Messgeraet getBean() {
        return (Messgeraet) super.getBasemodel().getBean();
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



        getIvPhoto().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String relativePath = getBean().getTodo().getNutzer().getLiegenschaft().getAdresseOneLine();
                relativePath = relativePath + "@" + getBean().getTodo().getNutzer().getBaseModel().getWohnungsnummerMitLage();
                String filename = "#" + getBean().getArt() + (getBean().getZielmodel() > 0 && !getBean().getNummer().equals("") ? "_NEW":"") + "@" + getBean().getNummer()+ "@" + getBean().getTodo().getNutzer().getNekoId()+ "@";
                if (!getBean().getNekoId().equals("")){
                    filename = filename  + getBean().getNekoId()+ "@";
                }
                PhotoHandler.getInstance().openCameraIntent(relativePath,filename,getActivity());
            }
        });
        if (getBean().getDatum() != null) {
            getLbAktuell().setText("Aktuell: " + new SimpleDateFormat(GlobalConst.dayMonthDateFormat).format(getBean().getDatum()));
        }else {
            getLbAktuell().setText("Aktuell: " + new SimpleDateFormat(GlobalConst.dayMonthDateFormat).format(Calendar.getInstance().getTime()));
        }

        getLbStichtag().setText("Stichtag: " + new SimpleDateFormat(GlobalConst.dayMonthDateFormat).format(getBean().getStichtagsdatum()));
        getTvAktuell().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentObjectNavigation.getInstance().setMessgeraet(getBean());
                if(MessgeraeteConroller.getInstance().getEingabeArt() == MessgeraeteConroller.EingabeArt.SPRACHE){
                    startSpracheingabe(REQUEST_BT_AKTUELL);
                } else {
                    inputDialogAktuell(getBean().getAktuellValue() != -1 ? getBean().getAktuellValue().toString(): "");
                }
            }
        });
        getTvStichtag().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {
                CurrentObjectNavigation.getInstance().setMessgeraet(getBean());
                if(MessgeraeteConroller.getInstance().getEingabeArt() == MessgeraeteConroller.EingabeArt.SPRACHE) {
                    startSpracheingabe(REQUEST_BT_STICHTAG);
                }else {
                    inputDialogStichtag(getBean().getStichtagValue() != -1 ? getBean().getStichtagValue().toString(): "");
                }
            }
        });
    }

    protected void loadData(){

    }
    protected abstract void createLayout();

    public void inputDialogStichtag(String value){
        new InputDialogClass(getActivity(), "stichtag", value){
            @Override
            public void OnDialogSubmit(String pValue) {
                String convertedValue = pValue.replace(" ","");
                if (isDouble(convertedValue)) {
                    getBean().setStichtagValue(Double.parseDouble(convertedValue.replace(",",".")));
                    loadData();
                }
            }
        }.show();
    }
    public void inputDialogAktuell(String value){
        new InputDialogClass(getActivity(), "aktuell", value){
            @Override
            public void OnDialogSubmit(String pValue) {
                String convertedValue = pValue.replace(" ","");
                if (isDouble(convertedValue)) {
                    getBean().setAktuellValue(Double.parseDouble(convertedValue.replace(",",".")));
                    loadData();
                }
            }
        }.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(mActivity, "error", Toast.LENGTH_SHORT).show();
            return;
        }
        if (requestCode == REQUEST_BT_AKTUELL && data != null ) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            new DetailViewHolder(null,new MessgeraetModel(CurrentObjectNavigation.getInstance().getMessgeraet()),mActivity){}.inputDialogAktuell(result.get(0));
        }
        if (requestCode == REQUEST_BT_STICHTAG && data != null ) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            new DetailViewHolder(null,new MessgeraetModel(CurrentObjectNavigation.getInstance().getMessgeraet()),mActivity){}.inputDialogStichtag(result.get(0));
        }


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

    // endregion properties

}
