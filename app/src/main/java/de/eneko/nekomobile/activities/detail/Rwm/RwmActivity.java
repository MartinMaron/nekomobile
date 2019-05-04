package de.eneko.nekomobile.activities.detail.Rwm;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;
import com.notbytes.barcode_reader.BarcodeReaderActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.NutzerListActivity;
import de.eneko.nekomobile.activities.RauchmelderWartungListActivity;
import de.eneko.nekomobile.beans.Rauchwarnmelder;
import de.eneko.nekomobile.controllers.FileHandler;

public class RwmActivity extends AppCompatActivity{

    private static final int BARCODE_READER_ACTIVITY_REQUEST_BT_1 = 1208;
    private static final int BARCODE_READER_ACTIVITY_REQUEST_BT_2 = 1208;

    protected Rauchwarnmelder rwm = null;
    protected Spinner spModele = null;
    protected TextView lbModel = null;
    protected TextView lbNummer = null;
    protected TextView lbRaum = null;
    protected TextView lbBemerkung = null;
    protected TextView lbAustauschgrund = null;
    protected TextView lbNewNummer = null;
    protected EditText etNummer = null;
    protected AutoCompleteTextView tvRaum = null;
    protected EditText etBemerkungen = null;
    protected Spinner spAustauschgrunde = null;
    protected EditText etNeueNummer = null;
    protected ImageView ivBarcode1 = null;
    protected ImageView ivBarcode2 = null;

    protected ArrayAdapter<KeyedValue> spinnerAdapter = null;
    protected ArrayAdapter<KeyedValue> austauschgrundeAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rwm_wartung_detail);
        rwm = FileHandler.getInstance().getRauchwarnmelder();
        spModele = findViewById(R.id.spModel);
        etNummer = findViewById(R.id.tvNummer);
        tvRaum = findViewById(R.id.tvRaum);
        etBemerkungen = findViewById(R.id.etBemerkung);
        spAustauschgrunde = findViewById(R.id.spAustauschgrund);
        etNeueNummer = findViewById(R.id.tvNewNummer);
        ivBarcode1 = findViewById(R.id.imageViewBarcode1);
        ivBarcode2 = findViewById(R.id.imageViewBarcode2);
        lbModel = findViewById(R.id.lbModel);
        lbAustauschgrund = findViewById(R.id.lbAustauschgrund);
        lbBemerkung = findViewById(R.id.lbBemerkung);
        lbNewNummer = findViewById(R.id.lbNewNummer);
        lbNummer = findViewById(R.id.lbNummer);
        lbRaum = findViewById(R.id.lbRaum);


        //region AutoCompleteTextView
        ArrayAdapter<String> adapter;
        String[] raumArray = getResources().getStringArray(R.array.raum_list);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,raumArray);
        tvRaum.setAdapter(adapter);
// endregion AutoCompleteTextView

        //region ModeleSpinner
        String[] rwmmodele = getResources().getStringArray(R.array.rwm_modele);
        List<KeyedValue> spinnerList = new ArrayList<KeyedValue>();

        for (String strTemp : rwmmodele){
            String[] _line = strTemp.split(";");
            spinnerList.add(new KeyedValue(Integer.parseInt(_line[0]),_line[1]));
        }

        /* Set your ArrayAdapter with the StringWithTag, and when each entry is shown in the Spinner, .toString() is called. */
        spinnerAdapter = new ArrayAdapter<KeyedValue>(this, android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//        spModele.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//        {
////            @Override
////            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                KeyedValue keyedValue = (KeyedValue) parent.getItemAtPosition(position);
////                rwm.setModel(Integer.parseInt(keyedValue.getKey().toString()));
////            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                rwm.setModel(0);
//            }
//        });
        spModele.setAdapter(spinnerAdapter);
// endregion ModeleSpinner

        //region AustauschgrundSpinner
        String[] austauschgrunde = getResources().getStringArray(R.array.rwm_Austauschgrund);
        List<KeyedValue> austauschgrundeList = new ArrayList<KeyedValue>();

        for (String strTemp : austauschgrunde){
            String[] _line = strTemp.split(";");
            austauschgrundeList.add(new KeyedValue(_line[0],_line[1]));
        }

        /* Set your ArrayAdapter with the StringWithTag, and when each entry is shown in the Spinner, .toString() is called. */
        austauschgrundeAdapter = new ArrayAdapter<KeyedValue>(this, android.R.layout.simple_spinner_item, austauschgrundeList);
        austauschgrundeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//        spAustauschgrunde.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//        {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                KeyedValue keyedValue = (KeyedValue) parent.getItemAtPosition(position);
//                rwm.setAustauschGrund(keyedValue.getKey().toString());
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
        spAustauschgrunde.setAdapter(austauschgrundeAdapter);
// endregion ModeleSpinner


        ivBarcode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = BarcodeReaderActivity.getLaunchIntent(v.getContext(), true, false);
                startActivityForResult(launchIntent, BARCODE_READER_ACTIVITY_REQUEST_BT_1);
            }
        });

        ivBarcode2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = BarcodeReaderActivity.getLaunchIntent(v.getContext(), true, false);
                startActivityForResult(launchIntent, BARCODE_READER_ACTIVITY_REQUEST_BT_2);
            }
        });

        loadData();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(this, "error in  scanning", Toast.LENGTH_SHORT).show();
            return;
        }

        if (requestCode == BARCODE_READER_ACTIVITY_REQUEST_BT_1 && data != null ) {
            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);
            Toast.makeText(this, barcode.rawValue, Toast.LENGTH_SHORT).show();
            etNummer.setText(barcode.rawValue);
        }

        if (requestCode == BARCODE_READER_ACTIVITY_REQUEST_BT_2 && data != null ) {
            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);
            Toast.makeText(this, barcode.rawValue, Toast.LENGTH_SHORT).show();
            etNeueNummer.setText(barcode.rawValue);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rwm_wartung_detail_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_save:
                save();
                exit();
                return true;
        }
        return false;
    }

    protected void save(){
        if(rwm.getNew().equals(true)){
            FileHandler.getInstance().getNutzerTodo().getRauchmelder().add(rwm);
            rwm.setNew(false);
        }

        rwm.setRaum(tvRaum.getText().toString());
        rwm.setNummer(etNummer.getText().toString());
        rwm.setNeueNummer(etNeueNummer.getText().toString());
        rwm.setBemerkung(etBemerkungen.getText().toString());
        Integer modelId = Integer.parseInt (((KeyedValue) spModele.getSelectedItem()).getKey().toString());
        rwm.setModel(modelId);

        String austauschId = ((KeyedValue) spAustauschgrunde.getSelectedItem()).getKey().toString();
        rwm.setAustauschGrund(austauschId);

        rwm.setWithError(
                java.util.Arrays.asList(new String[]{"ST", "RE", "NV", "ZE", "DE"})
                        .contains(rwm.getAustauschGrund()));


    }

    protected void exit(){
        Intent intent = new Intent(this, RauchmelderWartungListActivity.class);
        startActivity(intent);
    }

    protected void loadData(){
        tvRaum.setText(rwm.getRaum());
        etNummer.setText(rwm.getNummer());
        etBemerkungen.setText(rwm.getBemerkung());
        etNeueNummer.setText(rwm.getNeueNummer());

        for(int i=0 ; i<spinnerAdapter.getCount() ; i++){
            KeyedValue obj = (KeyedValue) spinnerAdapter.getItem(i);
            if (obj.mKey.equals(rwm.getModel())) {
                spModele.setSelection(i);
            }
        }

        for(int i=0 ; i<austauschgrundeAdapter.getCount() ; i++){
            KeyedValue obj = (KeyedValue) austauschgrundeAdapter.getItem(i);
            if (obj.mKey.equals(rwm.getAustauschGrund())) {
                spAustauschgrunde.setSelection(i);
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    public void onBackPressed(){
        exit();
    }

    private static class KeyedValue {
        public String mValue;
        public Object mKey;

        public KeyedValue( Object key, String value) {
            this.mValue = value;
            this.mKey = key;
        }

        public String getValue() {
            return mValue;
        }

        public Object getKey() {
            return mKey;
        }

        @Override
        public String toString() {
            return mValue;
        }
    }



}
