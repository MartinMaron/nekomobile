package de.eneko.nekomobile.activities.detail;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


//import com.google.android.gms.vision.barcode.Barcode;
//import com.notbytes.barcode_reader.BarcodeReaderActivity;
//import com.notbytes.barcode_reader.BarcodeReaderFragment;

import java.util.ArrayList;
import java.util.List;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.beans.Rauchwarnmelder;
import de.eneko.nekomobile.controllers.FileHandler;

public class RwmActivity extends AppCompatActivity{

    private static final int BARCODE_READER_ACTIVITY_REQUEST = 1208;

    Rauchwarnmelder rwm = null;

    Spinner spModele = null;
    EditText etNummer = null;
    AutoCompleteTextView tvRaum = null;
    EditText etBemerkungen = null;
    Spinner spAustauschgrunde = null;
    EditText etNeueNummer = null;
    ImageView ivBarcode1 = null;
    ImageView ivBarcode2 = null;
    ImageView ivSave = null;




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
        ivSave = findViewById(R.id.imageViewSave);

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
        ArrayAdapter<KeyedValue> spinnerAdapter = new ArrayAdapter<KeyedValue>(this, android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spModele.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                KeyedValue keyedValue = (KeyedValue) parent.getItemAtPosition(position);
                rwm.setModel(keyedValue.getValue());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                rwm.setModel("");
            }
        });
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
        ArrayAdapter<KeyedValue> austauschgrundeAdapter = new ArrayAdapter<KeyedValue>(this, android.R.layout.simple_spinner_item, austauschgrundeList);
        austauschgrundeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spAustauschgrunde.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                KeyedValue keyedValue = (KeyedValue) parent.getItemAtPosition(position);
                rwm.setAustauschGrund(keyedValue.getKey().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spAustauschgrunde.setAdapter(austauschgrundeAdapter);
// endregion ModeleSpinner


        ivBarcode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent launchIntent = BarcodeReaderActivity.getLaunchIntent(v.getContext(), true, false);
//                startActivityForResult(launchIntent, BARCODE_READER_ACTIVITY_REQUEST);
            }
        });

//        ivBarcode2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent launchIntent = BarcodeReaderActivity.getLaunchIntent(v.getContext(), true, false);
//                startActivityForResult(launchIntent, BARCODE_READER_ACTIVITY_REQUEST);
//            }
//        });



    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode != Activity.RESULT_OK) {
//            Toast.makeText(this, "error in  scanning", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (requestCode == BARCODE_READER_ACTIVITY_REQUEST_NUMBER && data != null) {
//            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);
//            Toast.makeText(this, barcode.rawValue, Toast.LENGTH_SHORT).show();
//            etNummer.setText(barcode.rawValue);
//        }
//
////        if (requestCode == BARCODE_READER_ACTIVITY_REQUEST_NEWNUMBER && data != null) {
////            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);
////            Toast.makeText(this, barcode.rawValue, Toast.LENGTH_SHORT).show();
////            etNeueNummer.setText(barcode.rawValue);
////        }
//
//    }



    @Override
    protected void onResume() {
        super.onResume();
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
