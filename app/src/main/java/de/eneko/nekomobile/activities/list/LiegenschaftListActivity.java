package de.eneko.nekomobile.activities.list;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import de.eneko.nekomobile.activities.adapter.LiegenschaftListViewAdapter;
import de.eneko.nekomobile.beans.Liegenschaft;
import de.eneko.nekomobile.controllers.FileHandler;

public class LiegenschaftListActivity extends ListActivity implements AdapterView.OnItemClickListener{

    //ListView Adapter welcher den Inhalt verwaltet
    private LiegenschaftListViewAdapter mAdapter = null;
    private ArrayList<Liegenschaft> datasource = new ArrayList<Liegenschaft>();


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //Aktuelle Notiz zwischenspeichern
        FileHandler.getInstance().setLiegenschaft((Liegenschaft) mAdapter.getItem(i));

        //Generieren eines Intents fuer die NutzerListActivity zu wrappen
        Intent intent = new Intent(view.getContext(), NutzerListActivity.class);

        //Aufrufen der Activity
        view.getContext().startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        datasource.addAll(FileHandler.getInstance().getRoute().getLiegenschaften().stream()
                .sorted(Comparator.comparing(Liegenschaft::getSortNo))
                .collect(Collectors.toList()));

        mAdapter = new LiegenschaftListViewAdapter(this,datasource);
        getListView().setOnItemClickListener(this);
        setListAdapter(mAdapter);

    }
    protected void exit(){
        Intent intent = new Intent(this, FileListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exit();
    }
}