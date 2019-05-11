package de.eneko.nekomobile.activities.viewHolder.Messgearete;


import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.MessgeraetListActivity;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.controllers.FileHandler;
import de.eneko.nekomobile.controllers.FormatHelper;
import de.eneko.nekomobile.controllers.MessgeraeteListViewActivityConroller;

public class MessgeraetRowViewHolder extends MessgeraetBaseViewHolder{

    public MessgeraetRowViewHolder(View pView, Messgeraet pBean) {
        this(pView, pBean,null);
    }

    public MessgeraetRowViewHolder(View pView, Messgeraet pBean, Activity pActivity) {
        super(pView, pBean, pActivity);
    }

    @Override
    public MessgeraetListActivity getActivity() {
        return (MessgeraetListActivity) mActivity;
    }

    @Override
    public void updateView() {
        super.updateView();
        setIvStatus(mView.findViewById(R.id.ivStatus));
        setIvPhoto(mView.findViewById(R.id.ivPhoto));
        setTvNummer(mView.findViewById(R.id.tvNummer));
        setTvRaum(mView.findViewById(R.id.tvRaum));
        setLbAktuell(mView.findViewById(R.id.lbAktuell));
        setLbStichtag(mView.findViewById(R.id.lbStichtag));
        setTvAktuell(mView.findViewById(R.id.tvAktuell));
        setTvLetzterWert(mView.findViewById(R.id.tvLetzterWert));
        setTvStichtag(mView.findViewById(R.id.tvStichtag));
        setIvStatus(mView.findViewById(R.id.ivStatus));



        getTvNummer().setText(getBean().getNummer());
        getTvRaum().setText(getBean().getRaum());
        getLbAktuell().setText("Aktuell");
        getLbStichtag().setText("Stichtag");
        getTvLetzterWert().setText(getBean().getLetzterWertText());
        getTvAktuell().setText(getBean().getAktuellValue() == -1.0 ? "" : FormatHelper.formatDouble(getBean().getAktuellValue()));
        getTvStichtag().setText(getBean().getStichtagValue() == -1.0 ? "" : FormatHelper.formatDouble(getBean().getStichtagValue()));
        mView.setBackgroundResource(getBean().getArtColor());
        getTvAktuell().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileHandler.getInstance().setMessgeraet(getBean());
                if(MessgeraeteListViewActivityConroller.getInstance().getEingabeArt() == MessgeraeteListViewActivityConroller.EingabeArt.SPRACHE){
                    Intent launchIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    launchIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    launchIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.GERMAN);
                    if (launchIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                        getActivity().startActivityForResult(launchIntent, REQUEST_BT_AKTUELL);
                    }
                } else {
                    getActivity().showInputDialog("aktuell",null);
                }
            }
        });
        getTvStichtag().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {
                FileHandler.getInstance().setMessgeraet(getBean());
                if(MessgeraeteListViewActivityConroller.getInstance().getEingabeArt() == MessgeraeteListViewActivityConroller.EingabeArt.SPRACHE) {
                    Intent launchIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    launchIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    launchIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.GERMAN);
                    if (launchIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                        getActivity().startActivityForResult(launchIntent, REQUEST_BT_STICHTAG);
                    }
                }else {
                    getActivity().showInputDialog("stichtag", null);
                }
            }
        });
        validate();
    }

    private void validate()
    {
        Boolean hasError = false;

        // falls gerät fortlaufend und aktueller Wert gefüllt muss der aktuelle Wert grösser sein als der letzte Wert.
        if (getBean().getFortlaufend() && getBean().getAktuellValue() != -1.0 && getBean().getLetzter_wert() > getBean().getAktuellValue()){
            if (getIvStatus() !=null) getIvStatus().setImageResource(R.drawable.icon_alert);
            if (getTvAktuell() !=null) getTvAktuell().setTextColor(ContextCompat.getColor(getActivity(), R.color.red));
            if (getTvLetzterWert() !=null) getTvLetzterWert().setTextColor(ContextCompat.getColor(getActivity(), R.color.red));
            hasError = true;
        }


        if (!hasError){
            if (getTvAktuell() !=null) getTvAktuell().setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
            if (getTvLetzterWert() !=null) getTvLetzterWert().setTextColor(ContextCompat.getColor(getActivity(), R.color.navy));
            if (getIvStatus() !=null) getIvStatus().setImageDrawable(null);
        }
    }
}
