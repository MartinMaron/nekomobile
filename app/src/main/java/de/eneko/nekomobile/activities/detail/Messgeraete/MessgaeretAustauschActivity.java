package de.eneko.nekomobile.activities.detail.Messgeraete;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.text.NumberFormat;
import java.util.Locale;

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

    protected static final String TAG = MessgaeretAustauschActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
  }




    @Override
    protected void createViewHolder(){
        Messgeraet obj = CurrentObjectNavigation.getInstance().getMessgeraet();

        viewHolder = new DetailViewHolder( null,obj.getBaseModel(), this){
            @Override
            public void save() {
                try{

                getBasemodel().setRaum(getActvRaum().getText().toString());

                if (getSpNewModel().getSelectedItem() != null)  getBasemodel().setZielmodel(((ZaehlerModel) getSpNewModel().getSelectedItem()).getId());
                getBasemodel().setAustauschGrund(((FunkCheck_Austauschgrund) getSpAustauschgrund().getSelectedItem()).getId());
                getBasemodel().setNeueNummer(getTvNewNummer().getText().toString());
                getBasemodel().setNeueFunkNummer(getTvNewFunkNummer().getText().toString());

                if (getBasemodel().getArt().equals("HKV") && getTvProcente() != null)  {
                    try{getBasemodel().setProcent(NumberFormat.getInstance(Locale.GERMANY).parse(getTvProcente().getText().toString()).doubleValue());
                    }catch (Exception e)
                    {}
                };

                if (!getBasemodel().getArt().equals("HKV") && getTvStartwert() != null) {
                    try{getBasemodel().setProcent(NumberFormat.getInstance(Locale.GERMANY).parse(getTvProcente().getText().toString()).doubleValue());
                    }catch (Exception e)
                    {}
                }

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


                getBasemodel().save();

                }catch (Exception e){
                    Exception mE = e;
                    Log.e(TAG,"Fehler: " + e.toString());
                    throw e;
                }
            }






            @Override
            protected void loadData(){
                super.loadData();
                createLayout();
            }

            @Override
            protected void createLayout() {

                if (getTvNummer() != null) getTvNummer().setFocusable(false);

                if (getTvProcente() != null) ((MessgaeretAustauschActivity) getActivity()).getCustomKeyboard().registerEditText(getTvProcente());
                if (getTvStartwert() != null) ((MessgaeretAustauschActivity) getActivity()).getCustomKeyboard().registerEditText(getTvStartwert());


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

                        getTvProcente().setVisibility(View.GONE);
                        getLbProcente().setVisibility(View.GONE);
                        getLbStartwert().setVisibility(View.GONE);
                        getTvStartwert().setVisibility(View.GONE);

                        getLbNewFunkNummer().setVisibility(View.GONE);
                        getTvNewFunkNummer().setVisibility(View.GONE);
                        getIvBarcodeNewFunkNummer().setVisibility(View.GONE);
                        getCbDefekt().setVisibility(View.VISIBLE);
                    }else{
                        getLbNewModel().setVisibility(View.GONE);
                        getSpNewModel().setVisibility(View.GONE);
                        getIvBarcodeNewModel().setVisibility(View.GONE);
                        getLbProcente().setVisibility(View.INVISIBLE);
                        getTvProcente().setVisibility(View.INVISIBLE);
                    }
                }

                if (getBasemodel().getBean().getTodo().getArt().equals(Dict.TODO_MONTAGE_HKV))
                {
                    getLbNewModel().setVisibility(View.VISIBLE);
                    getSpNewModel().setVisibility(View.VISIBLE);
                    getIvBarcodeNewModel().setVisibility(View.GONE);

                    getLbNewFunkModel().setVisibility(View.GONE);
                    getSpNewFunkModel().setVisibility(View.GONE);
                    getIvBarcodeNewFunkModel().setVisibility(View.GONE);

                    getLbStartwert().setVisibility(View.GONE);
                    getTvStartwert().setVisibility(View.GONE);

                    getLbNewFunkNummer().setVisibility(View.GONE);
                    getTvNewFunkNummer().setVisibility(View.GONE);
                    getIvBarcodeNewFunkNummer().setVisibility(View.GONE);
                    getCbDefekt().setVisibility(View.VISIBLE);
                }
            if (getBasemodel().getBean().getTodo().getArt().equals(Dict.TODO_MONTAGE_WMZ))
                {
                    getLbNewModel().setVisibility(View.VISIBLE);
                    getSpNewModel().setVisibility(View.VISIBLE);

                    getLbProcente().setVisibility(View.INVISIBLE);
                    getTvProcente().setVisibility(View.INVISIBLE);

                    getCbDefekt().setVisibility(View.VISIBLE);
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
