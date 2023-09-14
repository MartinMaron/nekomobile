package de.eneko.nekomobile.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.eneko.nekomobile.MainActivity;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.beans.hlpta.FunkCheck_Austauschgrund;
import de.eneko.nekomobile.beans.hlpta.FunkModel;
import de.eneko.nekomobile.beans.hlpta.Webes_Grundparameter;
import de.eneko.nekomobile.beans.hlpta.ZaehlerModel;

public class Dict {
    // Konstanten
    public static final String TODO_WARTUNG_RWM = "WAR_RWM";
    public static final String TODO_MONTAGE_HKV = "MON_HKV";
    public static final String TODO_MONTAGE_WMZ = "MON_WMZ";
    public static final String TODO_MONTAGE_WWZ = "MON_WWZ";
    public static final String TODO_MONTAGE_KWZ = "MON_KWZ";
    public static final String TODO_MONTAGE_RWM = "MON_RWM";
    public static final String TODO_ABLESUNG = "ABL_ALL";
    public static final String TODO_ZWISCHENABLESUNG = "ABL_ZWI";
    public static final String TODO_FUNK_CHECK = "FUN_CHK";
    public static final String TODO_INFO_RWM = "INF_RWM";
    public static final String TODO_INFO_GER = "INF_GER";


    private static Dict instance = null;
    private Dict(){}

    public static synchronized Dict getInstance()
    {
        if (instance == null) {instance = new Dict();}
        return instance;
    }



    public void initializeHelpers(MainActivity mainActivity) {
        String[] _zaehlermodele = mainActivity.getResources().getStringArray(R.array.zaehler_modele);
        mZaehlerModels.clear();
        for (String strTemp : _zaehlermodele){
            String[] _line = strTemp.split("@");
            mZaehlerModels.add(new ZaehlerModel(_line[0],_line[1],_line[2], _line[3],_line[4],_line[5],_line[6],_line[7],_line[8],_line[9],_line[10]));
        }

        String[] _funkmodele = mainActivity.getResources().getStringArray(R.array.funk_modele);
        mFunkModels.clear();
        for (String strTemp : _funkmodele){
            String[] _line = strTemp.split(";");
            mFunkModels.add(new FunkModel(_line[0],_line[1],_line[2]));
        }

        String[] _FunkAustauschGrund = mainActivity.getResources().getStringArray(R.array.funk_austausch_grund);
        mFunkAustauschGrunds.clear();
        for (String strTemp : _FunkAustauschGrund){
            String[] _line = strTemp.split(";");
            mFunkAustauschGrunds.add(new FunkCheck_Austauschgrund(_line[0],_line[1]));
        }

        String[] _WebesGrundParameter = mainActivity.getResources().getStringArray(R.array.webes_grundparameter);
        mWebesGrundparameters.clear();
        for (String strTemp : _WebesGrundParameter){
            String[] _line = strTemp.split(";");
            mWebesGrundparameters.add(new Webes_Grundparameter(_line[0],_line[1]));
        }

    }

    private ArrayList<FunkCheck_Austauschgrund> mFunkAustauschGrunds = new ArrayList<>();
    public ArrayList<FunkCheck_Austauschgrund> getFunkAustauschGrunds() {
        return mFunkAustauschGrunds;
    }
    public FunkCheck_Austauschgrund getFunkAustauschGrund(String Id) {
        FunkCheck_Austauschgrund ret_val = null;
        if (! Id.equals("")) {
            ArrayList<FunkCheck_Austauschgrund> q2 = new ArrayList<FunkCheck_Austauschgrund>();
            List<FunkCheck_Austauschgrund> q = Dict.getInstance().getFunkAustauschGrunds().stream()
                    .filter(item -> item.getId().equals(Id))
                    .collect(Collectors.toList());
            ret_val = q.size() > 0 ? q.get(0) : null ;
        }
        return  ret_val;
    }

    private ArrayList<ZaehlerModel> mZaehlerModels = new ArrayList<>();
    public ArrayList<ZaehlerModel> getZaehlerModels() {
        return mZaehlerModels;
    }
    public ZaehlerModel getZaehlerModel(Integer modelId) {
        ZaehlerModel ret_val = null;
        if (modelId != 0) {
            ArrayList<ZaehlerModel> q2 = new ArrayList<ZaehlerModel>();
            List<ZaehlerModel> q = Dict.getInstance().getZaehlerModels().stream()
                    .filter(item -> item.getId().equals(modelId))
                    .collect(Collectors.toList());
            ret_val = q.size() > 0 ? q.get(0) : null ;
        }
        return  ret_val;
    }

    private ArrayList<FunkModel> mFunkModels = new ArrayList<>();
    public ArrayList<FunkModel> getFunkModels() {
        return mFunkModels;
    }
    public FunkModel getFunkModel(String funkModelId) {
        FunkModel ret_val = null;
        if (!funkModelId.equals("")) {
            List<FunkModel> q = Dict.getInstance().getFunkModels().stream()
                    .filter(item -> item.getId().equals(funkModelId))
                    .collect(Collectors.toList());
            ret_val = q.size() > 0 ? q.get(0) : null ;
        }
        return  ret_val;
    }

    private ArrayList<Webes_Grundparameter> mWebesGrundparameters = new ArrayList<>();
    public ArrayList<Webes_Grundparameter> getWebesGrundparameters() {
        return mWebesGrundparameters;
    }
    public Webes_Grundparameter getWebesGrundparameter(String id) {
        Webes_Grundparameter ret_val = null;
        if (!id.equals("")) {
            List<Webes_Grundparameter> q = Dict.getInstance().getWebesGrundparameters().stream()
                    .filter(item -> item.getId().equals(id))
                    .collect(Collectors.toList());
            ret_val = q.size() > 0 ? q.get(0) : null ;
        }
        return  ret_val;
    }

}


