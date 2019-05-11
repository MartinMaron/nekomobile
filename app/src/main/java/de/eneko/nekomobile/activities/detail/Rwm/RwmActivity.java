package de.eneko.nekomobile.activities.detail.Rwm;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.RauchmelderWartungListActivity;
import de.eneko.nekomobile.activities.viewHolder.Rauchmelder.DetailViewHolder;
import de.eneko.nekomobile.beans.Rauchwarnmelder;
import de.eneko.nekomobile.controllers.FileHandler;

public class RwmActivity extends AppCompatActivity{



    protected DetailViewHolder viewHolder = null;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_rwm_wartung);
        Rauchwarnmelder rwm = FileHandler.getInstance().getRauchwarnmelder();
        viewHolder = new DetailViewHolder( null,rwm, this);
        viewHolder.updateView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewHolder.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rwm_wartung_detail_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_save:
                viewHolder.save();
                exit();
                return true;
        }
        return false;
    }


    protected void exit(){
        Intent intent = new Intent(this, RauchmelderWartungListActivity.class);
        startActivity(intent);
    }

    public void onBackPressed(){
        exit();
    }



}
