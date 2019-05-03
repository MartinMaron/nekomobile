package de.eneko.nekomobile.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;
import java.util.Calendar;

import de.eneko.nekomobile.activities.adapter.FileListViewAdapter;
import de.eneko.nekomobile.beans.Route;
import de.eneko.nekomobile.controllers.FileHandler;
import de.eneko.nekomobile.listener.FileListViewOnItemClickListener;

public class FileListActivity extends ListActivity {

    //ListView Adapter welcher den Inhalt verwaltet
    private FileListViewAdapter mfileListViewAdapter = null;

    //OnItemClickListener er wird aufgerufen wenn ein einzelnes Item geklickt wird
    private FileListViewOnItemClickListener mFileListViewOnItemClickListener = null;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Dateien wurden geladen
        FileHandler.getInstance().preLoadAllRoutes();
        // Init adapter
        try {
            ArrayList<Route> datasoure = new ArrayList<Route>();
            datasoure.addAll(
                    FileHandler.getInstance().getAllRoutes().stream()
                            .filter(r -> r.getDatum().compareTo(addDays(new Date(), -50)) > 0)
                            .sorted(Comparator.comparing(Route::getDatum))
                            .collect(Collectors.toList()));

            mfileListViewAdapter = new FileListViewAdapter(this, datasoure);
            mFileListViewOnItemClickListener = new FileListViewOnItemClickListener();
            getListView().setOnItemClickListener(mFileListViewOnItemClickListener);
            setListAdapter(mfileListViewAdapter);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        System.out.print("FileListActivity:onListItemClick: index =" + position);
    }



    private static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}
