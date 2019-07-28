package de.eneko.nekomobile.activities.detail.Messgeraete;

import android.view.View;

import de.eneko.nekomobile.activities.models.MessgeraetModel;
import de.eneko.nekomobile.activities.viewHolder.Messgearete.DetailViewHolder;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.beans.hlpta.ZaehlerModel;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.controllers.FileHandler;

public class MessgaeretAblesungActivity extends MessgeraetBaseActivity {


    @Override
    protected void createViewHolder(){
        Messgeraet obj = CurrentObjectNavigation.getInstance().getMessgeraet();
        viewHolder = new DetailViewHolder( null,obj.getBaseModel(), this){
            @Override
            public void save() {
                getBasemodel().setZielmodel(((ZaehlerModel) getSpNewModel().getSelectedItem()).getId());
                getBasemodel().setNeueNummer(getTvNewNummer().getText().toString());
                getBasemodel().setRaum(getActvRaum().getText().toString());
                getBasemodel().setDefekt(getCbDefekt().isChecked());
                getBasemodel().setBemerkung(getEtBemerkung().getText().toString());
                getBasemodel().save();
            }

            @Override
            protected void loadData(){
                super.loadData();
                createLayout();
            }

            @Override
            protected void createLayout() {
                if (getTvNummer() != null) getTvNummer().setFocusable(false);

                getLbNewModel().setVisibility(View.GONE);
                getSpNewModel().setVisibility(View.GONE);
                getIvBarcodeNewModel().setVisibility(View.GONE);
                getIvBarcodeNewNummer().setVisibility(View.GONE);

                getLbNewFunkModel().setVisibility(View.GONE);
                getSpNewFunkModel().setVisibility(View.GONE);
                getIvBarcodeNewFunkModel().setVisibility(View.GONE);

                getLbNewFunkNummer().setVisibility(View.GONE);
                getTvNewFunkNummer().setVisibility(View.GONE);
                getIvBarcodeNewFunkNummer().setVisibility(View.GONE);
            }
        };
        viewHolder.updateView();
    }


}
