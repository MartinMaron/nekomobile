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

import de.eneko.nekomobile.InputDialogChoiceListModeClass;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.adapter.NutzerListViewAdapter;
import de.eneko.nekomobile.activities.detail.Nutzer.NutzerActivity;
import de.eneko.nekomobile.beans.Nutzer;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.controllers.FileHandler;

public class NutzerListActivity extends AppCompatActivity implements
        SearchView.OnQueryTextListener, AdapterView.OnItemLongClickListener,
        AdapterView.OnItemClickListener {

    protected NutzerListViewAdapter mCurrentAdapter = null;
    protected ArrayList<Nutzer> datasource = new ArrayList<>();
    protected ListView mListView = null;
    protected MenuItem searchMenuItem = null;
    protected SearchView searchView = null;
    protected MenuItem modeMenuItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        getSupportActionBar().setTitle(CurrentObjectNavigation.getInstance().getLiegenschaft().getBaseModel().getAdresse());
        if(!CurrentObjectNavigation.getInstance().getLiegenschaft().getBaseModel().getBemerkung().equals(""));
        {
            getSupportActionBar().setSubtitle(CurrentObjectNavigation.getInstance().getLiegenschaft().getBaseModel().getBemerkung());
        }
        mListView = findViewById(R.id.listView);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDatasourceCore();
    }

    // region Data - Management

    protected void loadDatasourceCore(){
        getDatasource().clear();
        getDatasource().addAll(CurrentObjectNavigation.getInstance().getLiegenschaft().getNutzers().stream()
                .filter( r -> r.isWork())
                .sorted(Comparator.comparing(Nutzer::getWohnungsnummer))
                .collect(Collectors.toList()));
        setAdapterCurrent(new NutzerListViewAdapter(this,getDatasource()));
    }
    protected void loadInfoDatasourceCore(){
        getDatasource().clear();
        getDatasource().addAll(CurrentObjectNavigation.getInstance().getLiegenschaft().getNutzers().stream()
                .sorted(Comparator.comparing(Nutzer::getWohnungsnummer))
                .collect(Collectors.toList()));
        setAdapterCurrent(new NutzerListViewAdapter(this,getDatasource()));
    }


    public void setAdapterCurrent(NutzerListViewAdapter adapter) {
        mCurrentAdapter = adapter;
        getListView().setAdapter(mCurrentAdapter);
        mCurrentAdapter.notifyDataSetChanged();
        if(CurrentObjectNavigation.getInstance().getNutzer() != null) { getListView().setSelection(mCurrentAdapter.getPosition(CurrentObjectNavigation.getInstance().getNutzer()));}
    }

// endregion

    // region UI (On Click)
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        CurrentObjectNavigation.getInstance().setNutzer(getCurrentAdapter().getItem(i));
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
// endregion

    // region Exit


    protected void exit(){
        FileHandler.getInstance().saveFile(this);
        Intent intent = new Intent(this, LiegenschaftListActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exit();
    }
// endregion

    // region Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nutzer_menu, menu);

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

        modeMenuItem = menu.findItem(R.id.showMode);
        modeMenuItem.setIcon(getDrawable(R.drawable.ic_list));
        modeMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                showListChoiceDialog();
                return true;
            }
        });


        return true;
    }

    public void showListChoiceDialog(){
        new InputDialogChoiceListModeClass(this, "LA"){
            @Override
            protected void OnDialogSubmit(String selItem) {
                OnDialogChoiceListModeSubmit(selItem);
            }
        }.show();
    }

    protected void OnDialogChoiceListModeSubmit(String selItem){

        switch (selItem) {
            case "L":
                loadDatasourceCore();
                modeMenuItem.setIcon(getDrawable(R.drawable.ic_list));
                break;
            case "W":
                break;
            case "T":
                break;
            case "A":
                loadInfoDatasourceCore();
                modeMenuItem.setIcon(getDrawable(R.drawable.ic_list_info));
                break;
            default:
                break;

        }
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        getCurrentAdapter().getFilter().filter(newText);
        return false;
    }

// endregion

    // region properties

    public ArrayList<Nutzer> getDatasource() {
        return datasource;
    }

    public void setDatasource(ArrayList<Nutzer> datasource) {
        this.datasource = datasource;
    }

    public ListView getListView() {
        return mListView;
    }

    public NutzerListViewAdapter getCurrentAdapter() {
        return mCurrentAdapter;
    }

    // endregion
}
