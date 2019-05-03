package de.eneko.nekomobile.listener;

import android.view.View;
import android.widget.AdapterView;


/**
 * Dieser Listener reagiert auf die Klicks
 * in der ListViewActivity. Wenn ein ListViewItem
 * geklickt wird wertet diese Klasse die Informationen
 * aus und koordieniert die weiterfuehrenden Aktionen.
 *
 * Sobald ein Item geklickt wird rufen wir eine
 * neue Activity auf die es uns erlaubt die
 * Notiz zu bearbeiten, zu loeschen, oder auf
 * die SD-Card zu exportieren.
 *
 * Created by rKasper on 04.06.2015.
 */
public class FileListViewOnItemClickListener
        implements AdapterView.OnItemClickListener
{
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int index, long id)
    {
        return;
//        //Aktuelle Notiz zwischenspeichern
//        FileHandler.getInstance().setRoute(index);
//
//        //Generieren eines Intents fuer die ListViewItemDetialsActivity zu wrappen
//        Intent intentLiegenschaftListActivity = new Intent(view.getContext(), LiegenschaftListActivity.class);
//
//        //Aufrufen der Activity
//        view.getContext().startActivity(intentLiegenschaftListActivity);

    }
}