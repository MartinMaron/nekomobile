package de.eneko.nekomobile.activities.viewHolder.Messgearete;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.detail.Messgeraete.MessgaeretAustauschActivity;
import de.eneko.nekomobile.activities.list.MessgeraetListActivity;
import de.eneko.nekomobile.activities.models.MessgeraetModel;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.controllers.FileHandler;
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
    public MessgeraetModel getBasemodel() {
        return (MessgeraetModel) super.getBasemodel();
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


        getTvNummer().setText(getBean().getNummer());
        getTvRaum().setText(getBean().getRaum());
        getTvRaum().setFocusable(false);
        getTvLetzterWert().setText(getBasemodel().getLetzterWertText());
        getTvAktuell().setText(getBean().getAktuellValue() == -1.0 ? "" : FormatHelper.formatDouble(getBean().getAktuellValue()));
        getTvStichtag().setText(getBean().getStichtagValue() == -1.0 ? "" : FormatHelper.formatDouble(getBean().getStichtagValue()));
        mView.setBackgroundResource(getBasemodel().getArtColor());
        getIvDetail().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentObjectNavigation.getInstance().setMessgeraet(getBean());
                Intent  intent = new Intent(view.getContext(), MessgaeretAustauschActivity.class);
                view.getContext().startActivity(intent);
            }
        });




        validate();
    }

    @Override
    protected void createLayout() {

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
