package de.eneko.nekomobile.activities.viewHolder;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View;

import java.util.Locale;

public abstract class BaseViewHolder {
    protected final View mView;
    protected final Object mBean;
    protected final Activity mActivity;


    public BaseViewHolder(View pView, Object pBean ){
       this(pView,pBean,null);
    }

    public BaseViewHolder(View pView, Object pBean, Activity pActivity ){
        mView = pView;
        mBean = pBean;
        mActivity = pActivity;
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


    public View getView() {
        return mView;
    }

    public Object getBean() {
        return mBean;
    }

    public abstract void updateView();

    public Activity getActivity() {
        return mActivity;
    }
}
