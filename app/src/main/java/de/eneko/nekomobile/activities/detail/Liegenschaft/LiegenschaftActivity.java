package de.eneko.nekomobile.activities.detail.Liegenschaft;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.list.LiegenschaftListActivity;
import de.eneko.nekomobile.activities.viewHolder.Liegenschaft.LiegenschaftDetailViewHolder;
import de.eneko.nekomobile.beans.Liegenschaft;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;



public class LiegenschaftActivity extends AppCompatActivity{



    protected LiegenschaftDetailViewHolder viewHolder = null;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_liegenschaft);
        Liegenschaft liegenschaft = CurrentObjectNavigation.getInstance().getLiegenschaft();
        viewHolder = new LiegenschaftDetailViewHolder( null,liegenschaft.getBaseModel(), this);
        viewHolder.updateView();
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
        Intent intent = new Intent(this, LiegenschaftListActivity.class);
        startActivity(intent);
    }

    public void onBackPressed(){
        exit();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (viewHolder != null){
            viewHolder.updateView();
        }
    }
}
