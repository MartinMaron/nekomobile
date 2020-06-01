package de.eneko.nekomobile.activities.list;

import java.util.Comparator;
import java.util.stream.Collectors;

import de.eneko.nekomobile.InputDialogChoiceListModeClass;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.adapter.MessgeraetListViewAdapter;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;

public class MessgeraetInfoListActivity extends MessgeraetListActivity {

    @Override
    protected void onResume() {
        super.onResume();
        loadTodoDatasource();
    }

    protected void loadTodoDatasource() {
        getDatasource().clear();
        getDatasource().addAll(CurrentObjectNavigation.getInstance().getNutzerTodo().getMessgeraete().stream()
                .filter(r -> r.isInfo())
                .sorted(Comparator.comparing(Messgeraet::getSortNo))
                .collect(Collectors.toList()));
        setCurrentAdapter(new MessgeraetListViewAdapter (this,getDatasource(),MessgeraetListViewAdapter.ViewHolderType.INFO));
        if (modeMenuItem!= null) {modeMenuItem.setIcon(getDrawable(R.drawable.ic_list_info));}

    }




    protected void loadRealestateDatasource() {
        getDatasource().clear();
        getDatasource().addAll(CurrentObjectNavigation.getInstance().getLiegenschaft().getBaseModel().getNutzerMessgaerete().stream()
                .filter(r -> r.isInfo())
                .sorted(Comparator.comparing(Messgeraet::getSortNo))
                .collect(Collectors.toList()));
        setCurrentAdapter(new MessgeraetListViewAdapter (this,getDatasource(),MessgeraetListViewAdapter.ViewHolderType.INFO_WITH_NAME));
        if (modeMenuItem!= null) {modeMenuItem.setIcon(getDrawable(R.drawable.ic_list_info));}

    }

    // region implement abstract
    @Override
    public void showListChoiceDialog(){
        new InputDialogChoiceListModeClass(this, "WA"){
            @Override
            protected void OnDialogSubmit(String selItem) {
                OnDialogChoiceListModeSubmit(selItem);
            }
        }.show();
    }

    @Override
    protected void OnDialogChoiceListModeSubmit(String selItem) {
        switch (selItem) {
            case "A":
                loadRealestateDatasource();
                break;
            case "W":
                loadTodoDatasource();
                break;
            case "T":
                break;
            case "L":
                break;
            default:
                    break;

        }

    }
    // endregion


}



