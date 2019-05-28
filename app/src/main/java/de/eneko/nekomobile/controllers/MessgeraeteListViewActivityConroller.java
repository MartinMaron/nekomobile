package de.eneko.nekomobile.controllers;


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
