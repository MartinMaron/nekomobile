package de.eneko.nekomobile.activities.list;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.adapter.MessgeraetListViewAdapter;
import de.eneko.nekomobile.activities.detail.Messgeraete.MessgaeretAblesungActivity;
import de.eneko.nekomobile.activities.detail.Messgeraete.MessgaeretAustauschActivity;
import de.eneko.nekomobile.activities.models.NutzerTodoModel;
import de.eneko.nekomobile.activities.viewHolder.Messgearete.DetailViewHolder;
import de.eneko.nekomobile.activities.viewHolder.Messgearete.MessgeraetBaseViewHolder;
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

    protected MessgeraetListViewAdapter mAdapterCurrent = null;
    protected MessgeraetListViewAdapter mAdapter_man = null;
    protected MessgeraetListViewAdapter mAdapter_exm = null;
    protected MessgeraetListViewAdapter mAdapter_son = null;
    protected ArrayList<Messgeraet> datasource = new ArrayList<>();
    protected ArrayList<Messgeraet> datasource_man = new ArrayList<>();
    protected ArrayList<Messgeraet> datasource_exm = new ArrayList<>();
    protected ArrayList<Messgeraet> datasource_son = new ArrayList<>();

    protected ImageView ivManuell = null;
    protected ImageView ivSontex = null;
    protected ImageView ivExim = null;


    protected ListView mListView = null;
    protected MenuItem searchMenuItem = null;
    protected MenuItem eingabeMenuItem = null;
    protected SearchView searchView = null;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_ablesung);
        NutzerTodoModel nutzerTodoModel = CurrentObjectNavigation.getInstance().getNutzerTodo().getBaseModel();
        nutzerTodoModel.load();
        getSupportActionBar().setTitle(nutzerTodoModel.getBean().getNutzer().getBaseModel().getDisplay());

        // Init datasource
        datasource.addAll(CurrentObjectNavigation.getInstance().getNutzerTodo().getMessgeraete().stream()
                .sorted(Comparator.comparing(Messgeraet::getSortNo))
                .collect(Collectors.toList()));

        datasource_man.addAll(datasource.stream()
                .filter(r -> !r.getEximFunk() && ! r.getFunkSontex())
                .sorted(Comparator.comparing(Messgeraet::getSortNo))
                .collect(Collectors.toList()));
        datasource_exm.addAll(datasource.stream()
                .filter(r -> r.getEximFunk())
                .sorted(Comparator.comparing(Messgeraet::getSortNo))
                .collect(Collectors.toList()));
        datasource_son .addAll(datasource.stream()
                .filter(r -> r.getFunkSontex())
                .sorted(Comparator.comparing(Messgeraet::getSortNo))
                .collect(Collectors.toList()));


        mAdapter_man = new MessgeraetListViewAdapter (this,datasource_man);
        mAdapter_son = new MessgeraetListViewAdapter (this,datasource_son);
        mAdapter_exm = new MessgeraetListViewAdapter (this,datasource_exm);

        // init listview
        mListView = findViewById(R.id.listView);
        mListView.setAdapter(mAdapter_man);
        mListView.setOnItemClickListener(this);
        ivManuell = findViewById(R.id.btShowStandard);
        ivManuell.setOnClickListener(this);
        ivSontex = findViewById(R.id.btShowSontexFunk);
        ivSontex.setOnClickListener(this);
        ivExim = findViewById(R.id.btShowEximFunk);
        ivExim.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Messgeraet item = (Messgeraet) mAdapterCurrent.getItem(i);
        CurrentObjectNavigation.getInstance().setMessgeraet(item);
        Intent intent = null;
        switch (item.getTodo().getArt()) {
            case Dict.TODO_ABLESUNG:
                intent = new Intent(view.getContext(), MessgaeretAblesungActivity.class);
                break;
            default:
                intent = new Intent(view.getContext(), MessgaeretAustauschActivity.class);
        }
        view.getContext().startActivity(intent);
    }

            @Override
    protected void onResume() {
        super.onResume();
        setEingabeMenuItemIcon();
   }

    private void setEingabeMenuItemIcon(){
            if (MessgeraeteConroller.getInstance().getEingabeArt() == MessgeraeteConroller.EingabeArt.SPRACHE){
                if (eingabeMenuItem != null) eingabeMenuItem.setIcon(getDrawable(R.drawable.icon_speaker));
            }
            if (MessgeraeteConroller.getInstance().getEingabeArt() == MessgeraeteConroller.EingabeArt.TASTATUR){
                if (eingabeMenuItem != null)  eingabeMenuItem.setIcon(getDrawable(R.drawable.icon_speaker_off));
            }
        }


    public MessgeraetListViewAdapter getAdapterCurrent() {
        return mAdapterCurrent;
    }

    public void setAdapterCurrent(MessgeraetListViewAdapter adapterCurrent) {
        mAdapterCurrent = adapterCurrent;
        mListView.setAdapter(mAdapterCurrent);
        mAdapterCurrent.notifyDataSetChanged();
        mListView.setSelection(mAdapter_man.getPosition(CurrentObjectNavigation.getInstance().getMessgeraet()));
    }

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

        eingabeMenuItem = menu.findItem(R.id.eingabeMode);
        eingabeMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (MessgeraeteConroller.getInstance().getEingabeArt() == MessgeraeteConroller.EingabeArt.TASTATUR){
                    MessgeraeteConroller.getInstance().setEingabeArt( MessgeraeteConroller.EingabeArt.SPRACHE);
                }else if (MessgeraeteConroller.getInstance().getEingabeArt() == MessgeraeteConroller.EingabeArt.SPRACHE){
                    MessgeraeteConroller.getInstance().setEingabeArt( MessgeraeteConroller.EingabeArt.TASTATUR);
                }
                setEingabeMenuItemIcon();
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mAdapterCurrent.getFilter().filter(newText);
        return false;
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
        if (requestCode == MessgeraetBaseViewHolder.REQUEST_BT_AKTUELL && data != null ) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            new DetailViewHolder(null,CurrentObjectNavigation.getInstance().getMessgeraet().getBaseModel(),this){}.inputDialogAktuell(result.get(0).replace(" ",""));
        }
        if (requestCode == MessgeraetBaseViewHolder.REQUEST_BT_STICHTAG && data != null ) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            new DetailViewHolder(null,CurrentObjectNavigation.getInstance().getMessgeraet().getBaseModel(),this){}.inputDialogStichtag(result.get(0).replace(" ",""));
        }
        mAdapterCurrent.notifyDataSetChanged();

    }






}
