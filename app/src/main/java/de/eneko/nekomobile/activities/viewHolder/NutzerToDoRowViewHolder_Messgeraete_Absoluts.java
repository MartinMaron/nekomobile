package de.eneko.nekomobile.activities.viewHolder;


import android.view.View;
import android.widget.TextView;

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

        // Rauchmeldder
        setSvWMZ(mView.findViewById(R.id.ucWMZPart));
        vhWMZ = new ArtContentViewHolder(getSvWMZ(),getBasemodel());
        vhWMZ.updateView();
        vhWMZ.loadDataByArt("WMZ");

        setSvHKV(mView.findViewById(R.id.ucHKVPart));
        vhHKV = new ArtContentViewHolder(getSvHKV(),getBasemodel());
        vhHKV.updateView();
        vhHKV.loadDataByArt("HKV");

        setSvWWZ(mView.findViewById(R.id.ucWWZPart));
        vhWWZ = new ArtContentViewHolder(getSvWWZ(),getBasemodel());
        vhWWZ.updateView();
        vhWWZ.loadDataByArt("WWZ");

        setSvKWZ(mView.findViewById(R.id.ucKWZPart));
        vhKWZ = new ArtContentViewHolder(getSvKWZ(),getBasemodel());
        vhKWZ.updateView();
        vhKWZ.loadDataByArt("KWZ");

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

    public class ArtContentViewHolder extends BaseViewHolder{
        private TextView tvArt = null;
        private TextView tvDone = null;
        private TextView tvWithError = null;
        private TextView tvUnDone = null;

        @Override
        public void updateView() {
            setTvArt(mView.findViewById(R.id.tvArt));
            setTvDone(mView.findViewById(R.id.tvDone));
            setTvWithError(mView.findViewById(R.id.tvWithError));
            setTvUnDone(mView.findViewById(R.id.tvUnDone));
        }



        public void loadDataByArt(String pArt){
            getTvArt().setText(pArt);
            getTvDone().setText(getBasemodel().getDoneCount(pArt).toString());
            getTvUnDone().setText(getBasemodel().getUndoneCount(pArt).toString());
            getTvWithError().setText(getBasemodel().getWithErrorCount(pArt).toString());
            if (getTvDone().getText().toString().equals("0") &&
                    getTvUnDone().getText().toString().equals("0") &&
                    getTvWithError().getText().toString().equals("0"))
            {
                switch (pArt)
                {
                    case "HKV":
                        svHKV.setVisibility(View.INVISIBLE);
                        break;
                    case "WWZ":
                        svWWZ.setVisibility(View.INVISIBLE);
                        break;
                    case "WMZ":
                        svWMZ.setVisibility(View.INVISIBLE);
                        break;
                    case "KWZ":
                        svKWZ.setVisibility(View.INVISIBLE);
                        break;
                }


            }



        }

        public ArtContentViewHolder(View pView, NutzerTodoModel pModel) {
            super(pView, pModel);
        }

        @Override
        public NutzerTodoModel getBasemodel() {
            return (NutzerTodoModel) super.getBasemodel();
        }

        // region properties
        public TextView getTvArt() {
            return tvArt;
        }

        public void setTvArt(TextView tvArt) {
            this.tvArt = tvArt;
        }

        public TextView getTvDone() {
            return tvDone;
        }

        public void setTvDone(TextView tvDone) {
            this.tvDone = tvDone;
        }

        public TextView getTvWithError() {
            return tvWithError;
        }

        public void setTvWithError(TextView tvWithError) {
            this.tvWithError = tvWithError;
        }

        public TextView getTvUnDone() {
            return tvUnDone;
        }

        public void setTvUnDone(TextView tvUnDone) {
            this.tvUnDone = tvUnDone;
        }

        // endregion properties

    }



}
