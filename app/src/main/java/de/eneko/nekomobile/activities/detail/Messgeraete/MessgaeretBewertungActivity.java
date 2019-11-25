package de.eneko.nekomobile.activities.detail.Messgeraete;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.viewHolder.Messgearete.BewertungViewHolder;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.controllers.Dict;

public class MessgaeretBewertungActivity extends AppCompatActivity {

    protected BewertungViewHolder viewHolder = null;
    public BewertungViewHolder getViewHolder() {
        return viewHolder;
    }

    public void setViewHolder(BewertungViewHolder viewHolder) {
        this.viewHolder = viewHolder;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_bewertung);
        createViewHolder();
    }

    protected void createViewHolder(){
        Messgeraet obj = CurrentObjectNavigation.getInstance().getMessgeraet();
        viewHolder = new BewertungViewHolder( null,obj.getBaseModel(), this){

            @Override
            protected void loadData(){
                super.loadData();
                createLayout();
            }

            @Override
            protected void createLayout() {

                if (getBasemodel().getTodo().getArt().equals(Dict.TODO_MONTAGE_HKV)
                        || getBasemodel().getBean().getTodo().getArt().equals(Dict.TODO_FUNK_CHECK))
                {
                    if (getBasemodel().getArt().equals("HKV"))
                    {
                        getLbGrundparameter().setVisibility(View.VISIBLE);
                        getEtGrundparameter().setVisibility(View.VISIBLE);
                    }
                }
            }
        };
        viewHolder.updateView();
    }

    protected void exit() {
        Intent intent = new Intent(this, MessgaeretAustauschActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        exit();
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
}
