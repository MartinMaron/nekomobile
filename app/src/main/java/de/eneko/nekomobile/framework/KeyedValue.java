package de.eneko.nekomobile.framework;


public class KeyedValue {
    public String mValue;
    public Object mKey;

    public KeyedValue( Object key, String value) {
        this.mValue = value;
        this.mKey = key;
    }

    public String getValue() {
        return mValue;
    }

    public Object getKey() {
        return mKey;
    }

    @Override
    public String toString() {
        return mValue;
    }
}


