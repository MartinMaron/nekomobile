package de.eneko.nekomobile.activities.detail.Nutzer;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.list.NutzerListActivity;
import de.eneko.nekomobile.activities.models.viewHolder.Nutzer.NutzerDetailViewHolder;
import de.eneko.nekomobile.beans.Nutzer;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;

public class NutzerActivity extends AppCompatActivity{



    protected NutzerDetailViewHolder viewHolder = null;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_nuzter);
        Nutzer nutzer = CurrentObjectNavigation.getInstance().getNutzer();
        viewHolder = new NutzerDetailViewHolder( null,nutzer.getBaseModel(), this);
        viewHolder.updateView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewHolder.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_base_menu, menu);
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
        Intent intent = new Intent(this, NutzerListActivity.class);
        startActivity(intent);

    }

    public void onBackPressed(){
        viewHolder.save();
        exit();
    }



}
