package de.eneko.nekomobile.controllers;


import android.app.Activity;
import android.widget.Toast;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.beans.BaseObject;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.beans.sontex.Group;
import de.eneko.nekomobile.beans.sontex.Road;


/**
 * Diese Klasse ist ein Singleton.
 * Es existiert nur ein einziges Objekt
 * zur Laufzeit welches die Klasse FileHandler
 * selbst generiert.
 * <p/>
 *
 * <p/>
 *
 */
public class SontexFileHandler
{
    private static final String TAG = SontexFileHandler.class.getName();
    private static SontexFileHandler instance = null;
    private ArrayList<Road> allRoutes = new ArrayList<>();

    private SontexFileHandler(){}

    public static synchronized SontexFileHandler getInstance()
    {
        if (instance == null)
        {
            instance = new SontexFileHandler();
        }
        return instance;
    }

    public ArrayList<Road> getAllRoutes()
    {
        return this.allRoutes;
    }

    public void upsertSontexParam(Activity sourceActivity, ArrayList<Messgeraet> messgeraete){
        //finden der Datei und laden in Road-Objekt
        Road road = null;

        File dir = new File(GlobalConst.PATH_SONTEX);
        if (!dir.exists()) {
            Toast.makeText(sourceActivity, TAG + ":" + dir.getAbsolutePath() + " existiert nicht.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!dir.canRead()) {
            Toast.makeText(sourceActivity, TAG + ":" + dir.getAbsolutePath() + " kann nicht gelesen werden.", Toast.LENGTH_SHORT).show();
            return;
        }

        Element roadElement = null;
        File fi = messgeraete.get(0).getSontexFile();

        road = loadFile(fi,sourceActivity);
        roadElement = road.getElement();


        for (int i = 0; i < messgeraete.size(); i++)
        {
            //finden des Nutzers bzw. neuanlage
            NodeList nl =  roadElement.getElementsByTagName("Group");
            Node targetgroupNode = null;
            for (int j = 0; j < nl.getLength(); j++)
            {
                Node groupNode = nl.item(j);
                Node CaptionAttr =  groupNode.getAttributes().getNamedItem("Caption");
                if (CaptionAttr.getNodeValue().contains(messgeraete.get(j).getSontexInfoUser()))
                {
                    targetgroupNode = groupNode;
                    break;
                };
            }

            if (targetgroupNode == null ){
                targetgroupNode = new Group(messgeraete.get(i).getSontexGroupCaption(),messgeraete.get(i).getSontexInfoUser()).toXmlElement(roadElement.getOwnerDocument());
                roadElement.appendChild(targetgroupNode);
            }
            // stwprzyc Task
            targetgroupNode.appendChild(CreateTaskElement(messgeraete.get(i),roadElement.getOwnerDocument()));
        }

        //neuanlage der Aufgabe

        saveFile(sourceActivity, roadElement, fi);
    }

    public Road loadFile(File file, Activity sourceActivity)
    {
        Road route = null;
        try {
            InputStream fileInputStream = new FileInputStream(file);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fileInputStream);

            Element element = doc.getDocumentElement();
            element.normalize();
            route = new Road(element);
        } catch (Exception e) {
            Toast.makeText(sourceActivity, TAG + ":" + e.getMessage() , Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return route;
    }


    public void saveFile(Activity sourceActivity, Element element, File file)
    {
        try
        {
            InputStream fileInputStream = new FileInputStream(file);
            Document document = element.getOwnerDocument();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);

        } catch (DOMException e) {
            Toast.makeText(sourceActivity, TAG + ":" + e.getMessage() , Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (Exception e) {
            Toast.makeText(sourceActivity, TAG + ":" + e.getMessage() , Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }



    public File CreateNewSontexFile(String pFileName)
    {
        try
        {
            String tempFileName = pFileName + "_" ;
            File storageDir = new File(GlobalConst.PATH_SONTEX);
            storageDir.mkdirs();
            File file = File.createTempFile(pFileName + "_",".xml", storageDir);
            file.renameTo(new File(pFileName));

            InputStream fileInputStream = new FileInputStream(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.newDocument();
            document.appendChild(new Road().toXmlElement(document));


            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(file);

            // If you use
            // StreamResult result = new StreamResult(System.out);
            // the output will be pushed to the standard output ...
            // You can use that for debugging
            transformer.transform(domSource, streamResult);

            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String safeFileNameConverter(String pFileName) {
        String mFilename = pFileName;
        mFilename = mFilename.replaceAll("\\\\", "_");
        mFilename = mFilename.replaceAll("\\\\/", "_");
        mFilename = mFilename.replaceAll(":", "_");
        mFilename = mFilename.replaceAll("\\*", "_");
        mFilename = mFilename.replaceAll("\\?", "_");
        mFilename = mFilename.replaceAll("'", "_");
        mFilename = mFilename.replaceAll(">", "_");
        mFilename = mFilename.replaceAll("<", "_");
        mFilename = mFilename.replaceAll(" ", "_");
        mFilename = mFilename.replaceAll("ö", "oe");
        mFilename = mFilename.replaceAll("ü", "ue");
        mFilename = mFilename.replaceAll("ä", "ae");
        mFilename = mFilename.replaceAll("\\|", "_");
        return mFilename;
    }

    Element CreateTaskElement(Messgeraet messgeraet, Document document){
        Element _TaskElement = document.createElement("Task");
        Element _InfoElement = document.createElement("Info");
        Element _ParamElement = document.createElement("Param");
        Element _LastActionDateElement = document.createElement("LastActionDate");
        Element _DataElement = document.createElement("Data");
        Element _EncryptedDataElement = document.createElement("EncryptedData");

        String SontexAgent = "http://www.sontex.com/";




        //Attribute
        _TaskElement.setAttribute("Agent", "http://www.sontex.com/Son566-param");
        _TaskElement.setAttribute("Status", "ToDo");
        _TaskElement.setAttribute("Caption", "Parametrieren 566");

        BaseObject.CreateTextNode(_InfoElement,"Hint","");
        BaseObject.CreateTextNode(_InfoElement,"User","");

        //ParamElement
        BaseObject.CreateTextNode(_ParamElement,"RadioAddr",messgeraet.getNeueNummer());
        BaseObject.CreateTextNode(_ParamElement,"DataToRead","0");


        //MBusElement: AdjustDeviceClock
        Element AdjustDeviceClock_MBusElement = document.createElement("MbusRecord");
        Element AdjustDeviceClock_Key = document.createElement("Key");
        Element AdjustDeviceClock_Value = document.createElement("Value");
        AdjustDeviceClock_Key.setAttribute("AccessKey", "AdjustDeviceClock-0-0-0-0");
        AdjustDeviceClock_Key.setAttribute("Name", "AdjustDeviceClock");
        AdjustDeviceClock_Key.setAttribute("Subunit", "0");
        AdjustDeviceClock_Key.setAttribute("Tariff", "0");
        AdjustDeviceClock_Key.setAttribute("Storage", "0");
        AdjustDeviceClock_Key.setAttribute("Function", "0");
        AdjustDeviceClock_Value.setAttribute("PhysicalUnit", "");


        //MBusElement: Date_FutureValue
        Element Date_FutureValue_MBusElement = document.createElement("MbusRecord");
        Element Date_FutureValue_Key = document.createElement("Key");
        Element Date_FutureValue_Value = document.createElement("Value");
        Date_FutureValue_Key.setAttribute("AccessKey", "Date-0-0-1-0");
        Date_FutureValue_Key.setAttribute("Name", "Date");
        Date_FutureValue_Key.setAttribute("Subunit", "0");
        Date_FutureValue_Key.setAttribute("Tariff", "0");
        Date_FutureValue_Key.setAttribute("Storage", "1");
        Date_FutureValue_Key.setAttribute("Function", "0");
        Date_FutureValue_Value.setAttribute("PhysicalUnit", "");
        Date_FutureValue_Value.appendChild(document.createTextNode("2021-07-01"));


        //'MBusElement: AccessCodeOperator
        Element AccessCodeOperator_MBusElement = document.createElement("MbusRecord");
        Element AccessCodeOperator_Key = document.createElement("Key");
        Element AccessCodeOperator_Value = document.createElement("Value");
        AccessCodeOperator_Key.setAttribute("AccessKey", "AccessCodeOperator-0-0-0-0");
        AccessCodeOperator_Key.setAttribute( "Name", "AccessCodeOperator");
        AccessCodeOperator_Key.setAttribute( "Subunit", "0");
        AccessCodeOperator_Key.setAttribute("Tariff", "0");
        AccessCodeOperator_Key.setAttribute("Storage", "0");
        AccessCodeOperator_Key.setAttribute("Function", "0");
        AccessCodeOperator_Value.setAttribute( "PhysicalUnit", "");
        AccessCodeOperator_Value.appendChild(document.createTextNode("1234"));


        _DataElement.appendChild(AccessCodeOperator_MBusElement);
        AccessCodeOperator_MBusElement.appendChild(AccessCodeOperator_Key);
        AccessCodeOperator_MBusElement.appendChild(AccessCodeOperator_Value);

        _DataElement.appendChild(AdjustDeviceClock_MBusElement);
        AdjustDeviceClock_MBusElement.appendChild(AdjustDeviceClock_Key);
        AdjustDeviceClock_MBusElement.appendChild(AdjustDeviceClock_Value);

        _DataElement.appendChild(Date_FutureValue_MBusElement);
        Date_FutureValue_MBusElement.appendChild(Date_FutureValue_Key);
        Date_FutureValue_MBusElement.appendChild(Date_FutureValue_Value);

        _TaskElement.appendChild(_InfoElement);
        _TaskElement.appendChild(_ParamElement);
        _TaskElement.appendChild(_DataElement);
        return _TaskElement;
    }

}
