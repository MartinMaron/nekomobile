package de.eneko.nekomobile.controllers;

import java.io.File;
import java.io.IOException;

import de.eneko.nekomobile.GlobalConst;

public class FileFactory {
    /*
     * Die einzige Instanz die jemals zur Laufzeit
     * existieren wird
     */
    private static FileFactory instance = null;
    private FileFactory(){}

    public static synchronized FileFactory getInstance()
    {
        if (instance == null) {instance = new FileFactory();}
        return instance;
    }

    public File createJpgFile(String relativeNekoPath, String filename) throws IOException {
        String imageFileName = relativeNekoPath + filename + "_";
        File storageDir = new File(GlobalConst.PATH_NEKOMOBILE_PICTURES);
        storageDir.mkdirs();
        File file = File.createTempFile(
                imageFileName,  /* prefix */
                ".neko.jpg",         /* suffix */
                storageDir      /* directory */
        );
        return file;
    }

    public File createXmlFile(String filename) throws IOException {
        String imageFileName = filename + "_";
        File storageDir = new File(GlobalConst.PATH_NEKOMOBILE);
        storageDir.mkdirs();
        File file = File.createTempFile(
                imageFileName,  /* prefix */
                ".xml",         /* suffix */
                storageDir      /* directory */
        );
        return file;
    }

}
