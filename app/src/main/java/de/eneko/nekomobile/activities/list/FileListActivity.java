package de.eneko.nekomobile.activities.list;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.Date;
import java.util.Calendar;

import de.eneko.nekomobile.MainActivity;
import de.eneko.nekomobile.activities.adapter.FileListViewAdapter;
import de.eneko.nekomobile.beans.Liegenschaft;
import de.eneko.nekomobile.beans.Route;
import de.eneko.nekomobile.controllers.FileHandler;

public class FileListActivity extends ListActivity implements AdapterView.OnItemClickListener {

    private FileListViewAdapter mAdapter = null;

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        FileHandler.getInstance().setRoute(mAdapter.getItem(i));
        Intent intent = new Intent(view.getContext(), LiegenschaftListActivity.class);
        view.getContext().startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FileHandler.getInstance().preLoadAllRoutes();
        try {
            ArrayList<Route> datasoure = new ArrayList<Route>();
            datasoure.addAll(
                    FileHandler.getInstance().getAllRoutes().stream()
                            .filter(r -> r.getDatum().compareTo(addDays(new Date(), -50)) > 0)
                            .sorted(Comparator.comparing(Route::getDatum))
                            .collect(Collectors.toList()));

            mAdapter = new FileListViewAdapter(this, datasoure);
            getListView().setOnItemClickListener(this);
            setListAdapter(mAdapter);
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

    protected void exit(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exit();
    }
}
