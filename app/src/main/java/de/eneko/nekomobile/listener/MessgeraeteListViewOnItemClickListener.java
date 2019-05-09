package de.eneko.nekomobile.listener;


import android.content.DialogInterface;
import android.content.Intent;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.MessgeraetListActivity;
import de.eneko.nekomobile.activities.NutzerTodosListActivity;
import de.eneko.nekomobile.beans.Nutzer;
import de.eneko.nekomobile.controllers.FileHandler;

public class MessgeraeteListViewOnItemClickListener
        implements AdapterView.OnItemClickListener
{
    private MessgeraetListActivity mMessgeraetListActivity;


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int index, long id)
    {


//        //Aktuelle Notiz zwischenspeichern
//        FileHandler.getInstance().setNutzer((Nutzer) parent.getAdapter().getItem(index));
//        //Generieren eines Intents fuer die NutzerListActivity zu wrappen
//        Intent intent = new Intent(view.getContext(), NutzerTodosListActivity.class);
//
//        //Aufrufen der Activity
//        view.getContext().startActivity(intent);

    }

}
