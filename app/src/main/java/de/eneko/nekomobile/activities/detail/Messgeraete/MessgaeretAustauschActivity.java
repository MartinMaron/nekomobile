package de.eneko.nekomobile.activities.detail.Messgeraete;

import android.content.Intent;
import android.view.View;

import de.eneko.nekomobile.InputDialogClass;
import de.eneko.nekomobile.activities.list.MessgeraetMontageListActivity;
import de.eneko.nekomobile.activities.viewHolder.Messgearete.DetailViewHolder;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.beans.hlpta.FunkCheck_Austauschgrund;
import de.eneko.nekomobile.beans.hlpta.FunkModel;
import de.eneko.nekomobile.beans.hlpta.ZaehlerModel;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.controllers.Dict;

public class MessgaeretAustauschActivity extends MessgeraetBaseActivity {


    @Override
    protected void createViewHolder(){
       Messgeraet obj = CurrentObjectNavigation.getInstance().getMessgeraet();
        viewHolder = new DetailViewHolder( null,obj.getBaseModel(), this){
            @Override
            public void save() {
                getBasemodel().setRaum(getActvRaum().getText().toString());
                Object zm = getSpNewModel().getSelectedItem();

                if (getSpNewModel().getSelectedItem() != null)  getBasemodel().setZielmodel(((ZaehlerModel) getSpNewModel().getSelectedItem()).getId());
                getBasemodel().setAustauschGrund(((FunkCheck_Austauschgrund) getSpAustauschgrund().getSelectedItem()).getId());
                getBasemodel().setNeueNummer(getTvNewNummer().getText().toString());
                getBasemodel().setNeueFunkNummer(getTvNewFunkNummer().getText().toString());
                  if (getSpNewFunkModel().getSelectedItem() != null) {
                      getBasemodel().setNeuesFunkModel(((FunkModel) getSpNewFunkModel().getSelectedItem()).getId());
                  }
                getBasemodel().setDefekt(getCbDefekt().isChecked());
                getBasemodel().setBemerkung(getEtBemerkung().getText().toString());
                getBasemodel().setUnDoneGrundGrund(getAcUnDoneGrund().getText().toString());

                String convertedValue = getTvStichtag().getText().toString().replace(" ","");
                if (InputDialogClass.isDouble(convertedValue)) {
                    getBasemodel().setStichtagValue(Double.parseDouble(convertedValue.replace(",",".")));
                }
                convertedValue = getTvAktuell().getText().toString().replace(" ","");
                if (InputDialogClass.isDouble(convertedValue)) {
                    getBasemodel().setAktuellValue(Double.parseDouble(convertedValue.replace(",",".")));
                }

//test i brak
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

                if (getBasemodel().getBean().getTodo().getArt().equals(Dict.TODO_MONTAGE_HKV))
                {
                    if (getBasemodel().getBean().getArt().equals("HKV"))
                    {
                        getLbNewModel().setVisibility(View.VISIBLE);
                        getSpNewModel().setVisibility(View.VISIBLE);
                        getIvBarcodeNewModel().setVisibility(View.GONE);

                        getLbNewFunkModel().setVisibility(View.GONE);
                        getSpNewFunkModel().setVisibility(View.GONE);
                        getIvBarcodeNewFunkModel().setVisibility(View.GONE);

                        getLbNewFunkNummer().setVisibility(View.GONE);
                        getTvNewFunkNummer().setVisibility(View.GONE);
                        getIvBarcodeNewFunkNummer().setVisibility(View.GONE);
                        getCbDefekt().setVisibility(View.GONE);
                    }
                }
            }
        };
        viewHolder.updateView();
    }

    @Override
    protected void exit() {
        Intent intent = new Intent(this, MessgeraetMontageListActivity.class);
        startActivity(intent);
    }

}
