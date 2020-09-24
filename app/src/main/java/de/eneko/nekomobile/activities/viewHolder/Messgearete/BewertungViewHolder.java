package de.eneko.nekomobile.activities.viewHolder.Messgearete;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.adapter.BewertungListViewAdapter;
import de.eneko.nekomobile.activities.detail.Messgeraete.MessgaeretBewertungActivity;
import de.eneko.nekomobile.activities.models.MessgeraetModel;
import de.eneko.nekomobile.activities.viewHolder.BaseViewHolder;
import de.eneko.nekomobile.beans.Ibewertung;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.beans.hlpta.Webes_Grundparameter;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.controllers.Dict;
import de.eneko.nekomobile.controllers.FileHandler;

//
public class BewertungViewHolder extends BaseViewHolder implements View.OnClickListener, AdapterView.OnItemClickListener {
    private TextView lbGrundparameter = null;
    private AutoCompleteTextView etGrundparameter = null;
    private TextView lbReihenanordnung = null;
    private AutoCompleteTextView actvReihenanordnung = null;
    private TextView lbBewertungsfaktor01 = null;
    private AutoCompleteTextView actvBewertungsfaktor01 = null;
    private TextView lbBewertungsfaktor02 = null;
    private AutoCompleteTextView actvBewertungsfaktor02 = null;
    private TextView lbBewertungsfaktor03 = null;
    private AutoCompleteTextView actvBewertungsfaktor03 = null;
    private TextView lbBewertungsfaktor04 = null;
    private AutoCompleteTextView actvBewertungsfaktor04 = null;
    private TextView lbBewertungsfaktor05 = null;
    private AutoCompleteTextView actvBewertungsfaktor05 = null;
    private TextView lbBewertungsfaktor06 = null;
    private AutoCompleteTextView actvBewertungsfaktor06 = null;
    private TextView lbBewertungsfaktor07 = null;
    private AutoCompleteTextView actvBewertungsfaktor07 = null;
    private TextView lbBewertungsfaktor08 = null;
    private AutoCompleteTextView actvBewertungsfaktor08 = null;
    private TextView lbBewertungsfaktor09 = null;
    private AutoCompleteTextView actvBewertungsfaktor09 = null;
    private TextView lbBewertungsfaktor10 = null;
    private AutoCompleteTextView actvBewertungsfaktor10 = null;
    private TextView lbBewertungsfaktor11 = null;
    private AutoCompleteTextView actvBewertungsfaktor11 = null;
    private ListView listView = null;
    private BewertungListViewAdapter bewertungListViewAdapter = null;
    private Boolean mBLParamChanged = false;


    public BewertungViewHolder(View pView, MessgeraetModel pMessgeraet, Activity pActivity){
        super(pView, pMessgeraet, pActivity );
    }

    @Override
    public MessgaeretBewertungActivity getActivity() {
        return (MessgaeretBewertungActivity) super.getActivity();
    }

    public Messgeraet getBean() {
        return (Messgeraet) super.getBasemodel().getBean();
    }


    public MessgeraetModel getBasemodel() {
        return (MessgeraetModel) super.getBasemodel();
    }

    @Override
    public void onClick(View v) {
        AutoCompleteTextView ac = (AutoCompleteTextView) v;
        if (ac != null) ac.showDropDown();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

//    private View.OnFocusChangeListener focusListener = new View.OnFocusChangeListener() {
//        public void onFocusChange(View v, boolean hasFocus) {
//            if (hasFocus){
//                focusedView = v;
//            } else {
//                focusedView  = null;
//            }
//        }
//    }


    @Override
    public void updateView() {
        setLbGrundparameter(getActivity().findViewById(R.id.lbGrundparameter));
        setEtGrundparameter(getActivity().findViewById(R.id.etGrundparameter));
        setLbReihenanordnung(getActivity().findViewById(R.id.lbReihenanordnung));
        setActvReihenanordnung(getActivity().findViewById(R.id.actvReihenanordnung));
        setLbBewertungsfaktor01(getActivity().findViewById(R.id.lbBewertungsfaktor01));
        setActvBewertungsfaktor01(getActivity().findViewById(R.id.actvBewertungsfaktor01));
        setLbBewertungsfaktor02(getActivity().findViewById(R.id.lbBewertungsfaktor02));
        setActvBewertungsfaktor02(getActivity().findViewById(R.id.actvBewertungsfaktor02));
        setLbBewertungsfaktor03(getActivity().findViewById(R.id.lbBewertungsfaktor03));
        setActvBewertungsfaktor03(getActivity().findViewById(R.id.actvBewertungsfaktor03));
        setLbBewertungsfaktor04(getActivity().findViewById(R.id.lbBewertungsfaktor04));
        setActvBewertungsfaktor04(getActivity().findViewById(R.id.actvBewertungsfaktor04));
        setLbBewertungsfaktor05(getActivity().findViewById(R.id.lbBewertungsfaktor05));
        setActvBewertungsfaktor05(getActivity().findViewById(R.id.actvBewertungsfaktor05));
        setLbBewertungsfaktor06(getActivity().findViewById(R.id.lbBewertungsfaktor06));
        setActvBewertungsfaktor06(getActivity().findViewById(R.id.actvBewertungsfaktor06));
        setLbBewertungsfaktor07(getActivity().findViewById(R.id.lbBewertungsfaktor07));
        setActvBewertungsfaktor07(getActivity().findViewById(R.id.actvBewertungsfaktor07));
        setLbBewertungsfaktor08(getActivity().findViewById(R.id.lbBewertungsfaktor08));
        setActvBewertungsfaktor08(getActivity().findViewById(R.id.actvBewertungsfaktor08));
        setLbBewertungsfaktor09(getActivity().findViewById(R.id.lbBewertungsfaktor09));
        setActvBewertungsfaktor09(getActivity().findViewById(R.id.actvBewertungsfaktor09));
        setLbBewertungsfaktor10(getActivity().findViewById(R.id.lbBewertungsfaktor10));
        setActvBewertungsfaktor10(getActivity().findViewById(R.id.actvBewertungsfaktor10));
        setLbBewertungsfaktor11(getActivity().findViewById(R.id.lbBewertungsfaktor11));
        setActvBewertungsfaktor11(getActivity().findViewById(R.id.actvBewertungsfaktor11));
        setListView(getActivity().findViewById(R.id.listView));


        List<Webes_Grundparameter> qWebesParams = Dict.getInstance().getWebesGrundparameters();
        if (getEtGrundparameter() != null) getEtGrundparameter().setAdapter(
                new ArrayAdapter<>(mActivity,android.R.layout.simple_dropdown_item_1line,
                        qWebesParams.stream()
                                .map(r -> r.toString())
                                .collect(Collectors.toList())
                )
        );

        getEtGrundparameter().addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before,int count) {
                if(!s.equals("") ) {
                    //do your work here
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void afterTextChanged(Editable s) {
                setVisiblityForGrundparameter();
                if (getEtGrundparameter().getText().toString().length() > 2)
                {
                    getEtGrundparameter().setText(getEtGrundparameter().getText().toString().substring(0,2));
                }
                if (getEtGrundparameter().getText().toString().length() == 2)
                {
                    getEtGrundparameter().dismissDropDown();
                }

                List<Messgeraet> qHKVs = getBasemodel().getTodo().getNutzer().getLiegenschaft().getBaseModel().getNutzerMessgaereteByArt("HKV");
                if (getActvBewertungsfaktor01() != null) getActvBewertungsfaktor01().setAdapter(
                        new ArrayAdapter<Integer>(mActivity,android.R.layout.simple_list_item_1,
                                qHKVs.stream()
                                        .filter(r ->r.getGrundparameter().equals(s.toString()) && r.getBewertungsfaktor_01() != Messgeraet.INTEGER_NULLVALUE)
                                        .map(r -> r.getBewertungsfaktor_01()).distinct().collect(Collectors.toList())
                        )
                );
                if (getActvBewertungsfaktor02() != null) getActvBewertungsfaktor02().setAdapter(
                        new ArrayAdapter<Integer>(mActivity,android.R.layout.simple_list_item_1,
                                qHKVs.stream()
                                        .filter(r ->r.getGrundparameter().equals(s.toString()) && r.getBewertungsfaktor_02() != Messgeraet.INTEGER_NULLVALUE)
                                        .map(r -> r.getBewertungsfaktor_02()).distinct().collect(Collectors.toList())
                        )
                );
                if (getActvBewertungsfaktor03() != null) getActvBewertungsfaktor03().setAdapter(
                        new ArrayAdapter<Integer>(mActivity,android.R.layout.simple_list_item_1,
                                qHKVs.stream()
                                        .filter(r ->r.getGrundparameter().equals(s.toString()) && r.getBewertungsfaktor_03() != Messgeraet.INTEGER_NULLVALUE)
                                        .map(r -> r.getBewertungsfaktor_03()).distinct().collect(Collectors.toList())
                        )
                );
                if (getActvBewertungsfaktor04() != null) getActvBewertungsfaktor04().setAdapter(
                        new ArrayAdapter<Integer>(mActivity,android.R.layout.simple_list_item_1,
                                qHKVs.stream()
                                        .filter(r ->r.getGrundparameter().equals(s.toString()) && r.getBewertungsfaktor_04() != Messgeraet.INTEGER_NULLVALUE)
                                        .map(r -> r.getBewertungsfaktor_04()).distinct().collect(Collectors.toList())
                        )
                );
                if (getActvBewertungsfaktor05() != null) getActvBewertungsfaktor05().setAdapter(
                        new ArrayAdapter<Integer>(mActivity,android.R.layout.simple_list_item_1,
                                qHKVs.stream()
                                        .filter(r ->r.getGrundparameter().equals(s.toString()) && r.getBewertungsfaktor_05() != Messgeraet.INTEGER_NULLVALUE)
                                        .map(r -> r.getBewertungsfaktor_05()).distinct().collect(Collectors.toList())
                        )
                );
                if (getActvBewertungsfaktor06() != null) getActvBewertungsfaktor06().setAdapter(
                        new ArrayAdapter<Integer>(mActivity,android.R.layout.simple_list_item_1,
                                qHKVs.stream()
                                        .filter(r ->r.getGrundparameter().equals(s.toString()) && r.getBewertungsfaktor_06() != Messgeraet.INTEGER_NULLVALUE)
                                        .map(r -> r.getBewertungsfaktor_06()).distinct().collect(Collectors.toList())
                        )
                );
                if (getActvBewertungsfaktor07() != null) getActvBewertungsfaktor07().setAdapter(
                        new ArrayAdapter<Integer>(mActivity,android.R.layout.simple_list_item_1,
                                qHKVs.stream()
                                        .filter(r ->r.getGrundparameter().equals(s.toString()) && r.getBewertungsfaktor_07() != Messgeraet.INTEGER_NULLVALUE)
                                        .map(r -> r.getBewertungsfaktor_07()).distinct().collect(Collectors.toList())
                        )
                );
                if (getActvBewertungsfaktor08() != null) getActvBewertungsfaktor08().setAdapter(
                        new ArrayAdapter<Integer>(mActivity,android.R.layout.simple_list_item_1,
                                qHKVs.stream()
                                        .filter(r ->r.getGrundparameter().equals(s.toString()) && r.getBewertungsfaktor_08() != Messgeraet.INTEGER_NULLVALUE)
                                        .map(r -> r.getBewertungsfaktor_08()).distinct().collect(Collectors.toList())
                        )
                );
                if (getActvBewertungsfaktor09() != null) getActvBewertungsfaktor09().setAdapter(
                        new ArrayAdapter<Integer>(mActivity,android.R.layout.simple_list_item_1,
                                qHKVs.stream()
                                        .filter(r ->r.getGrundparameter().equals(s.toString()) && r.getBewertungsfaktor_09() != Messgeraet.INTEGER_NULLVALUE)
                                        .map(r -> r.getBewertungsfaktor_09()).distinct().collect(Collectors.toList())
                        )
                );

                if (getActvBewertungsfaktor10() != null) getActvBewertungsfaktor10().setAdapter(
                        new ArrayAdapter<Integer>(mActivity,android.R.layout.simple_list_item_1,
                                qHKVs.stream()
                                        .filter(r ->r.getGrundparameter().equals(s.toString()) && r.getBewertungsfaktor_10() != Messgeraet.INTEGER_NULLVALUE)
                                        .map(r -> r.getBewertungsfaktor_10()).distinct().collect(Collectors.toList())
                        )
                );

                if (getActvBewertungsfaktor11() != null) getActvBewertungsfaktor11().setAdapter(
                        new ArrayAdapter<Integer>(mActivity,android.R.layout.simple_list_item_1,
                                qHKVs.stream()
                                        .filter(r ->r.getGrundparameter().equals(s.toString()) && r.getBewertungsfaktor_11() != Messgeraet.INTEGER_NULLVALUE)
                                        .map(r -> r.getBewertungsfaktor_11()).distinct().collect(Collectors.toList())
                        )
                );
            }
        });


        TextWatcher textWatcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before,int count) {
                if(!s.equals("") ) {
                    //do your work here
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void afterTextChanged(Editable s) {
                updateListView();
                if (getActvBewertungsfaktor05().hasFocus() || getActvBewertungsfaktor09().hasFocus()){
                    if ( getEtGrundparameter().getText().toString().equals("12") &&  ! getActvBewertungsfaktor05().getText().toString().isEmpty() && ! getActvBewertungsfaktor09().getText().toString().isEmpty())
                    {
                        mBLParamChanged = true;
                   }
                }
            }
        };


        View.OnFocusChangeListener mOnFocusChangeListener= new View.OnFocusChangeListener() {
            String ret_val = "";
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && mBLParamChanged) {
                   if (getEtGrundparameter().getText().toString().equals("12") && !getActvBewertungsfaktor05().getText().toString().isEmpty() && !getActvBewertungsfaktor09().getText().toString().isEmpty()) {
                        Integer p1 = Integer.valueOf(getActvBewertungsfaktor05().getText().toString()).intValue();
                        Integer p2 = Integer.valueOf(getActvBewertungsfaktor09().getText().toString()).intValue();
                        ret_val = Integer.toString(p1 * p2);

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AlertDialogTheme);
                        AlertDialog dialog = builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {

                           }
                        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                getActvBewertungsfaktor03().setText(ret_val);
                            }
                        }).create();

                        //2. now setup to change color of the button
                       dialog.setOnShowListener( new DialogInterface.OnShowListener() {
                           @Override
                           public void onShow(DialogInterface arg0) {
                               dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(mActivity, R.color.black));
                               dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                           }
                       });
                       dialog.setTitle("Soll die Baulänge geändert werden?");
                       dialog.show();
                       mBLParamChanged = false;
                    }
                }
            }
//            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    switch (which){
//                        case DialogInterface.BUTTON_POSITIVE:
//                            getActvBewertungsfaktor03().setText(ret_val);
//                            break;
//
//                        case DialogInterface.BUTTON_NEGATIVE:
//                            //No button clicked
//                            break;
//                    }
//                }
//            };

        };





        getActvBewertungsfaktor05().setOnFocusChangeListener(mOnFocusChangeListener);
        getActvBewertungsfaktor09().setOnFocusChangeListener(mOnFocusChangeListener);

        getActvBewertungsfaktor01().setOnClickListener(this);
        getActvBewertungsfaktor02().setOnClickListener(this);
        getActvBewertungsfaktor03().setOnClickListener(this);
        getActvBewertungsfaktor04().setOnClickListener(this);
        getActvBewertungsfaktor05().setOnClickListener(this);
        getActvBewertungsfaktor06().setOnClickListener(this);
        getActvBewertungsfaktor07().setOnClickListener(this);
        getActvBewertungsfaktor08().setOnClickListener(this);
        getActvBewertungsfaktor09().setOnClickListener(this);
        getActvBewertungsfaktor10().setOnClickListener(this);
        getActvBewertungsfaktor11().setOnClickListener(this);
        getActvReihenanordnung().setOnClickListener(this);

        getActvBewertungsfaktor01().addTextChangedListener(textWatcher);
        getActvBewertungsfaktor02().addTextChangedListener(textWatcher);
        getActvBewertungsfaktor03().addTextChangedListener(textWatcher);
        getActvBewertungsfaktor04().addTextChangedListener(textWatcher);
        getActvBewertungsfaktor05().addTextChangedListener(textWatcher);
        getActvBewertungsfaktor06().addTextChangedListener(textWatcher);
        getActvBewertungsfaktor07().addTextChangedListener(textWatcher);
        getActvBewertungsfaktor08().addTextChangedListener(textWatcher);
        getActvBewertungsfaktor09().addTextChangedListener(textWatcher);
        getActvBewertungsfaktor10().addTextChangedListener(textWatcher);
        getActvBewertungsfaktor11().addTextChangedListener(textWatcher);
        getActvReihenanordnung().addTextChangedListener(textWatcher);

//        getActvBewertungsfaktor01().setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//
//                }
//            }
//        });
//        getActvBewertungsfaktor02().setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) getActvBewertungsfaktor02().showDropDown();
//            }
//        });





        List<Messgeraet> qHKVs = getBasemodel().getTodo().getNutzer().getLiegenschaft().getBaseModel().getNutzerMessgaereteByArt("HKV");
        if (getActvReihenanordnung() != null) getActvReihenanordnung().setAdapter(
                new ArrayAdapter<String>(mActivity,android.R.layout.simple_list_item_1,
                        qHKVs.stream().map(r -> r.getReihenanordnung()).distinct().collect(Collectors.toList())
                )
        );

        listView.setOnItemClickListener(this);

        loadData();
    }



    private void updateListView(){

        List<Messgeraet> datasource = null;

        datasource = CurrentObjectNavigation.getInstance().getLiegenschaft().getBaseModel().getNutzerMessgaereteByArt("HKV");

        datasource = datasource.stream()
                .filter(r -> r.getDatum() != null)
                .sorted(Comparator.comparing(Messgeraet::getDatum))
                .collect(Collectors.toList());

        datasource = datasource.stream()
                .filter(r -> r.getGrundparameter().equals(getEtGrundparameter().getText().toString()))
                .collect(Collectors.toList());

        if (getActvBewertungsfaktor01().getVisibility() == View.VISIBLE && getActvBewertungsfaktor01().getText().toString().length() > 0) {
            datasource = datasource.stream()
                    .filter(r -> r.getBewertungsfaktor_01() == Integer.parseInt(getActvBewertungsfaktor01().getText().toString()))
                    .collect(Collectors.toList());
        }

        if (getActvBewertungsfaktor02().getVisibility() == View.VISIBLE && getActvBewertungsfaktor02().getText().toString().length() > 0) {
            datasource = datasource.stream()
                    .filter(r -> r.getBewertungsfaktor_02() == Integer.parseInt(getActvBewertungsfaktor02().getText().toString()))
                    .collect(Collectors.toList());
        }
        if (getActvBewertungsfaktor03().getVisibility() == View.VISIBLE && getActvBewertungsfaktor03().getText().toString().length() > 0) {
            datasource = datasource.stream()
                    .filter(r -> r.getBewertungsfaktor_03() == Integer.parseInt(getActvBewertungsfaktor03().getText().toString()))
                    .collect(Collectors.toList());
        }
        if (getActvBewertungsfaktor04().getVisibility() == View.VISIBLE && getActvBewertungsfaktor04().getText().toString().length() > 0) {
            datasource = datasource.stream()
                    .filter(r -> r.getBewertungsfaktor_04() == Integer.parseInt(getActvBewertungsfaktor04().getText().toString()))
                    .collect(Collectors.toList());
        }
        if (getActvBewertungsfaktor05().getVisibility() == View.VISIBLE && getActvBewertungsfaktor05().getText().toString().length() > 0) {
            datasource = datasource.stream()
                    .filter(r -> r.getBewertungsfaktor_05() == Integer.parseInt(getActvBewertungsfaktor05().getText().toString()))
                    .collect(Collectors.toList());
        }
        if (getActvBewertungsfaktor06().getVisibility() == View.VISIBLE && getActvBewertungsfaktor06().getText().toString().length() > 0) {
            datasource = datasource.stream()
                    .filter(r -> r.getBewertungsfaktor_06() == Integer.parseInt(getActvBewertungsfaktor06().getText().toString()))
                    .collect(Collectors.toList());
        }
        if (getActvBewertungsfaktor07().getVisibility() == View.VISIBLE && getActvBewertungsfaktor07().getText().toString().length() > 0) {
            datasource = datasource.stream()
                    .filter(r -> r.getBewertungsfaktor_07() == Integer.parseInt(getActvBewertungsfaktor07().getText().toString()))
                    .collect(Collectors.toList());
        }
        if (getActvBewertungsfaktor08().getVisibility() == View.VISIBLE && getActvBewertungsfaktor08().getText().toString().length() > 0) {
            datasource = datasource.stream()
                    .filter(r -> r.getBewertungsfaktor_08() == Integer.parseInt(getActvBewertungsfaktor08().getText().toString()))
                    .collect(Collectors.toList());
        }
        if (getActvBewertungsfaktor09().getVisibility() == View.VISIBLE && getActvBewertungsfaktor09().getText().toString().length() > 0) {
            datasource = datasource.stream()
                    .filter(r -> r.getBewertungsfaktor_09() == Integer.parseInt(getActvBewertungsfaktor09().getText().toString()))
                    .collect(Collectors.toList());
        }
        if (getActvBewertungsfaktor11().getVisibility() == View.VISIBLE && getActvBewertungsfaktor11().getText().toString().length() > 0) {
            datasource = datasource.stream()
                    .filter(r -> r.getBewertungsfaktor_11() == Integer.parseInt(getActvBewertungsfaktor11().getText().toString()))
                    .collect(Collectors.toList());
        }
        if (getActvBewertungsfaktor10().getVisibility() == View.VISIBLE && getActvBewertungsfaktor10().getText().toString().length() > 0) {
            datasource = datasource.stream()
                    .filter(r -> r.getBewertungsfaktor_10() == Integer.parseInt(getActvBewertungsfaktor10().getText().toString()))
                    .collect(Collectors.toList());
        }
        if (getActvReihenanordnung().getVisibility() == View.VISIBLE && getActvReihenanordnung().getText().toString().length() > 0) {
            datasource = datasource.stream()
                    .filter(r -> r.getReihenanordnung().equals(getActvReihenanordnung().getText().toString()))
                    .collect(Collectors.toList());
        }


        ArrayList<Ibewertung> alist = new ArrayList<Ibewertung>();
        alist.addAll(datasource);

        bewertungListViewAdapter = new BewertungListViewAdapter(getActivity(), alist) {
            @Override
            public View getView(int index, View currentView, ViewGroup parent) {
                View v = super.getView(index, currentView, parent);
                BewertungRowViewHolder rh = (BewertungRowViewHolder) v.getTag();
                View.OnClickListener oCl = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Ibewertung bewertung = bewertungListViewAdapter.getItem(index);
                        if(bewertung != null){
                            if (getActvBewertungsfaktor01().getText().length() == 0)
                            {getActvBewertungsfaktor01().setText(bewertung.getBewertungsfaktor_01().toString());            }
                            if (getActvBewertungsfaktor02().getText().length() == 0)
                            {getActvBewertungsfaktor02().setText(bewertung.getBewertungsfaktor_02().toString());            }
                            if (getActvBewertungsfaktor03().getText().length() == 0)
                            {getActvBewertungsfaktor03().setText(bewertung.getBewertungsfaktor_03().toString());            }
                            if (getActvBewertungsfaktor04().getText().length() == 0)
                            {getActvBewertungsfaktor04().setText(bewertung.getBewertungsfaktor_04().toString());            }
                            if (getActvBewertungsfaktor05().getText().length() == 0)
                            {getActvBewertungsfaktor05().setText(bewertung.getBewertungsfaktor_05().toString());            }
                            if (getActvBewertungsfaktor06().getText().length() == 0)
                            {getActvBewertungsfaktor06().setText(bewertung.getBewertungsfaktor_06().toString());            }
                            if (getActvBewertungsfaktor07().getText().length() == 0)
                            {getActvBewertungsfaktor07().setText(bewertung.getBewertungsfaktor_07().toString());            }
                            if (getActvBewertungsfaktor08().getText().length() == 0)
                            {getActvBewertungsfaktor08().setText(bewertung.getBewertungsfaktor_08().toString());            }
                            if (getActvBewertungsfaktor09().getText().length() == 0)
                            {getActvBewertungsfaktor09().setText(bewertung.getBewertungsfaktor_09().toString());            }
                            if (getActvBewertungsfaktor10().getText().length() == 0)
                            {getActvBewertungsfaktor10().setText(bewertung.getBewertungsfaktor_10().toString());            }
                            if (getActvBewertungsfaktor11().getText().length() == 0)
                            {getActvBewertungsfaktor11().setText(bewertung.getBewertungsfaktor_11().toString());            }
                        }

                    }
                };

                v.setOnClickListener(oCl);
                rh.getTvNutzer1().setOnClickListener(oCl);
                rh.getTvNutzer2().setOnClickListener(oCl);
                rh.getTvNutzer3().setOnClickListener(oCl);
                return v;
            }

        };
        listView.setAdapter(bewertungListViewAdapter);




    }


    private void setVisiblityForGrundparameter() {
        switch (getEtGrundparameter().getText().toString()) {
            case "":
                // region code

                getLbReihenanordnung().setVisibility(View.INVISIBLE);
                getActvReihenanordnung().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor01().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor01().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor02().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor02().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor03().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor03().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor04().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor04().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor05().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor05().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor06().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor06().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor07().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor07().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor08().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor08().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor09().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor09().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor10().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor10().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor11().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor11().setVisibility(View.INVISIBLE);
                // endregion code
                break;
            case "11": case "13":
                // region code

                getLbReihenanordnung().setVisibility(View.INVISIBLE);
                getActvReihenanordnung().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor01().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor01().setVisibility(View.VISIBLE);

                getLbBewertungsfaktor02().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor02().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor02().setText("NA - Nabenabstand in mm");

                getLbBewertungsfaktor03().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor03().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor03().setText("BL - Baulänge in mm");

                getLbBewertungsfaktor04().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor04().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor04().setText("BT - Bautiefe in mm");

                getLbBewertungsfaktor05().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor05().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor05().setText("GK - Gliedbaulänge in mm");

                getLbBewertungsfaktor06().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor06().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor07().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor07().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor07().setText("SD - Säulendicke in mm");

                getLbBewertungsfaktor08().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor08().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor08().setText("SZ - Säulenzahl");

                getLbBewertungsfaktor09().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor09().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor09().setText("GZ - Gliederzahl");

                getLbBewertungsfaktor10().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor10().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor11().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor11().setVisibility(View.INVISIBLE);
                // endregion code
                break;
            case "12":
                // region code

                getLbReihenanordnung().setVisibility(View.INVISIBLE);
                getActvReihenanordnung().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor01().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor01().setVisibility(View.VISIBLE);

                getLbBewertungsfaktor02().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor02().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor02().setText("NA - Nabenabstand in mm");

                getLbBewertungsfaktor03().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor03().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor03().setText("BL - Baulänge in mm");

                getLbBewertungsfaktor04().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor04().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor04().setText("BT - Bautiefe in mm");

                getLbBewertungsfaktor05().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor05().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor05().setText("GL - Gliedbaulänge in mm");

                getLbBewertungsfaktor06().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor06().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor06().setText("ND - Nabendurchmesser in mm");

                getLbBewertungsfaktor07().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor07().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor07().setText("SD - Säulendicke in mm");

                getLbBewertungsfaktor08().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor08().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor08().setText("SZ - Säulenzahl");

                getLbBewertungsfaktor09().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor09().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor09().setText("GZ - Gliederzahl");

                getLbBewertungsfaktor10().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor10().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor11().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor11().setVisibility(View.INVISIBLE);
                // endregion code
                break;
            case "14":
                // region code

                getLbReihenanordnung().setVisibility(View.INVISIBLE);
                getActvReihenanordnung().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor01().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor01().setVisibility(View.VISIBLE);

                getLbBewertungsfaktor02().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor02().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor02().setText("NA - Nabenabstand in mm");

                getLbBewertungsfaktor03().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor03().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor03().setText("BL - Baulänge in mm");

                getLbBewertungsfaktor04().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor04().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor04().setText("BT - Bautiefe in mm");

                getLbBewertungsfaktor05().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor05().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor05().setText("GK - Gliedbaulänge in mm");

                getLbBewertungsfaktor06().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor06().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor07().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor07().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor08().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor08().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor08().setText("RZ - Rippenzahl");

                getLbBewertungsfaktor09().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor09().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor09().setText("GZ - Gliederzahl");

                getLbBewertungsfaktor10().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor10().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor11().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor11().setVisibility(View.INVISIBLE);
                // endregion code
                break;
            case "21": case "22": case "24":
                // region code

                getLbReihenanordnung().setVisibility(View.VISIBLE);
                getActvReihenanordnung().setVisibility(View.VISIBLE);

                getLbBewertungsfaktor01().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor01().setVisibility(View.VISIBLE);

                getLbBewertungsfaktor02().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor02().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor03().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor03().setVisibility(View.VISIBLE);

                getLbBewertungsfaktor04().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor04().setVisibility(View.VISIBLE);

                getLbBewertungsfaktor05().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor05().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor05().setText("T3 - Maß über 3 Teilungen in mm");

                getLbBewertungsfaktor06().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor06().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor06().setText("PD - Platendicke in mm");

                getLbBewertungsfaktor07().setVisibility(View.GONE);
                getActvBewertungsfaktor07().setVisibility(View.GONE);

                getLbBewertungsfaktor08().setVisibility(View.GONE);
                getActvBewertungsfaktor08().setVisibility(View.GONE);

                getLbBewertungsfaktor09().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor09().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor09().setText("LT - Lamellentiefe in mm");

                getLbBewertungsfaktor10().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor10().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor10().setText("LH - Lamellenhöhe in mm");

                getLbBewertungsfaktor11().setVisibility(View.GONE);
                getActvBewertungsfaktor11().setVisibility(View.GONE);
                // endregion code
                break;
            case "23":
                // region code

                getLbReihenanordnung().setVisibility(View.VISIBLE);
                getActvReihenanordnung().setVisibility(View.VISIBLE);

                getLbBewertungsfaktor01().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor01().setVisibility(View.VISIBLE);

                getLbBewertungsfaktor02().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor02().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor03().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor03().setVisibility(View.VISIBLE);

                getLbBewertungsfaktor04().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor04().setVisibility(View.VISIBLE);

                getLbBewertungsfaktor05().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor05().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor05().setText("T3 - Maß über 3 Teilungen in mm");

                getLbBewertungsfaktor06().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor06().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor06().setText("PD - Platendicke in mm");

                getLbBewertungsfaktor07().setVisibility(View.GONE);
                getActvBewertungsfaktor07().setVisibility(View.GONE);

                getLbBewertungsfaktor08().setVisibility(View.GONE);
                getActvBewertungsfaktor08().setVisibility(View.GONE);

                getLbBewertungsfaktor09().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor09().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor09().setText("LT - Lamellentiefe in mm");

                getLbBewertungsfaktor10().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor10().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor10().setText("LH - Lamellenhöhe in mm");

                getLbBewertungsfaktor11().setVisibility(View.GONE);
                getActvBewertungsfaktor11().setVisibility(View.GONE);
                // endregion code
                break;
            case "31": case "33":
                // region code

                getLbReihenanordnung().setVisibility(View.VISIBLE);
                getActvReihenanordnung().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor01().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor01().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor02().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor02().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor03().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor03().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor03().setText("BL - Baulänge in mm");

                getLbBewertungsfaktor04().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor04().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor04().setText("BT - Bautiefe in mm");

                getLbBewertungsfaktor05().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor05().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor06().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor06().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor06().setText("VT - Verteilkanaltiefe in mm");

                getLbBewertungsfaktor07().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor07().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor07().setText("RB - Rohrbreite in mm");

                getLbBewertungsfaktor08().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor08().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor08().setText("RH - Rohrtiefe in mm");

                getLbBewertungsfaktor09().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor09().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor09().setText("LT - Lamellentiefe in mm");

                getLbBewertungsfaktor10().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor10().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor10().setText("LH - Lamellenhöhe in mm");

                getLbBewertungsfaktor11().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor11().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor11().setText("ZR - Zahl der Rohre");
// endregion code
                break;
            case "32":
                // region code

                getLbReihenanordnung().setVisibility(View.VISIBLE);
                getActvReihenanordnung().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor01().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor01().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor02().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor02().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor03().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor03().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor03().setText("BL - Baulänge in mm");

                getLbBewertungsfaktor04().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor04().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor04().setText("BT - Bautiefe in mm");

                getLbBewertungsfaktor05().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor05().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor05().setText("LA - Lammelenabstand (10 mal) in mm");

                getLbBewertungsfaktor06().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor06().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor06().setText("VT - Verteilkanaltiefe in mm");

                getLbBewertungsfaktor07().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor07().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor07().setText("RB - Rohrbreite in mm");

                getLbBewertungsfaktor08().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor08().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor08().setText("RH - Rohrtiefe in mm");

                getLbBewertungsfaktor09().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor09().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor09().setText("LT - Lamellentiefe in mm");

                getLbBewertungsfaktor10().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor10().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor10().setText("LH - Lamellenhöhe in mm");

                getLbBewertungsfaktor11().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor11().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor11().setText("ZR - Zahl der Rohre");
// endregion code
                break;
            case "41": case "42":
                // region code

                getLbReihenanordnung().setVisibility(View.INVISIBLE);
                getActvReihenanordnung().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor01().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor01().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor02().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor02().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor03().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor03().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor03().setText("BL - Baulänge in mm");

                getLbBewertungsfaktor04().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor04().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor04().setText("BT - Bautiefe in mm");

                getLbBewertungsfaktor05().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor05().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor05().setText("T3 - Maß über 3 Teilungen in mm");

                getLbBewertungsfaktor06().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor06().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor06().setText("DR - Max. Abmessung des Rohres in mm");

                getLbBewertungsfaktor07().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor07().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor07().setText("dR - Max. Abmessung des Rohres in mm");

                getLbBewertungsfaktor08().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor08().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor08().setText("HV - Höhe des Verteilskanals in mm");

                getLbBewertungsfaktor09().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor09().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor09().setText("TV - Tiefe des Verteilskanals in mm");

                getLbBewertungsfaktor10().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor10().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor10().setText("RZ - Rohrregisterzahl");

                getLbBewertungsfaktor11().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor11().setVisibility(View.INVISIBLE);
// endregion code
                break;
            case "43":
                // region code

                getLbReihenanordnung().setVisibility(View.INVISIBLE);
                getActvReihenanordnung().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor01().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor01().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor02().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor02().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor03().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor03().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor03().setText("BL - Baulänge in mm");

                getLbBewertungsfaktor04().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor04().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor04().setText("BT - Bautiefe in mm");

                getLbBewertungsfaktor05().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor05().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor05().setText("T3 - Maß über 3 Teilungen in mm");

                getLbBewertungsfaktor06().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor06().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor06().setText("DR - Max. Abmessung des Rohres in mm");

                getLbBewertungsfaktor07().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor07().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor07().setText("dR - Max. Abmessung des Rohres in mm");

                getLbBewertungsfaktor08().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor08().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor08().setText("BV - Breite des Verteilskanals in mm");

                getLbBewertungsfaktor09().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor09().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor09().setText("TV - Tiefe des Verteilskanals in mm");

                getLbBewertungsfaktor10().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor10().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor10().setText("RZ - Rohrregisterzahl");

                getLbBewertungsfaktor11().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor11().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor11().setText("ZR - Zahl der Rohre");
// endregion code
                break;
            case "51":
                // region code
                getLbReihenanordnung().setVisibility(View.VISIBLE);
                getActvReihenanordnung().setVisibility(View.VISIBLE);
                getLbReihenanordnung().setText("Registeranordnung");

                getLbBewertungsfaktor01().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor01().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor02().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor02().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor02().setText("NA - Nabenabstand in mm");


                getLbBewertungsfaktor03().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor03().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor03().setText("BL - Baulänge in mm");

                getLbBewertungsfaktor04().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor04().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor04().setText("BT - Bautiefe in mm");

                getLbBewertungsfaktor05().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor05().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor05().setText("T1 - Teilungen in mm");

                getLbBewertungsfaktor06().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor06().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor07().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor07().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor08().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor08().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor09().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor09().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor10().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor10().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor11().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor11().setVisibility(View.INVISIBLE);
                // endregion code
                break;
            case "52":
                // region code
                getLbReihenanordnung().setVisibility(View.VISIBLE);
                getActvReihenanordnung().setVisibility(View.VISIBLE);
                getLbReihenanordnung().setText("Registeranordnung");

                getLbBewertungsfaktor01().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor01().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor02().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor02().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor02().setText("NA - Nabenabstand in mm");


                getLbBewertungsfaktor03().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor03().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor03().setText("BL - Baulänge in mm");

                getLbBewertungsfaktor04().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor04().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor04().setText("BT - Bautiefe in mm");

                getLbBewertungsfaktor05().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor05().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor05().setText("T1 - Teilungen in mm");

                getLbBewertungsfaktor06().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor06().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor07().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor07().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor08().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor08().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor08().setText("RZ - Anz. der Rohrregister");

                getLbBewertungsfaktor09().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor09().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor10().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor10().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor11().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor11().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor11().setText("SZ - Segmentzahl");

                // endregion code
                break;
            case "61":
                // region code
                getLbReihenanordnung().setVisibility(View.VISIBLE);
                getActvReihenanordnung().setVisibility(View.VISIBLE);

                getLbBewertungsfaktor01().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor01().setVisibility(View.VISIBLE);

                getLbBewertungsfaktor02().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor02().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor02().setText("NA - Nabenabstand in mm");


                getLbBewertungsfaktor03().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor03().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor03().setText("BL - Baulänge in mm");

                getLbBewertungsfaktor04().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor04().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor04().setText("BT - Bautiefe in mm");

                getLbBewertungsfaktor05().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor05().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor05().setText("T3 - Maß über 3 Teilungen in mm");

                getLbBewertungsfaktor06().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor06().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor07().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor07().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor08().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor08().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor09().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor09().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor10().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor10().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor10().setText("T10 - Maß über 10 Teilung in mm");

                getLbBewertungsfaktor11().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor11().setVisibility(View.INVISIBLE);

                // endregion code
                break;
            case "71":
                // region code
                getLbReihenanordnung().setVisibility(View.INVISIBLE);
                getActvReihenanordnung().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor01().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor01().setVisibility(View.VISIBLE);

                getLbBewertungsfaktor02().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor02().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor02().setText("BLR - berippte Baulänge in mm");


                getLbBewertungsfaktor03().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor03().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor03().setText("BT - Bautiefe in mm");

                getLbBewertungsfaktor04().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor04().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor04().setText("ZR - Zahl der Rohre");

                getLbBewertungsfaktor05().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor05().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor05().setText("T10 - Maß über 10 Teilungen in mm");

                getLbBewertungsfaktor06().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor06().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor07().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor07().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor08().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor08().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor09().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor09().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor10().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor10().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor11().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor11().setVisibility(View.INVISIBLE);

                // endregion code
                break;
            case "72":case "75":
                // region code
                getLbReihenanordnung().setVisibility(View.INVISIBLE);
                getActvReihenanordnung().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor01().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor01().setVisibility(View.VISIBLE);

                getLbBewertungsfaktor02().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor02().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor02().setText("BLR - berippte Baulänge in mm");


                getLbBewertungsfaktor03().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor03().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor03().setText("BT - Bautiefe in mm");

                getLbBewertungsfaktor04().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor04().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor04().setText("ZR - Zahl der Rohre");

                getLbBewertungsfaktor05().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor05().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor05().setText("T10 - Maß über 10 Teilungen in mm");

                getLbBewertungsfaktor06().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor06().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor06().setText("S2 - Lufteintritt in mm");

                getLbBewertungsfaktor07().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor07().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor07().setText("S3 - Luftaustritt in mm");

                getLbBewertungsfaktor08().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor08().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor09().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor09().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor09().setText("SK - Höhe Konvektor über Boden in mm");

                getLbBewertungsfaktor10().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor10().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor10().setText("S4 - Schachthöhe ");

                getLbBewertungsfaktor11().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor11().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor11().setText("ST - Schachttiefe in mm");

                // endregion code
                break;
            case "73": case "76":
                // region code
                getLbReihenanordnung().setVisibility(View.INVISIBLE);
                getActvReihenanordnung().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor01().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor01().setVisibility(View.VISIBLE);

                getLbBewertungsfaktor02().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor02().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor02().setText("BLR - berippte Baulänge in mm");


                getLbBewertungsfaktor03().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor03().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor03().setText("BT - Bautiefe in mm");

                getLbBewertungsfaktor04().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor04().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor04().setText("ZR - Zahl der Rohre");

                getLbBewertungsfaktor05().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor05().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor05().setText("T10 - Maß über 10 Teilungen in mm");

                getLbBewertungsfaktor06().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor06().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor06().setText("S2 - Lufteintritt in mm");

                getLbBewertungsfaktor07().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor07().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor07().setText("S3 - Luftaustritt in mm");

                getLbBewertungsfaktor08().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor08().setVisibility(View.INVISIBLE);
                getLbBewertungsfaktor08().setText("S4 - Schachthöhe in mm");

                getLbBewertungsfaktor09().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor09().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor09().setText("VBH - Verkleidungshöhe in mm");

                getLbBewertungsfaktor10().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor10().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor10().setText("VBL - Verkleidungslänge in mm");

                getLbBewertungsfaktor11().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor11().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor11().setText("VBT - Verkleidungstiefe in mm");

                // endregion code
                break;
            case "74":
                // region code
                getLbReihenanordnung().setVisibility(View.INVISIBLE);
                getActvReihenanordnung().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor01().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor01().setVisibility(View.VISIBLE);

                getLbBewertungsfaktor02().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor02().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor02().setText("BLR - berippte Baulänge in mm");


                getLbBewertungsfaktor03().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor03().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor03().setText("BT - Bautiefe in mm");

                getLbBewertungsfaktor04().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor04().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor04().setText("ZR - Zahl der Rohre");

                getLbBewertungsfaktor05().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor05().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor05().setText("T10 - Maß über 10 Teilungen in mm");

                getLbBewertungsfaktor06().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor06().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor07().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor07().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor07().setText("KH - Kanalhöhe in mm");

                getLbBewertungsfaktor08().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor08().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor08().setText("KB - Kanalbreite in mm");

                getLbBewertungsfaktor09().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor09().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor09().setText("SK - Höhe Konvektor über Boden in mm");

                getLbBewertungsfaktor10().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor10().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor10().setText("S4 - Schachthöhe ");

                getLbBewertungsfaktor11().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor11().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor11().setText("ST - Schachttiefe in mm");

                // endregion code
                break;
            case "82":
                // region code
                getLbReihenanordnung().setVisibility(View.INVISIBLE);
                getActvReihenanordnung().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor01().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor01().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor01().setText("ZR - Zahl der Rohre");

                getLbBewertungsfaktor02().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor02().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor02().setText("RL - berippte Länge in mm");


                getLbBewertungsfaktor03().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor03().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor03().setText("BL - Baulänge in mm");

                getLbBewertungsfaktor04().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor04().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor04().setText("RZ - Anzahl der Rohrregister");

                getLbBewertungsfaktor05().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor05().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor05().setText("T1 - Teilung in mm");

                getLbBewertungsfaktor06().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor06().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor06().setText("KD - Kernrohrdurchmesser in mm");

                getLbBewertungsfaktor07().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor07().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor07().setText("RS - Rippenstärke in mm");

                getLbBewertungsfaktor08().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor08().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor08().setText("dR - min Maß Rippe in mm");

                getLbBewertungsfaktor09().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor09().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor09().setText("DR - max Maß Rippe in mm");

                getLbBewertungsfaktor10().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor10().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor10().setText("HW - Höhe der Wellen in mm");

                getLbBewertungsfaktor11().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor11().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor11().setText("RT - Rippentielung in mm (über 10 Teilungen)");

                // endregion code
                break;
            case "81":
                // region code
                getLbReihenanordnung().setVisibility(View.INVISIBLE);
                getActvReihenanordnung().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor01().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor01().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor02().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor02().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor02().setText("RL - berippte Länge in mm");


                getLbBewertungsfaktor03().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor03().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor03().setText("BL - Baulänge in mm");

                getLbBewertungsfaktor04().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor04().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor05().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor05().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor06().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor06().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor06().setText("KD - Kernrohrdurchmesser in mm");

                getLbBewertungsfaktor07().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor07().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor07().setText("RS - Rippenstärke in mm");

                getLbBewertungsfaktor08().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor08().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor08().setText("dR - min Maß Rippe in mm");

                getLbBewertungsfaktor09().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor09().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor09().setText("DR - max Maß Rippe in mm");

                getLbBewertungsfaktor10().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor10().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor10().setText("HW - Höhe der Wellen in mm");

                getLbBewertungsfaktor11().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor11().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor11().setText("RT - Rippentielung in mm (über 10 Teilungen)");

                // endregion code
                break;
            case "83":
                // region code
                getLbReihenanordnung().setVisibility(View.INVISIBLE);
                getActvReihenanordnung().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor01().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor01().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor02().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor02().setVisibility(View.INVISIBLE);


                getLbBewertungsfaktor03().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor03().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor03().setText("LS - Länge in mm");

                getLbBewertungsfaktor04().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor04().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor05().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor05().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor06().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor06().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor06().setText("D1 - 1. Rohrmaß in mm");

                getLbBewertungsfaktor07().setVisibility(View.VISIBLE);
                getActvBewertungsfaktor07().setVisibility(View.VISIBLE);
                getLbBewertungsfaktor07().setText("D2 - 2. Rohrmaß in mm");

                getLbBewertungsfaktor08().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor08().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor09().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor09().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor10().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor10().setVisibility(View.INVISIBLE);

                getLbBewertungsfaktor11().setVisibility(View.INVISIBLE);
                getActvBewertungsfaktor11().setVisibility(View.INVISIBLE);

                // endregion code
                break;
        }
    }


    public void save() {
        setDataToModel();
        getBasemodel().save();
        FileHandler.getInstance().saveFile();
    }

    protected void loadData(){
        getEtGrundparameter().setText(getBasemodel().getGrundparameter());
        getActvReihenanordnung().setText(getBasemodel().getReihenanordnung());
        getActvBewertungsfaktor01().setText(getBasemodel().getBewertungsfaktor_01() == Messgeraet.INTEGER_NULLVALUE ? "" : getBasemodel().getBewertungsfaktor_01().toString());
        getActvBewertungsfaktor02().setText(getBasemodel().getBewertungsfaktor_02() == Messgeraet.INTEGER_NULLVALUE ? "" : getBasemodel().getBewertungsfaktor_02().toString());
        getActvBewertungsfaktor03().setText(getBasemodel().getBewertungsfaktor_03() == Messgeraet.INTEGER_NULLVALUE ? "" : getBasemodel().getBewertungsfaktor_03().toString());
        getActvBewertungsfaktor04().setText(getBasemodel().getBewertungsfaktor_04() == Messgeraet.INTEGER_NULLVALUE ? "" : getBasemodel().getBewertungsfaktor_04().toString());
        getActvBewertungsfaktor05().setText(getBasemodel().getBewertungsfaktor_05() == Messgeraet.INTEGER_NULLVALUE ? "" : getBasemodel().getBewertungsfaktor_05().toString());
        getActvBewertungsfaktor06().setText(getBasemodel().getBewertungsfaktor_06() == Messgeraet.INTEGER_NULLVALUE ? "" : getBasemodel().getBewertungsfaktor_06().toString());
        getActvBewertungsfaktor07().setText(getBasemodel().getBewertungsfaktor_07() == Messgeraet.INTEGER_NULLVALUE ? "" : getBasemodel().getBewertungsfaktor_07().toString());
        getActvBewertungsfaktor08().setText(getBasemodel().getBewertungsfaktor_08() == Messgeraet.INTEGER_NULLVALUE ? "" : getBasemodel().getBewertungsfaktor_08().toString());
        getActvBewertungsfaktor09().setText(getBasemodel().getBewertungsfaktor_09() == Messgeraet.INTEGER_NULLVALUE ? "" : getBasemodel().getBewertungsfaktor_09().toString());
        getActvBewertungsfaktor10().setText(getBasemodel().getBewertungsfaktor_10() == Messgeraet.INTEGER_NULLVALUE ? "" : getBasemodel().getBewertungsfaktor_10().toString());
        getActvBewertungsfaktor11().setText(getBasemodel().getBewertungsfaktor_11() == Messgeraet.INTEGER_NULLVALUE ? "" : getBasemodel().getBewertungsfaktor_11().toString());
    }

    protected void createLayout() {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {




    }

    public void setDataToModel() {

        // übergabe einfacher Werte
        getBasemodel().setGrundparameter(getEtGrundparameter().getText().toString());
        getBasemodel().setReihenanordnung(getActvReihenanordnung().getText().toString());
        getBasemodel().setBewertungsfaktor_01(getActvBewertungsfaktor01().getText().toString().equals("") ? Messgeraet.INTEGER_NULLVALUE :Integer.parseInt(getActvBewertungsfaktor01().getText().toString()));
        getBasemodel().setBewertungsfaktor_02(getActvBewertungsfaktor02().getText().toString().equals("") ? Messgeraet.INTEGER_NULLVALUE :Integer.parseInt(getActvBewertungsfaktor02().getText().toString()));
        getBasemodel().setBewertungsfaktor_03(getActvBewertungsfaktor03().getText().toString().equals("") ? Messgeraet.INTEGER_NULLVALUE :Integer.parseInt(getActvBewertungsfaktor03().getText().toString()));
        getBasemodel().setBewertungsfaktor_04(getActvBewertungsfaktor04().getText().toString().equals("") ? Messgeraet.INTEGER_NULLVALUE :Integer.parseInt(getActvBewertungsfaktor04().getText().toString()));
        getBasemodel().setBewertungsfaktor_05(getActvBewertungsfaktor05().getText().toString().equals("") ? Messgeraet.INTEGER_NULLVALUE :Integer.parseInt(getActvBewertungsfaktor05().getText().toString()));
        getBasemodel().setBewertungsfaktor_06(getActvBewertungsfaktor06().getText().toString().equals("") ? Messgeraet.INTEGER_NULLVALUE :Integer.parseInt(getActvBewertungsfaktor06().getText().toString()));
        getBasemodel().setBewertungsfaktor_07(getActvBewertungsfaktor07().getText().toString().equals("") ? Messgeraet.INTEGER_NULLVALUE :Integer.parseInt(getActvBewertungsfaktor07().getText().toString()));
        getBasemodel().setBewertungsfaktor_08(getActvBewertungsfaktor08().getText().toString().equals("") ? Messgeraet.INTEGER_NULLVALUE :Integer.parseInt(getActvBewertungsfaktor08().getText().toString()));
        getBasemodel().setBewertungsfaktor_09(getActvBewertungsfaktor09().getText().toString().equals("") ? Messgeraet.INTEGER_NULLVALUE :Integer.parseInt(getActvBewertungsfaktor09().getText().toString()));
        getBasemodel().setBewertungsfaktor_10(getActvBewertungsfaktor10().getText().toString().equals("") ? Messgeraet.INTEGER_NULLVALUE :Integer.parseInt(getActvBewertungsfaktor10().getText().toString()));
        getBasemodel().setBewertungsfaktor_11(getActvBewertungsfaktor11().getText().toString().equals("") ? Messgeraet.INTEGER_NULLVALUE :Integer.parseInt(getActvBewertungsfaktor11().getText().toString()));
    }

    // region properties
    public TextView getLbGrundparameter() {
        return lbGrundparameter;
    }

    public void setLbGrundparameter(TextView lbGrundparameter) {
        this.lbGrundparameter = lbGrundparameter;
    }

    public AutoCompleteTextView getEtGrundparameter() {
        return etGrundparameter;
    }

    public void setEtGrundparameter(AutoCompleteTextView etGrundparameter) {
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

    public AutoCompleteTextView getActvBewertungsfaktor02() {
        return actvBewertungsfaktor02;
    }

    public void setActvBewertungsfaktor02(AutoCompleteTextView actvBewertungsfaktor02) {
        this.actvBewertungsfaktor02 = actvBewertungsfaktor02;
    }

    public TextView getLbBewertungsfaktor03() {
        return lbBewertungsfaktor03;
    }

    public void setLbBewertungsfaktor03(TextView lbBewertungsfaktor03) {
        this.lbBewertungsfaktor03 = lbBewertungsfaktor03;
    }

    public AutoCompleteTextView getActvBewertungsfaktor03() {
        return actvBewertungsfaktor03;
    }

    public void setActvBewertungsfaktor03(AutoCompleteTextView actvBewertungsfaktor03) {
        this.actvBewertungsfaktor03 = actvBewertungsfaktor03;
    }

    public TextView getLbBewertungsfaktor04() {
        return lbBewertungsfaktor04;
    }

    public void setLbBewertungsfaktor04(TextView lbBewertungsfaktor04) {
        this.lbBewertungsfaktor04 = lbBewertungsfaktor04;
    }

    public AutoCompleteTextView getActvBewertungsfaktor04() {
        return actvBewertungsfaktor04;
    }

    public void setActvBewertungsfaktor04(AutoCompleteTextView actvBewertungsfaktor04) {
        this.actvBewertungsfaktor04 = actvBewertungsfaktor04;
    }

    public TextView getLbBewertungsfaktor05() {
        return lbBewertungsfaktor05;
    }

    public void setLbBewertungsfaktor05(TextView lbBewertungsfaktor05) {
        this.lbBewertungsfaktor05 = lbBewertungsfaktor05;
    }

    public AutoCompleteTextView getActvBewertungsfaktor05() {
        return actvBewertungsfaktor05;
    }

    public void setActvBewertungsfaktor05(AutoCompleteTextView actvBewertungsfaktor05) {
        this.actvBewertungsfaktor05 = actvBewertungsfaktor05;
    }

    public TextView getLbBewertungsfaktor06() {
        return lbBewertungsfaktor06;
    }

    public void setLbBewertungsfaktor06(TextView lbBewertungsfaktor06) {
        this.lbBewertungsfaktor06 = lbBewertungsfaktor06;
    }

    public AutoCompleteTextView getActvBewertungsfaktor06() {
        return actvBewertungsfaktor06;
    }

    public void setActvBewertungsfaktor06(AutoCompleteTextView actvBewertungsfaktor06) {
        this.actvBewertungsfaktor06 = actvBewertungsfaktor06;
    }

    public TextView getLbBewertungsfaktor07() {
        return lbBewertungsfaktor07;
    }

    public void setLbBewertungsfaktor07(TextView lbBewertungsfaktor07) {
        this.lbBewertungsfaktor07 = lbBewertungsfaktor07;
    }

    public AutoCompleteTextView getActvBewertungsfaktor07() {
        return actvBewertungsfaktor07;
    }

    public void setActvBewertungsfaktor07(AutoCompleteTextView actvBewertungsfaktor07) {
        this.actvBewertungsfaktor07 = actvBewertungsfaktor07;
    }

    public TextView getLbBewertungsfaktor08() {
        return lbBewertungsfaktor08;
    }

    public void setLbBewertungsfaktor08(TextView lbBewertungsfaktor08) {
        this.lbBewertungsfaktor08 = lbBewertungsfaktor08;
    }

    public AutoCompleteTextView getActvBewertungsfaktor08() {
        return actvBewertungsfaktor08;
    }

    public void setActvBewertungsfaktor08(AutoCompleteTextView actvBewertungsfaktor08) {
        this.actvBewertungsfaktor08 = actvBewertungsfaktor08;
    }

    public TextView getLbBewertungsfaktor09() {
        return lbBewertungsfaktor09;
    }

    public void setLbBewertungsfaktor09(TextView lbBewertungsfaktor09) {
        this.lbBewertungsfaktor09 = lbBewertungsfaktor09;
    }

    public AutoCompleteTextView getActvBewertungsfaktor09() {
        return actvBewertungsfaktor09;
    }

    public void setActvBewertungsfaktor09(AutoCompleteTextView actvBewertungsfaktor09) {
        this.actvBewertungsfaktor09 = actvBewertungsfaktor09;
    }

    public TextView getLbBewertungsfaktor10() {
        return lbBewertungsfaktor10;
    }

    public void setLbBewertungsfaktor10(TextView lbBewertungsfaktor10) {
        this.lbBewertungsfaktor10 = lbBewertungsfaktor10;
    }

    public AutoCompleteTextView getActvBewertungsfaktor10() {
        return actvBewertungsfaktor10;
    }

    public void setActvBewertungsfaktor10(AutoCompleteTextView actvBewertungsfaktor10) {
        this.actvBewertungsfaktor10 = actvBewertungsfaktor10;
    }

    public TextView getLbBewertungsfaktor11() {
        return lbBewertungsfaktor11;
    }

    public void setLbBewertungsfaktor11(TextView lbBewertungsfaktor11) {
        this.lbBewertungsfaktor11 = lbBewertungsfaktor11;
    }

    public AutoCompleteTextView getActvBewertungsfaktor11() {
        return actvBewertungsfaktor11;
    }

    public void setActvBewertungsfaktor11(AutoCompleteTextView actvBewertungsfaktor11) {
        this.actvBewertungsfaktor11 = actvBewertungsfaktor11;
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    // endregion properties
}
