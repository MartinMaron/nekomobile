package de.eneko.nekomobile.activities.models;

import android.util.Log;

import java.util.stream.Collectors;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.beans.Liegenschaft;
import de.eneko.nekomobile.beans.ToDo;
import de.eneko.nekomobile.controllers.Dict;

public class NutzerTodoModel extends Basemodel{
    private static final String TAG = NutzerTodoModel.class.getName();
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
        setDataLoaded(true);
    }


    public Integer getToDoCount(String pArt)
    {
        return getDoneCount(pArt) + getUndoneCount(pArt) + getWithErrorCount(pArt);
    }

    public Boolean isCompleted(String pArt){
        boolean ret_val = false;
        if(pArt.equals("RWM")){
            if (getBean().getArt().equals("WAR_RWM")){
                ret_val = getToDoCount(pArt) == (getWithErrorCount(pArt)+ getDoneCount(pArt));
                if (getNewCount("RWM") > 0) {ret_val = true;}
                if (getToDoCount(pArt)== 0) {ret_val = false;}
                if (getBean().getNutzer().getRwmSelbst()) {ret_val = true;}
            }
            if (getBean().getArt().equals("MON_RWM")){
                ret_val = false;
                if (getNewCount("RWM") > 0) {ret_val = true;}
            }
        }

        if(pArt.equals("GER")){
           Integer absoluteCount = getBean().getMessgeraete().stream()
                   .collect(Collectors.toList()).size();

           Integer absolutUndoneCount = getBean().getMessgeraete().stream()
                   .filter(r -> r.isUnDone())
                   .collect(Collectors.toList()).size();

           ret_val = (absolutUndoneCount == 0 && absoluteCount !=0) || absoluteCount == 0;
        }
        if (getBean().getArt().contains("INF")) ret_val = true;

        return ret_val;
    }
    public Integer getProgressStatusImageResourceId(){
        if (getBean().getArt().equals(Dict.TODO_WARTUNG_RWM)) {
            if (this.getBean().getNutzer().getRwmSelbst()) {
                return R.drawable.icon_smoke_detector_denied;
            } else {
                if (isCompleted("RWM")) {
                    return R.drawable.icon_smoke_detector_green_ok;
                } else {
                    return R.drawable.icon_smoke_detector_b;
                }
            }
        }
        if (getBean().getArt().equals(Dict.TODO_MONTAGE_RWM)) {
            if (this.getBean().getNutzer().getRwmSelbst()) {
                return R.drawable.icon_smoke_detector_denied;
            } else {
                if (isCompleted("RWM")) {
                    return R.drawable.icon_rwm_montage_done;
                } else {
                    return R.drawable.icon_rwm_montage;
                }
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
        if (getBean().getArt().equals(Dict.TODO_ZWISCHENABLESUNG))
        {
            if (isCompleted("GER")) {
                return R.drawable.icon_ablesung_zwischen_complete;
            }else {
                return R.drawable.icon_ablesung_zwischen;
            }
        }
        if (getBean().getArt().equals(Dict.TODO_FUNK_CHECK))
        {
            if (isCompleted("GER")) {
                return R.drawable.icon_funk_check_complete;
            }else {
                return R.drawable.icon_funk_check;
            }
        }

        if (getBean().getArt().equals(Dict.TODO_MONTAGE_WWZ) || getBean().getArt().equals(Dict.TODO_MONTAGE_HKV) || getBean().getArt().equals(Dict.TODO_MONTAGE_WMZ) || getBean().getArt().equals(Dict.TODO_MONTAGE_KWZ))
        {
            if (isCompleted("GER")) {
                return R.drawable.icon_montage_ok;
            }else {
                return R.drawable.icon_montage;
            }
        }

        if (getBean().getArt().equals(Dict.TODO_INFO_RWM))
        {

            return R.drawable.ic_system_information;
        }
        if (getBean().getArt().equals(Dict.TODO_INFO_GER))
        {

            return R.drawable.ic_system_information;
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
            Object l = getBean().getMessgeraete().stream()
                    .filter(r -> r.isUnDone() && r.getArt().equals(pArt))
                    .collect(Collectors.toList());

            return getBean().getMessgeraete().stream()
                    .filter(r -> r.isUnDone() && r.getArt().equals(pArt))
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
                    .filter(r -> r.isWithError() && r.getArt().equals(pArt))
                    .collect(Collectors.toList()).size();
        }
    }

    public Integer getNewCount(String pArt)
    {
        if (pArt.equals("RWM"))
        {
            return getBean().getRauchmelder().stream().filter(r -> r.getNew())
                    .collect(Collectors.toList()).size();
        }else {
            return getBean().getMessgeraete().stream().filter(r -> r.getNew())
                    .collect(Collectors.toList()).size();
        }
     }







    // endregion Messgeraet






    // region properties




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
