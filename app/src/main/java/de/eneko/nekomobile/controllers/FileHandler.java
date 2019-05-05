package de.eneko.nekomobile.controllers;


import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.MainActivity;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.beans.Liegenschaft;
import de.eneko.nekomobile.beans.Nutzer;
import de.eneko.nekomobile.beans.Rauchwarnmelder;
import de.eneko.nekomobile.beans.Route;
import de.eneko.nekomobile.beans.ToDo;
import de.eneko.nekomobile.beans.hlpta.ZaehlerModel;


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
    private ArrayList<ZaehlerModel> mZaehlerModels = new ArrayList<>();

    private Route mRoute = null;
    private Liegenschaft mLiegenschaft = null;
    private Nutzer mNutzer = null;
    private ToDo mNutzerTodo = null;
    private Rauchwarnmelder rauchwarnmelder = null;
    private MainActivity mainActivity = null;





    /**
     * Privater Konstruktor
     * So kann kein Objekt der
     * Klasse von anderen Programmteilen
     * aus generiert werden
     */
    private FileHandler()
    {

    }

    /**
     * Diese Funktion generiert einmal
     * die Instanz und gibt Sie bei jedem
     * weiteren Zugriff zurueck.
     *
     * @return instance : FileHandler
     */
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

    public Route getRoute()
    {
        return this.mRoute;
    }

    public void setRoute(int index)
    {
        this.mRoute = this.allRoutes.get(index);
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

    public Rauchwarnmelder getRauchwarnmelder() {
        return rauchwarnmelder;
    }

    public void setRauchwarnmelder(Rauchwarnmelder rauchwarnmelder) {
        this.rauchwarnmelder = rauchwarnmelder;
    }

    public void setMainActivity(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    public MainActivity getMainActivity()
    {
        return mainActivity;
    }

    /**
     * Gibt eine Liste aller Notizen zurueck
     *
     * @return ArrayList vom Typ NoteBean
     */
    public ArrayList<Route> getAllRoutes()
    {
        return this.allRoutes;
    }

    /**
     * Diese Funktion speichert eine Notiz auf dem Geruetespeicher
     *
     * @param route : NoteBean : die gespeichert werden
     * @return success : boolean
     */
    public boolean save(Route route)
    {

        //Decl and Init.
        boolean success = true;
//
//        try
//        {
//            if (this.fileService != null)
//            {
//                fileService.saveOnDevice(route);
//            }
//        }
//        catch (Exception e)
//        {
//            success = false;
//            Toast.makeText(mainActivity, e.getMessage(), Toast.LENGTH_LONG).show();
//        }
//        finally
//        {
//            //TODO Alles Schliessen
//        }

        return success;

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

    public Rauchwarnmelder createNewRauchmelder(){
        Rauchwarnmelder ret_val = new Rauchwarnmelder(getNutzerTodo());
        ret_val.setNew(true);
        ret_val.setNekoId(FileHandler.getInstance().getNutzerTodo().getRauchmelder().size() + ";new");
        return ret_val;
    }

    public ArrayList<ZaehlerModel> getZaehlerModels() {
       return mZaehlerModels;
    }



    public void initializeHelpers(MainActivity mainActivity) {
        String[] _zaehlermodele = mainActivity.getResources().getStringArray(R.array.zaehler_modele);
        for (String strTemp : _zaehlermodele){
            String[] _line = strTemp.split("@");
           mZaehlerModels.add(new ZaehlerModel(_line[0],_line[1],_line[2], _line[3]));
        }
    }
}
