package de.eneko.nekomobile.activities.viewHolder;

import android.app.Activity;
import android.view.View;

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
