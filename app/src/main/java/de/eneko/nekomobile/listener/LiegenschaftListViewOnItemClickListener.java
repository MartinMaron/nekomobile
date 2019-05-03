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
public class LiegenschaftListViewOnItemClickListener
        implements AdapterView.OnItemClickListener
{


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int index, long id)
    {
//        //Aktuelle Notiz zwischenspeichern
//        FileHandler.getInstance().setLiegenschaft((Liegenschaft) parent.getAdapter().getItem(index));
//
//        //Generieren eines Intents fuer die NutzerListActivity zu wrappen
//        Intent intent = new Intent(view.getContext(), NutzerListActivity.class);
//
//        //Aufrufen der Activity
//        view.getContext().startActivity(intent);

    }
}
