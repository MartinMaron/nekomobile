package de.eneko.nekomobile.controllers;


import android.util.Log;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.beans.Route;
import de.eneko.nekomobile.framework.dropbox.NekoDropBox;


/**
 * Diese Klasse ist ein Singleton.
 * Es existiert nur ein einziges Objekt
 * zur Laufzeit welches die Klasse FileHandler
 * selbst generiert.
 * <p/>
 * Sie dient zur Speicherung der Notizen
 * und regelt den Zugriff auf den Geraetespeicher
 * und die SD-Karte.
 * <p/>
 * Desweiteren enthaelt eine Liste der gespeicherten
 * Notizen.
 * Created by rKasper on 22.05.2015.
 */
public class FileHandler
{
    private static final String TAG = FileHandler.class.getName();
    private static FileHandler instance = null;
    private ArrayList<Route> allRoutes = new ArrayList<>();
    private NekoDropBox mNekoDropBox = null;

    private FileHandler(){}

    public static synchronized FileHandler getInstance()
    {
        //Wenn die Instanz null ist wurde Sie noch nicht generiert.
        if (instance == null)
        {
            //Generierung der Instanz bei ersten Aufruf des FileHandlers

            instance = new FileHandler();
        }

        //Rueckgabe der einzigen Instanz.
        return instance;
    }

    public ArrayList<Route> getAllRoutes()
    {
        return this.allRoutes;
    }

    public void saveFile()
    {
        Route route = CurrentObjectNavigation.getInstance().getRoute();

        try {
            File nFile = FileFactory.getInstance().createXmlFile(route.getAndroidFileName());
            File file = new File(GlobalConst.PATH_NEKOMOBILE + "/" + route.getAndroidFileName());
            nFile.renameTo(file);

            InputStream fileInputStream = new FileInputStream(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.newDocument();
            document.appendChild(route.toXmlElement(document));

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
            e.printStackTrace();
        }
    }

    public void preLoadAllRoutes()
    {
        ArrayList<Route> tmpAllRoutes = new ArrayList<>();
        try {
            File dir = new File(GlobalConst.PATH_NEKOMOBILE);
            if (!dir.exists()) {
                Log.e(TAG, dir.getAbsolutePath() + " existiert nicht");
            }
            if (!dir.canRead()) {
                Log.e(TAG, dir.getAbsolutePath() + " kann nicht gelesen werden");
            }
            String[] list = dir.list();
            if (list != null) {
                for (String file : list) {
                    if (file.endsWith("neko.xml")) {
                        try {


                            Route route = loadFile(GlobalConst.PATH_NEKOMOBILE + "/" + file,true);
                            Calendar validDate = Calendar.getInstance();
                            validDate.add(Calendar.DATE, GlobalConst.DAYS_TO_ARCHIVE);
                            if (route.getDatum().after(validDate.getTime())){
                                tmpAllRoutes.add(route);
                            }
                        }catch (Exception e)
                        {
                            Log.e(TAG, e.getMessage(),e);
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            Log.e(TAG, e.getMessage(),e);
        }
        finally
        {
            this.allRoutes.clear();
            this.allRoutes.addAll(tmpAllRoutes);
        }
    }

    public Route loadFile(String pfileName, Boolean withSubElements)
    {
        Route route = null;
        try {
            File file = new File(pfileName);
            InputStream fileInputStream = new FileInputStream(file);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fileInputStream);

            Element element = doc.getDocumentElement();
            element.normalize();
            route = new Route();
            route.updateRouteFromXmlElement(element, withSubElements);
            route.setAndroidFileName(file.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return route;
    }

    public NekoDropBox getNekoDropBox() {
        return mNekoDropBox;
    }

    public void setNekoDropBox(NekoDropBox nekoDropBox) {
        mNekoDropBox = nekoDropBox;
    }





}
