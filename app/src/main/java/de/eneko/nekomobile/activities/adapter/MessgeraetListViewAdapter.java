package de.eneko.nekomobile.activities.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.list.MessgeraetListActivity;
import de.eneko.nekomobile.activities.viewHolder.Messgearete.MessgeraetBaseViewHolder;
import de.eneko.nekomobile.activities.viewHolder.Messgearete.MessgeraetRowViewHolder;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;


public class MessgeraetListViewAdapter extends ArrayAdapter<Messgeraet> implements Filterable
{
    private final Context context;
    private final ArrayList<Messgeraet> values;
    private ArrayList<Messgeraet> valuesFiltered;

    private MessgeraetListActivity activity;
    private customFilter mFilter;
    private ViewHolderType vhType = ViewHolderType.WORK;

    public enum ViewHolderType
    {
        WORK, WORK_WITH_NAME, INFO, INFO_WITH_NAME, SONEXA
    }

    public MessgeraetListViewAdapter(Context context, ArrayList<Messgeraet> values, ViewHolderType viewHolderType) {
        super(context, -1, values);
        this.context = context;
        this.activity = (MessgeraetListActivity) context;
        this.values = values;
        this.valuesFiltered = values;
        this.vhType = viewHolderType;
        getFilter();
    }

    @Override
    public int getCount()
    {
        return valuesFiltered.size();
    }

    @Override
    public Messgeraet getItem(int index)
    {
        return valuesFiltered.get(index);
    }

    @Override
    public long getItemId(int id)
    {
        return id;
    }

    @Override
    public View getView(int index, View currentView, ViewGroup parent)
    {

     CurrentObjectNavigation.getInstance().setOnBackPressedIntent( activity.getOnBackPressedIntent());

        //Extrahieren der NoteBean zum nutzen der Werte
        Messgeraet obj = getItem(index);
        if(currentView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            currentView = inflater.inflate(R.layout.list_item_messgeraet, parent, false);
        }
        MessgeraetBaseViewHolder viewHolder = new MessgeraetRowViewHolder(currentView, obj.getBaseModel(), activity);
        viewHolder.updateView();

        if (vhType == ViewHolderType.WORK) {
            if(viewHolder.getTvOrt() != null) {viewHolder.getTvOrt().setVisibility(View.GONE);}
            if(viewHolder.getTvSontexNeueFunknummer() != null) {viewHolder.getTvSontexNeueFunknummer().setVisibility(View.GONE);}
            if(viewHolder.getTvSontexRssi() != null) {viewHolder.getTvSontexRssi().setVisibility(View.GONE);}
        }

        if (vhType == ViewHolderType.SONEXA) {
            viewHolder.getLbStichtag().setVisibility(View.GONE);
            viewHolder.getLbAktuell().setVisibility(View.GONE);
            viewHolder.getTvAktuell().setVisibility(View.GONE);
            viewHolder.getTvStichtag().setVisibility(View.GONE);
            viewHolder.getTvLetzterWert().setVisibility(View.GONE);
            viewHolder.getIvDetail().setVisibility(View.GONE);
            viewHolder.getIvStatus().setVisibility(View.GONE);
            viewHolder.getIvPhoto().setVisibility(View.GONE);
        }

        if (vhType == ViewHolderType.INFO) {
           viewHolder.getLbStichtag().setVisibility(View.GONE);
           viewHolder.getLbAktuell().setVisibility(View.GONE);
           viewHolder.getTvAktuell().setVisibility(View.GONE);
           viewHolder.getTvStichtag().setVisibility(View.GONE);
           viewHolder.getIvDetail().setVisibility(View.GONE);
           viewHolder.getIvStatus().setVisibility(View.GONE);
           viewHolder.getTvOrt().setVisibility(View.GONE);
           viewHolder.getTvSontexNeueFunknummer().setVisibility(View.GONE);
           viewHolder.getTvSontexRssi().setVisibility(View.GONE);
        }

        if (vhType == ViewHolderType.INFO_WITH_NAME) {
            viewHolder.getLbStichtag().setVisibility(View.GONE);
            viewHolder.getLbAktuell().setVisibility(View.GONE);
            viewHolder.getTvAktuell().setVisibility(View.GONE);
            viewHolder.getTvStichtag().setVisibility(View.GONE);
            viewHolder.getIvDetail().setVisibility(View.GONE);
            viewHolder.getIvStatus().setVisibility(View.GONE);
            viewHolder.getTvOrt().setVisibility(View.GONE);
            viewHolder.getTvSontexNeueFunknummer().setVisibility(View.GONE);
            viewHolder.getTvSontexRssi().setVisibility(View.GONE);

            if (obj.getTodo().getNutzer() != null ) { viewHolder.getTvLetzterWert().setText(obj.getTodo().getNutzer().getBaseModel().getDisplay());}else {viewHolder.getTvLetzterWert().setText("DDD");}
        }
        if (vhType == ViewHolderType.WORK_WITH_NAME) {
            if (obj.getTodo().getNutzer() != null ) { viewHolder.getTvLetzterWert().setText(obj.getTodo().getNutzer().getBaseModel().getDisplay());}else {viewHolder.getTvLetzterWert().setText("DDD");}
        }

        currentView.setTag(viewHolder);
        return currentView;
    }
  // region filter


    private class customFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint!=null && constraint.length()>0) {
                ArrayList<Messgeraet> tempList = new ArrayList<Messgeraet>();
                // search content in friend list
                for (Messgeraet messgeraet : values) {
                    if (messgeraet.getNummer().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(messgeraet);
                        continue;
                    }
                    if (messgeraet.getNeueFunkNummer().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(messgeraet);
                        continue;}
                    if (messgeraet.getNeueNummer().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(messgeraet);
                        continue;                    }
                }
                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = values.size();
                filterResults.values = values;
            }
            return filterResults;
        }
        /**
         * Notify about filtered list to ui
         * @param constraint text
         * @param results filtered result
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            valuesFiltered = (ArrayList<Messgeraet>) results.values;
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new customFilter();
        }

        return mFilter;
    }

    // endregion filter

}



