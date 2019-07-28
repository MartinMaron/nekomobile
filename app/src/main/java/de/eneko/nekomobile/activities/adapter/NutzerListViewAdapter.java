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
import de.eneko.nekomobile.activities.viewHolder.Nutzer.NutzerListRowViewHolder;
import de.eneko.nekomobile.beans.Nutzer;
import de.eneko.nekomobile.beans.Route;

/**
 * Der  ListViewActivityAdapter greift auf
 * die ArrayListe von {@link Route}s<br>
 * zu und nutzt diese zum Aufbau des Layouts der ListActivity.
 * <p/>
 * Deswegen passen wir alle Funktionen und Methoden
 * die von diese Klasse von dem Baseadapter geerbt werden
 * an, in dem wir die ArrayList von NoteBeans aus dem FileHandler
 * nutzen. So werden immer alle Notizen Angezeigt
 * <p/>
 * Created by rKasper on 31.05.2015.
 */
public class NutzerListViewAdapter extends ArrayAdapter<Nutzer> implements Filterable
{
    private final Context context;
    private final ArrayList<Nutzer> values;
    private ArrayList<Nutzer> valuesFiltered;

    private customFilter mFilter;



    public NutzerListViewAdapter(Context context, ArrayList<Nutzer> values) {
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
    public Nutzer getItem(int index)
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
        Nutzer obj = getItem(index);
        if(currentView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            currentView = inflater.inflate(R.layout.list_item_nuzter, parent, false);

        }
        NutzerListRowViewHolder viewHolder = new NutzerListRowViewHolder(currentView, obj.getBaseModel());
        viewHolder.updateView();
        currentView.setTag(viewHolder);
        return currentView;
    }



    // region filter


    private class customFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint!=null && constraint.length()>0) {
                ArrayList<Nutzer> tempList = new ArrayList<Nutzer>();
                // search content in friend list
                for (Nutzer nutzer : values) {
                    if (nutzer.getBaseModel().getDisplay().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(nutzer);
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
            valuesFiltered = (ArrayList<Nutzer>) results.values;
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



