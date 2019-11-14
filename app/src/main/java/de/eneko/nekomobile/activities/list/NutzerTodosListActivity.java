package de.eneko.nekomobile.activities.list;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.adapter.NutzerTodosListViewAdapter;
import de.eneko.nekomobile.activities.models.NutzerModel;
import de.eneko.nekomobile.beans.ToDo;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.controllers.FileHandler;

public class NutzerTodosListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private NutzerTodosListViewAdapter mAdapter = null;
    private ArrayList<ToDo> datasource = new ArrayList<ToDo>();
    private ListView mListView = null;
    private TextView titleTextView;

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ToDo todo =  mAdapter.getItem(i);
        CurrentObjectNavigation.getInstance().setNutzerTodo(todo);
        Intent intent = null;
        switch (todo.getArt()){
            case "WAR_RWM": case "MON_RWM":
                intent = new Intent(view.getContext(), RauchmelderWartungListActivity.class);
                view.getContext().startActivity(intent);
                break;
            case "ABL_ALL": case "ABL_ZWI":
                intent = new Intent(view.getContext(), MessgeraetAblesungListActivity.class);
                view.getContext().startActivity(intent);
                break;
            case "MON_WMZ": case "MON_WWZ": case "MON_KWZ":
                intent = new Intent(view.getContext(), MessgeraetMontageListActivity.class);
                view.getContext().startActivity(intent);
                break;
            case "MON_HKV":
                intent = new Intent(view.getContext(), MessgeraetMontageListActivity.class);
                view.getContext().startActivity(intent);
                break;
            case "FUN_CHK":
                intent = new Intent(view.getContext(), MessgeraetFunkCheckListActivity.class);
                view.getContext().startActivity(intent);
                break;
            default:
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);


        datasource.addAll(CurrentObjectNavigation.getInstance().getNutzer().getToDos().stream()
                .sorted(Comparator.comparing(ToDo::getArt))
                .collect(Collectors.toList()));
        mAdapter = new NutzerTodosListViewAdapter(this,datasource);
        mListView = findViewById(R.id.listView);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);

        android.support.v7.app.ActionBar mActionBar = getSupportActionBar();
        mActionBar.setSubtitle("Subtitle");

//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); //bellow setSupportActionBar(toolbar);
//        getSupportActionBar().setCustomView(R.layout.custom_bar_title);
//        titleTextView = (TextView) findViewById(R.id.action_bar_title);
//        titleTextView.setText("Custom text");


    }

    @Override
    protected void onResume() {
        super.onResume();
        CurrentObjectNavigation.getInstance().getNutzer().getBaseModel().load();
        NutzerModel nutzerModel = CurrentObjectNavigation.getInstance().getNutzer().getBaseModel();
        this.setTitle(nutzerModel.getDisplay());
        if (nutzerModel.hasZwischenAblesung())
        {
           this.getSupportActionBar().setSubtitle(nutzerModel.getDisplayZwischenablesung());
        }else {
            if (!nutzerModel.getNutzerNameNeuerInNeko().equals("")) {
                this.getSupportActionBar().setSubtitle(nutzerModel.getNutzerNameNeuerInNeko());
            }
        }
        mAdapter.notifyDataSetChanged();
    }
    protected void exit(){
        FileHandler.getInstance().saveFile();
        Intent intent = new Intent(this, NutzerListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exit();
    }
}
