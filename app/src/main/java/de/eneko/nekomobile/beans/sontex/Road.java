package de.eneko.nekomobile.beans.sontex;

import android.util.Log;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Date;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.beans.BaseObject;
import de.eneko.nekomobile.beans.ItoXmlElement;

public class Road extends BaseObject implements ItoXmlElement {
    private static final String TAG = Road.class.getName();
    private String mVersion;
    private Date mSaveDate;
    private SavedBy mSavedBy;

    public Road() {
        super();
        mVersion = GlobalConst.SONTEX_ROAD_VERSION;
        mSavedBy = new SavedBy();
        mSaveDate = new Date();
    }

    @Override
    protected Basemodel createBaseObject() {
        return null;
    }


    @Override
    public Element toXmlElement(Document document) {
        Element ret_val = document.createElement("Road");
        try {
            Attr attr = document.createAttribute("Version");
            attr.setValue(mVersion);
            ret_val.setAttributeNode(attr);
            CreateSontexDateTimeNode(ret_val,"SaveDate", mSaveDate);
            ret_val.appendChild(mSavedBy.toXmlElement(document));
        } catch (Exception e) {
            Log.e(TAG, "export error.", e);
        }

        return ret_val;
    }

    public void updateFromXmlElement(Element element) {
      try{
          this.mVersion = element.getAttributeNode("Version").getValue();
          NodeList nodeList = element.getChildNodes();
          for (int i = 0; i < nodeList.getLength(); i++) {
              Node node = nodeList.item(i);
              if (node.getNodeType() == Node.ELEMENT_NODE) {
                  Element propElement = (Element) node;
                  switch (propElement.getNodeName()) {
                      case "SavedBy":
                          mSavedBy = new SavedBy();
                          mSavedBy.updateFromXmlElement(propElement);
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
  // endregion properties




}
