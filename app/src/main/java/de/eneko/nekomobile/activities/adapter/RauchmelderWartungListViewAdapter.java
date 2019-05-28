package de.eneko.nekomobile.activities.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.list.RauchmelderWartungListActivity;
import de.eneko.nekomobile.activities.models.RauchmelderModel;
import de.eneko.nekomobile.activities.viewHolder.Rauchmelder.WartungRowViewHolder;
import de.eneko.nekomobile.beans.Rauchmelder;

public class RauchmelderWartungListViewAdapter extends ArrayAdapter<Rauchmelder>
{
    private final Context context;
    private final ArrayList<Rauchmelder> values;
    private RauchmelderWartungListActivity activity;

    public RauchmelderWartungListViewAdapter(Context context, ArrayList<Rauchmelder> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
        this.activity = (RauchmelderWartungListActivity) context;
    }

    @Override
    public int getCount()
    {
        return values.size();
    }

    @Override
    public Rauchmelder getItem(int index)
    {
        return values.get(index);
    }

    @Override
    public long getItemId(int id)
    {
        return id;
    }

    @Override
    public View getView(int index, View currentView, ViewGroup parent)
    {
        if (currentView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            currentView = inflater.inflate(R.layout.list_item_rwm_wartung, parent, false);
        }

        //Extrahieren der NoteBean zum nutzen der Werte
        Rauchmelder obj = getItem(index);
        WartungRowViewHolder viewHolder = new WartungRowViewHolder(currentView, obj.getBaseModel(), activity) {};
        viewHolder.updateView();
        currentView.setTag(viewHolder);
        return currentView;
    }


}



