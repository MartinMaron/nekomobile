package de.eneko.nekomobile.activities.viewHolder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.models.NutzerTodoModel;
import de.eneko.nekomobile.controllers.Dict;

public class NutzerToDoRowViewHolder extends BaseViewHolder{
    private NutzerTodoModel model;
    private TextView txtvDescription = null;
    private ImageView ivImage = null;
    private View svRwmAbsoluts = null;
    private View svMessgeraeteAbsoluts = null;
    private View svArtContentViewHolder = null;
    private NutzerToDoRowViewHolder_RwmWartung_Absoluts vhRwmAbsoluts = null;
    private NutzerToDoRowViewHolder_Messgeraete_Absoluts vhMessgeraeteAbsoluts = null;
    private ArtContentViewHolder vhArtContentViewHolder = null;

    @Override
    public void updateView() {
        setTxtvDescription(mView.findViewById(R.id.txtvDescription));
        setIvImage(mView.findViewById(R.id.ivImage));

        // Rauchmeldder
        setSvRwmAbsoluts(mView.findViewById(R.id.rwm_wartung_content));
        if (getSvRwmAbsoluts() != null) getSvRwmAbsoluts().setVisibility(View.INVISIBLE);
        setSvMessgeraeteAbsoluts(mView.findViewById(R.id.messgeraet_content));
        if (getSvMessgeraeteAbsoluts() != null) getSvMessgeraeteAbsoluts().setVisibility(View.INVISIBLE);
        setSvArtContentViewHolder(mView.findViewById(R.id.messgeraet_content_single));
        if (getSvArtContentViewHolder() != null) getSvArtContentViewHolder().setVisibility(View.INVISIBLE);
        getIvImage().setImageResource(getBasemodel().getProgressStatusImageResourceId());

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
                vhRwmAbsoluts.getTxtvUndone().setText("nicht geprüft: " + getBasemodel().getUndoneCount("RWM"));
                vhRwmAbsoluts.getTxtvDone().setText("geprüft: " + getBasemodel().getDoneCount("RWM"));
                vhRwmAbsoluts.getTxtvWithError().setText("mit fehler: " + getBasemodel().getWithErrorCount("RWM"));
                vhRwmAbsoluts.getTxtvNew().setText("neu installiert: " + getBasemodel().getNewCount("RWM"));
                getSvRwmAbsoluts().setVisibility(View.VISIBLE);
                break;
//            case Dict.TODO_MONTAGE_HKV: case Dict.TODO_MONTAGE_WMZ: case Dict.TODO_MONTAGE_WWZ: case Dict.TODO_MONTAGE_KWZ:
//                getIvImage().setImageResource(R.drawable.icon_montage);
//            break;
            case Dict.TODO_ABLESUNG: case Dict.TODO_FUNK_CHECK:
                    getSvMessgeraeteAbsoluts().setVisibility(View.VISIBLE);
                    vhMessgeraeteAbsoluts = new NutzerToDoRowViewHolder_Messgeraete_Absoluts(getSvMessgeraeteAbsoluts(),getBasemodel());
                    vhMessgeraeteAbsoluts.updateView();
                break;
            case Dict.TODO_MONTAGE_HKV:
                    getSvMessgeraeteAbsoluts().setVisibility(View.VISIBLE);
                    vhMessgeraeteAbsoluts = new NutzerToDoRowViewHolder_Messgeraete_Absoluts(getSvMessgeraeteAbsoluts(),getBasemodel());
                    vhMessgeraeteAbsoluts.updateView();
                    vhMessgeraeteAbsoluts.getSvKWZ().setVisibility(View.GONE);
                    vhMessgeraeteAbsoluts.getSvWMZ().setVisibility(View.GONE);
                    vhMessgeraeteAbsoluts.getSvWWZ().setVisibility(View.GONE);
                break;
            case Dict.TODO_MONTAGE_WMZ:
                    getSvArtContentViewHolder().setVisibility(View.VISIBLE);
                    vhArtContentViewHolder = new ArtContentViewHolder(getSvArtContentViewHolder(),getBasemodel());
                    vhArtContentViewHolder.updateView();
                    vhArtContentViewHolder.loadDataByArt(getSvArtContentViewHolder(),"WMZ");
                break;
            case Dict.TODO_MONTAGE_WWZ:
                    getSvArtContentViewHolder().setVisibility(View.VISIBLE);
                    vhArtContentViewHolder = new ArtContentViewHolder(getSvArtContentViewHolder(),getBasemodel());
                    vhArtContentViewHolder.updateView();
                    vhArtContentViewHolder.loadDataByArt(getSvArtContentViewHolder(),"WWZ");
                break;
            case Dict.TODO_MONTAGE_KWZ:
                    getSvArtContentViewHolder().setVisibility(View.VISIBLE);
                    vhArtContentViewHolder = new ArtContentViewHolder(getSvArtContentViewHolder(),getBasemodel());
                    vhArtContentViewHolder.updateView();
                    vhArtContentViewHolder.loadDataByArt(getSvArtContentViewHolder(),"KWZ");
                break;
            case Dict.TODO_ZWISCHENABLESUNG:
                    getSvMessgeraeteAbsoluts().setVisibility(View.VISIBLE);
                    vhMessgeraeteAbsoluts = new NutzerToDoRowViewHolder_Messgeraete_Absoluts(getSvMessgeraeteAbsoluts(),getBasemodel());
                    vhMessgeraeteAbsoluts.updateView();
               break;
            case Dict.TODO_MONTAGE_RWM:
                getIvImage().setImageResource(R.drawable.icon_rwm_montage);
                break;
            default:
        }
    }

    public NutzerToDoRowViewHolder(View pView, NutzerTodoModel model) {
        super(pView, model);
        this.model = model;
//        vhRwmAbsoluts = new NutzerToDoRowViewHolder_RwmWartung_Absoluts();

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

    public View getSvArtContentViewHolder() {
        return svArtContentViewHolder;
    }

    public void setSvArtContentViewHolder(View svArtContentViewHolder) {
        this.svArtContentViewHolder = svArtContentViewHolder;
    }

    public ArtContentViewHolder getVhArtContentViewHolder() {
        return vhArtContentViewHolder;
    }

    public void setVhArtContentViewHolder(ArtContentViewHolder vhArtContentViewHolder) {
        this.vhArtContentViewHolder = vhArtContentViewHolder;
    }

    // endregion
}
