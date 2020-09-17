package de.eneko.nekomobile.activities.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.beans.Liegenschaft;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.beans.Nutzer;
import de.eneko.nekomobile.beans.ToDo;
import de.eneko.nekomobile.controllers.Dict;

public class LiegenschaftModel extends Basemodel{
    private Date mStart;
    private Date mEnde;
    private String mArt;
    private String mBemerkung;
    private String mAdresse;
    private String mPlZ;
    private Integer mSortNo;
    private String mNotizMitarbeiter;

    public LiegenschaftModel(Object bean) {
        super(bean);
    }

    @Override
    public void save() {
        getBean().setNotizMitarbeiter(mNotizMitarbeiter);
    }

    @Override
    public void load() {
        mAdresse = getBean().getAdresse();
        mArt = getBean().getArt();
        mBemerkung = getBean().getBemerkung();
        mEnde = getBean().getEnde();
        mPlZ = getBean().getPlZ();
        mStart = getBean().getStart();
        mSortNo =getBean().getSortNo();
        mNotizMitarbeiter= getBean().getNotizMitarbeiter();
        setDataLoaded(true);
    }

    @Override
    public Liegenschaft getBean() {
        return (Liegenschaft) super.getBean();
    }


    public String getAdresseDisplay(){
        return getAdresse().replace("\n",",  ");
    }

    public String getTerminDisplay(){
      return new SimpleDateFormat("HH:mm").format(getBean().getStart()) + "-" +
              new SimpleDateFormat("HH:mm").format(getBean().getEnde());
    }

    public Boolean hasAblesung(){
        return getBean().getNutzers().stream().filter(r -> r.getBaseModel().hasAblesung())
                .collect(Collectors.toList()).size() > 0;
    }

    public Boolean hasMontage(){
        return getBean().getNutzers().stream().filter(r -> r.getBaseModel().hasMontage())
                .collect(Collectors.toList()).size() > 0;
    }
    public Boolean hasRwmMontage(){
        return getBean().getNutzers().stream().filter(r -> r.getBaseModel().hasRwmMontage())
                .collect(Collectors.toList()).size() > 0;
    }
    public Boolean hasRwmWartung(){
        return getBean().getNutzers().stream().filter(r -> r.getBaseModel().hasRwmWartung())
                .collect(Collectors.toList()).size() > 0;
    }
    public Boolean hasFunkcheck(){
        return getBean().getNutzers().stream().filter(r -> r.getBaseModel().hasFunkcheck())
                .collect(Collectors.toList()).size() > 0;
    }
    public List<Messgeraet> getNutzerMessgaereteByArt(String pArt){
        List<Messgeraet> qMessgeraet = new ArrayList<Messgeraet>();

        qMessgeraet.addAll(getNutzerMessgaerete().stream()
                                .filter(r -> r.getArt().equals(pArt))
                                .collect(Collectors.toList()));

        return qMessgeraet;
    }

    public List<Messgeraet> getNutzerMessgaerete(){
        List<Messgeraet> qMessgeraet = new ArrayList<Messgeraet>();
        for(Nutzer nutzer: getBean().getNutzers())
        {
            for(ToDo todo : nutzer.getToDos()) {
                qMessgeraet.addAll(
                        todo.getMessgeraete().stream().collect(Collectors.toList())
                );
            }
        }
        return qMessgeraet;
    }



    public Boolean isCompleted(String pArt){


            Integer absolutUndoneCount_RWM = getBean().getNutzers().stream()
                    .filter(r -> r.getBaseModel().isCompleted())
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




    // region properties



    public Integer getSortNo() {
        return mSortNo;
    }

    public Date getStart() {
        return mStart;
    }

    public void setStart(Date start) {
        mStart = start;
    }

    public Date getEnde() {
        return mEnde;
    }

    public void setEnde(Date ende) {
        mEnde = ende;
    }

    public String getArt() {
        return mArt;
    }

    public void setArt(String art) {
        mArt = art;
    }

    public String getBemerkung() {
        if (mBemerkung.replace("\n","").trim().isEmpty()){
            return "";
        }
        return mBemerkung;
    }

    public void setBemerkung(String bemerkung) {
        mBemerkung = bemerkung;
    }

    public String getAdresse() {
        return mAdresse;
    }

    public void setAdresse(String adresse) {
        mAdresse = adresse;
    }

    public String getPlZ() {
        return mPlZ;
    }

    public void setPlZ(String plZ) {
        mPlZ = plZ;
    }

    public String getNotizMitarbeiter() {
        return mNotizMitarbeiter;
    }

    public void setNotizMitarbeiter(String notizMitarbeiter) {
        mNotizMitarbeiter = notizMitarbeiter;
    }

    // endregion
}
