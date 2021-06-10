package de.eneko.nekomobile.controllers;


import android.app.Activity;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
import de.eneko.nekomobile.beans.Messgeraet;
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

        File fi = messgeraete.get(0).getSontexFile();
//        if(fi.exists()) {
//            road = loadFile(fi.getName(),sourceActivity);
//        }else {
//            road = new Road();
//        }

        road = new Road();
        //finden des Nutzers bzw. neuanlage

        //neuanlage der Aufgabe

        saveFile(sourceActivity, road, messgeraete.get(0).getSontexFile());
    }


    public void saveFile(Activity sourceActivity, Road road, File file)
    {
        try
        {

            InputStream fileInputStream = new FileInputStream(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.newDocument();
            document.appendChild(road.toXmlElement(document));


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

        } catch (Exception e) {
            Toast.makeText(sourceActivity, TAG + ":" + e.getMessage() , Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }




    public Road loadFile(String pfileName, Activity sourceActivity)
    {
        Road road= null;
        try {
            File file = new File(pfileName);
            InputStream fileInputStream = new FileInputStream(file);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fileInputStream);

            Element element = doc.getDocumentElement();
            element.normalize();
            road = new Road();
            road.updateFromXmlElement(element);
        } catch (Exception e) {
            Toast.makeText(sourceActivity, TAG + ":" + e.getMessage() , Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return road;
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
}
