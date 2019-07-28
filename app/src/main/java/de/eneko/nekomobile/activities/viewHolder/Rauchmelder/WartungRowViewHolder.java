package de.eneko.nekomobile.activities.viewHolder.Rauchmelder;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.list.RauchmelderWartungListActivity;
import de.eneko.nekomobile.activities.detail.Rwm.RwmActivity_Austausch;
import de.eneko.nekomobile.activities.detail.Rwm.RwmActivity_Info;
import de.eneko.nekomobile.activities.models.RauchmelderModel;
import de.eneko.nekomobile.beans.Rauchmelder;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.controllers.FileHandler;
import de.eneko.nekomobile.controllers.PhotoHandler;

public class WartungRowViewHolder extends RwmBaseViewHolder {


    public WartungRowViewHolder(View pView, RauchmelderModel pRauchmelder, Activity pActivity){
        super(pView, pRauchmelder, pActivity );
    }

    @Override
    public RauchmelderWartungListActivity getActivity() {
        return (RauchmelderWartungListActivity) mActivity;
    }


    public Rauchmelder getBean() {
        return super.getBasemodel().getBean();
    }

    @Override
    public void updateView() {
        setIvAustausch(mView.findViewById(R.id.ivDetail));
        setIvRwm(mView.findViewById(R.id.ivRwm));
        setIvPhoto(mView.findViewById(R.id.ivPhoto));
        setIvInfo(mView.findViewById(R.id.ivInfo));
        setTxtvDescription(mView.findViewById(R.id.txtvRaum));


        getIvRwm().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBasemodel().setDone(!getBasemodel().getDone());
                getBasemodel().save();
                setRwmImage(getIvRwm());
                getIvAustausch().setVisibility( getBean().getDone() || getBean().getNekoId().contains("new")? View.INVISIBLE:View.VISIBLE);
            }
        });

        setRwmImage(getIvRwm());

        getIvPhoto().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String relativePath = getBean().getTodo().getNutzer().getLiegenschaft().getAdresseOneLine();
                relativePath = relativePath + "@" + getBean().getTodo().getNutzer().getBaseModel().getWohnungsnummerMitLage();
                String filename = "#RWM@" + getBean().getNummer()+ "@" + getBean().getTodo().getNutzer().getNekoId()+ "@";
                if (!getBean().getNekoId().equals("")){
                    filename = filename  + getBean().getNekoId()+ "@";
                }
                PhotoHandler.getInstance().openCameraIntent(relativePath,filename,getActivity());
            }
        });

        getIvInfo().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentObjectNavigation.getInstance().setRauchmelder(getBean());
                Intent intent = new Intent(getActivity(), RwmActivity_Info.class);
                getActivity().startActivity(intent);
            }
        });
        getIvAustausch().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentObjectNavigation.getInstance().setRauchmelder(getBean());
                Intent intent = new Intent(getActivity(), RwmActivity_Austausch.class);
                getActivity().startActivity(intent);
            }
        });
        getIvAustausch().setVisibility( getBean().getDone() || getBean().getNekoId().contains("new")? View.INVISIBLE:View.VISIBLE);
        getTxtvDescription().setText((getBean().getNeueNummer() != null && !getBean().getNeueNummer().equals("") ? getBean().getNummer() + " (" + getBean().getNeueNummer() + ")": getBean().getNummer()) + (!getBean().getRaum().equals("") ? " - " + getBean().getRaum():""));
    }

    private void setRwmImage(ImageView iv){
        if (this.getBean().getDone()){
            iv.setImageResource(R.drawable.icon_smoke_detector_green_ok);
        }else {
            if (getBean().getNekoId().contains("new")) {
                iv.setImageResource(R.drawable.icon_smoke_detector_new);
                iv.setClickable(false);
            }else {
                if (getBean().getWithError()) {
                    iv.setImageResource(R.drawable.icon_smoke_detector_changed);
                }else{
                    iv.setImageResource(R.drawable.icon_smoke_detector_b);
                }
            }
        }
    }



    // region Controls

    public TextView getTxtvDescription() {
        return txtvDescription;
    }

    public void setTxtvDescription(TextView txtvDescription) {
        this.txtvDescription = txtvDescription;
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

// endregion Controls

}
