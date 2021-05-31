package de.eneko.nekomobile.controllers;


import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

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

    public void saveFile(Activity sourceActivity)
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
            Toast.makeText(sourceActivity, TAG + ":" + e.getMessage() , Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void preLoadAllRoutes(Activity sourceActivity)
    {
        ArrayList<Route> tmpAllRoutes = new ArrayList<>();
        try {
            File dir = new File(GlobalConst.PATH_NEKOMOBILE);
            if (!dir.exists()) {
                Toast.makeText(sourceActivity, TAG + ":" + dir.getAbsolutePath() + " existiert nicht.", Toast.LENGTH_SHORT).show();
            }
            if (!dir.canRead()) {
                Toast.makeText(sourceActivity, TAG + ":" + dir.getAbsolutePath() + " kann nicht gelesen werden.", Toast.LENGTH_SHORT).show();
            }
            String[] list = dir.list();
            if (list != null) {
                for (String file : list) {
                    if (file.endsWith("neko.xml")) {
                        try {
                            Route route = loadFile(GlobalConst.PATH_NEKOMOBILE + "/" + file,true, sourceActivity);
                            Calendar validDate = Calendar.getInstance();
                            validDate.add(Calendar.DATE, GlobalConst.DAYS_TO_ARCHIVE);
                            if (route.getDatum().after(validDate.getTime())){
                                tmpAllRoutes.add(route);
                            }
                        }catch (Exception e)
                        {
                            Toast.makeText(sourceActivity, TAG + ":" + e.getMessage() , Toast.LENGTH_SHORT).show();
                            Log.e(TAG, e.getMessage(),e);
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            Toast.makeText(sourceActivity, TAG + ":" + e.getMessage() , Toast.LENGTH_SHORT).show();
        }
        finally
        {
            this.allRoutes.clear();
            this.allRoutes.addAll(tmpAllRoutes);
        }
    }

    public Route loadFile(String pfileName, Boolean withSubElements, Activity sourceActivity)
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
            Toast.makeText(sourceActivity, TAG + ":" + e.getMessage() , Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return route;
    }

    public void archiveAllFiles() {
        moveFilesToArchive(GlobalConst.PATH_NEKOMOBILE);
        moveFilesToArchive(GlobalConst.PATH_SONTEX);
        moveFilesToArchive(GlobalConst.PATH_EXIM);
    }

    // region moveFiles
    private void moveFilesToArchive(String pHomeDevicePath)
    {
        //new java.util.Date(result.lastModified())
        String archivePath = pHomeDevicePath + "/archive";
        File dir = new File(pHomeDevicePath);
        if (dir != null && dir.listFiles() != null && dir.listFiles().length > 0) {
            for (File loopfile : Arrays.stream(dir.listFiles()).collect(Collectors.toList())) {
                if (loopfile.isFile() && loopfile.exists()) {
                    archiveFile(archivePath, loopfile);
                }
            }
        }

    }

    private void archiveFile(String archivePath, File file) {
        File folder = new File(archivePath);
        if (!folder.exists()) {folder.mkdir();}
        SimpleDateFormat zeitformat = new SimpleDateFormat("dd.MM.yyyy_HH");
        String lastModifiedString = zeitformat.format(new Date(file.lastModified()));
        file.renameTo(new File(archivePath,lastModifiedString + "_" + file.getName()));
        file.delete();
    }

    public NekoDropBox getNekoDropBox() {
        return mNekoDropBox;
    }

    public void setNekoDropBox(NekoDropBox nekoDropBox) {
        mNekoDropBox = nekoDropBox;
    }





}
