package de.eneko.nekomobile.activities;


import android.app.ListActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import de.eneko.nekomobile.activities.adapter.LiegenschaftListViewAdapter;
import de.eneko.nekomobile.beans.Liegenschaft;
import de.eneko.nekomobile.controllers.FileHandler;
import de.eneko.nekomobile.listener.FileListViewOnItemClickListener;
import de.eneko.nekomobile.listener.LiegenschaftListViewOnItemClickListener;

public class LiegenschaftListActivity extends ListActivity {

    //ListView Adapter welcher den Inhalt verwaltet
    private LiegenschaftListViewAdapter mLiegenschaftListViewAdapter = null;
    private ArrayList<Liegenschaft> datasource = new ArrayList<Liegenschaft>();

    //OnItemClickListener er wird aufgerufen wenn ein einzelnes Item geklickt wird
    private LiegenschaftListViewOnItemClickListener mLiegenschaftListViewOnItemClickListener = null;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Init adapter
        datasource.addAll(FileHandler.getInstance().getRoute().getLiegenschaften().stream()
                .sorted(Comparator.comparing(Liegenschaft::getSortNo))
                .collect(Collectors.toList()));

        mLiegenschaftListViewAdapter = new LiegenschaftListViewAdapter(this,datasource);
        mLiegenschaftListViewOnItemClickListener = new LiegenschaftListViewOnItemClickListener();
        getListView().setOnItemClickListener(mLiegenschaftListViewOnItemClickListener);

        // verbinden des adapters mit Listview
        setListAdapter(mLiegenschaftListViewAdapter);

    }
}