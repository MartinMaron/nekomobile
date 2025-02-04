package de.eneko.nekomobile.activities.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.stream.Collectors;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.detail.Messgeraete.MessgaeretBewertungActivity;
import de.eneko.nekomobile.activities.models.viewHolder.Messgearete.BewertungRowViewHolder;
import de.eneko.nekomobile.beans.Ibewertung;


public class BewertungListViewAdapter extends ArrayAdapter<Ibewertung>
{
    private final ArrayList<Ibewertung> values;
    private ArrayList<Ibewertung> valuesFiltered;
    private MessgaeretBewertungActivity activity;

    public BewertungListViewAdapter(MessgaeretBewertungActivity context, ArrayList<Ibewertung> pValues) {
        super(context, -1, pValues);
        this.activity = context;
        valuesFiltered = new ArrayList<>();
        for(Ibewertung bewertung: pValues)
        {
            if (valuesFiltered.stream()
                    .filter(r -> r.isBewertungEquals(bewertung))
                    .collect(Collectors.toList()).size() == 0)
            {
                this.valuesFiltered.add(bewertung);
            }
        }
        this.values = valuesFiltered;
    }






    @Override
    public int getCount()
    {
        return valuesFiltered.size();
    }

    @Override
    public Ibewertung getItem(int index)
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
        Ibewertung obj = getItem(index);
        if(currentView == null) {
            LayoutInflater inflater = (LayoutInflater) this.activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            currentView = inflater.inflate(R.layout.list_item_bewertung, parent, false);

        }

        BewertungRowViewHolder viewHolder = new BewertungRowViewHolder(currentView, obj.getBaseModel(), activity);
        viewHolder.updateView();

        currentView.setTag(viewHolder);
        return currentView;
    }

}



