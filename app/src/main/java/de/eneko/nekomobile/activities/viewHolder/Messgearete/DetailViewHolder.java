package de.eneko.nekomobile.activities.viewHolder.Messgearete;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;
import com.notbytes.barcode_reader.BarcodeReaderActivity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.detail.Messgeraete.MessgaeretAustauschActivity;
import de.eneko.nekomobile.activities.models.MessgeraetModel;
import de.eneko.nekomobile.beans.hlpta.FunkCheck_Austauschgrund;
import de.eneko.nekomobile.beans.hlpta.FunkModel;
import de.eneko.nekomobile.beans.hlpta.ZaehlerModel;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.controllers.Dict;
import de.eneko.nekomobile.controllers.FileHandler;
import de.eneko.nekomobile.controllers.PhotoHandler;
import de.eneko.nekomobile.framework.BarcodeHelper;
import de.eneko.nekomobile.framework.FormatHelper;

public class DetailViewHolder extends MessgeraetBaseViewHolder {
    protected ArrayAdapter<ZaehlerModel> spNewModelAdapter = null;
    protected ArrayAdapter<FunkModel> spNewFunkModelAdapter = null;
    protected ArrayAdapter<FunkCheck_Austauschgrund> spAustauschGrundAdapter = null;


    public DetailViewHolder(View pView, MessgeraetModel pMessgeraet, Activity pActivity){
        super(pView, pMessgeraet, pActivity );
    }


    @Override
    public void updateView() {
        super.updateView();
        setTvRaum(getActivity().findViewById(R.id.tvRaum));
        setActvRaum(getActivity().findViewById(R.id.actvRaum));
        setTvNummer(getActivity().findViewById(R.id.tvNummer));
        setTvLetzterWert(getActivity().findViewById(R.id.tvLetzterWert));
        setTvModel(getActivity().findViewById(R.id.tvModel));
        setLbNummer(getActivity().findViewById(R.id.lbNummer));
        setLbRaum(getActivity().findViewById(R.id.lbRaum));
        setLbNewModel(getActivity().findViewById(R.id.lbNewModel));
        setLbNewFunkModel(getActivity().findViewById(R.id.lbNewFunkModel));
        setLbNewNummer(getActivity().findViewById(R.id.lbNewNummer));
        setLbNewFunkNummer(getActivity().findViewById(R.id.lbNewFunkNummer));
        setLbAustauschGrund(getActivity().findViewById(R.id.lbAktuell));

        setSpNewModel(getActivity().findViewById(R.id.spNewModel));
        setSpNewFunkModel(getActivity().findViewById(R.id.spNewFunkModel));
        setSpAustauschgrund(getActivity().findViewById(R.id.spAustauschgrund));
        setTvNewNummer(getActivity().findViewById(R.id.etNewNummer));
        setTvNewFunkNummer(getActivity().findViewById(R.id.etNewFunkNummer));

        setIvBarcodeNewModel(getActivity().findViewById(R.id.ivBarcodeNewModel));
        setIvBarcodeNewFunkModel(getActivity().findViewById(R.id.ivBarcodeNewFunkModel));
        setIvBarcodeNewNummer(getActivity().findViewById(R.id.ivBarcodeNewNummer));
        setIvBarcodeNewFunkNummer(getActivity().findViewById(R.id.ivBarcodeNewFunkNummer));

        setCbDefekt(getActivity().findViewById(R.id.cbDefekt));
        setLbBemerkung(getActivity().findViewById(R.id.lbBemerkung));
        setEtBemerkung(getActivity().findViewById(R.id.etBemerkung));
        setIvSpechToText(getActivity().findViewById(R.id.ivSpechToText));

        setLbStartwert(getActivity().findViewById(R.id.lbStartwert));
        setLbProcente(getActivity().findViewById(R.id.lbProcente));
        setTvStartwert(getActivity().findViewById(R.id.tvStartwert));
        setTvProcente(getActivity().findViewById(R.id.tvProcente));

        if (getActvRaum() != null) getActvRaum().setAdapter(
                new ArrayAdapter<String>(mActivity,android.R.layout.simple_list_item_1,
                        mActivity.getResources().getStringArray(R.array.raum_list)
                )
        );

        if (getAcUnDoneGrund() != null) getAcUnDoneGrund().setAdapter(
                new ArrayAdapter<String>(mActivity,android.R.layout.simple_list_item_1,
                        mActivity.getResources().getStringArray(R.array.kein_austausch_grund)
                )
        );



        if (getSpNewModel() != null) {
            spNewModelAdapter = new ArrayAdapter<ZaehlerModel>(mActivity,
                    R.layout.spinner_textview,
                    Dict.getInstance().getZaehlerModels().stream()
                            .filter( r -> r.getAustauschmodel() && (r.getArt().equals(getBasemodel().getBean().getArt()) || r.getArt().equals("ALL") ) )
                            .sorted(Comparator.comparing(ZaehlerModel::getSortfield))
                            .collect(Collectors.toList())

                    ) {
                @Override
                public View getDropDownView(final int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = new TextView(mActivity);
                    }
                    ZaehlerModel obj = getItem(position);
                    TextView item = (TextView) convertView;
                    item.setTextSize(18);
                    item.setBackgroundResource(R.drawable.textview_border);
                    item.setPadding(10,5,10,5);
                    if(obj.isUse() > 0 && !obj.getBezeichnung().equals("empty")){
                        item.setText(obj.getBezeichnung() + " (" + obj.isUse() + ")");
                        int text_color = mActivity.getResources().getColor(R.color.blue,getActivity().getTheme());
                        item.setTextColor(text_color);
                        item.setTextSize(24);
                        item.setTypeface(null, Typeface.BOLD);
                    }else{
                        item.setText(obj.getBezeichnung());
                        int text_color = mActivity.getResources().getColor(R.color.black,getActivity().getTheme());
                        item.setTextSize(18);
                        item.setTextColor(text_color);
                        item.setTypeface(null, Typeface.NORMAL);
                    }

                    final TextView finalItem = item;
                    item.post(new Runnable() {
                        @Override
                        public void run() {
                            finalItem.setSingleLine(false);
                        }
                    });
                    return item;
                }



                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    //Extrahieren der NoteBean zum nutzen der Werte
                    ZaehlerModel obj = getItem(position);
                    if(convertView == null) {
                        LayoutInflater inflater = (LayoutInflater) mActivity
                                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        convertView = inflater.inflate(R.layout.spinner_textview, parent, false);
                        TextView tv = convertView.findViewById(R.id.multulineSpinnerTextview);

                        if(obj.getBezeichnung().startsWith("Super")){
                            tv.setTextColor(158);
                        }

                        tv.setText(obj.getBezeichnung());
                    }
                    convertView.setTag(obj);
                    return convertView;
                }
            };
            spNewModelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            getSpNewModel().setAdapter(spNewModelAdapter);
        }
        if (getSpNewFunkModel() != null) {
            spNewFunkModelAdapter = new ArrayAdapter<FunkModel>(mActivity, android.R.layout.simple_spinner_item, Dict.getInstance().getFunkModels());
            spNewFunkModelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            getSpNewFunkModel().setAdapter(spNewFunkModelAdapter);
        }
        if (getSpAustauschgrund() != null) {
            spAustauschGrundAdapter = new ArrayAdapter<FunkCheck_Austauschgrund>(mActivity, android.R.layout.simple_spinner_item, Dict.getInstance().getFunkAustauschGrunds());
            spAustauschGrundAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            getSpAustauschgrund().setAdapter(spAustauschGrundAdapter);
        }

        getIvBarcodeNewModel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                Intent launchIntent = BarcodeReaderActivity.getLaunchIntent(v.getContext(), true, false);
                mActivity.startActivityForResult(launchIntent, BT_BARCODE_NEW_MODEL);
            }
        });
        getIvBarcodeNewNummer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                Intent launchIntent = BarcodeReaderActivity.getLaunchIntent(v.getContext(), true, false);
                mActivity.startActivityForResult(launchIntent, BT_BARCODE_NEW_NUMMER);
            }
        });
        getIvBarcodeNewFunkModel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                Intent launchIntent = BarcodeReaderActivity.getLaunchIntent(v.getContext(), true, false);
                mActivity.startActivityForResult(launchIntent, BT_BARCODE_NEW_FUNKMODEL);
            }
        });
        getIvBarcodeNewFunkNummer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                Intent launchIntent = BarcodeReaderActivity.getLaunchIntent(v.getContext(), true, false);
                mActivity.startActivityForResult(launchIntent, BT_BARCODE_NEW_FUNKNUMMER);
            }
        });
        getIvSpechToText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                Intent launchIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                launchIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                launchIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.GERMAN);

                if (launchIntent.resolveActivity(mActivity.getPackageManager()) != null) {
                    mActivity.startActivityForResult(launchIntent, BT_SPEAKER);
                }
            }
        });
       loadData();
    }




    @Override
    protected void loadData(){
        if (getTvModel() != null) getTvModel().setText(new Object() {
            @Override
            public String toString() {
               ZaehlerModel mObj = Dict.getInstance().getZaehlerModel(getBasemodel().getModel());
               return mObj != null ? mObj.getBezeichnung() : "";
            }
        }.toString());

        if (getActvRaum() != null) getActvRaum().setText(getBasemodel().getRaum());
        if (getAcUnDoneGrund() != null) getAcUnDoneGrund().setText(getBasemodel().getUnDoneGrundGrund());
        if (getTvNummer() != null) getTvNummer().setText(getBasemodel().getBean().getNummer());

        if (getTvLetzterWert() != null) getTvLetzterWert().setText(getBasemodel().getLetzterWertText());
        if (getEtBemerkung() != null) getEtBemerkung().setText(getBasemodel().getBemerkung());
        if (getLbAktuell() != null) getLbAktuell().setText("aktuell");
        if (getLbStichtag() != null) getLbStichtag().setText("stichtag");
        if (getTvAktuell() != null) getTvAktuell().setText(getBasemodel().getAktuellValue() == -1.0 ? "" : FormatHelper.formatDisplayDouble(getBasemodel().getAktuellValue()));
        if (getTvStichtag() != null) getTvStichtag().setText(getBasemodel().getStichtagValue() == -1.0 ? "" : FormatHelper.formatDisplayDouble(getBasemodel().getStichtagValue()));
        if (getTvStartwert() != null) getTvStartwert().setText(getBasemodel().getStartWert() == -1.0 ? "" : FormatHelper.formatDisplayDouble(getBasemodel().getStartWert()));
        if (getTvProcente() != null) getTvProcente().setText(getBasemodel().getProcent() == -1.0 ? "" : FormatHelper.formatDisplayDouble(getBasemodel().getProcent()));

        if (getCbDefekt() != null) getCbDefekt().setChecked(getBasemodel().getDefekt());

        if (getSpNewModel() != null && getBasemodel().getZielmodel() > 0) {
            getSpNewModel().setSelection(
                       spNewModelAdapter.getPosition(Dict.getInstance().getZaehlerModel(getBasemodel().getZielmodel()))
            );
        }

        if (getSpNewModel() != null) {
            getSpNewModel().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                   ZaehlerModel zm = spNewModelAdapter.getItem(position);
                    if (spNewModelAdapter.getItem(position).getFunkintegriert()) {
                        getSpNewFunkModel().setSelection(
//                                spNewFunkModelAdapter.getPosition(Dict.getInstance().getFunkModel(Dict.getInstance().getZaehlerModel(position).getFunkadapter()))
                                spNewFunkModelAdapter.getPosition(Dict.getInstance().getFunkModel(spNewModelAdapter.getItem(position).getFunkadapter()))

                        );
                    } else {
                        getSpNewFunkModel().setSelection(
                               spNewFunkModelAdapter.getPosition(Dict.getInstance().getFunkModel("X"))
                            );
                    };
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }


        if (getSpAustauschgrund() != null) {
            getSpAustauschgrund().setSelection(
                    spAustauschGrundAdapter.getPosition(Dict.getInstance().getFunkAustauschGrund(getBasemodel().getAustauschGrund()))
            );
        }


        if (getTvNewNummer() != null) getTvNewNummer().setText(getBasemodel().getNeueNummer());
        getTvNewNummer().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("") &&  getSpAustauschgrund() != null && getSpAustauschgrund().getSelectedItemPosition() == 0) {
                    getSpAustauschgrund().setSelection(
                            spAustauschGrundAdapter.getPosition(Dict.getInstance().getFunkAustauschGrund("STA"))
                    );
                }
            }
        });

        if (getSpNewFunkModel() != null && !getBasemodel().getNeuesFunkModel().equals("")) {
            getSpNewFunkModel().setSelection(
                    spNewFunkModelAdapter.getPosition(Dict.getInstance().getFunkModel(getBasemodel().getNeuesFunkModel()))
            );
        }
        if (getTvNewFunkNummer() != null) getTvNewFunkNummer().setText(getBasemodel().getNeueFunkNummer());
        if (getEtBemerkung() != null) getEtBemerkung().setText(getBasemodel().getBemerkung());


    }

    @Override
    protected void createLayout() {
}

    public ZaehlerModel getZaehlerModelById(Integer pId){
        List<ZaehlerModel> q = Dict.getInstance().getZaehlerModels()
                .stream()
                .filter(r -> r.getId().equals(pId))
                .collect(Collectors.toList());
        return q.size() > 0 ? q.get(0): null ;
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean safe = false ;

        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(mActivity, "error in  scanning", Toast.LENGTH_SHORT).show();
            return;
        }

        if (requestCode == DetailViewHolder.BT_BARCODE_NEW_MODEL && data != null ) {
            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);
            BarcodeHelper.ReturnCode returnCode = new BarcodeHelper(barcode.displayValue.toString()).getReturnCode();
            if (!returnCode.getGeraetenummer().equals("")){
                getTvNewNummer().setText(returnCode.getGeraetenummer());
                safe = true;
            }
            if (returnCode.getZaehlerModel() != null){
                getSpNewModel().setSelection(
                    spNewModelAdapter.getPosition(Dict.getInstance().getZaehlerModel(returnCode.getZaehlerModel().getId()))
                );
             safe = true;
            }
        }

        if (requestCode == DetailViewHolder.BT_BARCODE_NEW_NUMMER && data != null ) {
            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);
            BarcodeHelper.ReturnCode returnCode = new BarcodeHelper(barcode.displayValue.toString()).getReturnCode();
            if (!returnCode.getGeraetenummer().equals("")){
                ZaehlerModel zielmodel = this.getZaehlerModelById(this.getBasemodel().getZielmodel());
                if (zielmodel != null && zielmodel.getmIsQundisSimpleBarcode())
                    { if (returnCode.getGeraetenummer().length() == 10)
                        {
                            //validate interleaved2of5
                            char[] chars = returnCode.getGeraetenummer().toCharArray();
                            int tempVal = Character.getNumericValue(chars[0])*3;
                                tempVal   += Character.getNumericValue(chars[1])*1;
                                tempVal   += Character.getNumericValue(chars[2])*3;
                                tempVal   += Character.getNumericValue(chars[3])*1;
                                tempVal   += Character.getNumericValue(chars[4])*3;
                                tempVal   += Character.getNumericValue(chars[5])*1;
                                tempVal   += Character.getNumericValue(chars[6])*3;
                                tempVal   += Character.getNumericValue(chars[7])*1;
                                tempVal   += Character.getNumericValue(chars[8])*3;
                            tempVal = 10-tempVal % 10;
                            if (tempVal == Character.getNumericValue(chars[9]))
                            {
                                getTvNewNummer().setText(returnCode.getGeraetenummer().substring(1,9));
                            }else
                            {
                                getTvNewNummer().setText("");
                            }

                        }
                    }else{
                        getTvNewNummer().setText(returnCode.getGeraetenummer());
                    }
                safe = true;
            }
        }

        if (requestCode == DetailViewHolder.BT_BARCODE_NEW_FUNKMODEL && data != null ) {
            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);
            BarcodeHelper.ReturnCode returnCode = new BarcodeHelper(barcode.displayValue.toString()).getReturnCode();
            if (!returnCode.getGeraetenummer().equals("")){
                getTvNewFunkNummer().setText(returnCode.getGeraetenummer());
            }
            if (returnCode.getFunkModel() != null) {
                if (returnCode.getZaehlerModel() != null) {
                    getSpNewModel().setSelection(
                            spNewFunkModelAdapter.getPosition(Dict.getInstance().getFunkModel(returnCode.getFunkModel().getId()))
                    );
                }
            }
            safe = true;

        }

        if (requestCode == DetailViewHolder.BT_BARCODE_NEW_FUNKNUMMER && data != null ) {
            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);
            BarcodeHelper.ReturnCode returnCode = new BarcodeHelper(barcode.displayValue.toString()).getReturnCode();
            if (!returnCode.getGeraetenummer().equals("")){
                ZaehlerModel zielmodel = this.getZaehlerModelById(this.getBasemodel().getZielmodel());
                if (zielmodel != null && zielmodel.getmIsQundisFunkSimpleBarcode() && returnCode.getGeraetenummer().length() == 10)
                {
                    getTvNewFunkNummer().setText(returnCode.getGeraetenummer().substring(1,9));
                }else{
                    getTvNewFunkNummer().setText(returnCode.getGeraetenummer());
                }
                safe = true;
            }
        }
//

        if (requestCode == DetailViewHolder.BT_SPEAKER && data != null ) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            getBasemodel().setBemerkung(! getEtBemerkung().getText().equals("") ? getEtBemerkung().getText() + "\n" + result.get(0): result.get(0));
            safe = true;
        }
        if (safe) {
            save();
            CurrentObjectNavigation.getInstance().setMessgeraet(getBasemodel().getBean());
            Intent intent = new Intent(getActivity(), MessgaeretAustauschActivity.class);
            getActivity().startActivity(intent);
        }

    }

    @Override
    public void setDataToModel() {

        // übergabe einfacher Werte
        getBasemodel().setRaum(getActvRaum().getText().toString());
        getBasemodel().setUnDoneGrundGrund(getAcUnDoneGrund().getText().toString());
        getBasemodel().setBemerkung(getEtBemerkung().getText().toString());
        getBasemodel().setDefekt(getCbDefekt().isChecked());
        getBasemodel().setNeueNummer(getTvNewNummer().getText().toString());
        getBasemodel().setNeueFunkNummer(getTvNewFunkNummer().getText().toString());
//        getBasemodel().setProcent(Double.parseDouble(getTvNewNummer().getText().toString()));
//        getBasemodel().setStartWert(Double.parseDouble(getTvStartwert().getText().toString()));



        // Übergabe convertierter Werte
        ZaehlerModel zaehlerModel = (ZaehlerModel) getSpNewModel().getSelectedItem();
        getBasemodel().setZielmodel(zaehlerModel.getId());

        FunkModel funkModel = (FunkModel) getSpNewFunkModel().getSelectedItem();
        if(funkModel != null) getBasemodel().setNeuesFunkModel(funkModel.getId());

        FunkCheck_Austauschgrund austauschId = (FunkCheck_Austauschgrund) getSpAustauschgrund().getSelectedItem();
        if(austauschId != null)  getBasemodel().setAustauschGrund(austauschId.getId());

        String valueString = getTvAktuell().getText().toString().replace(".","").replace(",",".");
        if(!valueString.equals("")) getBasemodel().setAktuellValue(Double.parseDouble(valueString));

        valueString = getTvStichtag().getText().toString().replace(".","").replace(",",".");
        if(!valueString.equals(""))getBasemodel().setStichtagValue(Double.parseDouble(valueString));

     }


    public void save() {
        setDataToModel();
        getBasemodel().save();
        PhotoHandler.getInstance().compressFiles();
        FileHandler.getInstance().saveFile(getActivity());
    }





}
