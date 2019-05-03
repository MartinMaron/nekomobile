package de.eneko.nekomobile.activities.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.wrapper.NutzerTodosListViewItemWrapper;
import de.eneko.nekomobile.beans.Route;
import de.eneko.nekomobile.beans.ToDo;
import de.eneko.nekomobile.controllers.FileHandler;

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
        ToDo todo = getItem(index);
        if (currentView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            currentView = inflater.inflate(R.layout.nutzertodos_list_item, parent, false);
        }

        /*
         * Wir nutzen den LvItemWidgetsWrapper um
         * alle Widgets zu generieren die ein
         * einzelnes ListViewItem beinhalten soll.
         * Die Layoutinformationen erhalten wir
         * ueber die Funktion findViewById die jede
         * View beinhaltet. Die Layoutinformation
         * erhalten wir wie immer ueber die R-Datei
         */
        NutzerTodosListViewItemWrapper itemWrapper = new NutzerTodosListViewItemWrapper();

        //ERzeugen der einzelen Widgets

        itemWrapper.setTxtvDescription(currentView.findViewById(R.id.txtvDescription));
        itemWrapper.setIvImage(currentView.findViewById(R.id.ivImage));
        itemWrapper.setRwmSubView(currentView.findViewById(R.id.rwm_wartung_content));
        itemWrapper.getRwmWartungWrapper().setTxtvUndone(currentView.findViewById(R.id.txtvUndone));
        itemWrapper.getRwmWartungWrapper().setTxtvDone(currentView.findViewById(R.id.txtvDone));
        itemWrapper.getRwmWartungWrapper().setTxtvWithError(currentView.findViewById(R.id.txtvWithError));
        itemWrapper.getRwmWartungWrapper().setTxtvNew(currentView.findViewById(R.id.txtvNew));


        itemWrapper.getRwmSubView().setVisibility(View.INVISIBLE);
        //Befuellen der einzelen Widgets
        itemWrapper.getTxtvDescription().setText(todo.getBezeichnung());
        switch (todo.getArt())
        {
            case "WAR_RWM":
                // TODO: Tu trzeba tezznalezc odpowiedni objeck NutzerTodo moze sie nie zgadzac czy wejsciu
                itemWrapper.getIvImage().setImageResource(todo.getRwmStatusImageResourceId());
                itemWrapper.getRwmWartungWrapper().getTxtvUndone().setText("nicht geprüft: " + todo.getRwmWartungUndoneCount());
                itemWrapper.getRwmWartungWrapper().getTxtvDone().setText("geprüft: " + todo.getRwmWartungDoneCount());
                itemWrapper.getRwmWartungWrapper().getTxtvWithError().setText("mit fehler: " + todo.getRwmWartungWithErrorCount());
                itemWrapper.getRwmWartungWrapper().getTxtvNew().setText("neu installiert: " + todo.getRwmWartungNewCount());
                itemWrapper.getRwmSubView().setVisibility(View.VISIBLE);

                break;
            case "MON_HKV": case "MON_WMZ": case "MON_WWZ": case "MON_KWZ":
            itemWrapper.getIvImage().setImageResource(R.drawable.icon_montage);
            break;
            case "ABL_ALL":
                itemWrapper.getIvImage().setImageResource(R.drawable.icon_ablesung);
                break;
            case "MON_RWM":
                itemWrapper.getIvImage().setImageResource(R.drawable.icon_rwm_montage);
                break;
            case "FUN_CHK":
                itemWrapper.getIvImage().setImageResource(R.drawable.icon_funk_check);
                break;
            default:
        }
        /* Hinzufuegen des ListViewItemWidgetWrappers
         * mit alle seinen Widgets zum ListViewItem
         */
        currentView.setTag(itemWrapper);

        //Rueckgabe der genierten View
        return currentView;
    }
}



