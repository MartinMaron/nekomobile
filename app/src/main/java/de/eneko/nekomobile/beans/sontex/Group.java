package de.eneko.nekomobile.beans.sontex;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.beans.BaseObject;
import de.eneko.nekomobile.beans.ItoXmlElement;

public class Group extends BaseObject implements ItoXmlElement {
    private static final String TAG = Group.class.getName();
    private String mCaption;
    private Info mInfo;


    public Group(String pCaption, Info pInfo) {
        super();
        mCaption = pCaption;
        mInfo = pInfo;
    }

    public Group(String pCaption, String pUser) {
        super();
        mCaption = pCaption;
        mInfo = new Info("", pUser);
    }


    @Override
    protected Basemodel createBaseObject() {
        return null;
    }

    @Override
    public Element toXmlElement(Document document) {
        Element ret_val = document.createElement("Group");
        try {
            CreateTextNode(ret_val,"Caption", mCaption.toString());
            ret_val.appendChild(mInfo.toXmlElement(document));
        } catch (Exception e) {
            Log.e(TAG, "export error.", e);
        }
        return ret_val;
    }


    public void updateFromXmlElement(Element element) {
        try{
            NodeList nodeList = element.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element propElement = (Element) node;
                    switch (propElement.getNodeName()) {
                        case "Caption":
                            mCaption = getString(propElement);
                            break;
                        case "Hint" :
                            mInfo = new Info();
                            mInfo.updateFromXmlElement(propElement);
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

    public String getCaption() {
        return mCaption;
    }

    public Info getInfo() {
        return mInfo;
    }


    // endregion properties




}
