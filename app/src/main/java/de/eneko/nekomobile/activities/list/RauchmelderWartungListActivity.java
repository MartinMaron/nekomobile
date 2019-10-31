package de.eneko.nekomobile.activities.list;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.adapter.RauchmelderWartungListViewAdapter;
import de.eneko.nekomobile.activities.detail.Rwm.RwmActivity_New;
import de.eneko.nekomobile.activities.models.RauchmelderModel;
import de.eneko.nekomobile.beans.Rauchmelder;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.controllers.FileHandler;
import de.eneko.nekomobile.controllers.ObjectFactory;

public class RauchmelderWartungListActivity extends AppCompatActivity {


    private RauchmelderWartungListViewAdapter mAdapter = null;
    private ArrayList<Rauchmelder> datasource = new ArrayList<>();
    private ListView mListView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        getSupportActionBar().setTitle("RWM: " + CurrentObjectNavigation.getInstance().getNutzer().getBaseModel().getDisplay());

        datasource.addAll(CurrentObjectNavigation.getInstance().getNutzerTodo().getRauchmelder().stream()
                .sorted(Comparator.comparing(Rauchmelder::getNekoId))
                .collect(Collectors.toList()));

        mAdapter = new RauchmelderWartungListViewAdapter(this,datasource);
        mListView = findViewById(R.id.listView);
        mListView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rwm_wartung_list_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_add_new:
                Rauchmelder new_rwm = ObjectFactory.getInstance().createNewRauchmelder(CurrentObjectNavigation.getInstance().getNutzerTodo());
                CurrentObjectNavigation.getInstance().setRauchmelder(new_rwm);
                Intent intent = new Intent(this, RwmActivity_New.class);
                startActivity(intent);
                return true;
        }
        return false;
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
}
