package de.eneko.nekomobile.activities.list;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.adapter.NutzerListViewAdapter;
import de.eneko.nekomobile.activities.detail.Nutzer.NutzerActivity;
import de.eneko.nekomobile.beans.Nutzer;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.controllers.FileHandler;

public class NutzerListActivity extends AppCompatActivity implements
        SearchView.OnQueryTextListener, AdapterView.OnItemLongClickListener,
        AdapterView.OnItemClickListener {

    private NutzerListViewAdapter mAdapter = null;
    private ArrayList<Nutzer> datasource = new ArrayList<>();
    private ListView mListView = null;
    private MenuItem searchMenuItem = null;
    private SearchView searchView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        getSupportActionBar().setTitle(CurrentObjectNavigation.getInstance().getLiegenschaft().getBaseModel().getAdresse());

        datasource.addAll(CurrentObjectNavigation.getInstance().getLiegenschaft().getNutzers().stream()
                .sorted(Comparator.comparing(Nutzer::getWohnungsnummer))
                .collect(Collectors.toList()));
        mAdapter = new NutzerListViewAdapter(this,datasource);
        mListView = findViewById(R.id.listView);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        CurrentObjectNavigation.getInstance().setNutzer(mAdapter.getItem(i));
        Intent intent = null;
        if (isLongClick) {
            intent = new Intent(view.getContext(), NutzerActivity.class);
            isLongClick = false;
        }else
        {
            intent = new Intent(view.getContext(), NutzerTodosListActivity.class);
        }
        view.getContext().startActivity(intent);
    }

    private Boolean isLongClick = false;
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        isLongClick = true;
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
        mListView.setSelection(mAdapter.getPosition(CurrentObjectNavigation.getInstance().getNutzer()));
    }

    protected void exit(){
        Intent intent = new Intent(this, LiegenschaftListActivity.class);
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
