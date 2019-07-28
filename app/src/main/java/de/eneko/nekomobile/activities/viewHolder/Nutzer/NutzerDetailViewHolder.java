package de.eneko.nekomobile.activities.viewHolder.Nutzer;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.framework.KeyedValue;

public class NutzerDetailViewHolder extends NutzerBaseViewHolder {

    protected EditText etTelefon;
    protected EditText etNeuerNutzer;
    protected EditText etBemerkungen = null;


    public NutzerDetailViewHolder(View pView, Basemodel pModel) {
        super(pView, pModel);
    }
    public NutzerDetailViewHolder(View pView, Basemodel pModel, Activity pActivity) {
        super(pView, pModel,pActivity);
    }

   @Override
    public void updateView() {
        super.updateView();
        etTelefon = findViewById(R.id.etTelefon);
        etBemerkungen = findViewById(R.id.etBemerkung);
        etNeuerNutzer = findViewById(R.id.etNeuerNutzer);
        loadData();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(mActivity, "error in  scanning", Toast.LENGTH_SHORT).show();
            return;
        }

//        if (requestCode == de.eneko.nekomobile.activities.viewHolder.Nutzer.DetailViewHolder.RWM_ACTIVITY_REQUEST_BT_3 && data != null ) {
//            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//            getEtBemerkungen().setText(! getEtBemerkungen().getText().equals("") ? getEtBemerkungen().getText() + "\n" + result.get(0): result.get(0));
//        }



    }

    protected void loadData(){
        etBemerkungen.setText(getBasemodel().getBemerkung());
        etTelefon.setText(getBasemodel().getTelNummer());
        etNeuerNutzer.setText(getBasemodel().getRwmNeuerNutzer());
    }

    public void save(){

        getBasemodel().setRwmNeuerNutzer(etNeuerNutzer.getText().toString());
        getBasemodel().setTelNummer(etTelefon.getText().toString());
        getBasemodel().setBemerkung(etBemerkungen.getText().toString());
        getBasemodel().save();

//        getBasemodel().setRaum(tvRaum.getText().toString());
//        getBasemodel().setNummer(etNummer.getText().toString());
//        getBasemodel().setNeueNummer(etNeueNummer.getText().toString());
//        getBasemodel().setBemerkung(etBemerkungen.getText().toString());
//
//        Integer modelId = Integer.parseInt (((KeyedValue) spModele.getSelectedItem()).getKey().toString());
//        getBasemodel().setModel(modelId);
//
//        String austauschId = ((KeyedValue) spAustauschgrunde.getSelectedItem()).getKey().toString();
//        getBasemodel().setAustauschGrund(austauschId);
//
//        getBasemodel().setWithError(
//                java.util.Arrays.asList(new String[]{"ST", "RE", "NV", "ZE", "DE"})
//                        .contains(getBasemodel().getAustauschGrund()));
//
//        getBasemodel().save();
//
//        if(getBasemodel().getBean().getNew().equals(true)){
//            if (getBasemodel().getNummer().equals("")){
//                getBasemodel().getBean().getTodo().getRauchmelder().remove(getBasemodel().getBean());
//            };
//        }
    }

    // region properties

    public TextView getEtTelefon() {
        return etTelefon;
    }

    public void setEtTelefon(EditText etTelefon) {
        this.etTelefon = etTelefon;
    }

    public void setEtNeuerNutzer(EditText etNeuerNutzer) {
        this.etNeuerNutzer = etNeuerNutzer;
    }

    public TextView getEtNeuerNutzer() {
        return etNeuerNutzer;
    }

    // endregion

}
