package de.eneko.nekomobile.activities.list;

import android.app.Activity;
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
import android.widget.Toast;

import java.util.ArrayList;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.adapter.MessgeraetListViewAdapter;
import de.eneko.nekomobile.activities.detail.Messgeraete.MessgaeretAblesungActivity;
import de.eneko.nekomobile.activities.detail.Messgeraete.MessgaeretAustauschActivity;
import de.eneko.nekomobile.activities.detail.Messgeraete.MessgaeretNeuActivity;
import de.eneko.nekomobile.activities.models.NutzerTodoModel;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.controllers.Dict;
import de.eneko.nekomobile.controllers.FileHandler;
import de.eneko.nekomobile.controllers.MessgeraeteConroller;


public abstract class MessgeraetListActivity extends AppCompatActivity
        implements View.OnClickListener,
        SearchView.OnQueryTextListener,
        AdapterView.OnItemClickListener
        {
            protected MessgeraetListViewAdapter mCurrentAdapter = null;
            protected ArrayList<Messgeraet> datasource = new ArrayList<>();

            protected ListView mListView = null;
            protected MenuItem searchMenuItem = null;
            protected MenuItem modeMenuItem = null;
            protected MenuItem newMenuItem = null;
            protected SearchView searchView = null;


            @Override
            protected void onCreate(Bundle savedInstanceState)
            {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_list_view);

                NutzerTodoModel nutzerTodoModel = (NutzerTodoModel) CurrentObjectNavigation.getInstance().getNutzerTodo().getBaseModel();
                nutzerTodoModel.load();
                getSupportActionBar().setTitle(nutzerTodoModel.getBean().getNutzer().getBaseModel().getDisplay());

                // init listview
                mListView = findViewById(R.id.listView);
                mListView.setOnItemClickListener(this);
                loadDatasourceCore();
            }

            @Override
            protected void onResume() {
                super.onResume();
                loadDatasourceCore();
            }


// region Data-Management
            protected void loadDatasourceCore(){

            }




            protected void setCurrentAdapter(MessgeraetListViewAdapter currentAdapter) {
                mCurrentAdapter = currentAdapter;
                mListView.setAdapter(mCurrentAdapter);
                mCurrentAdapter.notifyDataSetChanged();
                if(CurrentObjectNavigation.getInstance().getMessgeraet() != null) { mListView.setSelection(mCurrentAdapter.getPosition(CurrentObjectNavigation.getInstance().getMessgeraet()));}
            }

// endregion

// region Exit

            protected void exit(){
                FileHandler.getInstance().saveFile();
                Intent intent = new Intent(this, NutzerTodosListActivity.class);
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
                inflater.inflate(R.menu.ablesung_menu, menu);

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
                modeMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        showListChoiceDialog();
                        return true;
                    }
                });

                newMenuItem = menu.findItem(R.id.mi_add_new);
                newMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        AddNeuMessgaeret();
                        return true;
                    }
                });

                return true;
            }




            protected abstract void OnDialogChoiceListModeSubmit(String selItem);
            protected abstract void showListChoiceDialog();
            protected void AddNeuMessgaeret(){};



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

// region UI (on Click)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Messgeraet item = (Messgeraet) mCurrentAdapter.getItem(i);
                CurrentObjectNavigation.getInstance().setMessgeraet(item);
                Intent intent = null;
                switch (item.getTodo().getArt()) {

                    case Dict.TODO_ABLESUNG:
                        intent = new Intent(view.getContext(), MessgaeretAblesungActivity.class);
                        break;
                    default:
                        if (item.isNew()) {
                            intent = new Intent(view.getContext(), MessgaeretNeuActivity.class);
                        }else{
                            intent = new Intent(view.getContext(), MessgaeretAustauschActivity.class);
                        }
                }
                view.getContext().startActivity(intent);
            }

            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btShowEximFunk:
                            MessgeraeteConroller.getInstance().setGereteart(MessgeraeteConroller.GeraeteArt.EXIM);
                        break;
                    case R.id.btShowSontexFunk:
                        MessgeraeteConroller.getInstance().setGereteart(MessgeraeteConroller.GeraeteArt.SONTEX);
                        break;
                    case R.id.btShowStandard:
                        MessgeraeteConroller.getInstance().setGereteart(MessgeraeteConroller.GeraeteArt.MANUELL);
                        break;
                }
                onResume();
            }

            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);

                if (resultCode != Activity.RESULT_OK) {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                    return;
                }
                mCurrentAdapter.notifyDataSetChanged();

            }

// endregion

// region properties
    public MessgeraetListViewAdapter getCurrentAdapter() {
                return mCurrentAdapter;
            }

    public ArrayList<Messgeraet> getDatasource() {
        return datasource;
    }

    public ListView getListView() {
        return mListView;
    }
// endregion


}
