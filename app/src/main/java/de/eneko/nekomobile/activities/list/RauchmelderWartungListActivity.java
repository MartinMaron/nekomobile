package de.eneko.nekomobile.activities.list;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.adapter.RauchmelderWartungListViewAdapter;
import de.eneko.nekomobile.activities.detail.Nutzer.NutzerActivity;
import de.eneko.nekomobile.activities.detail.Rwm.RwmActivity_New;
import de.eneko.nekomobile.activities.models.RauchmelderModel;
import de.eneko.nekomobile.beans.Rauchmelder;
import de.eneko.nekomobile.beans.ToDo;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.controllers.FileHandler;
import de.eneko.nekomobile.controllers.ObjectFactory;

public class RauchmelderWartungListActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {


    private RauchmelderWartungListViewAdapter mAdapter = null;
    private ArrayList<Rauchmelder> datasource = new ArrayList<>();
    private ListView mListView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        getSupportActionBar().setTitle("RWM: " + CurrentObjectNavigation.getInstance().getNutzer().getBaseModel().getDisplay());

        datasource.addAll(CurrentObjectNavigation.getInstance().getNutzerTodo().getRauchmelder().stream()
                .sorted(Comparator.comparing(Rauchmelder::getNekoId))
                .collect(Collectors.toList()));

        mAdapter = new RauchmelderWartungListViewAdapter(this, datasource);
        mListView = findViewById(R.id.listView);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemLongClickListener(this);

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


    protected void exit() {
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
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        //Falls der Rauchmelder neu hinzugefügt wurde so können wir nach einer Frage den rwm löschen

        Rauchmelder rwm = mAdapter.getItem(position);
        if (rwm.getNew()) {


            AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialogTheme);
            AlertDialog dialog = builder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mAdapter.remove(rwm);
                    ToDo t = rwm.getTodo();
                    t.getRauchmelder().remove(rwm);
                }
            }).create();

            //2. now setup to change color of the button
            dialog.setOnShowListener( new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface arg0) {
                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(getBaseContext(), R.color.black));
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(getBaseContext(), R.color.black));
                }
            });
            dialog.setTitle("Soll der Rauchmelder gelöscht werden?");
            dialog.show();
        };
        return true;
    }
}
