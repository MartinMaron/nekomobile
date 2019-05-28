package de.eneko.nekomobile.activities.models;

import java.util.stream.Collectors;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.beans.Liegenschaft;
import de.eneko.nekomobile.beans.Nutzer;
import de.eneko.nekomobile.beans.ToDo;

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


    //region Rauchmelder
    /*
    noch zu prüfende Rauchmelder
     */
    public Integer getRwmWartungUndoneCount ()
    {
        return getBean().getRauchmelder().stream().filter(r -> r.getBaseModel().getUnDone())
                .collect(Collectors.toList()).size();
    }
    /*
    bereits geprüfte Rauchmelder
     */
    public Integer getRwmWartungDoneCount ()
    {
        return getBean().getRauchmelder().stream().filter(r -> r.getDone())
                .collect(Collectors.toList()).size();
    }
    /*
    fehlende oder zu ersetztende rwm
     */
    public Integer getRwmWartungWithErrorCount ()
    {
        return getBean().getRauchmelder().stream().filter(r -> r.getWithError())
                .collect(Collectors.toList()).size();
    }
    /*
    neu instalierte rwm
     */
    public Integer getRwmWartungNewCount ()
    {
        return getBean().getRauchmelder().stream().filter(r -> r.getNekoId().contains("new"))
                .collect(Collectors.toList()).size();
    }
    /*
    Anzahl der Racuhmelder, welche zu prüfen waren
     */
    public Integer getRwmToDoCount ()
    {
        return getRwmWartungDoneCount() + getRwmWartungUndoneCount() + getRwmWartungWithErrorCount();
    }

    public Boolean isRwmCompleted(){
        if(getRwmToDoCount()== 0) {return false;}
        return getRwmToDoCount() == (getRwmWartungWithErrorCount()+ getRwmWartungDoneCount());
    }
    public Integer getRwmStatusImageResourceId(){
        if (isRwmCompleted()) {
            return R.drawable.icon_smoke_detector_green_ok;
        }else {
            return R.drawable.icon_smoke_detector_b;
        }
    }


    // endregion Rauchmelder


    //region Ablesung
    /*
    noch zum Ablesen
     */
    public Integer getAblesungUndoneCount (String pArt)
    {
        return getBean().getMessgeraete().stream()
                .filter(r -> !(r.getDefekt() || r.getStichtagValue() >= 0 || r.getAktuellValue() >= 0) && !r.getFunk() && r.getArt().equals(pArt))
                .collect(Collectors.toList()).size();
    }
    /*
    bereits abgelesene
     */
    public Integer getAblesungDoneCount (String pArt)
    {
        return getBean().getMessgeraete().stream()
                .filter(r -> (r.getStichtagValue() >= 0 || r.getAktuellValue() >= 0) && !r.getFunk() && r.getArt().equals(pArt))
                .collect(Collectors.toList()).size();

    }

    /*
    defekte
     */
    public Integer getAblesungWithErrorCount (String pArt)
    {
        return getBean().getMessgeraete().stream()
                .filter(r -> r.getDefekt() && !r.getFunk() && r.getArt().equals(pArt))
                .collect(Collectors.toList()).size();
    }

    public Boolean isAblesungCompleted(){
//        Integer absolutDoneCount = getBean().getMessgeraete().stream()
//                .filter(r -> (r.getDefekt() || r.getStichtagValue() >= 0 || r.getAktuellValue() >= 0) && !r.getFunk())
//                .collect(Collectors.toList()).size();

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

    public Integer getAblesungImageResourceId(){
        if (isAblesungCompleted()) {
            return R.drawable.icon_ablesung_complete;
        }else {
            return R.drawable.icon_ablesung;
        }
    }



    // endregion Messgeraet






    // region properties


    public Nutzer getNutzer() {
        return getBean().getNutzer();
    }

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
