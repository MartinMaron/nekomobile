package de.eneko.nekomobile.controllers;


/**
 * Diese Klasse ist ein Singleton.
 * Es existiert nur ein einziges Objekt
 * zur Laufzeit welches die Klasse
 * selbst generiert.
 */
public class MessgeraeteConroller
{

    public enum GeraeteArt { MANUELL, EXIM, SONTEX}
    public enum EingabeArt {TASTATUR,SPRACHE}


    private GeraeteArt gereteart = GeraeteArt.MANUELL;
    private EingabeArt eingabeArt = EingabeArt.TASTATUR;


    private static MessgeraeteConroller instance = null;
    private MessgeraeteConroller()
    {

    }

    public static synchronized MessgeraeteConroller getInstance()
    {
        if (instance == null){instance = new MessgeraeteConroller();}
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
