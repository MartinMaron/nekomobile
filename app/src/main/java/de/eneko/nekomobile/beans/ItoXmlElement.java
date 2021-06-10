package de.eneko.nekomobile.beans;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface ItoXmlElement {
    Element toXmlElement(Document document);
}
