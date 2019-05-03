package de.eneko.nekomobile.activities.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.wrapper.FileListViewItemWrapper;
import de.eneko.nekomobile.beans.Route;
import de.eneko.nekomobile.controllers.FileHandler;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
public class FileListViewAdapter extends ArrayAdapter<Route>
{
    private final Context context;
    private final ArrayList<Route> values;

    public FileListViewAdapter(Context context, ArrayList<Route> values) {
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
    public Route getItem(int index)
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
        Route route = getItem(index);

        if (rowView == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.file_list_item, parent, false);
        }

        FileListViewItemWrapper lvItemWidgetWrapper = new FileListViewItemWrapper();

        lvItemWidgetWrapper.setTxtvRouteName(rowView.findViewById(R.id.txtvRouteName));
        lvItemWidgetWrapper.setTxtvDatum(rowView.findViewById(R.id.txtvDatum));
        lvItemWidgetWrapper.setTxtvErstellDatum(rowView.findViewById(R.id.txtvErstelldatum));

        //Befuellen der einzelen Widgets
        lvItemWidgetWrapper.getTxtvRouteName().setText(route.getBezeichnung());
        lvItemWidgetWrapper.getTxtvDatum().setText(new SimpleDateFormat(GlobalConst.dateFormat).format(route.getDatum()));
        lvItemWidgetWrapper.getTxtvErstellDatum().setText(route.getCreateTimestamp());

        rowView.setTag(lvItemWidgetWrapper);

        //Rueckgabe der genierten View
        return rowView;
    }
}



