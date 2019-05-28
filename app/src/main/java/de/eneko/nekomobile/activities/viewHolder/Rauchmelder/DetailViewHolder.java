package de.eneko.nekomobile.activities.viewHolder.Rauchmelder;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;
import com.notbytes.barcode_reader.BarcodeReaderActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.detail.Rwm.RwmActivity;
import de.eneko.nekomobile.activities.models.RauchmelderModel;
import de.eneko.nekomobile.controllers.FileHandler;
import de.eneko.nekomobile.framework.KeyedValue;

public class DetailViewHolder extends RwmBaseViewHolder {
    public static final int RWM_ACTIVITY_REQUEST_BT_1 = 1208;
    public static final int RWM_ACTIVITY_REQUEST_BT_2 = 1209;
    public static final int RWM_ACTIVITY_REQUEST_BT_3 = 1210;

    public DetailViewHolder(View pView, RauchmelderModel pRauchmelder, Activity pActivity){
        super(pView, pRauchmelder, pActivity );
    }
    @Override
    public RwmActivity getActivity() {
        return (RwmActivity) mActivity;
    }

//    public Rauchmelder getBean() {
//        return getBasemodel().getBean();
//    }

    @Override
    public void updateView() {
        spModele = mActivity.findViewById(R.id.spModel);
        etNummer = mActivity.findViewById(R.id.tvNummer);
        tvRaum = mActivity.findViewById(R.id.actvRaum);
        etBemerkungen = mActivity.findViewById(R.id.etBemerkung);
        spAustauschgrunde = mActivity.findViewById(R.id.spAustauschgrund);
        etNeueNummer = mActivity.findViewById(R.id.tvNewNummer);
        ivBarcode1 = mActivity.findViewById(R.id.imageViewBarcode1);
        ivBarcode2 = mActivity.findViewById(R.id.imageViewBarcode2);
        lbModel = mActivity.findViewById(R.id.lbModel);
        lbAustauschgrund = mActivity.findViewById(R.id.lbAustauschgrund);
        lbBemerkung = mActivity.findViewById(R.id.lbBemerkung);
        lbNewNummer = mActivity.findViewById(R.id.lbNewNummer);
        lbNummer = mActivity.findViewById(R.id.lbNummer);
        lbRaum = mActivity.findViewById(R.id.lbRaum);
        ivSpeaker = mActivity.findViewById(R.id.imageSpechToText);




        //region AutoCompleteTextView
        ArrayAdapter<String> adapter;
        String[] raumArray = mActivity.getResources().getStringArray(R.array.raum_list);
        adapter = new ArrayAdapter<String>(mActivity,android.R.layout.simple_list_item_1,raumArray);
        tvRaum.setAdapter(adapter);
// endregion AutoCompleteTextView

        //region ModeleSpinner
        String[] rwmmodele = mActivity.getResources().getStringArray(R.array.rwm_modele);
        List<KeyedValue> spinnerList = new ArrayList<KeyedValue>();

        for (String strTemp : rwmmodele){
            String[] _line = strTemp.split(";");
            spinnerList.add(new KeyedValue(Integer.parseInt(_line[0]),_line[1]));
        }

        /* Set your ArrayAdapter with the StringWithTag, and when each entry is shown in the Spinner, .toString() is called. */
        spinnerAdapter = new ArrayAdapter<KeyedValue>(mActivity, android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spModele.setAdapter(spinnerAdapter);
// endregion ModeleSpinner

        //region AustauschgrundSpinner
        String[] austauschgrunde = mActivity.getResources().getStringArray(R.array.rwm_Austauschgrund);
        List<KeyedValue> austauschgrundeList = new ArrayList<KeyedValue>();

        for (String strTemp : austauschgrunde){
            String[] _line = strTemp.split(";");
            austauschgrundeList.add(new KeyedValue(_line[0],_line[1]));
        }

        /* Set your ArrayAdapter with the StringWithTag, and when each entry is shown in the Spinner, .toString() is called. */
        austauschgrundeAdapter = new ArrayAdapter<KeyedValue>(mActivity, android.R.layout.simple_spinner_item, austauschgrundeList);
        austauschgrundeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spAustauschgrunde.setAdapter(austauschgrundeAdapter);
// endregion ModeleSpinner


        ivBarcode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = BarcodeReaderActivity.getLaunchIntent(v.getContext(), true, false);
                mActivity.startActivityForResult(launchIntent, RWM_ACTIVITY_REQUEST_BT_1);
            }
        });

        ivBarcode2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = BarcodeReaderActivity.getLaunchIntent(v.getContext(), true, false);
                mActivity.startActivityForResult(launchIntent, RWM_ACTIVITY_REQUEST_BT_2);
            }
        });
        ivSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                launchIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                launchIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.GERMAN);

                if (launchIntent.resolveActivity(mActivity.getPackageManager()) != null) {
                    mActivity.startActivityForResult(launchIntent, RWM_ACTIVITY_REQUEST_BT_3);
                }
            }
        });
        loadData();
    }

    protected void loadData(){
        tvRaum.setText(getBasemodel().getRaum());
        etNummer.setText(getBasemodel().getNummer());
        etBemerkungen.setText(getBasemodel().getBemerkung());
        etNeueNummer.setText(getBasemodel().getNeueNummer());

        for(int i=0 ; i<spinnerAdapter.getCount() ; i++){
            KeyedValue obj = (KeyedValue) spinnerAdapter.getItem(i);
            if (obj.mKey.equals(getBasemodel().getModel())) {
                spModele.setSelection(i);
            }
        }

        for(int i=0 ; i<austauschgrundeAdapter.getCount() ; i++){
            KeyedValue obj = (KeyedValue) austauschgrundeAdapter.getItem(i);
            if (obj.mKey.equals(getBasemodel().getAustauschGrund())) {
                spAustauschgrunde.setSelection(i);
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(mActivity, "error in  scanning", Toast.LENGTH_SHORT).show();
            return;
        }

        if (requestCode == de.eneko.nekomobile.activities.viewHolder.Rauchmelder.DetailViewHolder.RWM_ACTIVITY_REQUEST_BT_1 && data != null ) {
            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);
            Toast.makeText(mActivity, barcode.rawValue, Toast.LENGTH_SHORT).show();
            getEtNummer().setText(barcode.rawValue);
        }

        if (requestCode == de.eneko.nekomobile.activities.viewHolder.Rauchmelder.DetailViewHolder.RWM_ACTIVITY_REQUEST_BT_2 && data != null ) {
            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);
            Toast.makeText(mActivity, barcode.rawValue, Toast.LENGTH_SHORT).show();
            getEtNeueNummer().setText(barcode.rawValue);
        }

        if (requestCode == de.eneko.nekomobile.activities.viewHolder.Rauchmelder.DetailViewHolder.RWM_ACTIVITY_REQUEST_BT_3 && data != null ) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            getEtBemerkungen().setText(! getEtBemerkungen().getText().equals("") ? getEtBemerkungen().getText() + "\n" + result.get(0): result.get(0));
        }



    }




    public void save(){
        if(getBasemodel().getBean().getNew().equals(true)){
            FileHandler.getInstance().getNutzerTodo().getRauchmelder().add(getBasemodel().getBean());
            getBasemodel().setNew(false);
        }

        getBasemodel().setRaum(tvRaum.getText().toString());
        getBasemodel().setNummer(etNummer.getText().toString());
        getBasemodel().setNeueNummer(etNeueNummer.getText().toString());
        getBasemodel().setBemerkung(etBemerkungen.getText().toString());

        Integer modelId = Integer.parseInt (((KeyedValue) spModele.getSelectedItem()).getKey().toString());
        getBasemodel().setModel(modelId);

        String austauschId = ((KeyedValue) spAustauschgrunde.getSelectedItem()).getKey().toString();
        getBasemodel().setAustauschGrund(austauschId);

        getBasemodel().setWithError(
                java.util.Arrays.asList(new String[]{"ST", "RE", "NV", "ZE", "DE"})
                        .contains(getBasemodel().getAustauschGrund()));

        getBasemodel().save();
    }


}
