package de.eneko.nekomobile.activities.list;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.adapter.NutzerTodosListViewAdapter;
import de.eneko.nekomobile.beans.ToDo;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.controllers.FileHandler;

public class NutzerTodosListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private NutzerTodosListViewAdapter mAdapter = null;
    private ArrayList<ToDo> datasource = new ArrayList<ToDo>();
    private ListView mListView = null;

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
            case "ABL_ALL":
                intent = new Intent(view.getContext(), MessgeraetAblesungListActivity.class);
                view.getContext().startActivity(intent);
                break;
            case "FUN_CHK": case "MON_HKV": case "MON_WMZ": case "MON_WWZ": case "MON_KWZ":
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        CurrentObjectNavigation.getInstance().getNutzer().getBaseModel().load();
        this.setTitle(CurrentObjectNavigation.getInstance().getNutzer().getBaseModel().getDisplay());
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
