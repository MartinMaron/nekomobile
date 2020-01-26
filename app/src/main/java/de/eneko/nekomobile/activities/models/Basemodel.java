package de.eneko.nekomobile.activities.models;

public abstract class Basemodel {
    abstract public void save();
    abstract public void load();
    protected Object bean;
    public Boolean isDataLoaded = false;

    Basemodel(Object bean){
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }

    public Boolean getDataLoaded() {
        return isDataLoaded;
    }

    public void setDataLoaded(Boolean dataLoaded) {
        isDataLoaded = dataLoaded;
    }
}
