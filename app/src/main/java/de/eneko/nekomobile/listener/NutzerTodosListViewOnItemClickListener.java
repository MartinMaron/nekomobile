package de.eneko.nekomobile.listener;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;


import de.eneko.nekomobile.activities.MessgeraetListActivity;
import de.eneko.nekomobile.activities.RauchmelderWartungListActivity;
import de.eneko.nekomobile.beans.ToDo;
import de.eneko.nekomobile.controllers.FileHandler;


public class NutzerTodosListViewOnItemClickListener
        implements AdapterView.OnItemClickListener
{


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int index, long id)
    {

        ToDo todo = (ToDo) parent.getAdapter().getItem(index);
        //Aktuelle Notiz zwischenspeichern
        FileHandler.getInstance().setNutzerTodo(todo);
        Intent intent = null;
       switch (todo.getArt()){
            case "WAR_RWM": case "MON_RWM":
                intent = new Intent(view.getContext(), RauchmelderWartungListActivity.class);
                view.getContext().startActivity(intent);
                break;
            case "ABL_ALL":case "FUN_CHK": case "MON_HKV": case "MON_WMZ": case "MON_WWZ": case "MON_KWZ":
                intent = new Intent(view.getContext(), MessgeraetListActivity.class);
                view.getContext().startActivity(intent);
                break;
            default:
        }



    }
}
