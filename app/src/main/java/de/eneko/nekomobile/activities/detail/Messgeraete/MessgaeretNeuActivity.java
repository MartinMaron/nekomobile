package de.eneko.nekomobile.activities.detail.Messgeraete;

import android.content.Intent;
import android.view.View;

import java.text.NumberFormat;
import java.util.Locale;

import de.eneko.nekomobile.activities.list.MessgeraetMontageListActivity;
import de.eneko.nekomobile.activities.viewHolder.Messgearete.DetailViewHolder;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.beans.hlpta.FunkCheck_Austauschgrund;
import de.eneko.nekomobile.beans.hlpta.FunkModel;
import de.eneko.nekomobile.beans.hlpta.ZaehlerModel;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.controllers.Dict;

public class MessgaeretNeuActivity extends MessgeraetBaseActivity {
    protected static final String TAG = MessgaeretNeuActivity.class.getSimpleName();


    @Override
    protected void createViewHolder(){
       Messgeraet obj = CurrentObjectNavigation.getInstance().getMessgeraet();
        viewHolder = new DetailViewHolder( null,obj.getBaseModel(), this){
            @Override
            public void save() {
                getBasemodel().setRaum(getActvRaum().getText().toString());
                if (obj.getArt().equals("HKV")){
                    if (getSpNewModel().getSelectedItem() != null)  getBasemodel().setZielmodel(((ZaehlerModel) getSpNewModel().getSelectedItem()).getId());
                    getBasemodel().setAustauschGrund(((FunkCheck_Austauschgrund) getSpAustauschgrund().getSelectedItem()).getId());
                    getBasemodel().setNeueNummer(getTvNewNummer().getText().toString());
                    getBasemodel().setBemerkung(getEtBemerkung().getText().toString());
                    try{getBasemodel().setProcent(NumberFormat.getInstance(Locale.GERMANY).parse(getTvProcente().getText().toString()).doubleValue());
                    }catch (Exception e){}

                }else{
                    if (getSpNewModel().getSelectedItem() != null)  getBasemodel().setZielmodel(((ZaehlerModel) getSpNewModel().getSelectedItem()).getId());
                    getBasemodel().setAustauschGrund(((FunkCheck_Austauschgrund) getSpAustauschgrund().getSelectedItem()).getId());
                    getBasemodel().setNeueNummer(getTvNewNummer().getText().toString());
                    getBasemodel().setNeueFunkNummer(getTvNewFunkNummer().getText().toString());
                    getBasemodel().setNeuesFunkModel(((FunkModel) getSpNewFunkModel().getSelectedItem()).getId());
                    getBasemodel().setBemerkung(getEtBemerkung().getText().toString());
                    getBasemodel().setUnDoneGrundGrund(getAcUnDoneGrund().getText().toString());
                    try{getBasemodel().setStartWert(NumberFormat.getInstance(Locale.GERMANY).parse(getTvStartwert().getText().toString()).doubleValue());
                    }catch (Exception e){}
                }
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

                if (getTvProcente() != null) ((MessgaeretNeuActivity) getActivity()).getCustomKeyboard().registerEditText(getTvProcente());
                if (getTvStartwert() != null) ((MessgaeretNeuActivity) getActivity()).getCustomKeyboard().registerEditText(getTvStartwert());

                if (getBasemodel().getBean().getTodo().getArt().equals(Dict.TODO_FUNK_CHECK))
                {
                    if (getBasemodel().getBean().getArt().equals("HKV"))
                    {
                        getLbNewModel().setVisibility(View.GONE);
                        getSpNewModel().setVisibility(View.GONE);
                        getIvBarcodeNewModel().setVisibility(View.GONE);
                        getTvProcente().setVisibility(View.VISIBLE);
                        getLbStartwert().setVisibility(View.GONE);
                        getTvStartwert().setVisibility(View.GONE);
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
                        getTvProcente().setVisibility(View.GONE);
                        getTvStartwert().setVisibility(View.VISIBLE);
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

                        getTvProcente().setVisibility(View.VISIBLE);
                        getTvStartwert().setVisibility(View.GONE);
                        getLbStartwert().setVisibility(View.GONE);

                        getLbNewFunkModel().setVisibility(View.GONE);
                        getSpNewFunkModel().setVisibility(View.GONE);
                        getIvBarcodeNewFunkModel().setVisibility(View.GONE);

                        getLbNewFunkNummer().setVisibility(View.GONE);
                        getTvNewFunkNummer().setVisibility(View.GONE);
                        getIvBarcodeNewFunkNummer().setVisibility(View.GONE);
                        getCbDefekt().setVisibility(View.GONE);
                    }

                    if (getBasemodel().getBean().isNew()) {
                        getTvNummer().setEnabled(true);
                        getTvNummer().setFocusable(true);
                        getLbStichtag().setVisibility(View.GONE);
                        getTvStichtag().setVisibility(View.GONE);
                        getTvAktuell().setVisibility(View.GONE);
                        getLbAktuell().setVisibility(View.GONE);
                   }
                }else{
                    if (getBasemodel().getBean().isNew()) {
                        getTvNummer().setEnabled(true);
                        getTvNummer().setFocusable(true);
                        getLbStichtag().setVisibility(View.GONE);
                        getTvStichtag().setVisibility(View.GONE);
                        getTvAktuell().setVisibility(View.GONE);
                        getLbAktuell().setVisibility(View.GONE);
                        getTvProcente().setVisibility(View.GONE);
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
