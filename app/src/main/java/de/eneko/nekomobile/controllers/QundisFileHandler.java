package de.eneko.nekomobile.controllers;


import android.app.Activity;
import android.widget.Toast;

import java.io.*;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.beans.Liegenschaft;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.beans.hlpta.ZaehlerModel;
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

        String data = null;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String fileName  = GlobalConst.PATH_QUNDIS + "/" + SontexFileHandler.getInstance().safeFileNameConverter(pLiegenschaft.getAdresseOneLine()+ " " + timestamp.getTime() + ".neko.plt");
        data = "[PLANT]" + System.getProperty("line.separator");
        data += "Nummer	Hersteller	Geräte ID	Fabrikations-Nr.	Version	Gerätetyp " + System.getProperty("line.separator");

        ArrayList<Messgeraet> messgeraete = pLiegenschaft.getNewQundisMessgeraets();
        for (int i = 0; i < messgeraete.size(); i++)
        {
            ZaehlerModel zm = Dict.getInstance().getZaehlerModel(messgeraete.get(i).getZielmodel());
            String nummer = messgeraete.get(i).getNeueFunkNummer().equals("") ? messgeraete.get(i).getNeueNummer(): messgeraete.get(i).getNeueFunkNummer();
            if (nummer.length() != 8) {
                if(messgeraete.get(i).getTodo().getNutzer() != null){
                    nummer = padLeftZeros(messgeraete.get(i).getTodo().getNutzer().getNutzernummer().toString(),8);
                }else{
                    nummer = "00000000";
                }
            }

            data += (i+1) + "\t"
                    + "QDS" + "\t"
                    + nummer + "\t"
                    + nummer + "\t"
                    + "*" + "\t"
                    + "*" + System.getProperty("line.separator");
        }

        ArrayList<Messgeraet> son_messgeraete = pLiegenschaft.getSontexOMSMessgeraets();
        for (int i = 0; i < son_messgeraete.size(); i++)
        {
            ZaehlerModel zm = Dict.getInstance().getZaehlerModel(son_messgeraete.get(i).getZielmodel());
            String nummer = son_messgeraete.get(i).getNeueFunkNummer().equals("") ? son_messgeraete.get(i).getNeueNummer(): son_messgeraete.get(i).getNeueFunkNummer();
            if (nummer.length() != 8) {
                if(son_messgeraete.get(i).getTodo().getNutzer() != null){
                    nummer = padLeftZeros(son_messgeraete.get(i).getTodo().getNutzer().getNutzernummer().toString(),8);
                }else{
                    nummer = "00000000";
                }
            }

            data += (i+1 + messgeraete.stream().count()) + "\t"
                    + "SON" + "\t"
                    + nummer + "\t"
                    + nummer + "\t"
                    + "*" + "\t"
                    + "*" + System.getProperty("line.separator");
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

    private String padLeftZeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
    }

}
