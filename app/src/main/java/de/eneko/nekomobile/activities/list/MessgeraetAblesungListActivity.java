package de.eneko.nekomobile.activities.list;

import android.content.Intent;

import java.util.Comparator;
import java.util.stream.Collectors;

import de.eneko.nekomobile.InputDialogChoiceListModeClass;
import de.eneko.nekomobile.InputDialogChoiceZaehlerArtClass;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.adapter.MessgeraetListViewAdapter;
import de.eneko.nekomobile.activities.detail.Messgeraete.MessgaeretNeuActivity;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.controllers.ObjectFactory;

public class MessgeraetAblesungListActivity extends MessgeraetListActivity {

    @Override
    protected void onResume() {
        super.onResume();
        loadTodoDatasource();
        onBackPressedIntent = new Intent(this, MessgeraetAblesungListActivity.class);
    }

    @Override
    protected void OnDialogChoiceListModeSubmit(String selItem) {
        switch (selItem) {
            case "A":
                loadRealestateInfoDatasource();
                break;
            case "W":
                break;
            case "T":
                loadTodoDatasource();
                break;
            case "L":
                loadRealestateTodoDatasource();
                break;
            default:
                break;

        }
    }

    @Override
    public void showListChoiceDialog(){
        new InputDialogChoiceListModeClass(this, "TAL"){
            @Override
            protected void OnDialogSubmit(String selItem) {
                OnDialogChoiceListModeSubmit(selItem);
            }
        }.show();
    }

    protected void loadTodoDatasource(){
        getDatasource().clear();
        getDatasource().addAll(CurrentObjectNavigation.getInstance().getNutzerTodo().getMessgeraete().stream()
                .sorted(Comparator.comparing(Messgeraet::getSortNo))
                .collect(Collectors.toList()));
        setCurrentAdapter(new MessgeraetListViewAdapter(this,getDatasource(),MessgeraetListViewAdapter.ViewHolderType.WORK));
        if (modeMenuItem!= null) {modeMenuItem.setIcon(getDrawable(R.drawable.ic_list));}
    }
    protected void loadRealestateTodoDatasource(){
        getDatasource().clear();
        getDatasource().addAll(CurrentObjectNavigation.getInstance().getLiegenschaft().getBaseModel().getNutzerMessgaerete().stream()
                .filter( r -> r.isWork())
                .sorted(Comparator.comparing(Messgeraet::getSortNo))
                .collect(Collectors.toList()));
        setCurrentAdapter(new MessgeraetListViewAdapter (this,getDatasource(),MessgeraetListViewAdapter.ViewHolderType.WORK_WITH_NAME));
        if (modeMenuItem!= null) {modeMenuItem.setIcon(getDrawable(R.drawable.ic_list));}
    }

    protected void loadRealestateInfoDatasource(){
        getDatasource().clear();
        getDatasource().addAll(CurrentObjectNavigation.getInstance().getLiegenschaft().getBaseModel().getNutzerMessgaerete().stream()
                .filter( r -> r.isInfo())
                .sorted(Comparator.comparing(Messgeraet::getSortNo))
                .collect(Collectors.toList()));
        setCurrentAdapter(new MessgeraetListViewAdapter (this,getDatasource(),MessgeraetListViewAdapter.ViewHolderType.INFO_WITH_NAME));

        if (modeMenuItem!= null) {modeMenuItem.setIcon(getDrawable(R.drawable.ic_list_info));}

    }

    @Override
    protected void AddNeuMessgaeret() {
//        Zählerart auswählen
        new InputDialogChoiceZaehlerArtClass(this){
            @Override
            protected void OnDialogSubmit(String selItem) {AddNewByArt(selItem);}
        }.show();

    }

    protected void AddNewByArt(String pArt){
        Messgeraet new_obj = ObjectFactory.getInstance().createNewMessgaeret(CurrentObjectNavigation.getInstance().getNutzerTodo());
        CurrentObjectNavigation.getInstance().setMessgeraet(new_obj);
        new_obj.setArt(pArt);
        Intent intent = new Intent(this, MessgaeretNeuActivity.class);
        startActivity(intent);
    }




}
