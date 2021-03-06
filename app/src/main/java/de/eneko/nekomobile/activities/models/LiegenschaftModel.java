package de.eneko.nekomobile.activities.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import de.eneko.nekomobile.beans.Liegenschaft;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.beans.Nutzer;
import de.eneko.nekomobile.beans.ToDo;

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

            Integer absolutUndoneCount = getBean().getNutzers().stream()
                    .filter(r -> ! r.getBaseModel().isCompleted(pArt))
                    .collect(Collectors.toList()).size();

            List<Nutzer> n = getBean().getNutzers().stream()
                    .filter(r -> ! r.getBaseModel().isCompleted(pArt))
                    .collect(Collectors.toList());


            if (absolutUndoneCount == 0) {
                return true;
            }else
            {
                return false;
            }
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
