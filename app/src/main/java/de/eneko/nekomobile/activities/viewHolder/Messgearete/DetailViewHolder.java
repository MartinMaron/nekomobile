package de.eneko.nekomobile.activities.viewHolder.Messgearete;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;
import com.notbytes.barcode_reader.BarcodeReaderActivity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Collectors;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.detail.Messgeraete.MessgaeretBewertungActivity;
import de.eneko.nekomobile.activities.models.MessgeraetModel;
import de.eneko.nekomobile.beans.hlpta.FunkCheck_Austauschgrund;
import de.eneko.nekomobile.beans.hlpta.FunkModel;
import de.eneko.nekomobile.beans.hlpta.ZaehlerModel;
import de.eneko.nekomobile.controllers.Dict;
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
        setIbBewertung(findViewById(R.id.ibBewertung));




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
                    android.R.layout.simple_spinner_item,
                    Dict.getInstance().getZaehlerModels().stream()
                            .filter( r -> r.getAustauschmodel() && (r.getArt().equals(getBasemodel().getBean().getArt()) || r.getArt().equals("ALL") ) )
                            .sorted(Comparator.comparing(ZaehlerModel::getBezeichnung))
                            .collect(Collectors.toList())
                    );
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

        getIbBewertung().setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                save();
                Intent intent = new Intent(getActivity(), MessgaeretBewertungActivity.class);
                getActivity().startActivity(intent);
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
        if (getCbDefekt() != null) getCbDefekt().setChecked(getBasemodel().getDefekt());

        if (getSpNewModel() != null && getBasemodel().getZielmodel() > 0) {
            getSpNewModel().setSelection(
                       spNewModelAdapter.getPosition(Dict.getInstance().getZaehlerModel(getBasemodel().getZielmodel()))
            );
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(mActivity, "error in  scanning", Toast.LENGTH_SHORT).show();
            return;
        }

        if (requestCode == DetailViewHolder.BT_BARCODE_NEW_MODEL && data != null ) {
            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);
            BarcodeHelper.ReturnCode returnCode = new BarcodeHelper(barcode.displayValue.toString()).getReturnCode();
            if (!returnCode.getGeraetenummer().equals("")){
                getBasemodel().setNeueNummer(returnCode.getGeraetenummer());
            }
            if (returnCode.getZaehlerModel() != null){
                getBasemodel().setZielmodel(returnCode.getZaehlerModel().getId());
            }
            loadData();
        }

        if (requestCode == DetailViewHolder.BT_BARCODE_NEW_NUMMER && data != null ) {
            loadData();
            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);
            BarcodeHelper.ReturnCode returnCode = new BarcodeHelper(barcode.displayValue.toString()).getReturnCode();
            if (!returnCode.getGeraetenummer().equals("")){
                getBasemodel().setNeueNummer(returnCode.getGeraetenummer());
                loadData();
            }

        }

        if (requestCode == DetailViewHolder.BT_BARCODE_NEW_FUNKMODEL && data != null ) {
            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);
            BarcodeHelper.ReturnCode returnCode = new BarcodeHelper(barcode.displayValue.toString()).getReturnCode();
            if (!returnCode.getGeraetenummer().equals("")){
                getBasemodel().setNeueFunkNummer(returnCode.getGeraetenummer());
                getTvNewFunkNummer().setText(returnCode.getGeraetenummer());
            }
            if (returnCode.getFunkModel() != null){
                getBasemodel().setNeuesFunkModel(returnCode.getFunkModel().getId());
            }
            loadData();
        }

        if (requestCode == DetailViewHolder.BT_BARCODE_NEW_FUNKNUMMER && data != null ) {
            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);
            BarcodeHelper.ReturnCode returnCode = new BarcodeHelper(barcode.displayValue.toString()).getReturnCode();
            if (!returnCode.getGeraetenummer().equals("")){
                getBasemodel().setNeueFunkNummer(returnCode.getGeraetenummer());
                loadData();
            }
        }
//

        if (requestCode == DetailViewHolder.BT_SPEAKER && data != null ) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            getBasemodel().setBemerkung(! getEtBemerkung().getText().equals("") ? getEtBemerkung().getText() + "\n" + result.get(0): result.get(0));
            loadData();
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

        // Übergabe convertierter Werte
        ZaehlerModel zaehlerModel = (ZaehlerModel) getSpNewModel().getSelectedItem();
        getBasemodel().setZielmodel(zaehlerModel.getId());

        FunkModel funkModel = (FunkModel) getSpNewFunkModel().getSelectedItem();
        getBasemodel().setNeuesFunkModel(funkModel.getId());

        FunkCheck_Austauschgrund austauschId = (FunkCheck_Austauschgrund) getSpAustauschgrund().getSelectedItem();
        getBasemodel().setAustauschGrund(austauschId.getId());

        String valueString = getTvAktuell().getText().toString().replace(".","").replace(",",".");
        if(!valueString.equals("")) getBasemodel().setAktuellValue(Double.parseDouble(valueString));

        valueString = getTvStichtag().getText().toString().replace(".","").replace(",",".");
        if(!valueString.equals(""))getBasemodel().setStichtagValue(Double.parseDouble(valueString));

     }


    public void save() {
        setDataToModel();
        getBasemodel().save();
    }





}
