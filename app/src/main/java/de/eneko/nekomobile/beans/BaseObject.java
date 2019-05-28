package de.eneko.nekomobile.beans;

import de.eneko.nekomobile.activities.models.Basemodel;

public abstract class BaseObject {
    private Basemodel baseModel;

    public BaseObject() {
        this.baseModel = createBaseObject();
    }

    protected abstract Basemodel createBaseObject();

    public Basemodel getBaseModel() {
        return baseModel;
    }
}
