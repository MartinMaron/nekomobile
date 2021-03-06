package de.eneko.nekomobile.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.controllers.FileHandler;
import de.eneko.nekomobile.framework.dropbox.NekoDropBox;

public class DropBoxService extends Service {
    private static final String TAG = DropBoxService.class.getName();
    private Looper serviceLooper;
    private ServiceHandler serviceHandler;
    private NekoDropBox nekoDropBox = null;
    private boolean firstStart = true;

    // Handler that receives messages from the thread
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            try {

                WifiManager wifiManager;
                wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();

                if (firstStart == true) {
                    firstStart = false;
                    Thread.sleep(GlobalConst.DROPBOX_SERVICE_INTERVALL);
                } else {

                    if (wifiInfo.getSupplicantState() == SupplicantState.COMPLETED) {
                        Calendar kalender = Calendar.getInstance();
                        SimpleDateFormat zeitformat = new SimpleDateFormat("HH");
//                        int uhr = Integer.parseInt(zeitformat.format(kalender.getTime()));
//                        if (uhr >= 22 || uhr <= 6) {
                            nekoDropBox.synchronize(true);
//                        }
                    }
                    Thread.sleep(GlobalConst.DROPBOX_SERVICE_INTERVALL);
                }

            }   catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    @Override
    public void onCreate() {
        // Start up the thread running the service. Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block. We also make it
        // background priority so CPU-intensive work doesn't disrupt our UI.
        HandlerThread thread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        // Get the HandlerThread's Looper and use it for our Handler
        serviceLooper = thread.getLooper();
        serviceHandler = new ServiceHandler(serviceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        nekoDropBox = FileHandler.getInstance().getNekoDropBox();

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        Message msg = serviceHandler.obtainMessage();
        msg.arg1 = startId;
        serviceHandler.sendMessage(msg);

        System.out.println("Main thread: " + Thread.currentThread());
        Timer timer = new Timer();
        final long start = System.currentTimeMillis();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run () {
                Message msg = serviceHandler.obtainMessage();
                msg.arg1 = startId;
                serviceHandler.sendMessage(msg);
              }
        }, 3600000, 3600000);

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }



    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Nekomobile: Dropboxservice done", Toast.LENGTH_SHORT).show();
    }
}