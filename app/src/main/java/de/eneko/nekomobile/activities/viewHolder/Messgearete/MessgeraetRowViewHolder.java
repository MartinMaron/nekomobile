package de.eneko.nekomobile.activities.viewHolder.Messgearete;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.detail.Messgeraete.MessgaeretAustauschActivity;
import de.eneko.nekomobile.activities.detail.Messgeraete.MessgaeretNeuActivity;
import de.eneko.nekomobile.activities.list.MessgeraetListActivity;
import de.eneko.nekomobile.activities.models.MessgeraetModel;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.framework.FormatHelper;

public class MessgeraetRowViewHolder extends MessgeraetBaseViewHolder{

    public MessgeraetRowViewHolder(View pView, MessgeraetModel pBean) {
        this(pView, pBean,null);
    }

    public MessgeraetRowViewHolder(View pView, MessgeraetModel pBean, Activity pActivity) {
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
        setTvNummer(mView.findViewById(R.id.tvNummer));
        setTvRaum(mView.findViewById(R.id.tvRaum));
        setTvLetzterWert(mView.findViewById(R.id.tvLetzterWert));
        setIvStatus(mView.findViewById(R.id.ivStatus));
        setIvDetail(mView.findViewById(R.id.ivDetail));
        getTvRaum().setFocusable(false);


        mView.setBackgroundResource(getBasemodel().getArtColor());
        getIvDetail().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentObjectNavigation.getInstance().setMessgeraet(getBasemodel().getBean());

                if (getBasemodel().getBean().isNew()) {
                    Intent  intent = new Intent(view.getContext(), MessgaeretNeuActivity.class);
                    view.getContext().startActivity(intent);
                }else{
                    Intent  intent = new Intent(view.getContext(), MessgaeretAustauschActivity.class);
                    view.getContext().startActivity(intent);
                }
            }
        });

        loadData();

        if (getIvDetail() !=null) setImage(getIvDetail());
        validate();
    }

    @Override
    protected void loadData(){
        if (getBasemodel().getBean().isNew()){
            getTvNummer().setText("N:" + getBasemodel().getBean().getNeueNummer());
        } else {
            getTvNummer().setText(getBasemodel().getBean().getNummer());
        }
        getTvRaum().setText(getBasemodel().getRaum());
        getTvLetzterWert().setText(getBasemodel().getLetzterWertText());
        getTvAktuell().setText(getBasemodel().getAktuellValue() == -1.0 ? "" : FormatHelper.formatDisplayDouble(getBasemodel().getAktuellValue()));
        getTvStichtag().setText(getBasemodel().getStichtagValue() == -1.0 ? "" : FormatHelper.formatDisplayDouble(getBasemodel().getStichtagValue()));
    }


    @Override
    protected void createLayout() {

    }

    private void validate()
    {
        Boolean hasError = false;

        // falls gerät fortlaufend und aktueller Wert gefüllt muss der aktuelle Wert grösser sein als der letzte Wert.
        if (getBasemodel().getBean().getFortlaufend() && getBasemodel().getAktuellValue() != -1.0 && getBasemodel().getBean().getLetzter_wert() > getBasemodel().getAktuellValue()){
            if (getIvStatus() !=null) getIvStatus().setImageResource(R.drawable.icon_alert);
            if (getTvAktuell() !=null) getTvAktuell().setTextColor(ContextCompat.getColor(getActivity(), R.color.red));
            if (getTvLetzterWert() !=null) getTvLetzterWert().setTextColor(ContextCompat.getColor(getActivity(), R.color.red));
            hasError = true;
        }

        if (!getBasemodel().getAustauschGrund().equals("X") &&
                (getBasemodel().getNeueNummer().equals("") || getBasemodel().getZielmodel() == -1.0)){
            if (getIvStatus() !=null) getIvStatus().setImageResource(R.drawable.icon_alert);
            if (getTvLetzterWert() !=null) {
                getTvLetzterWert().setTextColor(ContextCompat.getColor(getActivity(), R.color.red));
                getTvLetzterWert().setText("Kein Model oder Nummer eingetragen");
            }
            hasError = true;
        }


        if (!hasError){
            if (getTvAktuell() !=null) getTvAktuell().setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
            if (getTvLetzterWert() !=null) getTvLetzterWert().setText(getBasemodel().getLetzterWertText());
            if (getTvLetzterWert() !=null) getTvLetzterWert().setTextColor(ContextCompat.getColor(getActivity(), R.color.navy));
            if (getIvStatus() !=null)  getIvStatus().setImageDrawable(null);
        }






    }

    private void setImage(ImageView iv){
        if (this.getBasemodel().getBean().isDone()){
            iv.setImageResource(R.drawable.icon_montage_ok);
        }else {
            if (getBasemodel().getBean().isNew()) {
                iv.setImageResource(R.drawable.icon_montage_new);
            }else {
                if (getBasemodel().getBean().isWithError()) {
                    iv.setImageResource(R.drawable.icon_montage_error);
                }else{
                    // getBean().isUnDone()
                    if (this.getBasemodel().getTodo().getArt().equals("FUN_CHK") && this.getBasemodel().getBean().isForFunkCheck()) {
                        if(getBasemodel().getBean().getEximFunk() == true){
                            if(getBasemodel().getBean().getFunkfehler_offen() == true){
                                iv.setImageResource(R.drawable.icon_ablesung_exim_open);
                            } else {
                                iv.setImageResource(R.drawable.icon_ablesung_exim_funk);
                            }
                        }
                        if(getBasemodel().getBean().getFunkSontex() == true){
                            if(getBasemodel().getBean().getFunkfehler_offen() == true){
                                iv.setImageResource(R.drawable.icon_ablesung_sontex_open);
                            } else {
                                iv.setImageResource(R.drawable.icon_ablesung_sontex_funk);
                            }
                        }
                    } else {
                        iv.setImageResource(R.drawable.icon_montage);
                    }
                }
            }
        }
    }




}
