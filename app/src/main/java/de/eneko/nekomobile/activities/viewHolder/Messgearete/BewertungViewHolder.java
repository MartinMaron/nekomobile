package de.eneko.nekomobile.activities.viewHolder.Messgearete;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.stream.Collectors;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.models.MessgeraetModel;
import de.eneko.nekomobile.activities.viewHolder.BaseViewHolder;
import de.eneko.nekomobile.beans.Messgeraet;

public class BewertungViewHolder extends BaseViewHolder {
    private TextView lbGrundparameter = null;
    private EditText etGrundparameter = null;
    private TextView lbReihenanordnung = null;
    private AutoCompleteTextView actvReihenanordnung = null;
    private TextView lbBewertungsfaktor01 = null;
    private AutoCompleteTextView actvBewertungsfaktor01 = null;
    private TextView lbBewertungsfaktor02 = null;
    private EditText etBewertungsfaktor02 = null;
    private TextView lbBewertungsfaktor03 = null;
    private EditText etBewertungsfaktor03 = null;
    private TextView lbBewertungsfaktor04 = null;
    private EditText etBewertungsfaktor04 = null;
    private TextView lbBewertungsfaktor05 = null;
    private EditText etBewertungsfaktor05 = null;
    private TextView lbBewertungsfaktor06 = null;
    private EditText etBewertungsfaktor06 = null;
    private TextView lbBewertungsfaktor07 = null;
    private EditText etBewertungsfaktor07 = null;
    private TextView lbBewertungsfaktor08 = null;
    private EditText etBewertungsfaktor08 = null;
    private TextView lbBewertungsfaktor09 = null;
    private EditText etBewertungsfaktor09 = null;
    private TextView lbBewertungsfaktor10 = null;
    private EditText etBewertungsfaktor10 = null;
    private TextView lbBewertungsfaktor11 = null;
    private EditText etBewertungsfaktor11 = null;

    public BewertungViewHolder(View pView, MessgeraetModel pMessgeraet, Activity pActivity){
        super(pView, pMessgeraet, pActivity );
    }


    public Messgeraet getBean() {
        return (Messgeraet) super.getBasemodel().getBean();
    }


    public MessgeraetModel getBasemodel() {
        return (MessgeraetModel) super.getBasemodel();
    }


    @Override
    public void updateView() {
        setLbGrundparameter(getActivity().findViewById(R.id.lbGrundparameter));
        setEtGrundparameter(getActivity().findViewById(R.id.etGrundparameter));
        setLbReihenanordnung(getActivity().findViewById(R.id.lbReihenanordnung));
        setActvReihenanordnung(getActivity().findViewById(R.id.actvReihenanordnung));
        setLbBewertungsfaktor01(getActivity().findViewById(R.id.lbBewertungsfaktor01));
        setActvBewertungsfaktor01(getActivity().findViewById(R.id.actvBewertungsfaktor01));
        setLbBewertungsfaktor02(getActivity().findViewById(R.id.lbBewertungsfaktor02));
        setEtBewertungsfaktor02(getActivity().findViewById(R.id.etBewertungsfaktor02));
        setLbBewertungsfaktor03(getActivity().findViewById(R.id.lbBewertungsfaktor03));
        setEtBewertungsfaktor03(getActivity().findViewById(R.id.etBewertungsfaktor03));
        setLbBewertungsfaktor04(getActivity().findViewById(R.id.lbBewertungsfaktor04));
        setEtBewertungsfaktor04(getActivity().findViewById(R.id.etBewertungsfaktor04));
        setLbBewertungsfaktor05(getActivity().findViewById(R.id.lbBewertungsfaktor05));
        setEtBewertungsfaktor05(getActivity().findViewById(R.id.etBewertungsfaktor05));
        setLbBewertungsfaktor06(getActivity().findViewById(R.id.lbBewertungsfaktor06));
        setEtBewertungsfaktor06(getActivity().findViewById(R.id.etBewertungsfaktor06));
        setLbBewertungsfaktor07(getActivity().findViewById(R.id.lbBewertungsfaktor07));
        setEtBewertungsfaktor07(getActivity().findViewById(R.id.etBewertungsfaktor07));
        setLbBewertungsfaktor08(getActivity().findViewById(R.id.lbBewertungsfaktor08));
        setEtBewertungsfaktor08(getActivity().findViewById(R.id.etBewertungsfaktor08));
        setLbBewertungsfaktor09(getActivity().findViewById(R.id.lbBewertungsfaktor09));
        setEtBewertungsfaktor09(getActivity().findViewById(R.id.etBewertungsfaktor09));
        setLbBewertungsfaktor10(getActivity().findViewById(R.id.lbBewertungsfaktor10));
        setEtBewertungsfaktor10(getActivity().findViewById(R.id.etBewertungsfaktor10));
        setLbBewertungsfaktor11(getActivity().findViewById(R.id.lbBewertungsfaktor11));
        setEtBewertungsfaktor11(getActivity().findViewById(R.id.etBewertungsfaktor11));


        getEtGrundparameter().addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before,
            int count) {
                if(!s.equals("") ) {
                    //do your work here
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }
            public void afterTextChanged(Editable s) {
                setVisiblityForGrundparameter();
            }
        });

        List<Messgeraet> qHKVs = getBasemodel().getTodo().getNutzer().getLiegenschaft().getBaseModel().getNutzerMessgaereteByArt("HKV");
        if (getActvReihenanordnung() != null) getActvReihenanordnung().setAdapter(
                new ArrayAdapter<String>(mActivity,android.R.layout.simple_list_item_1,
                        qHKVs.stream().map(r -> r.getReihenanordnung()).distinct().collect(Collectors.toList())
                )
        );
        loadData();
    }

    private void setVisiblityForGrundparameter() {
        switch (getEtGrundparameter().getText().toString()) {
            case "":
                getLbReihenanordnung().setVisibility(View.INVISIBLE);
                getActvReihenanordnung().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor01().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor01().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor02().setVisibility(View.INVISIBLE);
                getEtBewertungsfaktor02().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor03().setVisibility(View.INVISIBLE);
                getEtBewertungsfaktor03().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor04().setVisibility(View.INVISIBLE);
                getEtBewertungsfaktor04().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor05().setVisibility(View.INVISIBLE);
                getEtBewertungsfaktor05().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor06().setVisibility(View.INVISIBLE);
                getEtBewertungsfaktor06().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor07().setVisibility(View.INVISIBLE);
                getEtBewertungsfaktor07().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor08().setVisibility(View.INVISIBLE);
                getEtBewertungsfaktor08().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor09().setVisibility(View.INVISIBLE);
                getEtBewertungsfaktor09().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor10().setVisibility(View.INVISIBLE);
                getEtBewertungsfaktor10().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor11().setVisibility(View.INVISIBLE);
                getEtBewertungsfaktor11().setVisibility(View.INVISIBLE);
                break;
            case "21":
                getLbReihenanordnung().setVisibility(View.VISIBLE);
                getActvReihenanordnung().setVisibility(View.VISIBLE);

                getLbBewertungsfaktor01().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor01().setVisibility(View.VISIBLE);

                getLbBewertungsfaktor02().setVisibility(View.INVISIBLE);
                getEtBewertungsfaktor02().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor03().setVisibility(View.VISIBLE);
                getEtBewertungsfaktor03().setVisibility(View.VISIBLE);

                getLbBewertungsfaktor04().setVisibility(View.VISIBLE);
                getEtBewertungsfaktor04().setVisibility(View.VISIBLE);

                getLbBewertungsfaktor05().setVisibility(View.VISIBLE);
                getEtBewertungsfaktor05().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor05().setText("T3 - Maß über 3 Teilungen in mm");

                getLbBewertungsfaktor06().setVisibility(View.VISIBLE);
                getEtBewertungsfaktor06().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor06().setText("PD - Platendicke in mm");

                getLbBewertungsfaktor07().setVisibility(View.GONE);
                getEtBewertungsfaktor07().setVisibility(View.GONE);

                getLbBewertungsfaktor08().setVisibility(View.GONE);
                getEtBewertungsfaktor08().setVisibility(View.GONE);

                getLbBewertungsfaktor09().setVisibility(View.VISIBLE);
                getEtBewertungsfaktor09().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor09().setText("LT - Lamellentiefe in mm");

                getLbBewertungsfaktor10().setVisibility(View.VISIBLE);
                getEtBewertungsfaktor10().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor10().setText("LH - Lamellenhöhe in mm");

                getLbBewertungsfaktor11().setVisibility(View.INVISIBLE);
                getEtBewertungsfaktor11().setVisibility(View.INVISIBLE);
                break;
       }
    }


    public void save() {
        setDataToModel();
        getBasemodel().save();
    }

    protected void loadData(){
        getEtGrundparameter().setText(getBasemodel().getGrundparameter());
        getActvReihenanordnung().setText(getBasemodel().getReihenanordnung());
        getActvBewertungsfaktor01().setText(getBasemodel().getBewertungsfaktor_01().toString());
        getEtBewertungsfaktor02().setText(getBasemodel().getBewertungsfaktor_02().toString());
        getEtBewertungsfaktor03().setText(getBasemodel().getBewertungsfaktor_03().toString());
        getEtBewertungsfaktor04().setText(getBasemodel().getBewertungsfaktor_04().toString());
        getEtBewertungsfaktor05().setText(getBasemodel().getBewertungsfaktor_05().toString());
        getEtBewertungsfaktor06().setText(getBasemodel().getBewertungsfaktor_06().toString());
        getEtBewertungsfaktor07().setText(getBasemodel().getBewertungsfaktor_07().toString());
        getEtBewertungsfaktor08().setText(getBasemodel().getBewertungsfaktor_08().toString());
        getEtBewertungsfaktor09().setText(getBasemodel().getBewertungsfaktor_09().toString());
        getEtBewertungsfaktor10().setText(getBasemodel().getBewertungsfaktor_10().toString());
        getEtBewertungsfaktor11().setText(getBasemodel().getBewertungsfaktor_11().toString());
    }

    protected void createLayout() {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {




    }

    public void setDataToModel() {

        // übergabe einfacher Werte
        getBasemodel().setGrundparameter(getEtGrundparameter().getText().toString());
        getBasemodel().setReihenanordnung(getActvReihenanordnung().getText().toString());
        getBasemodel().setBewertungsfaktor_01(Integer.parseInt(getActvBewertungsfaktor01().getText().toString()));
        getBasemodel().setBewertungsfaktor_02(Integer.parseInt(getEtBewertungsfaktor02().getText().toString()));
        getBasemodel().setBewertungsfaktor_03(Integer.parseInt(getEtBewertungsfaktor03().getText().toString()));
        getBasemodel().setBewertungsfaktor_04(Integer.parseInt(getEtBewertungsfaktor04().getText().toString()));
        getBasemodel().setBewertungsfaktor_05(Integer.parseInt(getEtBewertungsfaktor05().getText().toString()));
        getBasemodel().setBewertungsfaktor_06(Integer.parseInt(getEtBewertungsfaktor06().getText().toString()));
        getBasemodel().setBewertungsfaktor_07(Integer.parseInt(getEtBewertungsfaktor07().getText().toString()));
        getBasemodel().setBewertungsfaktor_08(Integer.parseInt(getEtBewertungsfaktor08().getText().toString()));
        getBasemodel().setBewertungsfaktor_09(Integer.parseInt(getEtBewertungsfaktor09().getText().toString()));
        getBasemodel().setBewertungsfaktor_10(Integer.parseInt(getEtBewertungsfaktor10().getText().toString()));
        getBasemodel().setBewertungsfaktor_11(Integer.parseInt(getEtBewertungsfaktor11().getText().toString()));
        getBasemodel().setBewertungsfaktor_02(Integer.parseInt(getEtBewertungsfaktor02().getText().toString()));
        getBasemodel().setBewertungsfaktor_02(Integer.parseInt(getEtBewertungsfaktor02().getText().toString()));
        getBasemodel().setBewertungsfaktor_02(Integer.parseInt(getEtBewertungsfaktor02().getText().toString()));


    }


    public TextView getLbGrundparameter() {
        return lbGrundparameter;
    }

    public void setLbGrundparameter(TextView lbGrundparameter) {
        this.lbGrundparameter = lbGrundparameter;
    }

    public EditText getEtGrundparameter() {
        return etGrundparameter;
    }

    public void setEtGrundparameter(EditText etGrundparameter) {
        this.etGrundparameter = etGrundparameter;
    }

    public TextView getLbReihenanordnung() {
        return lbReihenanordnung;
    }

    public void setLbReihenanordnung(TextView lbReihenanordnung) {
        this.lbReihenanordnung = lbReihenanordnung;
    }

    public AutoCompleteTextView getActvReihenanordnung() {
        return actvReihenanordnung;
    }

    public void setActvReihenanordnung(AutoCompleteTextView actvReihenanordnung) {
        this.actvReihenanordnung = actvReihenanordnung;
    }

    public TextView getLbBewertungsfaktor01() {
        return lbBewertungsfaktor01;
    }

    public void setLbBewertungsfaktor01(TextView lbBewertungsfaktor01) {
        this.lbBewertungsfaktor01 = lbBewertungsfaktor01;
    }

    public AutoCompleteTextView getActvBewertungsfaktor01() {
        return actvBewertungsfaktor01;
    }

    public void setActvBewertungsfaktor01(AutoCompleteTextView actvBewertungsfaktor01) {
        this.actvBewertungsfaktor01 = actvBewertungsfaktor01;
    }

    public TextView getLbBewertungsfaktor02() {
        return lbBewertungsfaktor02;
    }

    public void setLbBewertungsfaktor02(TextView lbBewertungsfaktor02) {
        this.lbBewertungsfaktor02 = lbBewertungsfaktor02;
    }

    public EditText getEtBewertungsfaktor02() {
        return etBewertungsfaktor02;
    }

    public void setEtBewertungsfaktor02(EditText etBewertungsfaktor02) {
        this.etBewertungsfaktor02 = etBewertungsfaktor02;
    }

    public TextView getLbBewertungsfaktor03() {
        return lbBewertungsfaktor03;
    }

    public void setLbBewertungsfaktor03(TextView lbBewertungsfaktor03) {
        this.lbBewertungsfaktor03 = lbBewertungsfaktor03;
    }

    public EditText getEtBewertungsfaktor03() {
        return etBewertungsfaktor03;
    }

    public void setEtBewertungsfaktor03(EditText etBewertungsfaktor03) {
        this.etBewertungsfaktor03 = etBewertungsfaktor03;
    }

    public TextView getLbBewertungsfaktor04() {
        return lbBewertungsfaktor04;
    }

    public void setLbBewertungsfaktor04(TextView lbBewertungsfaktor04) {
        this.lbBewertungsfaktor04 = lbBewertungsfaktor04;
    }

    public EditText getEtBewertungsfaktor04() {
        return etBewertungsfaktor04;
    }

    public void setEtBewertungsfaktor04(EditText etBewertungsfaktor04) {
        this.etBewertungsfaktor04 = etBewertungsfaktor04;
    }

    public TextView getLbBewertungsfaktor05() {
        return lbBewertungsfaktor05;
    }

    public void setLbBewertungsfaktor05(TextView lbBewertungsfaktor05) {
        this.lbBewertungsfaktor05 = lbBewertungsfaktor05;
    }

    public EditText getEtBewertungsfaktor05() {
        return etBewertungsfaktor05;
    }

    public void setEtBewertungsfaktor05(EditText etBewertungsfaktor05) {
        this.etBewertungsfaktor05 = etBewertungsfaktor05;
    }

    public TextView getLbBewertungsfaktor06() {
        return lbBewertungsfaktor06;
    }

    public void setLbBewertungsfaktor06(TextView lbBewertungsfaktor06) {
        this.lbBewertungsfaktor06 = lbBewertungsfaktor06;
    }

    public EditText getEtBewertungsfaktor06() {
        return etBewertungsfaktor06;
    }

    public void setEtBewertungsfaktor06(EditText etBewertungsfaktor06) {
        this.etBewertungsfaktor06 = etBewertungsfaktor06;
    }

    public TextView getLbBewertungsfaktor07() {
        return lbBewertungsfaktor07;
    }

    public void setLbBewertungsfaktor07(TextView lbBewertungsfaktor07) {
        this.lbBewertungsfaktor07 = lbBewertungsfaktor07;
    }

    public EditText getEtBewertungsfaktor07() {
        return etBewertungsfaktor07;
    }

    public void setEtBewertungsfaktor07(EditText etBewertungsfaktor07) {
        this.etBewertungsfaktor07 = etBewertungsfaktor07;
    }

    public TextView getLbBewertungsfaktor08() {
        return lbBewertungsfaktor08;
    }

    public void setLbBewertungsfaktor08(TextView lbBewertungsfaktor08) {
        this.lbBewertungsfaktor08 = lbBewertungsfaktor08;
    }

    public EditText getEtBewertungsfaktor08() {
        return etBewertungsfaktor08;
    }

    public void setEtBewertungsfaktor08(EditText etBewertungsfaktor08) {
        this.etBewertungsfaktor08 = etBewertungsfaktor08;
    }

    public TextView getLbBewertungsfaktor09() {
        return lbBewertungsfaktor09;
    }

    public void setLbBewertungsfaktor09(TextView lbBewertungsfaktor09) {
        this.lbBewertungsfaktor09 = lbBewertungsfaktor09;
    }

    public EditText getEtBewertungsfaktor09() {
        return etBewertungsfaktor09;
    }

    public void setEtBewertungsfaktor09(EditText etBewertungsfaktor09) {
        this.etBewertungsfaktor09 = etBewertungsfaktor09;
    }

    public TextView getLbBewertungsfaktor10() {
        return lbBewertungsfaktor10;
    }

    public void setLbBewertungsfaktor10(TextView lbBewertungsfaktor10) {
        this.lbBewertungsfaktor10 = lbBewertungsfaktor10;
    }

    public EditText getEtBewertungsfaktor10() {
        return etBewertungsfaktor10;
    }

    public void setEtBewertungsfaktor10(EditText etBewertungsfaktor10) {
        this.etBewertungsfaktor10 = etBewertungsfaktor10;
    }

    public TextView getLbBewertungsfaktor11() {
        return lbBewertungsfaktor11;
    }

    public void setLbBewertungsfaktor11(TextView lbBewertungsfaktor11) {
        this.lbBewertungsfaktor11 = lbBewertungsfaktor11;
    }

    public EditText getEtBewertungsfaktor11() {
        return etBewertungsfaktor11;
    }

    public void setEtBewertungsfaktor11(EditText etBewertungsfaktor11) {
        this.etBewertungsfaktor11 = etBewertungsfaktor11;
    }
}
