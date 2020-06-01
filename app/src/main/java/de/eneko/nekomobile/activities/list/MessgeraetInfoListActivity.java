package de.eneko.nekomobile.activities.list;

import android.view.View;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import de.eneko.nekomobile.InputDialogChoiceListModeClass;
import de.eneko.nekomobile.activities.adapter.MessgeraetListViewAdapter;
import de.eneko.nekomobile.beans.Messgeraet;

public class MessgeraetInfoListActivity extends MessgeraetListActivity {
    protected ArrayList<Messgeraet> datasource_FunkCheck = new ArrayList<>();


    @Override
    protected void onResume() {
        super.onResume();
        ivExim.setBackground(null);
        ivSontex.setBackground(null);
        ivManuell.setBackground(null);
        ivExim.setVisibility(View.GONE);
        ivSontex.setVisibility(View.GONE);
        ivManuell.setVisibility(View.GONE);
        loadDatasourceCore();
        loadTodoDatasource();
    }

    @Override
    protected void loadTodoDatasource() {
        super.loadTodoDatasource();
        datasource_man.clear();
        datasource_man.addAll(datasource.stream()
                .filter(r -> r.isInfo())
                .sorted(Comparator.comparing(Messgeraet::getSortNo))
                .collect(Collectors.toList()));
        setAdapterCurrent(new MessgeraetListViewAdapter (this,datasource_man,MessgeraetListViewAdapter.ViewHolderType.WORK));
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

    @Override
    protected void loadRealestateDatasource() {
        super.loadRealestateDatasource();
        datasource_man.clear();
        datasource_man.addAll(datasource.stream()
                .filter(r -> r.isInfo())
                .sorted(Comparator.comparing(Messgeraet::getSortNo))
                .collect(Collectors.toList()));
        setAdapterCurrent(new MessgeraetListViewAdapter (this,datasource_man,MessgeraetListViewAdapter.ViewHolderType.WORK));
    }

    protected void loadRealestateTodoDatasource() {
        super.loadRealestateDatasource();
        datasource_man.clear();
        datasource_man.addAll(datasource.stream()
                .filter(r -> r.isWork())
                .sorted(Comparator.comparing(Messgeraet::getSortNo))
                .collect(Collectors.toList()));
        setAdapterCurrent(new MessgeraetListViewAdapter (this,datasource_man,MessgeraetListViewAdapter.ViewHolderType.WORK));
    }



    @Override
    protected void OnDialogChoiceListModeSubmit(String selItem) {
        switch (selItem) {
            case "A":
                loadRealestateDatasource();
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
}



