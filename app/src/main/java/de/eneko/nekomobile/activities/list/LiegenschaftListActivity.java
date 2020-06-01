package de.eneko.nekomobile.activities.list;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import de.eneko.nekomobile.activities.adapter.LiegenschaftListViewAdapter;
import de.eneko.nekomobile.activities.detail.Liegenschaft.LiegenschaftActivity;
import de.eneko.nekomobile.beans.Liegenschaft;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.controllers.FileHandler;

public class LiegenschaftListActivity extends ListActivity implements AdapterView.OnItemLongClickListener,
        AdapterView.OnItemClickListener
{

    //ListView Adapter welcher den Inhalt verwaltet
    private LiegenschaftListViewAdapter mAdapterCurrent = null;
    private ArrayList<Liegenschaft> datasource = new ArrayList<Liegenschaft>();
    protected MenuItem modeMenuItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getListView().setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(this);
        loadDatasourceCore();
    }


    // region UI (on click)

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //aktuelle Liegenschaft in der Navigation speichern
        CurrentObjectNavigation.getInstance().setLiegenschaft((Liegenschaft) getAdapterCurrent().getItem(i));

        if(!CurrentObjectNavigation.getInstance().getLiegenschaft().getBemerkung().equals("")){
            Toast toast= Toast.makeText(getApplicationContext(),
                    CurrentObjectNavigation.getInstance().getLiegenschaft().getBemerkung(), Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 400);
            toast.show();
         }

        Intent intent = new Intent(view.getContext(), NutzerListActivity.class);

        if (isLongClick) {
            intent = new Intent(view.getContext(), LiegenschaftActivity.class);
            isLongClick = false;
        }else
        {
            intent = new Intent(view.getContext(), NutzerListActivity.class);
        }


        //Aufrufen der Activity
        view.getContext().startActivity(intent);
    }

    private Boolean isLongClick = false;
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        isLongClick = true;
        return false;
    }
    // endregion

    // region Data - Management

    protected void loadDatasourceCore(){
        getDatasource().clear();
        getDatasource().addAll(CurrentObjectNavigation.getInstance().getRoute().getLiegenschaften().stream()
                .sorted(Comparator.comparing(Liegenschaft::getSortNo))
                .collect(Collectors.toList()));
        setAdapterCurrent(new LiegenschaftListViewAdapter(this,getDatasource()));
    }


    public void setAdapterCurrent(LiegenschaftListViewAdapter adapterCurrent) {
        mAdapterCurrent = adapterCurrent;
        setListAdapter(mAdapterCurrent);
        mAdapterCurrent.notifyDataSetChanged();
        if(CurrentObjectNavigation.getInstance().getMessgeraet() != null) { getListView().setSelection(mAdapterCurrent.getPosition(CurrentObjectNavigation.getInstance().getLiegenschaft()));}
    }

// endregion


    public LiegenschaftListViewAdapter getAdapterCurrent() {
        return mAdapterCurrent;
    }
    public ArrayList<Liegenschaft> getDatasource() {
        return datasource;
    }


    protected void exit(){
        FileHandler.getInstance().saveFile();
        Intent intent = new Intent(this, FileListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FileHandler.getInstance().saveFile();
        exit();
    }
}