package de.eneko.nekomobile.activities.wrapper;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NutzerTodosListViewItemWrapper {
    /*
     * Objekt Attribute Decl. and init
     */
    private TextView txtvDescription = null;
    private ImageView ivImage = null;
    private View rwmSubView = null;
    private NutzerTodosRwmWartungListViewItemWrapper rwmWartungWrapper = null;

    public  NutzerTodosListViewItemWrapper() {
        rwmWartungWrapper = new NutzerTodosRwmWartungListViewItemWrapper();
    }

    public ImageView getIvImage() {
        return ivImage;
    }

    public void setIvImage(ImageView ivImage) {
        this.ivImage = ivImage;
    }

    public TextView getTxtvDescription() {
        return txtvDescription;
    }

    public void setTxtvDescription(TextView txtvDescription) {
        this.txtvDescription = txtvDescription;
    }

    public NutzerTodosRwmWartungListViewItemWrapper getRwmWartungWrapper() {
        return rwmWartungWrapper;
    }

    public View getRwmSubView() {
        return rwmSubView;
    }

    public void setRwmSubView(View rwmSubView) {
        this.rwmSubView = rwmSubView;
    }
}
