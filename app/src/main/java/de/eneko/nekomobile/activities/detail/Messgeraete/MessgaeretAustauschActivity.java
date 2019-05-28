package de.eneko.nekomobile.activities.detail.Messgeraete;

import android.view.View;

import de.eneko.nekomobile.activities.models.MessgeraetModel;
import de.eneko.nekomobile.activities.viewHolder.Messgearete.DetailViewHolder;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.beans.hlpta.FunkCheck_Austauschgrund;
import de.eneko.nekomobile.beans.hlpta.FunkModel;
import de.eneko.nekomobile.beans.hlpta.ZaehlerModel;
import de.eneko.nekomobile.controllers.Dict;
import de.eneko.nekomobile.controllers.FileHandler;

public class MessgaeretAustauschActivity extends MessgeraetBaseActivity {


    @Override
    protected void createViewHolder(){
        Messgeraet obj = FileHandler.getInstance().getMessgeraet();
        viewHolder = new DetailViewHolder( null,obj.getBaseModel(), this){
            @Override
            public void save() {
                getBasemodel().setRaum(getActvRaum().getText().toString());
                getBasemodel().setZielmodel(((ZaehlerModel) getSpNewModel().getSelectedItem()).getId());
                getBasemodel().setAustauschGrund(((FunkCheck_Austauschgrund) getSpAustauschgrund().getSelectedItem()).getId());
                getBasemodel().setNeueNummer(getTvNewNummer().getText().toString());
                getBasemodel().setNeueFunkNummer(getTvNewFunkNummer().getText().toString());
                getBasemodel().setNeuesFunkModel(((FunkModel) getSpNewFunkModel().getSelectedItem()).getId());
                getBasemodel().setDefekt(getCbDefekt().isChecked());
                getBasemodel().setBemerkung(getEtBemerkung().getText().toString());
                getBasemodel().setDefekt(getCbDefekt().isChecked());
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

                if (getBasemodel().getBean().getTodo().getArt().equals(Dict.TODO_FUNK_CHECK))
                {
                    if (getBasemodel().getBean().getArt().equals("HKV"))
                    {
                        getLbNewModel().setVisibility(View.GONE);
                        getSpNewModel().setVisibility(View.GONE);
                        getIvBarcodeNewModel().setVisibility(View.GONE);

                        getLbNewFunkModel().setVisibility(View.GONE);
                        getSpNewFunkModel().setVisibility(View.GONE);
                        getIvBarcodeNewFunkModel().setVisibility(View.GONE);

                        getLbNewFunkNummer().setVisibility(View.GONE);
                        getTvNewFunkNummer().setVisibility(View.GONE);
                        getIvBarcodeNewFunkNummer().setVisibility(View.GONE);
                        getCbDefekt().setVisibility(View.GONE);
                    }else{
                        getLbNewModel().setVisibility(View.GONE);
                        getSpNewModel().setVisibility(View.GONE);
                        getIvBarcodeNewModel().setVisibility(View.GONE);

                    }
                }
            }
        };
        viewHolder.updateView();
    }


}
