package de.eneko.nekomobile.beans;

import android.util.Log;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.activities.models.LiegenschaftModel;
import de.eneko.nekomobile.controllers.SontexFileHandler;

public class Liegenschaft extends BaseObject implements ItoXmlElement, InekoId {
    private static final String TAG = Liegenschaft.class.getName();
    private String nekoId;
    private String bud_guid;
    private Date mStart;
    private Date mEnde;
    private int mSortNo;
    private int mZeitInMin;
    private String mArt;
    private String mBemerkung = "";
    private String mNotizMitarbeiter = "";
    private String mGoogleEventId;
    private double mLatitude;
    private double mLongitude;
    private List<ToDo> mToDos;
    private List<Nutzer> mNutzers;
    private List<Messgeraet> mMessgeraets;
    private String mAdresse;
    private String mPlZ;
    private final Route route;
    private Date mStichtag = new Date();
    private String mSontexFileName;


    public Route getRoute() {
        return route;
    }

    public Liegenschaft(Route route) {
        super();
        mToDos = new ArrayList<ToDo>();
        mNutzers = new ArrayList<Nutzer>();
        mMessgeraets = new ArrayList<Messgeraet>();
        this.route = route;
    }

    @Override
    protected Basemodel createBaseObject() {
        return new LiegenschaftModel(this) ;
    }

    @Override
    public LiegenschaftModel getBaseModel() {
        return (LiegenschaftModel) super.getBaseModel();
    }

    @Override
    public Element toXmlElement(Document document) {
        Element ret_val = document.createElement("Liegenschaft");
        try {
            if(this instanceof InekoId)
            {
                Attr attr = document.createAttribute("nekoId");
                attr.setValue(this.getNekoId());
                ret_val.setAttributeNode(attr);
            }

            Attr attr = document.createAttribute("BUD_GUID");
            attr.setValue(this.getBud_guid());
            ret_val.setAttributeNode(attr);

            CreateDateTimeNode(ret_val,"start" ,mStart);
            CreateDateTimeNode(ret_val,"ende" ,mEnde);
            CreateIntegerNode(ret_val,"sortNo",mSortNo);
            CreateIntegerNode(ret_val,"zeitInMin",mZeitInMin);
            CreateTextNode(ret_val,"art",mArt);
            CreateTextNode(ret_val,"bemerkung",mBemerkung);
            CreateTextNode(ret_val,"googleEventId",mGoogleEventId);
            CreateDoubleNode(ret_val,"latitude",mLatitude);
            CreateDoubleNode(ret_val,"longitude",mLongitude);
            CreateTextNode(ret_val,"adresse",mAdresse);
            CreateTextNode(ret_val,"plZ",mPlZ);
            CreateTextNode(ret_val,"notizMitarbeiter",mNotizMitarbeiter);
            CreateDateTextNode(ret_val, "stichtag", mStichtag);
            CreateTextNode(ret_val,"sontexFileName",mSontexFileName);

            Element element = document.createElement("todos");
            mToDos.forEach(item -> element.appendChild(item.toXmlElement(document)));
            ret_val.appendChild(element);

            Element nutzer_element = document.createElement("Nutzerliste");
            mNutzers.forEach(item -> nutzer_element.appendChild(item.toXmlElement(document)));
            ret_val.appendChild(nutzer_element);

            Element zaehler_element = document.createElement("Zaehlerliste");
            mMessgeraets.forEach(item -> zaehler_element.appendChild(item.toXmlElement(document)));
            ret_val.appendChild(zaehler_element);

        } catch (Exception e) {
            Log.e(TAG, "export error.", e);
        }

        return ret_val;
    }

    public void updateRouteFromXmlElement(Element element) {
      try{
        this.nekoId = element.getAttributeNode("nekoId").getValue();
        this.bud_guid = element.getAttributeNode("BUD_GUID").getValue();
          NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element propElement = (Element) node;
                switch (propElement.getNodeName()) {
                    case "start":
                        mStart = getSipleLongDate(propElement);
                        break;
                    case "ende":
                        mEnde = getSipleLongDate(propElement);
                        break;
                    case "sortNo":
                        mSortNo = getInteger(propElement);
                        break;
                    case "zeitInMin":
                        mZeitInMin = getInteger(propElement);
                        break;
                    case "art":
                        mArt = getString(propElement);
                        break;
                    case "bemerkung":
                        mBemerkung = getString(propElement);
                        break;
                    case "googleEventId":
                        mGoogleEventId = getString(propElement);
                        break;
                    case "latitude":
                        mLatitude = getDouble(propElement);
                        break;
                    case "longitude":
                        mLongitude = getDouble(propElement);
                        break;
                    case "adresse":
                        mAdresse = getString(propElement);
                        break;
                    case "plz":
                        mPlZ = getString(propElement);
                        break;
                    case "notizMitarbeiter":
                        mNotizMitarbeiter = getString(propElement);
                        break;
                    case "sontexFileName":
                        //mSontexFileName = getString(propElement);
                        break;
                    case "stichtag":
                        mStichtag = getSipleDate(propElement);
                        break;
                    case "Nutzerliste":
                        NodeList nutzernodeList = propElement.getChildNodes();
                        for (int j = 0; j < nutzernodeList.getLength(); j++)
                        {
                            Node nutzerNode = nutzernodeList.item(j);
                            if (nutzerNode.getNodeType() == Node.ELEMENT_NODE)
                            {
                                Element nutzerPropElement = (Element) nutzerNode;
                                Nutzer nutzer = new Nutzer(this);
                                nutzer.updateRouteFromXmlElement(nutzerPropElement);
                                mNutzers.add(nutzer);
                            }
                        }
                        break;
                    case "todos":
                        NodeList todos = propElement.getChildNodes();
                        for (int j = 0; j < todos.getLength(); j++)
                        {
                            Node todoNode = todos.item(j);
                            if (todoNode.getNodeType() == Node.ELEMENT_NODE)
                            {
                                Element todoPropElement = (Element) todoNode;
                                ToDo todo = new ToDo(this);
                                todo.updateRouteFromXmlElement(todoPropElement);
                                mToDos.add(todo);
                            }
                        }
                        break;
                    case "Zaehlerliste":
                        NodeList zaehlerList = propElement.getChildNodes();
                        for (int j = 0; j < zaehlerList.getLength(); j++)
                        {
                            Node zaehlerNode = zaehlerList.item(j);
                            if (zaehlerNode.getNodeType() == Node.ELEMENT_NODE)
                            {
                                Element zaehlerPropElement = (Element) zaehlerNode;
                                Messgeraet messgeraet = new Messgeraet(this);
                                messgeraet.updateRouteFromXmlElement(zaehlerPropElement);
                                mMessgeraets.add(messgeraet);
                            }
                        }
                        break;

                    default:
                        Log.e(TAG, propElement.getNodeName() + ": keine bekannte Property");
                        break;
                }
            }
        }
      } catch (Exception e) {
          Log.e(TAG, "import error.", e);
      }
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

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public List<Nutzer> getNutzers() {
        return mNutzers;
    }

    public void setNutzers(List<Nutzer> nutzers) {
        mNutzers = nutzers;
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

    public int getSortNo() {
        return mSortNo;
    }

    public void setSortNo(int sortNo) {
        mSortNo = sortNo;
    }

    public int getZeitInMin() {
        return mZeitInMin;
    }

    public void setZeitInMin(int zeitInMin) {
        mZeitInMin = zeitInMin;
    }

    public String getArt() {
        return mArt;
    }

    public void setArt(String art) {
        mArt = art;
    }

    public String getBemerkung() {
        return mBemerkung;
    }

    public void setBemerkung(String bemerkung) {
        mBemerkung = bemerkung;
    }

    public String getGoogleEventId() {
        return mGoogleEventId;
    }

    public void setGoogleEventId(String googleEventId) {
        mGoogleEventId = googleEventId;
    }

    public List<ToDo> getToDos() {
        return mToDos;
    }

    public void setToDos(List<ToDo> toDos) {
        mToDos = toDos;
    }
    public String getAdresse() {
        return mAdresse;
    }
    public String getAdresseOneLine() {
        return mAdresse.replace("\n",",");
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

    public String getBud_guid() {
        return bud_guid;
    }

    public void setBud_guid(String bud_guid) {
        this.bud_guid = bud_guid;
    }

    public String getNotizMitarbeiter() {
        return mNotizMitarbeiter;
    }

    public void setNotizMitarbeiter(String notizMitarbeiter) {
        mNotizMitarbeiter = notizMitarbeiter;
    }

    public File getSontexFile() {
         try {
            if (mSontexFileName == null) {
                String fileName = SontexFileHandler.getInstance().safeFileNameConverter(getAdresseOneLine());
                String imageFileName = fileName + "_";
                File storageDir = new File(GlobalConst.PATH_SONTEX);
                storageDir.mkdirs();
                File file = File.createTempFile(
                        imageFileName,  /* prefix */
                        ".xml",         /* suffix */
                        storageDir      /* directory */
                );

                SontexFileHandler.getInstance().CreateNewSontexFile(file);
                mSontexFileName = file.toString();
                return file;
            } else
            {
                File file = new File(mSontexFileName);
                return file;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getSontexGroupCaption() {
        return "Gesamtzähler";
    }
    public String getSontexInfoUser() {
        return "000";
    }


     // endregion properties




}
