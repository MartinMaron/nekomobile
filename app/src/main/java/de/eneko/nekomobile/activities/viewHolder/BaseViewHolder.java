package de.eneko.nekomobile.activities.viewHolder;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View;

import java.util.Locale;

import de.eneko.nekomobile.activities.models.Basemodel;

public abstract class BaseViewHolder {
    protected final View mView;
    protected final Activity mActivity;
    protected final Basemodel mBasemodel;


    public BaseViewHolder(View pView, Basemodel pModel ){
       this(pView,pModel,null);
    }

    public BaseViewHolder(View pView, Basemodel pModel, Activity pActivity ){
        mView = pView;
        mActivity = pActivity;
        mBasemodel = pModel;
        mBasemodel.load();
    }

    public void startSpracheingabe(Integer requestId){
        if (getActivity()!= null) {
            Intent launchIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            launchIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            launchIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.GERMAN);
            if (launchIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                getActivity().startActivityForResult(launchIntent, requestId);
            }
        }
    }

    public <T extends View> T findViewById(int id) {
        return mView != null ? mView.findViewById(id) : mActivity.findViewById(id);
    }


    public View getView() {
        return mView;
    }

    public Basemodel getBasemodel() {
        return mBasemodel;
    }

    public abstract void updateView();

    public Activity getActivity() {
        return mActivity;
    }
}
