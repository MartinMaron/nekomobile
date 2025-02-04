package de.eneko.nekomobile.activities.models.viewHolder;


import android.view.View;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.models.NutzerTodoModel;

public class NutzerToDoRowViewHolder_Messgeraete_Absoluts extends BaseViewHolder{
    protected ArtContentViewHolder vhWMZ = null;
    protected View svWMZ = null;
    protected ArtContentViewHolder vhHKV = null;
    protected View svHKV = null;
    protected ArtContentViewHolder vhWWZ = null;
    protected View svWWZ = null;
    protected ArtContentViewHolder vhKWZ = null;
    protected View svKWZ = null;


    public NutzerToDoRowViewHolder_Messgeraete_Absoluts(View pView, NutzerTodoModel model) {
        super(pView, model);
    }


    @Override
    public NutzerTodoModel getBasemodel() {
        return (NutzerTodoModel) super.getBasemodel();
    }

    @Override
    public void updateView() {

        setSvWMZ(mView.findViewById(R.id.ucWMZPart));
        vhWMZ = new ArtContentViewHolder(getSvWMZ(),getBasemodel());
        vhWMZ.updateView();
        vhWMZ.loadDataByArt(getSvWMZ(), "WMZ");

        setSvHKV(mView.findViewById(R.id.ucHKVPart));
        vhHKV = new ArtContentViewHolder(getSvHKV(),getBasemodel());
        vhHKV.updateView();
        vhHKV.loadDataByArt(getSvHKV(),"HKV");

        setSvWWZ(mView.findViewById(R.id.ucWWZPart));
        vhWWZ = new ArtContentViewHolder(getSvWWZ(),getBasemodel());
        vhWWZ.updateView();
        vhWWZ.loadDataByArt(getSvWWZ(),"WWZ");

        setSvKWZ(mView.findViewById(R.id.ucKWZPart));
        vhKWZ = new ArtContentViewHolder(getSvKWZ(),getBasemodel());
        vhKWZ.updateView();
        vhKWZ.loadDataByArt(getSvKWZ(),"KWZ");

    }


    // region properties
    public ArtContentViewHolder getVhWMZ() {
        return vhWMZ;
    }

    public void setVhWMZ(ArtContentViewHolder vhWMZ) {
        this.vhWMZ = vhWMZ;
    }

    public View getSvWMZ() {
        return svWMZ;
    }

    public void setSvWMZ(View svWMZ) {
        this.svWMZ = svWMZ;
    }

    public ArtContentViewHolder getVhHKV() {
        return vhHKV;
    }

    public void setVhHKV(ArtContentViewHolder vhHKV) {
        this.vhHKV = vhHKV;
    }

    public View getSvHKV() {
        return svHKV;
    }

    public void setSvHKV(View svHKV) {
        this.svHKV = svHKV;
    }

    public ArtContentViewHolder getVhWWZ() {
        return vhWWZ;
    }

    public void setVhWWZ(ArtContentViewHolder vhWWZ) {
        this.vhWWZ = vhWWZ;
    }

    public View getSvWWZ() {
        return svWWZ;
    }

    public void setSvWWZ(View svWWZ) {
        this.svWWZ = svWWZ;
    }

    public ArtContentViewHolder getVhKWZ() {
        return vhKWZ;
    }

    public void setVhKWZ(ArtContentViewHolder vhKWZ) {
        this.vhKWZ = vhKWZ;
    }

    public View getSvKWZ() {
        return svKWZ;
    }

    public void setSvKWZ(View svKWZ) {
        this.svKWZ = svKWZ;
    }

    // endregion


}
