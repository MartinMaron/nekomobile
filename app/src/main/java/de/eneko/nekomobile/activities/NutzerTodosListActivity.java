package de.eneko.nekomobile.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.adapter.NutzerTodosListViewAdapter;
import de.eneko.nekomobile.beans.ToDo;
import de.eneko.nekomobile.controllers.FileHandler;
import de.eneko.nekomobile.listener.NutzerListViewOnItemClickListener;
import de.eneko.nekomobile.listener.NutzerTodosListViewOnItemClickListener;

public class NutzerTodosListActivity extends AppCompatActivity {

    //ListView Adapter welcher den Inhalt verwaltet
    private NutzerTodosListViewAdapter mAdapter = null;
    private ArrayList<ToDo> datasource = new ArrayList<ToDo>();
    private ListView mListView = null;
    private Toolbar mToolbar= null;
    private NutzerTodosListViewOnItemClickListener mNutzerTodosListViewOnItemClickListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);


        this.setTitle(FileHandler.getInstance().getNutzer().getDisplay());

        // Init adapter
        datasource.addAll(FileHandler.getInstance().getNutzer().getToDos().stream()
                .sorted(Comparator.comparing(ToDo::getArt))
                .collect(Collectors.toList()));

        mAdapter = new NutzerTodosListViewAdapter(this,datasource);

        mNutzerTodosListViewOnItemClickListener = new NutzerTodosListViewOnItemClickListener();

        mListView = findViewById(R.id.listView);

        // verbinden des adapters mit Listview
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(mNutzerTodosListViewOnItemClickListener);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }
    protected void exit(){
        Intent intent = new Intent(this, NutzerListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exit();
    }
}
