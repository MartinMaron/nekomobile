package de.eneko.nekomobile.beans;


import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.eneko.nekomobile.controllers.FileHandler;

public interface ItoXmlElement {
    Element toXmlElement(Document document);
}
