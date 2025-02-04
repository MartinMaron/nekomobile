package de.eneko.nekomobile.activities.detail.Messgeraete;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import java.util.ArrayList;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.models.viewHolder.Messgearete.DetailViewHolder;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.controllers.PhotoHandler;
import de.eneko.nekomobile.controllers.SontexFileHandler;
import de.eneko.nekomobile.framework.ISaveAndExit;
import de.eneko.nekomobile.framework.NumberCustomKeyboard;

public abstract class MessgeraetBaseActivity extends AppCompatActivity implements ISaveAndExit {

    protected NumberCustomKeyboard mCustomKeyboard;

    protected DetailViewHolder viewHolder = null;
    public DetailViewHolder getViewHolder() {
        return viewHolder;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_messgeraet);
        mCustomKeyboard= new NumberCustomKeyboard(this, R.id.keyboardview, R.xml.keyboard );
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        createViewHolder();
    }

    @Override
    public void saveAndExit() {
        if (viewHolder != null) viewHolder.save();
        PhotoHandler.getInstance().compressFiles();
        exit();
    }

    protected abstract void createViewHolder();

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
            case R.id.menu_item_bewertung:
                viewHolder.save();
                Intent intent = new Intent(viewHolder.getActivity(), MessgaeretBewertungActivity.class);
                viewHolder.getActivity().startActivity(intent);
                return true;
            case R.id.menu_item_parametrieren:
                viewHolder.save();
                ArrayList<Messgeraet> messg = new ArrayList<Messgeraet>();
                messg.add(viewHolder.getBean());
                SontexFileHandler.getInstance().upsertSontexParamRoad(viewHolder.getActivity(),messg);
                exit();
                return true;
        }
        return false;
    }


    protected void exit(){

    }

    public void onBackPressed(){
        if( mCustomKeyboard.isCustomKeyboardVisible() ) mCustomKeyboard.hideCustomKeyboard(); else this.finish();
        exit();
    }

    @Override
    protected void onResume() {
        super.onResume();
         if (viewHolder != null) viewHolder.updateView();
    }

    public NumberCustomKeyboard getCustomKeyboard() {
        return mCustomKeyboard;
    }

    public void setCustomKeyboard(NumberCustomKeyboard customKeyboard) {
        mCustomKeyboard = customKeyboard;
    }
}
