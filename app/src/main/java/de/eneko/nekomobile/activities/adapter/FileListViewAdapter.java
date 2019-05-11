package de.eneko.nekomobile.activities.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.viewHolder.RouteViewHolder;

import android.view.LayoutInflater;

import java.util.ArrayList;

/**
 * Der  ListViewActivityAdapter greift auf
 * die ArrayListe von {@link de.eneko.nekomobile.beans.Route}s<br>
 * zu und nutzt diese zum Aufbau des Layouts der ListActivity.
 * <p/>
 * Deswegen passen wir alle Funktionen und Methoden
 * die von diese Klasse von dem Baseadapter geerbt werden
 * an, in dem wir die ArrayList von NoteBeans aus dem FileHandler
 * nutzen. So werden immer alle Notizen Angezeigt
 * <p/>
 * Created by rKasper on 31.05.2015.
 */
public class FileListViewAdapter extends ArrayAdapter<de.eneko.nekomobile.beans.Route>
{
    private final Context context;
    private final ArrayList<de.eneko.nekomobile.beans.Route> values;

    public FileListViewAdapter(Context context, ArrayList<de.eneko.nekomobile.beans.Route> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount()
    {
        return values.size();
    }

    @Override
    public de.eneko.nekomobile.beans.Route getItem(int index)
    {
        return values.get(index);
    }

    @Override
    public long getItemId(int id)
    {
        return id;
    }

    @Override
    public View getView(int index, View rowView, ViewGroup parent) {
        de.eneko.nekomobile.beans.Route route = getItem(index);

        if (rowView == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item_file, parent, false);
        }

        RouteViewHolder viewHolder = new RouteViewHolder(rowView,route);
        viewHolder.updateView();
        rowView.setTag(viewHolder);

        //Rueckgabe der genierten View
        return rowView;
    }
}



