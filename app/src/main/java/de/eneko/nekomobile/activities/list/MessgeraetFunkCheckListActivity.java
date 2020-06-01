package de.eneko.nekomobile.activities.list;

import android.os.Bundle;

import java.util.Comparator;
import java.util.stream.Collectors;

import de.eneko.nekomobile.InputDialogChoiceListModeClass;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.adapter.MessgeraetListViewAdapter;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;

public class MessgeraetFunkCheckListActivity extends MessgeraetListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void loadDatasourceCore() {
        super.loadDatasourceCore();
        loadTodoDatasource();
    }

    // nur die zu verarbeitenden Tätigkeiten
    protected void loadTodoDatasource() {
        getDatasource().clear();
        getDatasource().addAll(CurrentObjectNavigation.getInstance().getNutzerTodo().getMessgeraete().stream()
                .filter( r -> r.isWork() && r.isForFunkCheck())
                .sorted(Comparator.comparing(Messgeraet::getSortNo))
                .collect(Collectors.toList()));

        setCurrentAdapter(new MessgeraetListViewAdapter (this,getDatasource(),MessgeraetListViewAdapter.ViewHolderType.WORK));
        if (modeMenuItem!= null) {modeMenuItem.setIcon(getDrawable(R.drawable.ic_list));}


    }

    // alle Geräte einer Nutzeinheit
    protected void loadWohnungDatasource() {
        getDatasource().clear();
        getDatasource().addAll(CurrentObjectNavigation.getInstance().getNutzerTodo().getMessgeraete().stream()
                .filter( r -> r.isWork())
                .sorted(Comparator.comparing(Messgeraet::getSortNo))
                .collect(Collectors.toList()));
        setCurrentAdapter(new MessgeraetListViewAdapter (this,getDatasource(),MessgeraetListViewAdapter.ViewHolderType.WORK));
        if (modeMenuItem!= null) {modeMenuItem.setIcon(getDrawable(R.drawable.ic_list));}

    }

    protected void loadRealestateTodoDatasource(){
        getDatasource().clear();
        getDatasource().addAll(CurrentObjectNavigation.getInstance().getLiegenschaft().getBaseModel().getNutzerMessgaerete().stream()
                .filter( r -> r.isWork() && r.isForFunkCheck())
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



    // region implement abstract
    @Override
    protected void OnDialogChoiceListModeSubmit(String selItem) {
        switch (selItem) {
            case "A":
                loadRealestateInfoDatasource();
                break;
            case "W":
                loadWohnungDatasource();
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
        new InputDialogChoiceListModeClass(this, "TALW"){
            @Override
            protected void OnDialogSubmit(String selItem) {
                OnDialogChoiceListModeSubmit(selItem);
            }
        }.show();
    }

   // endregion
}



