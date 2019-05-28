package de.eneko.nekomobile.activities.viewHolder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.activities.models.NutzerTodoModel;
import de.eneko.nekomobile.beans.ToDo;
import de.eneko.nekomobile.controllers.Dict;

public class NutzerToDoRowViewHolder extends BaseViewHolder{
    protected NutzerTodoModel model;

    private TextView txtvDescription = null;
    private ImageView ivImage = null;
    private View svRwmAbsoluts = null;
    private View svMessgeraeteAbsoluts = null;
    private NutzerToDoRowViewHolder_RwmWartung_Absoluts vhRwmAbsoluts = null;
    private NutzerToDoRowViewHolder_Messgeraete_Absoluts vhMessgeraeteAbsoluts = null;

    @Override
    public void updateView() {
        setTxtvDescription(mView.findViewById(R.id.txtvDescription));
        setIvImage(mView.findViewById(R.id.ivImage));

        // Rauchmeldder
        setSvRwmAbsoluts(mView.findViewById(R.id.rwm_wartung_content));
        if (getSvRwmAbsoluts() != null) getSvRwmAbsoluts().setVisibility(View.INVISIBLE);
        setSvMessgeraeteAbsoluts(mView.findViewById(R.id.messgeraet_content));
        if (getSvMessgeraeteAbsoluts() != null) getSvMessgeraeteAbsoluts().setVisibility(View.INVISIBLE);


        //Befuellen der einzelen Widgets
        getTxtvDescription().setText(getBasemodel().getBezeichnung());
        switch (model.getArt())
        {
            case Dict.TODO_WARTUNG_RWM:
                vhRwmAbsoluts = new NutzerToDoRowViewHolder_RwmWartung_Absoluts();
                vhRwmAbsoluts.setTxtvUndone(svRwmAbsoluts.findViewById(R.id.txtvUndone));
                vhRwmAbsoluts.setTxtvDone(svRwmAbsoluts.findViewById(R.id.txtvDone));
                vhRwmAbsoluts.setTxtvWithError(svRwmAbsoluts.findViewById(R.id.txtvWithError));
                vhRwmAbsoluts.setTxtvNew(svRwmAbsoluts.findViewById(R.id.txtvNew));
                getIvImage().setImageResource(getBasemodel().getRwmStatusImageResourceId());
                vhRwmAbsoluts.getTxtvUndone().setText("nicht geprüft: " + getBasemodel().getRwmWartungUndoneCount());
                vhRwmAbsoluts.getTxtvDone().setText("geprüft: " + getBasemodel().getRwmWartungDoneCount());
                vhRwmAbsoluts.getTxtvWithError().setText("mit fehler: " + getBasemodel().getRwmWartungWithErrorCount());
                vhRwmAbsoluts.getTxtvNew().setText("neu installiert: " + getBasemodel().getRwmWartungNewCount());
                getSvRwmAbsoluts().setVisibility(View.VISIBLE);
                break;
            case Dict.TODO_MONTAGE_HKV: case Dict.TODO_MONTAGE_WMZ: case Dict.TODO_MONTAGE_WWZ: case Dict.TODO_MONTAGE_KWZ:
                getIvImage().setImageResource(R.drawable.icon_montage);
            break;
            case Dict.TODO_ABLESUNG:
                getIvImage().setImageResource(getBasemodel().getAblesungImageResourceId());
                getSvMessgeraeteAbsoluts().setVisibility(View.VISIBLE);
                vhMessgeraeteAbsoluts = new NutzerToDoRowViewHolder_Messgeraete_Absoluts(getSvMessgeraeteAbsoluts(),getBasemodel());
                vhMessgeraeteAbsoluts.updateView();
                break;
            case Dict.TODO_MONTAGE_RWM:
                getIvImage().setImageResource(R.drawable.icon_rwm_montage);
                break;
            case Dict.TODO_FUNK_CHECK:
                getIvImage().setImageResource(R.drawable.icon_funk_check);
                break;
            default:
        }
    }

    public NutzerToDoRowViewHolder(View pView, NutzerTodoModel model) {
        super(pView, model);
        this.model = model;
        vhRwmAbsoluts = new NutzerToDoRowViewHolder_RwmWartung_Absoluts();

    }

    @Override
    public NutzerTodoModel getBasemodel() {
        return (NutzerTodoModel) super.getBasemodel();
    }

    // region properties

    public TextView getTxtvDescription() {
        return txtvDescription;
    }

    public void setTxtvDescription(TextView txtvDescription) {
        this.txtvDescription = txtvDescription;
    }

    public ImageView getIvImage() {
        return ivImage;
    }

    public void setIvImage(ImageView ivImage) {
        this.ivImage = ivImage;
    }

    public View getSvRwmAbsoluts() {
        return svRwmAbsoluts;
    }

    public void setSvRwmAbsoluts(View svRwmAbsoluts) {
        this.svRwmAbsoluts = svRwmAbsoluts;
    }

    public NutzerToDoRowViewHolder_RwmWartung_Absoluts getVhRwmAbsoluts() {
        return vhRwmAbsoluts;
    }

    public void setVhRwmAbsoluts(NutzerToDoRowViewHolder_RwmWartung_Absoluts vhRwmAbsoluts) {
        this.vhRwmAbsoluts = vhRwmAbsoluts;
    }

    public View getSvMessgeraeteAbsoluts() {
        return svMessgeraeteAbsoluts;
    }

    public void setSvMessgeraeteAbsoluts(View svMessgeraeteAbsoluts) {
        this.svMessgeraeteAbsoluts = svMessgeraeteAbsoluts;
    }

    public NutzerToDoRowViewHolder_Messgeraete_Absoluts getVhMessgeraeteAbsoluts() {
        return vhMessgeraeteAbsoluts;
    }

    public void setVhMessgeraeteAbsoluts(NutzerToDoRowViewHolder_Messgeraete_Absoluts vhMessgeraeteAbsoluts) {
        this.vhMessgeraeteAbsoluts = vhMessgeraeteAbsoluts;
    }
    // endregion
}
