package de.eneko.nekomobile.beans.sontex;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.beans.BaseObject;
import de.eneko.nekomobile.beans.ItoXmlElement;

public class Info extends BaseObject implements ItoXmlElement {
    private static final String TAG = Info.class.getName();
    private String mHint;
    private String mUser;


    public Info(String pHint, String pUser) {
        super();
        mHint = pHint;
        mUser = pUser;
     }
    public Info() {
        super();
    }

    @Override
    protected Basemodel createBaseObject() {
        return null;
    }

    @Override
    public Element toXmlElement(Document document) {
        Element ret_val = document.createElement("Info");
        try {
            CreateTextNode(ret_val,"Hint", mHint.toString());
            CreateTextNode(ret_val,"User", mUser.toString());
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
                        case "User":
                            mUser = getString(propElement);
                            break;
                        case "Hint" :
                            mHint = getString(propElement);
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

    public String getHint() {
        return mHint;
    }

    public String getUser() {
        return mUser;
    }


    // endregion properties




}
