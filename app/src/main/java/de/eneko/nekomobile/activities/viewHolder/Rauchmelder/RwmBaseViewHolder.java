package de.eneko.nekomobile.activities.viewHolder.Rauchmelder;

import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.models.RauchmelderModel;
import de.eneko.nekomobile.beans.Rauchmelder;
import de.eneko.nekomobile.framework.KeyedValue;

public class RwmBaseViewHolder extends de.eneko.nekomobile.activities.viewHolder.BaseViewHolder {

    protected Spinner spModele = null;
    protected TextView lbModel = null;
    protected TextView lbNummer = null;
    protected TextView lbRaum = null;
    protected TextView lbBemerkung = null;
    protected TextView lbAustauschgrund = null;
    protected TextView lbNewNummer = null;
    protected EditText etNummer = null;
    protected AutoCompleteTextView tvRaum = null;
    protected EditText etBemerkungen = null;
    protected Spinner spAustauschgrunde = null;
    protected EditText etNeueNummer = null;
    protected ImageView ivBarcode1 = null;
    protected ImageView ivBarcode2 = null;
    protected ImageView ivSpeaker = null;

    protected ArrayAdapter<KeyedValue> spinnerAdapter = null;
    protected ArrayAdapter<KeyedValue> austauschgrundeAdapter = null;

    protected ImageView ivRwm = null;
    protected ImageView ivPhoto = null;
    protected ImageView ivInfo = null;
    protected ImageView ivAustausch = null;
    protected TextView txtvDescription = null;
    protected Rauchmelder bean = null;


    public RwmBaseViewHolder(View pView, RauchmelderModel pRauchmelder, Activity pActivity){
        super(pView, pRauchmelder, pActivity );
    }

    @Override
    public Activity getActivity() {
        return mActivity;
    }

    @Override
    public RauchmelderModel getBasemodel() {
        return (RauchmelderModel) super.getBasemodel();
    }

    @Override
    public void updateView() {

    }

    private void setRwmImage(ImageView iv){
        if (getBasemodel().getBean().getDone()){
            iv.setImageResource(R.drawable.icon_smoke_detector_green_ok);
        }else {
            if (getBasemodel().getBean().getNekoId().contains("new")) {
                iv.setImageResource(R.drawable.icon_smoke_detector_new);
                iv.setClickable(false);
            }else {
                if (getBasemodel().getBean().getWithError()) {
                    iv.setImageResource(R.drawable.icon_smoke_detector_changed);
                }else{
                    iv.setImageResource(R.drawable.icon_smoke_detector_b);
                }
            }
        }
    }



    // region Controls

    public Spinner getSpModele() {
        return spModele;
    }

    public void setSpModele(Spinner spModele) {
        this.spModele = spModele;
    }

    public TextView getLbModel() {
        return lbModel;
    }

    public void setLbModel(TextView lbModel) {
        this.lbModel = lbModel;
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

    public TextView getLbBemerkung() {
        return lbBemerkung;
    }

    public void setLbBemerkung(TextView lbBemerkung) {
        this.lbBemerkung = lbBemerkung;
    }

    public TextView getLbAustauschgrund() {
        return lbAustauschgrund;
    }

    public void setLbAustauschgrund(TextView lbAustauschgrund) {
        this.lbAustauschgrund = lbAustauschgrund;
    }

    public TextView getLbNewNummer() {
        return lbNewNummer;
    }

    public void setLbNewNummer(TextView lbNewNummer) {
        this.lbNewNummer = lbNewNummer;
    }

    public EditText getEtNummer() {
        return etNummer;
    }

    public void setEtNummer(EditText etNummer) {
        this.etNummer = etNummer;
    }

    public AutoCompleteTextView getTvRaum() {
        return tvRaum;
    }

    public void setTvRaum(AutoCompleteTextView tvRaum) {
        this.tvRaum = tvRaum;
    }

    public EditText getEtBemerkungen() {
        return etBemerkungen;
    }

    public void setEtBemerkungen(EditText etBemerkungen) {
        this.etBemerkungen = etBemerkungen;
    }

    public Spinner getSpAustauschgrunde() {
        return spAustauschgrunde;
    }

    public void setSpAustauschgrunde(Spinner spAustauschgrunde) {
        this.spAustauschgrunde = spAustauschgrunde;
    }

    public EditText getEtNeueNummer() {
        return etNeueNummer;
    }

    public void setEtNeueNummer(EditText etNeueNummer) {
        this.etNeueNummer = etNeueNummer;
    }

    public ImageView getIvBarcode1() {
        return ivBarcode1;
    }

    public void setIvBarcode1(ImageView ivBarcode1) {
        this.ivBarcode1 = ivBarcode1;
    }

    public ImageView getIvBarcode2() {
        return ivBarcode2;
    }

    public void setIvBarcode2(ImageView ivBarcode2) {
        this.ivBarcode2 = ivBarcode2;
    }

    public ImageView getIvSpeaker() {
        return ivSpeaker;
    }

    public void setIvSpeaker(ImageView ivSpeaker) {
        this.ivSpeaker = ivSpeaker;
    }

    public ArrayAdapter<KeyedValue> getSpinnerAdapter() {
        return spinnerAdapter;
    }

    public void setSpinnerAdapter(ArrayAdapter<KeyedValue> spinnerAdapter) {
        this.spinnerAdapter = spinnerAdapter;
    }

    public ArrayAdapter<KeyedValue> getAustauschgrundeAdapter() {
        return austauschgrundeAdapter;
    }

    public void setAustauschgrundeAdapter(ArrayAdapter<KeyedValue> austauschgrundeAdapter) {
        this.austauschgrundeAdapter = austauschgrundeAdapter;
    }

    public ImageView getIvRwm() {
        return ivRwm;
    }

    public void setIvRwm(ImageView ivRwm) {
        this.ivRwm = ivRwm;
    }

    public ImageView getIvPhoto() {
        return ivPhoto;
    }

    public void setIvPhoto(ImageView ivPhoto) {
        this.ivPhoto = ivPhoto;
    }

    public ImageView getIvInfo() {
        return ivInfo;
    }

    public void setIvInfo(ImageView ivInfo) {
        this.ivInfo = ivInfo;
    }

    public ImageView getIvAustausch() {
        return ivAustausch;
    }

    public void setIvAustausch(ImageView ivAustausch) {
        this.ivAustausch = ivAustausch;
    }

    public TextView getTxtvDescription() {
        return txtvDescription;
    }

    public void setTxtvDescription(TextView txtvDescription) {
        this.txtvDescription = txtvDescription;
    }

    // endregion Controls

}
