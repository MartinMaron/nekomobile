package de.eneko.nekomobile.activities.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import java.util.ArrayList;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.MessgeraetListActivity;
import de.eneko.nekomobile.activities.wrapper.MessgeraetListViewItemWrapper;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.beans.Nutzer;


public class MessgeraetListViewAdapter extends ArrayAdapter<Messgeraet> implements Filterable
{
    private final Context context;
    private final ArrayList<Messgeraet> values;
    private ArrayList<Messgeraet> valuesFiltered;

    private MessgeraetListActivity activity;
    private customFilter mFilter;

    public MessgeraetListViewAdapter(Context context, ArrayList<Messgeraet> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
        this.valuesFiltered = values;
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

        //Extrahieren der NoteBean zum nutzen der Werte
        Messgeraet obj = getItem(index);
        MessgeraetListViewItemWrapper wrapper = new MessgeraetListViewItemWrapper();

        if(currentView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            currentView = inflater.inflate(R.layout.messgeraet_list_item, parent, false);

        }

//        wrapper.setIvStatus(currentView.findViewById(R.id.ivStatus));
//        wrapper.getIvStatus().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!nutzer.isCompleted()){
//                    nutzer.setAbwesend(!nutzer.getAbwesend());
//                }
//                wrapper.getIvStatus().setImageResource(nutzer.getStatusImageResourceId());
//            }
//        });



//        wrapper.setTxtvDescription(currentView.findViewById(R.id.txtvDescription));
//        wrapper.setTxtvNutzerLage(currentView.findViewById(R.id.txtvNutzerLage));
//
//        wrapper.getTodoRow().setIvAblesung(currentView.findViewById(R.id.ivAblesung));
//        wrapper.getTodoRow().setIvFunkCheck(currentView.findViewById(R.id.ivFunkCheck));
//        wrapper.getTodoRow().setIvMontage(currentView.findViewById(R.id.ivMontage));
//        wrapper.getTodoRow().setIvRwmMontage(currentView.findViewById(R.id.ivRwmMontage));
//        wrapper.getTodoRow().setIvRwmWartung(currentView.findViewById(R.id.ivRwmWartung));
//
//
//
//
//        //Befuellen der einzelen Widgets
//        wrapper.getTxtvDescription().setText(nutzer.getNutzerName());
//        wrapper.getTxtvNutzerLage().setText(nutzer.getWohnungsnummerMitLage());
//
//
//        // sichtbarkei der Icons
//        wrapper.getTodoRow().getIvAblesung().setVisibility(nutzer.hasAblesung() ? View.VISIBLE: View.GONE);
//        wrapper.getTodoRow().getIvMontage().setVisibility(nutzer.hasMontage() ? View.VISIBLE: View.GONE);
//        wrapper.getTodoRow().getIvRwmMontage().setVisibility(nutzer.hasRwmMontage() ? View.VISIBLE: View.GONE);
//        wrapper.getTodoRow().getIvRwmWartung().setVisibility(nutzer.hasRwmWartung() ? View.VISIBLE: View.GONE);
//        wrapper.getTodoRow().getIvRwmWartung().setImageResource(nutzer.getRwmStatusImageResourceId());
//        wrapper.getTodoRow().getIvFunkCheck().setVisibility(nutzer.hasFunkcheck() ? View.VISIBLE: View.GONE);
//
//        setStatusImage(wrapper.getIvStatus(),nutzer);

        currentView.setTag(wrapper);

        //Rueckgabe der genierten View
        return currentView;
    }

    private void setStatusImage( ImageView iv, Nutzer pNutzer){
        iv.setImageResource(pNutzer.getStatusImageResourceId());
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
                    }
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



