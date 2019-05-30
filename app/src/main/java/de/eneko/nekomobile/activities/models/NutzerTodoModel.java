package de.eneko.nekomobile.activities.models;

import java.util.stream.Collectors;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.beans.Liegenschaft;
import de.eneko.nekomobile.beans.Nutzer;
import de.eneko.nekomobile.beans.ToDo;
import de.eneko.nekomobile.controllers.Dict;

public class NutzerTodoModel extends Basemodel{

    private String bezeichnung;
    private String art;

    public NutzerTodoModel(ToDo bean) {
        super(bean);
    }



    @Override
    public ToDo getBean() {
        return (ToDo) super.getBean();
    }

    @Override
    public void save() {
        getBean().setBezeichnung(bezeichnung);
        getBean().setArt(art);
    }

    @Override
    public void load() {
        bezeichnung = getBean().getBezeichnung();
        art = getBean().getArt();
    }




    /*

    /*
    Anzahl der Racuhmelder, welche zu prüfen waren
     */
    public Integer getToDoCount(String pArt)
    {
            return getDoneCount(pArt) + getUndoneCount(pArt) + getWithErrorCount(pArt);
    }

    public Boolean isCompleted(String pArt){

       if(pArt.equals("RWM")){
            if (getToDoCount(pArt)== 0) {return false;}
            return getToDoCount(pArt) == (getWithErrorCount(pArt)+ getDoneCount(pArt));
       }else{
           Integer absolutUndoneCount = getBean().getMessgeraete().stream()
                   .filter(r -> !(r.getDefekt() || r.getStichtagValue() >= 0 || r.getAktuellValue() >= 0) && !r.getFunk())
                   .collect(Collectors.toList()).size();

           if (absolutUndoneCount == 0) {
               return true;
           }else
           {
               return false;
           }
       }
    }
    public Integer getProgressStatusImageResourceId(){
        if (getBean().getArt().equals(Dict.TODO_WARTUNG_RWM))
        {
            if (isCompleted("RWM")) {
                return R.drawable.icon_smoke_detector_green_ok;
            }else {
                return R.drawable.icon_smoke_detector_b;
            }
        }
        if (getBean().getArt().equals(Dict.TODO_ABLESUNG))
        {
            if (isCompleted("GER")) {
                return R.drawable.icon_ablesung_complete;
            }else {
                return R.drawable.icon_ablesung;
            }
        }


        return 0;
    }



    public Integer getUndoneCount(String pArt)
    {
        if (pArt.equals("RWM"))
        {
            return getBean().getRauchmelder().stream().filter(r -> r.getBaseModel().getUnDone())
                    .collect(Collectors.toList()).size();
        }else {
            return getBean().getMessgeraete().stream()
                    .filter(r -> !(r.getDefekt() || r.getStichtagValue() >= 0 || r.getAktuellValue() >= 0) && !r.getFunk() && r.getArt().equals(pArt))
                    .collect(Collectors.toList()).size();
        }
    }

    public Integer getDoneCount(String pArt)
    {
        if (pArt.equals("RWM"))
        {
            return getBean().getRauchmelder().stream().filter(r -> r.getDone())
                    .collect(Collectors.toList()).size();
        }else{
        return getBean().getMessgeraete().stream()
                .filter(r -> r.isDone() && r.getArt().equals(pArt))
                .collect(Collectors.toList()).size();
        }
   }

    public Integer getWithErrorCount(String pArt)
    {
        if (pArt.equals("RWM"))
        {
            return getBean().getRauchmelder().stream().filter(r -> r.getWithError())
                    .collect(Collectors.toList()).size();
        }else {
            return getBean().getMessgeraete().stream()
                    .filter(r -> r.getDefekt() && !r.getFunk() && r.getArt().equals(pArt))
                    .collect(Collectors.toList()).size();
        }
    }

    public Integer getNewCount(String pArt)
    {
        if (pArt.equals("RWM"))
        {
            return getBean().getRauchmelder().stream().filter(r -> r.getNekoId().contains("new"))
                    .collect(Collectors.toList()).size();
        }else {
            return 0;
        }
     }




//    public Integer getAblesungImageResourceId(){
//        if (isCompleted("GER")) {
//            return R.drawable.icon_ablesung_complete;
//        }else {
//            return R.drawable.icon_ablesung;
//        }
//    }



    // endregion Messgeraet






    // region properties


//    public Nutzer getNutzer() {
//        return getBean().getNutzer();
//    }

    public Liegenschaft getLiegenschaft() {
        return getBean().getLiegenschaft();
    }



    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }
    // endregion properties
}
