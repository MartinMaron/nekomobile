package de.eneko.nekomobile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import de.eneko.nekomobile.controllers.Dict;
import de.eneko.nekomobile.controllers.FileHandler;
import de.eneko.nekomobile.framework.dropbox.NekoDropBox;
import de.eneko.nekomobile.framework.ftp.FTPManager;
import de.eneko.nekomobile.listener.MainActivityOnClickListener;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getName();
    HandlerThread handlerThread = null;
    Handler requestHandler = null;
    private EditText etFtpip = null;
    public static final String FTP_FILE = "FTP_IP.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Configuration config = getResources().getConfiguration();

        // erstellen der Activity
        setContentView(R.layout.activity_main);
        // initialisierung listener
        MainActivityOnClickListener mainActivityOnClickListener = new MainActivityOnClickListener(this);

        // init Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Hallo " + GlobalConst.NEKOMOBILE_USER);
        setSupportActionBar(toolbar);

        // init FloatingActionButton
        FloatingActionButton fab = findViewById(R.id.fab);
        //fab.setVisibility(NavigationView.VISIBLE);
        fab.setOnClickListener(mainActivityOnClickListener);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;



        // init DrawerLayout
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        // init NavigationView
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        // init welcomeText
//        TextView tvWelcome = findViewById(R.id.tvWelcome);
//        tvWelcome.setText("Hallo " + GlobalConst.NEKOMOBILE_USER);

        // init Buttons
        Button btCmdGetRoutes = findViewById(R.id.btCmdGetRoutes);
        btCmdGetRoutes.setOnClickListener(mainActivityOnClickListener);

        Button btCmdDropbox = findViewById(R.id.btCmdDropbox);
        btCmdDropbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileHandler.getInstance().getNekoDropBox().synchronize(true);
            }
        });

        /* Create HandlerThread to run Network or IO operations */
        handlerThread = new HandlerThread("FTP_Operation");
        handlerThread.start();
        /* Create a Handler for HandlerThread to post Runnable object */
        requestHandler = new Handler(handlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                //txtView.setText((String) msg.obj);
                Toast.makeText(MainActivity.this,
                        (String)msg.obj,
                        Toast.LENGTH_SHORT)
                        .show();
            }
        };

        Button btCmdFtpDownload = findViewById(R.id.btCmdFtpDownload);
        btCmdFtpDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WriteFtpData();
                FileHandler.getInstance().getFTPManager().download(requestHandler);
            }
        });


        Button btCmdFtpUpload = findViewById(R.id.btCmdFtpUpload);
        btCmdFtpUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WriteFtpData();
                FileHandler.getInstance().getFTPManager().upload(requestHandler);
            }

        });

        Button btCmdFtpUploadPhotos = findViewById(R.id.btCmdFtpPhotoUpload);
        btCmdFtpUploadPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WriteFtpData();
                FileHandler.getInstance().getFTPManager().uploadFiles(requestHandler);
            }

        });

        Button btCmdArchiviere = findViewById(R.id.btCmdArchiviere);
        btCmdArchiviere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(),R.style.AlertDialogTheme);
                AlertDialog dialog = builder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FileHandler.getInstance().archiveAllFiles();
                    }
                }).create();

                //2. now setup to change color of the button
                dialog.setOnShowListener( new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(getBaseContext(), R.color.black));
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(getBaseContext(), R.color.black));
                    }
                });
                dialog.setTitle("Sollen alle Daten archiviert werden? Die Daten sind dann nicht mehr sichtbar");
                dialog.show();
            };
        });
        //registerNetworkCallback();

        etFtpip = findViewById(R.id.etFTP_IP);
        ReadFtp();

        initializeHlptas();
    }


    private void initializeHlptas(){
        FileHandler.getInstance().setNekoDropBox(new NekoDropBox(this));
        FileHandler.getInstance().setFTPManager(new FTPManager(this));
        Dict.getInstance().initializeHelpers(this);
    }


    public void WriteFtpData() {
        // add-write text into file
        try {
            FileOutputStream fileout=openFileOutput(FTP_FILE, MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(etFtpip.getText().toString());
            outputWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void ReadFtp() {
        //reading text from file
        try {
            FileInputStream fileIn=openFileInput(FTP_FILE);
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[256];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                etFtpip.setText(readstring);
            }
            InputRead.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}