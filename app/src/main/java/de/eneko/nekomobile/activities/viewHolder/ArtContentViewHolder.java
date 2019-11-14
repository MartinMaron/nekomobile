package de.eneko.nekomobile.activities.viewHolder;

import android.view.View;
import android.widget.TextView;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.models.NutzerTodoModel;

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



    public void loadDataByArt(View pView, String pArt){
        if (getTvArt() != null) getTvArt().setText(pArt);
        getTvDone().setText(getBasemodel().getDoneCount(pArt).toString());
        getTvUnDone().setText(getBasemodel().getUndoneCount(pArt).toString());
        getTvWithError().setText(getBasemodel().getWithErrorCount(pArt).toString());
        if (getTvDone().getText().toString().equals("0") &&
                getTvUnDone().getText().toString().equals("0") &&
                getTvWithError().getText().toString().equals("0"))
        {
//                switch (pArt)
//                {
//                    case "HKV":
//                        svHKV.setVisibility(View.INVISIBLE);
//                        break;
//                    case "WWZ":
//                        svWWZ.setVisibility(View.INVISIBLE);
//                        break;
//                    case "WMZ":
//                        svWMZ.setVisibility(View.INVISIBLE);
//                        break;
//                    case "KWZ":
//                        svKWZ.setVisibility(View.INVISIBLE);
//                        break;
//                }

                pView.setVisibility(View.VISIBLE);
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
