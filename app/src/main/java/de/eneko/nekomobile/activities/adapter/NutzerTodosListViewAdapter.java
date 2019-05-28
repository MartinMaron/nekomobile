package de.eneko.nekomobile.activities.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.models.NutzerTodoModel;
import de.eneko.nekomobile.activities.viewHolder.NutzerToDoRowViewHolder;
import de.eneko.nekomobile.beans.Route;
import de.eneko.nekomobile.beans.ToDo;

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
public class NutzerTodosListViewAdapter extends ArrayAdapter<ToDo>
{
    private final Context context;
    private final ArrayList<ToDo> values;

    public NutzerTodosListViewAdapter(Context context, ArrayList<ToDo> values) {
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
    public ToDo getItem(int index)
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

        //Extrahieren der NoteBean zum nutzen der Werte
        ToDo obj = getItem(index);
        if (currentView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            currentView = inflater.inflate(R.layout.list_item_nutzertodos, parent, false);
        }

        NutzerToDoRowViewHolder viewHolder = new NutzerToDoRowViewHolder(currentView,obj.getBaseModel());
        viewHolder.updateView();
        currentView.setTag(viewHolder);
        return currentView;
    }
}



