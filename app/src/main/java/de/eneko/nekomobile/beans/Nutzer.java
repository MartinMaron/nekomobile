package de.eneko.nekomobile.beans;



import android.text.TextUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import de.eneko.nekomobile.R;


    public class Nutzer implements InekoId, ItoXmlElement {
    private Date mStart;
    private Date mEnde;
    private String nekoId;
    private String mLage = "";
    private Integer mWohnungsnummer = 0;
    private Integer mNutzernummer = 0;
    private Boolean mAbwesend = false;
    private Boolean mRwmPflicht = false;
    private Boolean mRwmSelbst = false ;
    private String mRwmNeuerNutzer = "";
    private String mBemerkung ="";
    private String mNutzerName ="";
    private String telNummer ="";
    private final Liegenschaft mLiegenschaft;

    private List<ToDo> mToDos;

    public Nutzer(Liegenschaft liegenschaft) {
        mLiegenschaft = liegenschaft;
        mToDos = new ArrayList<ToDo>();
    }


    // region Xml

    @Override
    public Element toXmlElement(Document document) {
        return  null;
        // TODO: Implement ToXmlString
    }

    public void updateRouteFromXmlElement(Element element) {
        this.nekoId = element.getAttributeNode("nekoId").getValue();
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element propElement = (Element) node;
                switch (propElement.getNodeName()) {
                    case "start":
                        mStart = XmlHelper.getSipleLongDate(propElement);
                        break;
                    case "ende":
                        mEnde = XmlHelper.getSipleLongDate(propElement);
                        break;
                    case "lage":
                        mLage = XmlHelper.getString(propElement);
                        break;
                    case "wohnungsNummer":
                        mWohnungsnummer = XmlHelper.getInteger(propElement);
                        break;
                    case "nutzerNummer":
                        mNutzernummer = XmlHelper.getInteger(propElement);
                        break;
                    case "rwmPflicht":
                        mRwmPflicht = XmlHelper.getBoolean(propElement);
                        break;
                    case "rwmSelbst":
                        mRwmSelbst = XmlHelper.getBoolean(propElement);
                        break;
                    case "rwmNeuerNutzer":
                        mRwmNeuerNutzer = XmlHelper.getString(propElement);
                        break;
                    case "bemerkung":
                        mBemerkung = XmlHelper.getString(propElement);
                        break;
                    case "nutzerName":
                        mNutzerName = XmlHelper.getString(propElement);
                        break;
                    case "telNummer":
                        telNummer = XmlHelper.getString(propElement);
                        break;
                    case "todos":
                        NodeList todoNodeList = propElement.getChildNodes();
                        for (int j = 0; j < todoNodeList.getLength(); j++)
                        {
                            Node todoNode = todoNodeList.item(j);
                            if (todoNode.getNodeType() == Node.ELEMENT_NODE)
                            {
                                Element todoPropElement = (Element) todoNode;
                                ToDo todo = new ToDo(this);
                                todo.updateRouteFromXmlElement(todoPropElement);
                                mToDos.add(todo);
                            }
                        }
                        break;
                    default:
                        System.out.println(propElement.getNodeName() + ": keine bekannte Property");
                }
                System.err.print("W");
            }
        }

    }

    // endregion Xml


    public Boolean hasAblesung(){
        return mToDos.stream().filter(r -> r.getArt().equals("ABL_ALL"))
                .collect(Collectors.toList()).size() > 0;
    }
    public Boolean hasMontage(){
        return mToDos.stream().filter(r -> Arrays.asList(new String[]{"MON_HKV", "MON_WMZ","MON_WWZ","MON_KWZ"}).contains(r.getArt()))
                .collect(Collectors.toList()).size() > 0;
    }
    public Boolean hasRwmMontage(){
        return mToDos.stream().filter(r -> r.getArt().equals("MON_RWM"))
                .collect(Collectors.toList()).size() > 0;
    }
    public Boolean hasRwmWartung(){
        return mToDos.stream().filter(r -> r.getArt().equals("WAR_RWM"))
                .collect(Collectors.toList()).size() > 0;
    }
    public Boolean hasFunkcheck(){
        return mToDos.stream().filter(r -> r.getArt().equals("FUN_CHK"))
                .collect(Collectors.toList()).size() > 0;
    }



    public String getDisplay()
    {
        String retval = this.mNutzerName + " " + getWohnungsnummerMitLage();
        return  retval;
    }

    public String getWohnungsnummerMitLage() {
        String retval = String.format("%03d" , mWohnungsnummer) + "-" +
                String.format("%03d" , mNutzernummer);
        if(!TextUtils.isEmpty(mLage))
        {
            retval = retval + " " +  mLage;
        }
        return retval;
    }


    public Boolean isCompleted(){
        return false;
    }

    public Integer getStatusImageResourceId() {
        if (mAbwesend) {
            return R.drawable.nutzer_abwesend;
        } else if (isCompleted()) {
            return R.drawable.icon_ok_48;
        } else {
            return R.drawable.set_abwesend;
        }
    }

    public ToDo getRwmTodo(){
        return getToDoByArt("WAR_RWM");
    }

    public ToDo getToDoByArt(String pArt){
        return mToDos.stream().filter(el -> el.getArt().equals(pArt)).findFirst().orElse(null);
    }

    public Integer getRwmStatusImageResourceId() {
        return getRwmTodo() != null ? getRwmTodo().getRwmStatusImageResourceId():0;
    }


        // region properties
    @Override
    public String getNekoId() {
        return nekoId;
    }

    @Override
    public void setNekoId(String nekoId) {
        this.nekoId = nekoId;
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

    public String getLage() {
        return mLage;
    }

    public void setLage(String lage) {
        mLage = lage;
    }

    public Integer getWohnungsnummer() {
        return mWohnungsnummer;
    }

    public void setWohnungsnummer(Integer wohnungsnummer) {
        mWohnungsnummer = wohnungsnummer;
    }

    public Integer getNutzernummer() {
        return mNutzernummer;
    }

    public void setNutzernummer(Integer nutzernummer) {
        mNutzernummer = nutzernummer;
    }

    public Boolean getAbwesend() {
        return mAbwesend;
    }

    public void setAbwesend(Boolean abwesend) {
        mAbwesend = abwesend;
    }

    public Boolean getRwmPflicht() {
        return mRwmPflicht;
    }

    public void setRwmPflicht(Boolean rwmPflicht) {
        mRwmPflicht = rwmPflicht;
    }

    public Boolean getRwmSelbst() {
        return mRwmSelbst;
    }

    public void setRwmSelbst(Boolean rwmSelbst) {
        mRwmSelbst = rwmSelbst;
    }

    public String getRwmNeuerNutzer() {
        return mRwmNeuerNutzer;
    }

    public void setRwmNeuerNutzer(String rwmNeuerNutzer) {
        mRwmNeuerNutzer = rwmNeuerNutzer;
    }

    public String getBemerkung() {
        return mBemerkung;
    }

    public void setBemerkung(String bemerkung) {
        mBemerkung = bemerkung;
    }

    public String getNutzerName() {
        return mNutzerName;
    }

    public void setNutzerName(String nutzerName) {
        mNutzerName = nutzerName;
    }

    public String getTelNummer() {
        return telNummer;
    }

    public void setTelNummer(String telNummer) {
        this.telNummer = telNummer;
    }


    public List<ToDo> getToDos() {
        return mToDos;
    }

    public void setToDos(List<ToDo> toDos) {
        mToDos = toDos;
    }

    public Liegenschaft getLiegenschaft() {
        return mLiegenschaft;
    }


    // endregion properties
}
