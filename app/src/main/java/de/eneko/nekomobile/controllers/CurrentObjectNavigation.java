package de.eneko.nekomobile.controllers;

import android.content.SharedPreferences;

import de.eneko.nekomobile.MainActivity;
import de.eneko.nekomobile.beans.Liegenschaft;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.beans.Nutzer;
import de.eneko.nekomobile.beans.Rauchmelder;
import de.eneko.nekomobile.beans.Route;
import de.eneko.nekomobile.beans.ToDo;

public class CurrentObjectNavigation {

    private Route mRoute = null;
    private Liegenschaft mLiegenschaft = null;
    private Nutzer mNutzer = null;
    private ToDo mNutzerTodo = null;
    private Rauchmelder mRauchmelder = null;
    private Messgeraet messgeraet = null;
    private MainActivity mainActivity = null;
    SharedPreferences sharedPreferences = null;
    private CurrentObjectNavigation(){}

    /*
     * Die einzige Instanz die jemals zur Laufzeit
     * existieren wird
     */
    private static CurrentObjectNavigation instance = null;

    public static synchronized CurrentObjectNavigation getInstance()
    {
        //Wenn die Instanz null ist wurde Sie noch nicht generiert.
        if (instance == null)
        {
            //Generierung der Instanz bei ersten Aufruf des FileHandlers

            instance = new CurrentObjectNavigation();
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
        mRauchmelder = rauchmelder;
    }

    public Messgeraet getMessgeraet() {
        return messgeraet;
    }

    public void setMessgeraet(Messgeraet messgeraet) {
        this.messgeraet = messgeraet;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
