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

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.MainActivity;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.beans.Liegenschaft;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.beans.Nutzer;
import de.eneko.nekomobile.beans.Rauchwarnmelder;
import de.eneko.nekomobile.beans.Route;
import de.eneko.nekomobile.beans.ToDo;
import de.eneko.nekomobile.beans.hlpta.ZaehlerModel;


/**
 * Diese Klasse ist ein Singleton.
 * Es existiert nur ein einziges Objekt
 * zur Laufzeit welches die Klasse
 * selbst generiert.
 */
public class MessgeraeteListViewActivityConroller
{

    public enum GeraeteArt { MANUELL, EXIM, SONTEX}
    public enum EingabeArt {TASTATUR,SPRACHE}


    private GeraeteArt gereteart = GeraeteArt.MANUELL;
    private EingabeArt eingabeArt = EingabeArt.TASTATUR;


    private static MessgeraeteListViewActivityConroller instance = null;
    private MessgeraeteListViewActivityConroller()
    {

    }

    public static synchronized MessgeraeteListViewActivityConroller getInstance()
    {
        //Wenn die Instanz null ist wurde Sie noch nicht generiert.
        if (instance == null)
        {
            //Generierung der Instanz bei ersten Aufruf des FileHandlers

            instance = new MessgeraeteListViewActivityConroller();
        }

        //Rueckgabe der einzigen Instanz.
        return instance;
    }

    public GeraeteArt getGereteart() {
        return gereteart;
    }

    public void setGereteart(GeraeteArt gereteart) {
        this.gereteart = gereteart;
    }

    public EingabeArt getEingabeArt() {
        return eingabeArt;
    }

    public void setEingabeArt(EingabeArt eingabeArt) {
        this.eingabeArt = eingabeArt;
    }
}
