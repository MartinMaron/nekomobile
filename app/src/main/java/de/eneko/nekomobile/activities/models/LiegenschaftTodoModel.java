package de.eneko.nekomobile.activities.models;

import de.eneko.nekomobile.beans.Liegenschaft;
import de.eneko.nekomobile.beans.ToDo;

public class LiegenschaftTodoModel extends Basemodel{
    private static final String TAG = LiegenschaftTodoModel.class.getName();
    private String bezeichnung;
    private String art;

    public LiegenschaftTodoModel(ToDo bean) {
        super(bean);
    }



    @Override
    public ToDo getBean() {
        return (ToDo) super.getBean();
    }

    @Override
    public void save() {
        getBean().setBezeichnung(bezeichnung);
        getBean().setArt(art);
    }

    @Override
    public void load() {
        bezeichnung = getBean().getBezeichnung();
        art = getBean().getArt();
        setDataLoaded(true);
    }








    // region properties




    public Liegenschaft getLiegenschaft() {
        return getBean().getLiegenschaft();
    }



    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }
    // endregion properties
}
