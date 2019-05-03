package de.eneko.nekomobile.listener;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import de.eneko.nekomobile.activities.RauchmelderWartungListActivity;
import de.eneko.nekomobile.beans.ToDo;
import de.eneko.nekomobile.controllers.FileHandler;


public class NutzerTodosListViewOnItemClickListener
        implements AdapterView.OnItemClickListener
{


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int index, long id)
    {


        //Aktuelle Notiz zwischenspeichern
        FileHandler.getInstance().setNutzerTodo((ToDo) parent.getAdapter().getItem(index));
        //Generieren eines Intents fuer die NutzerListActivity zu wrappen
        Intent intent = new Intent(view.getContext(), RauchmelderWartungListActivity.class);

        //Aufrufen der Activity
        view.getContext().startActivity(intent);

    }
}
