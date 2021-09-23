package de.eneko.nekomobile.controllers;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PhotoHandler {
    private static final String TAG = PhotoHandler.class.getName();
    public static final int REQUEST_CAPTURE_IMAGE = 100;
    private static ArrayList<File> photofiles = null;


    public void openCameraIntent(String relativeNekoPath, String filename, Activity parent) {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if(pictureIntent.resolveActivity(parent.getPackageManager()) != null){
            //Create a file to store the image
            File photoFile = null;
            try {
                photoFile = FileFactory.getInstance().createJpgFile(relativeNekoPath,filename);
            } catch (IOException ex) {
                // Error occurred while creating the File
                System.err.print(ex.getMessage());
                return;
            }
            if (photoFile != null && photoFile.exists()) {
                try {
                    photofiles.add(photoFile);
                    Uri photoURI = FileProvider.getUriForFile(parent,"de.eneko.nekomobile.provider", photoFile);
                    pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                            photoURI);
                    pictureIntent.putExtra("filename",photoFile.getAbsolutePath());
                    parent.startActivityForResult(pictureIntent,
                            REQUEST_CAPTURE_IMAGE);
                }catch (Exception e)
                {
                    Log.e(TAG, "foto geht nicht", e);
                }
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (resultCode != Activity.RESULT_OK) {
                return;
            }
        } catch (Exception e){
            System.out.println (e.toString());
        }

    }

    public void compressFiles(){
    for (int i = 0;i< photofiles.size();i++){
            saveBitmapToFile(photofiles.get(i));
        }
        photofiles.clear();
    }

    public File saveBitmapToFile(File file){
        try {

            // BitmapFactory options to downsize the image
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            o.inSampleSize = 6;
            // factor of downsizing the image

            FileInputStream inputStream = new FileInputStream(file);
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o);
            inputStream.close();

            // The new size we want to scale to
            final int REQUIRED_SIZE=100;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            inputStream = new FileInputStream(file);

            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
            inputStream.close();

            // here i override the original image file
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);

            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100 , outputStream);

            return file;
        } catch (Exception e) {
            return null;
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
            photofiles = new ArrayList<File>();
        }

        //Rueckgabe der einzigen Instanz.
        return instance;
    }


}
