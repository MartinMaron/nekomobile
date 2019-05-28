package de.eneko.nekomobile.framework;

import java.util.List;
import java.util.stream.Collectors;

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
        if (mBarcode.trim().startsWith("http://qr.tefm.ch/")){
            String[] retString = mBarcode.split("&");
            for (String _line : retString){
              _line = _line.trim().replace("?","");
              _line = _line.trim().replace("http://qr.tefm.ch/","");
              if (_line.startsWith("rub="))ret_val.setArtikelnummer(_line.replace("rub=",""));
              if (_line.startsWith("NS="))ret_val.setGeraetenummer(_line.replace("NS=",""));
            }
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
