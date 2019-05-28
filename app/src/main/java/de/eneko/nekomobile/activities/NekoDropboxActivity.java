package de.eneko.nekomobile.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.Runnable;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.beans.Route;
import de.eneko.nekomobile.framework.NekoDropBox;

public class NekoDropboxActivity extends AppCompatActivity {

    private Button btButton = null;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dropbox_activity);
        btButton = findViewById(R.id.btButtonUpload);

        btButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                File dir = new File(GlobalConst.PATH_NEKOMOBILE);
//                if (!dir.exists()) {
//                    Toast.makeText(getMainActivity(), dir.getAbsolutePath() + " existiert nicht", Toast.LENGTH_LONG).show();
//                }
//                if (!dir.canRead()) {
//                    Toast.makeText(getMainActivity(), dir.getAbsolutePath() + " kann nicht gelesen werden", Toast.LENGTH_LONG).show();
//                }
                String[] list = dir.list();
                if (list != null) {
                    for (String file : list) {
                        if (file.endsWith("neko.xml")) {

                            try {
                                NekoDropBox.main(GlobalConst.PATH_NEKOMOBILE + "/" + file);
                            } catch (com.dropbox.core.DbxException e) {
                                System.out.println("Dropbox error "+e.getMessage());
                            } catch (java.io.IOException e) {
                                System.out.println("datei konnte nicht gefunden werden "+e.getMessage());
                            }



                            //UploadToDropboxFromPath(GlobalConst.PATH_NEKOMOBILE + "/" + file,"nekomobile/" + file);
                        }
                    }
                }




            }
        });




    }



}
