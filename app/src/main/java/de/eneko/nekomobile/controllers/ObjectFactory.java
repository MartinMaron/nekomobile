package de.eneko.nekomobile.controllers;

import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.beans.Rauchmelder;
import de.eneko.nekomobile.beans.ToDo;

public class ObjectFactory {
    /*
     * Die einzige Instanz die jemals zur Laufzeit
     * existieren wird
     */
    private static ObjectFactory instance = null;
    private ObjectFactory(){}

    public static synchronized ObjectFactory getInstance()
    {
        if (instance == null) {instance = new ObjectFactory();}
        return instance;
    }

    public Rauchmelder createNewRauchmelder(ToDo pNutzerToDo){
        Rauchmelder ret_val = new Rauchmelder(pNutzerToDo);
        pNutzerToDo.getRauchmelder().add(ret_val);
        ret_val.setNew(true);
        ret_val.setNekoId("new_" + pNutzerToDo.getRauchmelder().size());
        return ret_val;
    }

    public Messgeraet createNewMessgaeret(ToDo pToDo){
        Messgeraet ret_val = new Messgeraet(pToDo);
        pToDo.getMessgeraete().add(ret_val);
        ret_val.setArt(pToDo.getArt().replace("MON_",""));
        ret_val.setNew(true);
        ret_val.setNekoId("new_" + pToDo.getMessgeraete().size());
        ret_val.setNummer(ret_val.getNekoId());
        ret_val.setStartWert(0.0);
        ret_val.setProcent(100.0);
        return ret_val;
    }

}
