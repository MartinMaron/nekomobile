package de.eneko.nekomobile.activities.models;

public abstract class Basemodel {
    abstract public void save();
    abstract public void load();
    protected Object bean;

    Basemodel(Object bean){
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
