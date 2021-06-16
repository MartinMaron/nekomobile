package de.eneko.nekomobile.beans;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.activities.models.LiegenschaftModel;
import de.eneko.nekomobile.activities.models.NutzerTodoModel;

public class ToDo extends BaseObject implements ItoXmlElement {
    private static final String TAG = Nutzer.class.getName();
    private String bezeichnung;
    private String art;
    private final Nutzer mNutzer;
    private final Liegenschaft mLiegenschaft;
    private List<Rauchmelder> mRauchmelder;
    private List<Messgeraet> mMessgeraete;

    public ToDo(Nutzer nutzer) {
        super();
        mNutzer = nutzer;
        mLiegenschaft = null;
        mRauchmelder = new ArrayList<Rauchmelder>();
        mMessgeraete = new ArrayList<Messgeraet>();
    }

    public ToDo(Liegenschaft liegenschaft) {
        super();
        mLiegenschaft = liegenschaft;
        mNutzer = null;
        mRauchmelder = new ArrayList<Rauchmelder>();
        mMessgeraete = new ArrayList<Messgeraet>();
    }

    @Override
    protected Basemodel createBaseObject() {
       if(mLiegenschaft != null){
           return new LiegenschaftModel(this);
       }
        if(mNutzer != null){
            return new NutzerTodoModel(this);
        }
        return null;
    }

    @Override
    public Basemodel getBaseModel() {
        if(super.getBaseModel() == null) baseModel = createBaseObject();
        return super.getBaseModel();
    }

    //

    public List<Messgeraet> getMessgeraete() {
        return mMessgeraete;
    }

    public List<Rauchmelder> getRauchmelder() {
        return mRauchmelder;
    }

    @Override
    public Element toXmlElement(Document document) {

        Element ret_val = document.createElement("todo");
        try{
            CreateTextNode(ret_val,"bezeichnung",bezeichnung);
            CreateTextNode(ret_val,"art",art);
            mRauchmelder.forEach(item -> ret_val.appendChild(item.toXmlElement(document)));
            mMessgeraete.forEach(item -> ret_val.appendChild(item.toXmlElement(document)));
        } catch (Exception e) {
            Log.e(TAG, "export error.", e);
        }
        return ret_val;
    }

    public void updateRouteFromXmlElement(Element element) {
        try{
            NodeList nodeList = element.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element propElement = (Element) node;
                switch (propElement.getNodeName()) {
                    case "bezeichnung":
                        bezeichnung = getString(propElement);
                        break;
                    case "art":
                        art = getString(propElement);
                        break;
                    case "RWM_Device":
                        Rauchmelder rwm = new Rauchmelder(this);
                        rwm.updateRouteFromXmlElement(propElement);
                        mRauchmelder.add(rwm);
                        break;
                    case "zaehler":
                        Messgeraet zaehler = new Messgeraet(this);
                        zaehler.updateRouteFromXmlElement(propElement);
                        mMessgeraete.add(zaehler);
                        break;
                    default:
                        Log.e(TAG, propElement.getNodeName() + ": keine bekannte Property");
                }
            }
        }
        } catch (Exception e) {
            Log.e(TAG, "import error.", e);
        }

    }


    public File getSontexFile() {
        if (mLiegenschaft != null) {
            return mLiegenschaft.getSontexFile();
        }
        if (mNutzer != null) {
            return mNutzer.getSontexFile();
        }
        return null;
    }
    public String getSontexGroupCaption() {
        if (mLiegenschaft != null) {
            return mLiegenschaft.getSontexGroupCaption();
        }
        if (mNutzer != null) {
            return mNutzer.getSontexGroupCaption();
        }
        return null;
    }
    public String getSontexInfoUser() {
        if (mLiegenschaft != null) {
            return mLiegenschaft.getSontexInfoUser();
        }
        if (mNutzer != null) {
            return mNutzer.getSontexInfoUser();
        }
        return null;
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

    public Nutzer getNutzer() {
        return mNutzer;
    }

    public Liegenschaft getLiegenschaft() {
        return mLiegenschaft;
    }


}
