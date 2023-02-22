package de.eneko.nekomobile.activities.list;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.Comparator;
import java.util.stream.Collectors;

import de.eneko.nekomobile.InputDialogChoiceListModeClass;
import de.eneko.nekomobile.InputDialogChoiceZaehlerArtClass;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.adapter.MessgeraetListViewAdapter;
import de.eneko.nekomobile.activities.detail.Liegenschaft.LiegenschaftActivity;
import de.eneko.nekomobile.activities.detail.Messgeraete.MessgaeretNeuActivity;
import de.eneko.nekomobile.activities.models.NutzerTodoModel;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.controllers.FileHandler;
import de.eneko.nekomobile.controllers.ObjectFactory;
import de.eneko.nekomobile.controllers.PhotoHandler;

public class MessgeraetSonexaListActivity extends MessgeraetListActivity {




    @Override
    protected void onResume() {
        super.onResume();
        loadDatasource();
        onBackPressedIntent = new Intent(this, MessgeraetSonexaListActivity.class);
    }
    @Override
    protected void setTitle(){
        getSupportActionBar().setTitle("SONEXA - Ablesung neugeräte");
    }


    @Override
    protected void OnDialogChoiceListModeSubmit(String selItem) {

    }

    @Override
    protected void showListChoiceDialog() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        newMenuItem.setVisible(false);
        recalculateReihenfolgeMenuItem.setVisible(false);
        modeMenuItem.setVisible(false);
        return true;
    }


    @Override
    protected void exit(){
        FileHandler.getInstance().saveFile(this);
        Intent intent = new Intent(this, LiegenschaftActivity.class);
        startActivity(intent);
    }

    protected void loadDatasource(){
        getDatasource().clear();
        getDatasource().addAll(CurrentObjectNavigation.getInstance().getLiegenschaft().getMessgeraets().stream()
                .sorted(Comparator.comparing(Messgeraet::getSortNo))
                .filter(r -> r.isSonexaInstall())
                .collect(Collectors.toList()));
        setCurrentAdapter(new MessgeraetListViewAdapter(this,getDatasource(),MessgeraetListViewAdapter.ViewHolderType.SONEXA));
        if (modeMenuItem!= null) {modeMenuItem.setIcon(getDrawable(R.drawable.ic_list));}
    }



}
