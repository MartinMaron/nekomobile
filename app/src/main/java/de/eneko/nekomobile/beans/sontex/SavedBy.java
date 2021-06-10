package de.eneko.nekomobile.beans.sontex;

import android.util.Log;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.beans.BaseObject;
import de.eneko.nekomobile.beans.ItoXmlElement;

public class SavedBy extends BaseObject implements ItoXmlElement {
    private static final String TAG = SavedBy.class.getName();
    private String mSwName;
    private String mSwVersion;


    public SavedBy() {
        super();
        mSwName = GlobalConst.SONTEX_SAVEDBY_SWNAME;
        mSwVersion = GlobalConst.SONTEX_SAVEDBY_SWVERSION;
    }

    @Override
    protected Basemodel createBaseObject() {
        return null;
    }

    @Override
    public Element toXmlElement(Document document) {
        Element ret_val = document.createElement("SavedBy");
        try {
            Attr attrSwName = document.createAttribute("SwName");
            attrSwName.setValue(mSwName);
            ret_val.setAttributeNode(attrSwName);

            Attr attrSwVersion = document.createAttribute("SwVersion");
            attrSwVersion.setValue(mSwVersion);
            ret_val.setAttributeNode(attrSwVersion);
        } catch (Exception e) {
            Log.e(TAG, "export error.", e);
        }
        return ret_val;
    }

    public void updateFromXmlElement(Element element) {
      try{
        this.mSwName = element.getAttributeNode("SwName").getValue();
        this.mSwVersion = element.getAttributeNode("SwVersion").getValue();

      } catch (Exception e) {
          Log.e(TAG, "import error.", e);
      }
    }
    // region properties

    // endregion properties




}
