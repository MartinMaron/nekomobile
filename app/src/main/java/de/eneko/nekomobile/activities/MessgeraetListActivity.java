package de.eneko.nekomobile.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.adapter.MessgeraetListViewAdapter;
import de.eneko.nekomobile.activities.adapter.NutzerListViewAdapter;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.beans.Nutzer;
import de.eneko.nekomobile.controllers.FileHandler;
import de.eneko.nekomobile.listener.MessgeraeteListViewOnItemClickListener;
import de.eneko.nekomobile.listener.NutzerListViewOnItemClickListener;

public class MessgeraetListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    //ListView Adapter welcher den Inhalt verwaltet
    private MessgeraetListViewAdapter mAdapter = null;
    private ArrayList<Messgeraet> datasource = new ArrayList<>();
    private ListView mListView = null;
    private MenuItem searchMenuItem = null;
    private SearchView searchView = null;
    private Toolbar mToolbar= null;

    //OnItemClickListener er wird aufgerufen wenn ein einzelnes Item geklickt wird
    private MessgeraeteListViewOnItemClickListener mOnItemClickListener = null;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        // Liegenschaftsadresse als Title
        getSupportActionBar().setTitle(FileHandler.getInstance().getNutzerTodo().getNutzer().getDisplay());

        // Init adapter
        datasource.addAll(FileHandler.getInstance().getNutzerTodo().getMessgeraete().stream()
                .sorted(Comparator.comparing(Messgeraet::getSortNo))
                .collect(Collectors.toList()));
        mAdapter = new MessgeraetListViewAdapter (this,datasource);

        // init listview
        mListView = findViewById(R.id.listView);
        mListView.setAdapter(mAdapter);
        mOnItemClickListener = new MessgeraeteListViewOnItemClickListener();
        mListView.setOnItemClickListener(mOnItemClickListener);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
        mListView.setSelection(mAdapter.getPosition(FileHandler.getInstance().getMessgeraet()));
    }

    protected void exit(){
        Intent intent = new Intent(this, NutzerTodosListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
        searchMenuItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchMenuItem.getActionView();
        searchView.onActionViewCollapsed();


        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);

        searchView.onActionViewCollapsed();

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mAdapter.getFilter().filter(newText);
        return false;
    }



}
