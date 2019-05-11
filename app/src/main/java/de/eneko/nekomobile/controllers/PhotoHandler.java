package de.eneko.nekomobile.controllers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.IOException;

public class PhotoHandler {
    private static final int REQUEST_CAPTURE_IMAGE = 100;

    public void openCameraIntent(String relativeNekoPath, String filename, Activity parent) {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if(pictureIntent.resolveActivity(parent.getPackageManager()) != null){
            //Create a file to store the image
            File photoFile = null;
            try {
                photoFile = FileHandler.getInstance().createJpgFile(relativeNekoPath,filename);
            } catch (IOException ex) {
                // Error occurred while creating the File
                System.err.print(ex.getMessage());
                return;
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(parent,"de.eneko.nekomobile.provider", photoFile);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        photoURI);
                parent.startActivityForResult(pictureIntent,
                        REQUEST_CAPTURE_IMAGE);
            }
        }
    }

    private static PhotoHandler instance = null;
    private PhotoHandler()
    {

    }

    public static synchronized PhotoHandler getInstance()
    {
        //Wenn die Instanz null ist wurde Sie noch nicht generiert.
        if (instance == null)
        {
            //Generierung der Instanz bei ersten Aufruf des FileHandlers

            instance = new PhotoHandler();
        }

        //Rueckgabe der einzigen Instanz.
        return instance;
    }


}
