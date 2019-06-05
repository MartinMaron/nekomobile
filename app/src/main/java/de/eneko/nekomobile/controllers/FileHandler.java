package de.eneko.nekomobile.controllers;


import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.MainActivity;
import de.eneko.nekomobile.beans.Liegenschaft;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.beans.Nutzer;
import de.eneko.nekomobile.beans.Rauchmelder;
import de.eneko.nekomobile.beans.Route;
import de.eneko.nekomobile.beans.ToDo;


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
    /*
     * Die einzige Instanz die jemals zur Laufzeit
     * existieren wird
     */
    private static FileHandler instance = null;

    //Liste aller Notizen
    private ArrayList<Route> allRoutes = new ArrayList<>();

    private Route mRoute = null;
    private Liegenschaft mLiegenschaft = null;
    private Nutzer mNutzer = null;
    private ToDo mNutzerTodo = null;
    private Rauchmelder mRauchmelder = null;
    private Messgeraet messgeraet = null;
    private MainActivity mainActivity = null;

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

    public Route getRoute() {
        return mRoute;
    }

    public void setRoute(Route route) {
        mRoute = route;
    }

    public Liegenschaft getLiegenschaft() {
        return mLiegenschaft;
    }

    public void setLiegenschaft(Liegenschaft liegenschaft) {
        mLiegenschaft = liegenschaft;
    }

    public Nutzer getNutzer() {
        return mNutzer;
    }

    public void setNutzer(Nutzer nutzer) {
        mNutzer = nutzer;
    }

    public ToDo getNutzerTodo() {
        return mNutzerTodo;
    }

    public void setNutzerTodo(ToDo nutzerTodo) {
        mNutzerTodo = nutzerTodo;
    }

    public Rauchmelder getRauchmelder() {
        return mRauchmelder;
    }

    public void setRauchmelder(Rauchmelder rauchmelder) {
        this.mRauchmelder = rauchmelder;
    }

    public Messgeraet getMessgeraet() {
        return messgeraet;
    }

    public void setMessgeraet(Messgeraet messgeraet) {
        this.messgeraet = messgeraet;
    }

    public void setMainActivity(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    public MainActivity getMainActivity()
    {
        return mainActivity;
    }

    public ArrayList<Route> getAllRoutes()
    {
        return this.allRoutes;
    }

    public void saveFile()
    {
        Route route = getRoute();

        try {
            File nFile = createXmlFile(route.getAndroidFileName());
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

    /**
     * Diese Methode laedt alle Notizen
     * vom Geraetespeicher und Ueberschreibt die
     * akutelle Liste all Notes
     *
     * @return
     */
    public void preLoadAllRoutes()
    {
        //Temopraere Liste zum Arbeiten
        ArrayList<Route> tmpAllRoutes = new ArrayList<>();
        try {
            // Read all files sorted into the values-array
            File dir = new File(GlobalConst.PATH_NEKOMOBILE);
            if (!dir.exists()) {
                Toast.makeText(getMainActivity(), dir.getAbsolutePath() + " existiert nicht", Toast.LENGTH_LONG).show();
            }
            if (!dir.canRead()) {
                Toast.makeText(getMainActivity(), dir.getAbsolutePath() + " kann nicht gelesen werden", Toast.LENGTH_LONG).show();
            }
            String[] list = dir.list();
            if (list != null) {
                for (String file : list) {
                    if (file.endsWith("neko.xml")) {
                        Route route = loadFile(GlobalConst.PATH_NEKOMOBILE + "/" + file,true);
                        tmpAllRoutes.add(route);
                    }
                }
            }
        }
        catch (Exception e)
        {
            Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_LONG).show();
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
        //TODO einlesen der XML - Datei

        //Decl and Init Return Objekt
        File importfile = new File(pfileName);

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


    public File createJpgFile(String relativeNekoPath, String filename) throws IOException {
        String imageFileName = filename + "_";
        File storageDir = new File(GlobalConst.PATH_NEKOMOBILE_PICTURES + "/" + relativeNekoPath);
        storageDir.mkdirs();
        File file = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return file;
    }

    public File createXmlFile(String filename) throws IOException {
        String imageFileName = filename + "_";
        File storageDir = new File(GlobalConst.PATH_NEKOMOBILE_PICTURES);
        storageDir.mkdirs();
        File file = File.createTempFile(
                imageFileName,  /* prefix */
                ".xml",         /* suffix */
                storageDir      /* directory */
        );
        return file;
    }

    public Rauchmelder createNewRauchmelder(){
        Rauchmelder ret_val = new Rauchmelder(getNutzerTodo());
        ret_val.setNew(true);
        ret_val.setNekoId(FileHandler.getInstance().getNutzerTodo().getRauchmelder().size() + ";new");
        return ret_val;
    }


}
