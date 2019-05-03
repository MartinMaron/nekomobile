package de.eneko.nekomobile.activities.wrapper;

import android.content.Intent;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

public class RauchmelderWartungListViewItemWrapper {
    /*
     * Objekt Attribute Decl. and init
     */
    private ImageView ivRwm = null;
    private ImageView ivPhoto = null;
    private ImageView ivInfo = null;
    private ImageView ivAustausch = null;

    public TextView getTxtvDescription() {
        return txtvDescription;
    }

    public void setTxtvDescription(TextView txtvDescription) {
        this.txtvDescription = txtvDescription;
    }

    private TextView txtvDescription = null;


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
}
