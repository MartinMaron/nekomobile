package de.eneko.nekomobile.controllers;


import android.app.Activity;
import android.widget.Toast;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.beans.BaseObject;
import de.eneko.nekomobile.beans.Liegenschaft;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.beans.hlpta.ZaehlerModel;
import de.eneko.nekomobile.beans.sontex.Group;
import de.eneko.nekomobile.beans.sontex.Road;


/**
 * Diese Klasse ist ein Singleton.
 * Es existiert nur ein einziges Objekt
 * zur Laufzeit welches die Klasse FileHandler
 * selbst generiert.
 * <p/>
 *
 * <p/>
 *
 */
public class QundisFileHandler
{
    private static final String TAG = QundisFileHandler.class.getName();
    private static QundisFileHandler instance = null;
    private ArrayList<Road> allRoutes = new ArrayList<>();

    private QundisFileHandler(){}

    public static synchronized QundisFileHandler getInstance()
    {
        if (instance == null)
        {
            instance = new QundisFileHandler();
        }
        return instance;
    }

    public ArrayList<Road> getAllRoutes()
    {
        return this.allRoutes;
    }

    public void exportQundisPltFile(Activity sourceActivity, Liegenschaft pLiegenschaft ){
        //erstellen einer Datei für Qundis Auslesung

        File dir = new File(GlobalConst.PATH_QUNDIS);
        if (!dir.exists()) {
            Toast.makeText(sourceActivity, TAG + ":" + dir.getAbsolutePath() + " existiert nicht.", Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (!dir.canRead()) {
            Toast.makeText(sourceActivity, TAG + ":" + dir.getAbsolutePath() + " kann nicht gelesen werden.", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<Messgeraet> messgeraete = pLiegenschaft.getNewQundisMessgeraets();
        String data = null;
        String fileName  = GlobalConst.PATH_QUNDIS + "/" + SontexFileHandler.getInstance().safeFileNameConverter(pLiegenschaft.getAdresseOneLine() + ".plt");
        data = "[PLANT]" + System.getProperty("line.separator");
        data += "Nummer	Hersteller	Geräte ID	Fabrikations-Nr.	Version	Gerätetyp " + System.getProperty("line.separator");

        for (int i = 0; i < messgeraete.size(); i++)
        {
            ZaehlerModel zm = Dict.getInstance().getZaehlerModel(messgeraete.get(i).getZielmodel());
            String nummer = messgeraete.get(i).getNeueFunkNummer().equals("") ? messgeraete.get(i).getNeueNummer(): messgeraete.get(i).getNeueFunkNummer();
            data += (i+1) + "\t"
                    + "QDS" + "   "
                    + nummer + "\t"
                    + nummer + "   "
                    + "*" + "   "
                    + zm.getmQundisGeraeteArt() + System.getProperty("line.separator");
        }
        writeUsingFileWriter(data,fileName);
    }

    /**
     * Use FileWriter when number of write operations are less
     * @param data
     */
    private static void writeUsingFileWriter(String data, String pFilename) {
        File file = new File(pFilename);
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
