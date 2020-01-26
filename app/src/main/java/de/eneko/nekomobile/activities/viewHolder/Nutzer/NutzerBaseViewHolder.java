package de.eneko.nekomobile.activities.viewHolder.Nutzer;


import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.activities.models.NutzerModel;
import de.eneko.nekomobile.activities.viewHolder.BaseViewHolder;
import de.eneko.nekomobile.beans.Nutzer;

public class NutzerBaseViewHolder extends BaseViewHolder {
    /*
     * Objekt Attribute Decl. and init
     */
    protected TextView txtvDescription = null;
    protected TextView txtvNutzerLage = null;
    protected TextView tvZwischenablesung = null;

    protected ImageView ivStatus = null;

    public NutzerBaseViewHolder(View pView, Basemodel pModel) {
        super(pView, pModel);
    }

    public NutzerBaseViewHolder(View pView, Basemodel pModel, Activity pActivity) {
        super(pView, pModel, pActivity);
    }


    @Override
    public NutzerModel getBasemodel() {
        return (NutzerModel) super.getBasemodel();
    }

    @Override
    public void updateView() {
        setIvStatus(findViewById(R.id.ivStatus));
        getIvStatus().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!getBasemodel().isCompleted()){
                    getBasemodel().setAbwesend(!getBasemodel().getAbwesend());
                    getBasemodel().save();
                }
                getIvStatus().setImageResource(getBasemodel().getStatusImageResourceId());
            }
        });

        setTxtvDescription(findViewById(R.id.tvDisplay));
        setTxtvNutzerLage(findViewById(R.id.txtvNutzerLage));
        setTvZwischenablesung(findViewById(R.id.txtvZwischenablesung));

        //Befuellen der einzelen Widgets
        getTxtvDescription().setText(getBasemodel().getNutzerName());
        getTxtvNutzerLage().setText(getBasemodel().getWohnungsnummerMitLage());

       if (getTvZwischenablesung() != null) {
            if (this.getBasemodel().hasZwischenAblesung()) {
                getTvZwischenablesung().setText("Zwischenablesung bei " + this.getBasemodel().getDisplayZwischenablesung());
            } else {
                if (! this.getBasemodel().getNutzerNameNeuerInNeko().equals("")) {
                    getTvZwischenablesung().setText("Neuer Nutzername in Neko: " + this.getBasemodel().getNutzerNameNeuerInNeko());
                }else {
                    getTvZwischenablesung().setVisibility(View.GONE);
                }
            }
        }
        setStatusImage(getIvStatus(),getBasemodel().getBean());

    }

    protected void setStatusImage( ImageView iv, Nutzer pNutzer){
        iv.setImageResource(pNutzer.getBaseModel().getStatusImageResourceId());
    }

    public TextView getTxtvDescription() {
        return txtvDescription;
    }

    public void setTxtvDescription(TextView txtvDescription) {
        this.txtvDescription = txtvDescription;
    }

    public TextView getTxtvNutzerLage() {
        return txtvNutzerLage;
    }

    public void setTxtvNutzerLage(TextView txtvNutzerLage) {
        this.txtvNutzerLage = txtvNutzerLage;
    }

    public ImageView getIvStatus() {
        return ivStatus;
    }

    public void setIvStatus(ImageView ivStatus) {
        this.ivStatus = ivStatus;
    }


    public TextView getTvZwischenablesung() {
        return tvZwischenablesung;
    }

    public void setTvZwischenablesung(TextView tvZwischenablesung) {
        this.tvZwischenablesung = tvZwischenablesung;
    }

}
