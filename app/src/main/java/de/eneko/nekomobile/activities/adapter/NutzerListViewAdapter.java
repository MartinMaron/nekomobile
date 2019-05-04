package de.eneko.nekomobile.activities.adapter;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.NutzerListActivity;
import de.eneko.nekomobile.activities.wrapper.NutzerListViewItemWrapper;
import de.eneko.nekomobile.beans.Nutzer;
import de.eneko.nekomobile.beans.Route;

import android.widget.Filter;

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

    private NutzerListActivity activity;
    private customFilter mFilter;
    private Typeface typeface;



    public NutzerListViewAdapter(Context context, ArrayList<Nutzer> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
        this.valuesFiltered = values;
        //typeface = Typeface.createFromAsset(context.getAssets(), "fonts/vegur_2.otf");

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

        //Extrahieren der NoteBean zum nutzen der Werte
        Nutzer nutzer = getItem(index);
        NutzerListViewItemWrapper wrapper = new NutzerListViewItemWrapper();

        if(currentView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            currentView = inflater.inflate(R.layout.nuzter_list_item, parent, false);

        }

        wrapper.setIvStatus(currentView.findViewById(R.id.ivStatus));
        wrapper.getIvStatus().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!nutzer.isCompleted()){
                    nutzer.setAbwesend(!nutzer.getAbwesend());
                }
                wrapper.getIvStatus().setImageResource(nutzer.getStatusImageResourceId());
            }
        });



        wrapper.setTxtvDescription(currentView.findViewById(R.id.txtvDescription));
        wrapper.setTxtvNutzerLage(currentView.findViewById(R.id.txtvNutzerLage));

        wrapper.getTodoRow().setIvAblesung(currentView.findViewById(R.id.ivAblesung));
        wrapper.getTodoRow().setIvFunkCheck(currentView.findViewById(R.id.ivFunkCheck));
        wrapper.getTodoRow().setIvMontage(currentView.findViewById(R.id.ivMontage));
        wrapper.getTodoRow().setIvRwmMontage(currentView.findViewById(R.id.ivRwmMontage));
        wrapper.getTodoRow().setIvRwmWartung(currentView.findViewById(R.id.ivRwmWartung));




        //Befuellen der einzelen Widgets
        wrapper.getTxtvDescription().setText(nutzer.getNutzerName());
        wrapper.getTxtvNutzerLage().setText(nutzer.getWohnungsnummerMitLage());


        // sichtbarkei der Icons
        wrapper.getTodoRow().getIvAblesung().setVisibility(nutzer.hasAblesung() ? View.VISIBLE: View.GONE);
        wrapper.getTodoRow().getIvMontage().setVisibility(nutzer.hasMontage() ? View.VISIBLE: View.GONE);
        wrapper.getTodoRow().getIvRwmMontage().setVisibility(nutzer.hasRwmMontage() ? View.VISIBLE: View.GONE);
        wrapper.getTodoRow().getIvRwmWartung().setVisibility(nutzer.hasRwmWartung() ? View.VISIBLE: View.GONE);
        wrapper.getTodoRow().getIvRwmWartung().setImageResource(nutzer.getRwmStatusImageResourceId());
        wrapper.getTodoRow().getIvFunkCheck().setVisibility(nutzer.hasFunkcheck() ? View.VISIBLE: View.GONE);

        setStatusImage(wrapper.getIvStatus(),nutzer);

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
                ArrayList<Nutzer> tempList = new ArrayList<Nutzer>();
                // search content in friend list
                for (Nutzer nutzer : values) {
                    if (nutzer.getDisplay().toLowerCase().contains(constraint.toString().toLowerCase())) {
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



