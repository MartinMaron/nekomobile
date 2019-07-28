package de.eneko.nekomobile.controllers;

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


}
