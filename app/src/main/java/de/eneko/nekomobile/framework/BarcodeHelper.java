package de.eneko.nekomobile.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.beans.hlpta.FunkModel;
import de.eneko.nekomobile.beans.hlpta.ZaehlerModel;
import de.eneko.nekomobile.controllers.Dict;

public class BarcodeHelper {
    private String mBarcode;
    public BarcodeHelper(String barcode){
        mBarcode = barcode;
    }

    public ReturnCode getReturnCode(){
        ReturnCode ret_val = new ReturnCode("","");
        ret_val.setGeraetenummer(mBarcode);

        //eigene Aufkleber
        if (mBarcode.startsWith("NEKONEKO")) {
            int nekoID =  Integer.parseInt(mBarcode.replaceAll("NEKO", ""));
            List<ZaehlerModel> q = Dict.getInstance().getZaehlerModels()
                    .stream()
                    .filter(r -> r.getId() == nekoID)
                    .collect(Collectors.toList());
                    if (q.size() > 0 ) {
                        ret_val.setArtikelnummer(q.get(0).getArtikelnummer());
                    }
        }

        //3D Barcode vom Sontex
        if (mBarcode.trim().startsWith("http://qr.tefm.ch/")){
            String[] retString = mBarcode.split("&");
            for (String _line : retString){
                _line = _line.trim().replace("http://qr.tefm.ch/","");
                _line = _line.trim().replace("?","");
                _line = _line.trim().replace("http://qr.tefm.ch/","");
                if (_line.startsWith("rub=")) ret_val.setArtikelnummer(_line.replace("rub=",""));
                if (_line.contains("NS=")){
                    _line = _line.substring(_line.indexOf("NS="));
                }
                if (_line.startsWith("NS="))ret_val.setGeraetenummer(_line.replace("NS=",""));
            }
        }

        //3D Barcode vom Sontex
        if (mBarcode.trim().startsWith("8SON22")){
            ret_val.setGeraetenummer(mBarcode.trim().replace("8SON22",""));
        }

        //3D Barcode vom Qundis
        if (mBarcode.trim().startsWith("P") && mBarcode.length() > 50 ){
            String[] retString = mBarcode.split("\\+");
            for (String _line : retString){
                if (_line.startsWith("P")) ret_val.setArtikelnummer(_line.replace("P",""));
                if (_line.startsWith("S")) ret_val.setGeraetenummer(_line.replace("S",""));
            }

//            ret_val.setArtikelnummer(retString[0].substring(1));
//            ret_val.setGeraetenummer(retString[6].substring(1));
        }

        //2D Barcode vom Sontex Rauchmelder
        if (mBarcode.trim().startsWith("SON;")){
            String[] retString = mBarcode.split(";");
            ret_val.setGeraetenummer(retString[1]);
            ret_val.setArtikelnummer("1353");  //Ei6500 Typ C Ferninspektion nach DIN 14676
        }
        if (mBarcode.trim().startsWith("EIE;")){
            String[] retString = mBarcode.split(";");
            ret_val.setGeraetenummer(retString[1]);
            ret_val.setArtikelnummer("1530");  //Ei6500 Typ C Ferninspektion nach DIN 14676
        }

        return  ret_val;
    }


    public class ReturnCode{
        private String mArtikelnummer;
        private String mGeraetenummer;

        public ReturnCode(String pArtikelnummer, String pGeraeteNummer){
            mArtikelnummer = pArtikelnummer;
            mGeraetenummer = pGeraeteNummer;
        }

        public String getArtikelnummer() {
            return mArtikelnummer;
        }

        public void setArtikelnummer(String artikelnummer) {
            mArtikelnummer = artikelnummer;
        }

        public String getGeraetenummer() {
            return mGeraetenummer;
        }

        public void setGeraetenummer(String geraetenummer) {
            mGeraetenummer = geraetenummer;
        }

        public FunkModel getFunkModel(){
           List<FunkModel> q = Dict.getInstance().getFunkModels()
                    .stream()
                    .filter(r -> r.getArtikelnummer().equals(mArtikelnummer))
                    .collect(Collectors.toList());
           return q.size() > 0 ? q.get(0): null ;
        }
        public ZaehlerModel getZaehlerModel(){
            List<ZaehlerModel> q = Dict.getInstance().getZaehlerModels()
                    .stream()
                    .filter(r -> r.getArtikelnummer().equals(mArtikelnummer))
                    .collect(Collectors.toList());
            return q.size() > 0 ? q.get(0): null ;
        }




    }




}
