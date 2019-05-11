package de.eneko.nekomobile.activities;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import de.eneko.nekomobile.InputDialogClass;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.adapter.MessgeraetListViewAdapter;
import de.eneko.nekomobile.activities.viewHolder.Messgearete.MessgeraetBaseViewHolder;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.controllers.FileHandler;
import de.eneko.nekomobile.controllers.MessgeraeteListViewActivityConroller;
import de.eneko.nekomobile.listener.MessgeraeteListViewOnItemClickListener;

public class MessgeraetListActivity extends AppCompatActivity
        implements View.OnClickListener, SearchView.OnQueryTextListener
        {

    //ListView Adapter welcher den Inhalt verwaltet
    private MessgeraetListViewAdapter mAdapterCurrent = null;
    private MessgeraetListViewAdapter mAdapter_man = null;
    private MessgeraetListViewAdapter mAdapter_exm = null;
    private MessgeraetListViewAdapter mAdapter_son = null;
    private ArrayList<Messgeraet> datasource = new ArrayList<>();
    private ArrayList<Messgeraet> datasource_man = new ArrayList<>();
    private ArrayList<Messgeraet> datasource_exm = new ArrayList<>();
    private ArrayList<Messgeraet> datasource_son = new ArrayList<>();

    private ImageView ivManuell = null;
    private ImageView ivSontex = null;
    private ImageView ivExim = null;


    private ListView mListView = null;
    private MenuItem searchMenuItem = null;
    private MenuItem eingabeMenuItem = null;
    private SearchView searchView = null;

    //OnItemClickListener er wird aufgerufen wenn ein einzelnes Item geklickt wird
    private MessgeraeteListViewOnItemClickListener mOnItemClickListener = null;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_ablesung);
        getSupportActionBar().setTitle(FileHandler.getInstance().getNutzerTodo().getNutzer().getDisplay());

        // Init datasource
        datasource.addAll(FileHandler.getInstance().getNutzerTodo().getMessgeraete().stream()
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
        mOnItemClickListener = new MessgeraeteListViewOnItemClickListener();
        mListView.setOnItemClickListener(mOnItemClickListener);

        ivManuell = findViewById(R.id.btShowStandard);
        ivManuell.setOnClickListener(this);
        ivSontex = findViewById(R.id.btShowSontexFunk);
        ivSontex.setOnClickListener(this);
        ivExim = findViewById(R.id.btShowEximFunk);
        ivExim.setOnClickListener(this);



    }

    @Override
    protected void onResume() {
        super.onResume();
        ivExim.setBackground(null);
        ivSontex.setBackground(null);
        ivManuell.setBackground(null);

        if (MessgeraeteListViewActivityConroller.getInstance().getGereteart() == MessgeraeteListViewActivityConroller.GeraeteArt.EXIM){
            setAdapterCurrent(mAdapter_exm);
            ivExim.setBackground(getDrawable(R.drawable.textview_border));
        }
        if (MessgeraeteListViewActivityConroller.getInstance().getGereteart() == MessgeraeteListViewActivityConroller.GeraeteArt.SONTEX){
            setAdapterCurrent(mAdapter_son);
            ivSontex.setBackground(getDrawable(R.drawable.textview_border));
        }
        if (MessgeraeteListViewActivityConroller.getInstance().getGereteart() == MessgeraeteListViewActivityConroller.GeraeteArt.MANUELL){
            setAdapterCurrent(mAdapter_man);
            ivManuell.setBackground(getDrawable(R.drawable.textview_border));
        }

       setEingabeMenuItemIcon();

    }

    private void setEingabeMenuItemIcon(){
            if (MessgeraeteListViewActivityConroller.getInstance().getEingabeArt() == MessgeraeteListViewActivityConroller.EingabeArt.SPRACHE){
                if (eingabeMenuItem != null) eingabeMenuItem.setIcon(getDrawable(R.drawable.icon_speaker));
            }
            if (MessgeraeteListViewActivityConroller.getInstance().getEingabeArt() == MessgeraeteListViewActivityConroller.EingabeArt.TASTATUR){
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
        mListView.setSelection(mAdapter_man.getPosition(FileHandler.getInstance().getMessgeraet()));
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
                if (MessgeraeteListViewActivityConroller.getInstance().getEingabeArt() == MessgeraeteListViewActivityConroller.EingabeArt.TASTATUR){
                    MessgeraeteListViewActivityConroller.getInstance().setEingabeArt( MessgeraeteListViewActivityConroller.EingabeArt.SPRACHE);
                }else if (MessgeraeteListViewActivityConroller.getInstance().getEingabeArt() == MessgeraeteListViewActivityConroller.EingabeArt.SPRACHE){
                    MessgeraeteListViewActivityConroller.getInstance().setEingabeArt( MessgeraeteListViewActivityConroller.EingabeArt.TASTATUR);
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
                    MessgeraeteListViewActivityConroller.getInstance().setGereteart(MessgeraeteListViewActivityConroller.GeraeteArt.EXIM);
                break;
            case R.id.btShowSontexFunk:
                MessgeraeteListViewActivityConroller.getInstance().setGereteart(MessgeraeteListViewActivityConroller.GeraeteArt.SONTEX);
                break;
            case R.id.btShowStandard:
                MessgeraeteListViewActivityConroller.getInstance().setGereteart(MessgeraeteListViewActivityConroller.GeraeteArt.MANUELL);
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
            new MessgeraetBaseViewHolder(null,FileHandler.getInstance().getMessgeraet(),this){}.inputDialogAktuell(result.get(0));
        }
        if (requestCode == MessgeraetBaseViewHolder.REQUEST_BT_STICHTAG && data != null ) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            new MessgeraetBaseViewHolder(null,FileHandler.getInstance().getMessgeraet(),this){}.inputDialogStichtag(result.get(0));
        }
        mAdapterCurrent.notifyDataSetChanged();

    }






}
