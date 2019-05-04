package de.eneko.nekomobile.activities;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.Date;
import java.util.stream.Collectors;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.adapter.NutzerListViewAdapter;
import de.eneko.nekomobile.activities.adapter.RauchmelderWartungListViewAdapter;
import de.eneko.nekomobile.activities.detail.Rwm.RwmActivity_Info;
import de.eneko.nekomobile.activities.detail.Rwm.RwmActivity_New;
import de.eneko.nekomobile.beans.Nutzer;
import de.eneko.nekomobile.beans.Rauchwarnmelder;
import de.eneko.nekomobile.controllers.FileHandler;
import de.eneko.nekomobile.listener.NutzerListViewOnItemClickListener;

public class RauchmelderWartungListActivity extends AppCompatActivity {

    private static final int REQUEST_CAPTURE_IMAGE = 100;

    //ListView Adapter welcher den Inhalt verwaltet
    private RauchmelderWartungListViewAdapter mAdapter = null;
    private ArrayList<Rauchwarnmelder> datasource = new ArrayList<>();
    private ListView mListView = null;
    private Toolbar mToolbar= null;

    //OnItemClickListener er wird aufgerufen wenn ein einzelnes Item geklickt wird
    //private NutzerListViewOnItemClickListener mNutzerListViewOnItemClickListener = null;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ((AppCompatActivity)this).getSupportActionBar().setTitle("RWM: " + FileHandler.getInstance().getNutzer().getDisplay());


        // Init adapter
        datasource.addAll(FileHandler.getInstance().getNutzerTodo().getRauchmelder().stream()
                .sorted(Comparator.comparing(Rauchwarnmelder::getNekoId))
                .collect(Collectors.toList()));

        mAdapter = new RauchmelderWartungListViewAdapter(this,datasource);
        //mNutzerListViewOnItemClickListener = new NutzerListViewOnItemClickListener();


        mListView = findViewById(R.id.listView);

        // verbinden des adapters mit listview
        mListView.setAdapter(mAdapter);
        //mListView.setOnItemClickListener(mNutzerListViewOnItemClickListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rwm_wartung_list_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_add_new:
                Rauchwarnmelder new_rwm = FileHandler.getInstance().createNewRauchmelder();
                FileHandler.getInstance().setRauchwarnmelder(new_rwm);
                Intent intent = new Intent(this, RwmActivity_New.class);
                startActivity(intent);
                return true;
        }
        return false;
    }


    public void openCameraIntent(String relativeNekoPath, String filename) {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if(pictureIntent.resolveActivity(getPackageManager()) != null){
            //Create a file to store the image
            File photoFile = null;
            try {
                photoFile = FileHandler.getInstance().createJpgFile(relativeNekoPath,filename);
            } catch (IOException ex) {
                // Error occurred while creating the File
                System.err.print(ex.getMessage());
                return;
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,"de.eneko.nekomobile.provider", photoFile);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        photoURI);
                startActivityForResult(pictureIntent,
                        REQUEST_CAPTURE_IMAGE);
            }
        }
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
}
