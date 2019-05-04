package de.eneko.nekomobile.activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.adapter.NutzerListViewAdapter;
import de.eneko.nekomobile.beans.Nutzer;
import de.eneko.nekomobile.controllers.FileHandler;
import de.eneko.nekomobile.listener.NutzerListViewOnItemClickListener;

public class NutzerListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    //ListView Adapter welcher den Inhalt verwaltet
    private NutzerListViewAdapter mAdapter = null;
    private ArrayList<Nutzer> datasource = new ArrayList<>();
    private ListView mListView = null;
    private MenuItem searchMenuItem = null;
    private SearchView searchView = null;
    private Toolbar mToolbar= null;

    //OnItemClickListener er wird aufgerufen wenn ein einzelnes Item geklickt wird
    private NutzerListViewOnItemClickListener mNutzerListViewOnItemClickListener = null;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        // Liegenschaftsadresse als Title
        getSupportActionBar().setTitle(FileHandler.getInstance().getLiegenschaft().getAdresse());

        // Init adapter
        datasource.addAll(FileHandler.getInstance().getLiegenschaft().getNutzers().stream()
                .sorted(Comparator.comparing(Nutzer::getWohnungsnummer))
                .collect(Collectors.toList()));
        mAdapter = new NutzerListViewAdapter(this,datasource);

        // init listview
        mListView = findViewById(R.id.listView);
        mListView.setAdapter(mAdapter);
        mNutzerListViewOnItemClickListener = new NutzerListViewOnItemClickListener();
        mListView.setOnItemClickListener(mNutzerListViewOnItemClickListener);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
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
