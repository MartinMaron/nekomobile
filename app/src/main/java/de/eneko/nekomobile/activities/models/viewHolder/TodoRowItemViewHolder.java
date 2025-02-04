package de.eneko.nekomobile.activities.models.viewHolder;


import android.view.View;
import android.widget.ImageView;

import de.eneko.nekomobile.activities.models.Basemodel;

public class TodoRowItemViewHolder extends BaseViewHolder{
    private ImageView ivAblesung = null;
    private ImageView ivRwmWartung = null;
    private ImageView ivMontage = null;
    private ImageView ivRwmMontage = null;
    private ImageView ivFunkCheck = null;

    public TodoRowItemViewHolder(View pView, Basemodel pModel) {
        super(pView, pModel);
    }

    @Override
    public void updateView() {

    }





    // region properties
    public ImageView getIvAblesung() {
        return ivAblesung;
    }

    public void setIvAblesung(ImageView ivAblesung) {
        this.ivAblesung = ivAblesung;
    }

    public ImageView getIvRwmWartung() {
        return ivRwmWartung;
    }

    public void setIvRwmWartung(ImageView ivRwmWartung) {
        this.ivRwmWartung = ivRwmWartung;
    }

    public ImageView getIvMontage() {
        return ivMontage;
    }

    public void setIvMontage(ImageView ivMontage) {
        this.ivMontage = ivMontage;
    }

    public ImageView getIvRwmMontage() {
        return ivRwmMontage;
    }

    public void setIvRwmMontage(ImageView ivRwmMontage) {
        this.ivRwmMontage = ivRwmMontage;
    }

    public ImageView getIvFunkCheck() {
        return ivFunkCheck;
    }

    public void setIvFunkCheck(ImageView ivFunkCheck) {
        this.ivFunkCheck = ivFunkCheck;
    }

// endregion properties
}
