package de.eneko.nekomobile.activities.viewHolder.Messgearete;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
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
import de.eneko.nekomobile.activities.models.MessgeraetModel;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.beans.hlpta.FunkCheck_Austauschgrund;
import de.eneko.nekomobile.beans.hlpta.FunkModel;
import de.eneko.nekomobile.beans.hlpta.ZaehlerModel;
import de.eneko.nekomobile.controllers.Dict;
import de.eneko.nekomobile.framework.BarcodeHelper;
import de.eneko.nekomobile.framework.FormatHelper;
import de.eneko.nekomobile.framework.KeyedValue;

public class DetailViewHolder extends MessgeraetBaseViewHolder {
    protected ArrayAdapter<ZaehlerModel> spNewModelAdapter = null;
    protected ArrayAdapter<FunkModel> spNewFunkModelAdapter = null;
    protected ArrayAdapter<FunkCheck_Austauschgrund> spAustauschGrundAdapter = null;


    public DetailViewHolder(View pView, MessgeraetModel pMessgeraet, Activity pActivity){
        super(pView, pMessgeraet, pActivity );
    }
//    @Override
//    public MessgeraetBaseActivity getActivity() {
//        return (MessgeraetBaseActivity) mActivity;
//    }
//    @Override
//    public Messgeraet getBean() {
//        return (Messgeraet) super.getBean();
//    }



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
                            .filter( r -> r.getAustauschmodel() && (r.getArt().equals(getBean().getArt())|| r.getArt().equals("ALL") ) )
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
                Intent launchIntent = BarcodeReaderActivity.getLaunchIntent(v.getContext(), true, false);
                mActivity.startActivityForResult(launchIntent, BT_BARCODE_NEW_MODEL);
            }
        });
        getIvBarcodeNewNummer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDataToModel();
                Intent launchIntent = BarcodeReaderActivity.getLaunchIntent(v.getContext(), true, false);
                mActivity.startActivityForResult(launchIntent, BT_BARCODE_NEW_NUMMER);
            }
        });
        getIvBarcodeNewFunkModel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = BarcodeReaderActivity.getLaunchIntent(v.getContext(), true, false);
                mActivity.startActivityForResult(launchIntent, BT_BARCODE_NEW_FUNKMODEL);
            }
        });
        getIvBarcodeNewFunkNummer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = BarcodeReaderActivity.getLaunchIntent(v.getContext(), true, false);
                mActivity.startActivityForResult(launchIntent, BT_BARCODE_NEW_FUNKNUMMER);
            }
        });

        getIvSpechToText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    public MessgeraetModel getBasemodel() {
        return (MessgeraetModel) super.getBasemodel();
    }

    protected void loadData(){
        if (getTvModel() != null) getTvModel().setText(new Object() {
            @Override
            public String toString() {
               ZaehlerModel mObj = Dict.getInstance().getZaehlerModel(getBasemodel().getZielmodel());
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
        if (getTvAktuell() != null) getTvAktuell().setText(getBasemodel().getAktuellValue() == -1.0 ? "" : FormatHelper.formatDouble(getBean().getAktuellValue()));
        if (getTvStichtag() != null) getTvStichtag().setText(getBasemodel().getStichtagValue() == -1.0 ? "" : FormatHelper.formatDouble(getBean().getStichtagValue()));
        if (getCbDefekt() != null) getCbDefekt().setChecked(getBasemodel().getDefekt());

        if (getSpNewModel() != null && getBean().getZielmodel() > 0) {
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


        if (getSpNewFunkModel() != null && !getBean().getNeuesFunkModel().equals("")) {
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
                getBean().setNeueNummer(returnCode.getGeraetenummer());
                getTvNewNummer().setText(returnCode.getGeraetenummer());
            }
            if (returnCode.getZaehlerModel() != null){
                getBean().setZielmodel(returnCode.getZaehlerModel().getId());
                getSpNewModel().setSelection(
                        spNewModelAdapter.getPosition(returnCode.getZaehlerModel())
                );
            }
        }

        if (requestCode == DetailViewHolder.BT_BARCODE_NEW_NUMMER && data != null ) {
            loadData();
            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);
            BarcodeHelper.ReturnCode returnCode = new BarcodeHelper(barcode.displayValue.toString()).getReturnCode();
            if (!returnCode.getGeraetenummer().equals("")){
                getBasemodel().setNeueNummer(returnCode.getGeraetenummer());
                getTvNewNummer().setText(returnCode.getGeraetenummer());
            }

        }

        if (requestCode == DetailViewHolder.BT_BARCODE_NEW_FUNKMODEL && data != null ) {
            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);
            BarcodeHelper.ReturnCode returnCode = new BarcodeHelper(barcode.displayValue.toString()).getReturnCode();
            if (!returnCode.getGeraetenummer().equals("")){
                getBean().setNeueFunkNummer(returnCode.getGeraetenummer());
                getTvNewFunkNummer().setText(returnCode.getGeraetenummer());
            }
            if (returnCode.getFunkModel() != null){
                getBean().setNeuesFunkModel(returnCode.getFunkModel().getId());
                getSpNewFunkModel().setSelection(
                        spNewFunkModelAdapter.getPosition(returnCode.getFunkModel())
                );
            }
        }

        if (requestCode == DetailViewHolder.BT_BARCODE_NEW_FUNKNUMMER && data != null ) {
            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);
            BarcodeHelper.ReturnCode returnCode = new BarcodeHelper(barcode.displayValue.toString()).getReturnCode();
            if (!returnCode.getGeraetenummer().equals("")){
                getBean().setNeueFunkNummer(returnCode.getGeraetenummer());
                getTvNewFunkNummer().setText(returnCode.getGeraetenummer());
            }
        }
//

        if (requestCode == DetailViewHolder.BT_SPEAKER && data != null ) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            getBean().setBemerkung(! getEtBemerkung().getText().equals("") ? getEtBemerkung().getText() + "\n" + result.get(0): result.get(0));
        }



    }

    public void setDataToModel() {
//        getBasemodel().setRaum(tvRaum.getText().toString());
//        getBasemodel().setNummer(etNummer.getText().toString());
//        getBasemodel().setNeueNummer(etNeueNummer.getText().toString());
//        getBasemodel().setBemerkung(etBemerkungen.getText().toString());
//        getBasemodel().setmDatenAufnahmeFremd(getCbDatenAufnahmeFremd().isChecked());
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


        getBasemodel().setRaum(getActvRaum().getText().toString());
        FunkCheck_Austauschgrund austauschId = (FunkCheck_Austauschgrund) getSpAustauschgrund().getSelectedItem();

        //String austauschId =  ((KeyedValue) getSpAustauschgrund().getSelectedItem()).getKey().toString();
        getBasemodel().setAustauschGrund(austauschId.getId());




//        if (getAcUnDoneGrund() != null) getAcUnDoneGrund().setText(getBasemodel().getUnDoneGrundGrund());
//        if (getTvNummer() != null) getTvNummer().setText(getBasemodel().getBean().getNummer());
//
//        if (getTvLetzterWert() != null) getTvLetzterWert().setText(getBasemodel().getLetzterWertText());
//        if (getEtBemerkung() != null) getEtBemerkung().setText(getBasemodel().getBemerkung());
//        if (getLbAktuell() != null) getLbAktuell().setText("aktuell");
//        if (getLbStichtag() != null) getLbStichtag().setText("stichtag");
//        if (getTvAktuell() != null) getTvAktuell().setText(getBasemodel().getAktuellValue() == -1.0 ? "" : FormatHelper.formatDouble(getBean().getAktuellValue()));
//        if (getTvStichtag() != null) getTvStichtag().setText(getBasemodel().getStichtagValue() == -1.0 ? "" : FormatHelper.formatDouble(getBean().getStichtagValue()));
//        if (getCbDefekt() != null) getCbDefekt().setChecked(getBasemodel().getDefekt());
//
//        if (getSpNewModel() != null && getBean().getZielmodel() > 0) {
//            getSpNewModel().setSelection(
//                    spNewModelAdapter.getPosition(Dict.getInstance().getZaehlerModel(getBasemodel().getZielmodel()))
//            );
//        }
//
//
//
//
//        if (getTvNewNummer() != null) getTvNewNummer().setText(getBasemodel().getNeueNummer());
//
//
//        if (getSpNewFunkModel() != null && !getBean().getNeuesFunkModel().equals("")) {
//            getSpNewFunkModel().setSelection(
//                    spNewFunkModelAdapter.getPosition(Dict.getInstance().getFunkModel(getBasemodel().getNeuesFunkModel()))
//            );
//        }
//        if (getTvNewFunkNummer() != null) getTvNewFunkNummer().setText(getBasemodel().getNeueFunkNummer());
//        if (getEtBemerkung() != null) getEtBemerkung().setText(getBasemodel().getBemerkung());
//
//

    }


    public void save() {

        getBasemodel().save();
    }


}
