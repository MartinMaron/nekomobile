package de.eneko.nekomobile.framework.ftp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.MainActivity;
import de.eneko.nekomobile.controllers.FileHandler;


public class FTPManager {
    private static final String TAG = FTPManager.class.getName();
    public static final String NEKOMOBILE_PATH = GlobalConst.NEKOMOBILE_USER + "/nekomobile";
    public static final String QUNDIS_PATH = GlobalConst.NEKOMOBILE_USER + "/qundis";
    public static final String SONTEX_PATH = GlobalConst.NEKOMOBILE_USER + "/sontex";
    public static final String EXIM_PATH = GlobalConst.NEKOMOBILE_USER + "/exim";
    public static final String BILDER_PATH = GlobalConst.NEKOMOBILE_USER + "/bilder";

    private MainActivity mMainActivity = null;
    protected String email = null;
    protected String username = null;
    protected List<String> currentlyDownloadedFiles = null;
    protected List<PathPair> downloadPaths = null;
    protected FTPClientFunctions ftpHelper = new FTPClientFunctions();;

    private String ftpHost = "91.13.160.64"; //"neko.dyndns-remote.com"; //"91.42.236.106";
    private String uname = "nekoadmin";
    private String haslo = "neko2008";
    private int port = 21;
    private Handler responseHandler;
    private DownloadRunnable dr;



    public FTPManager(MainActivity mainActivity) {
        mMainActivity = mainActivity;

   }

    public void download(Handler pResponseHandler) {
        downloadPaths = new ArrayList<PathPair>();
        downloadPaths.add(new PathPair(NEKOMOBILE_PATH,GlobalConst.PATH_NEKOMOBILE));
        downloadPaths.add(new PathPair(SONTEX_PATH,GlobalConst.PATH_SONTEX));
        downloadPaths.add(new PathPair(EXIM_PATH,GlobalConst.PATH_EXIM));
        downloadPaths.add(new PathPair(QUNDIS_PATH,GlobalConst.PATH_QUNDIS));
        responseHandler = pResponseHandler;
        currentlyDownloadedFiles = new ArrayList<String>();
        performDownloadWithSetPermissions();
    }
    public void upload(Handler pResponseHandler) {
        downloadPaths = new ArrayList<PathPair>();
        downloadPaths.add(new PathPair(NEKOMOBILE_PATH,GlobalConst.PATH_NEKOMOBILE));
        downloadPaths.add(new PathPair(SONTEX_PATH,GlobalConst.PATH_SONTEX));
        downloadPaths.add(new PathPair(EXIM_PATH,GlobalConst.PATH_EXIM));
        downloadPaths.add(new PathPair(QUNDIS_PATH,GlobalConst.PATH_QUNDIS));
        responseHandler = pResponseHandler;
        performUploadWithSetPermissions();
    }
    public void uploadFiles(Handler pResponseHandler) {
        downloadPaths = new ArrayList<PathPair>();
        downloadPaths.add(new PathPair(BILDER_PATH,GlobalConst.PATH_NEKOMOBILE_PICTURES));
        responseHandler = pResponseHandler;
        performUploadWithSetPermissions();
    }

    // region FileUpload
    private void performUploadWithSetPermissions() {
        if (hasPermissionsForAction(FileAction.UPLOAD)) {
            performUploadCore();
            return;
        }

        if (shouldDisplayRationaleForAction(FileAction.UPLOAD)) {
            new AlertDialog.Builder(mMainActivity)
                    .setMessage("This app requires storage access to download and upload files.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermissionsForAction(FileAction.UPLOAD);
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .create()
                    .show();
        } else {
            requestPermissionsForAction(FileAction.UPLOAD);
        }
    }
    private void performUploadCore() {
        // ein neuer Thread wird gestartet: dieser arbeitet unabhängig vom MainUIThread
        new Thread(new UploadRunnable(responseHandler)){
            @Override
            public synchronized void start() {
                super.start();
                responseHandler.post(dr);
            }
        }.start();
    }
    /* Runnable wird mit mit Handler (Looper)
    aus dem MainUIThread versehen.
    Diese technik ermöglicht die Kommunikation mit dem MainUIThread*/
    class UploadRunnable implements Runnable{
        Handler uiHandler;

        public UploadRunnable(Handler uiHandler){
            this.uiHandler=uiHandler;
        }
        public void run(){
            try {
                boolean status = false;
                for(int j = 0; j < downloadPaths.size(); j++){
                    if (ftpHelper.ftpConnect("eneko.zapto.org",uname,haslo,port)){
                        File dir = new File(downloadPaths.get(j).getAndroidTargetPath());
                        if (dir!=null && dir.listFiles() != null && dir.listFiles().length > 0){
                            String[] fileList = null;
                            for(File androidfile: Arrays.stream(dir.listFiles()).filter(r -> r.isFile()).collect(Collectors.toList()))
                            {
                                ftpHelper.ftpChangeDirectory("/" + downloadPaths.get(j).getFTPPath());
                                String ftpFileName = androidfile.getName();
                                if (androidfile.getName().endsWith(".neko.jpg")) {
                                    String[] dropBoxPathCore = androidfile.getName().split("#");
                                    String[] ftpFolderPath = dropBoxPathCore[0].split("@");
                                    for (int i = 0; i < ftpFolderPath.length; i++){
                                        ftpHelper.ftpChangeDirectory(ftpFolderPath[i]);
                                    }
                                    ftpFileName = dropBoxPathCore[1].replace("#","");
                                }else{
                                    ftpFileName = androidfile.getName();
                                }

                                FTPFile[] ftpFiles = ftpHelper.mFTPClient.listFiles(ftpHelper.ftpGetCurrentWorkingDirectory());
                                // die Dateien mit gleichen Namen werden synchronisiert
                                // neue Dateien werden zu dropBox gesendet
                                if (androidfile.isFile() && androidfile.exists()) {

                                    // suchen der entsprechenden Datei im Ordner
                                    List<FTPFile> l = Arrays.stream(ftpFiles).filter(r -> r.isFile() && r.getName().contains(androidfile.getName())).collect(Collectors.toList());
                                    //wenn die Datei vorhanden ist wird geprüft ob die Datei bereits in neko eingelesen wurde
                                    //ansonsten wird neue Datei erzeugt
                                    //Mehr als eine Datei dürfte es nicht geben

                                    if (l.size()==1) {
                                        if (! l.get(0).getName().contains("XXX_DONE_XXX_")){
                                            Message msg = new Message();
                                            msg.obj = l.get(0).getName().replace("File :: ","") + " wird hochgeladen";
                                            /* Send result back to UI Thread Handler */
                                            uiHandler.sendMessage(msg);
                                            tryUploadFile(androidfile,l.get(0).getName());
                                        }
                                    }else if (l.size()==0) {
                                        Message msg = new Message();
                                        msg.obj = ftpFileName.replace("File :: ","") + " wird hochgeladen";
                                        /* Send result back to UI Thread Handler */
                                        uiHandler.sendMessage(msg);
                                        tryUploadFile(androidfile,ftpFileName);
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        }

        private void tryUploadFile(File pAndroidTargetPath, String filename) throws IOException {
            if(ftpHelper.ftpUpload(pAndroidTargetPath.getAbsolutePath(),filename)){
               if (pAndroidTargetPath.getName().contains("neko.jpg")){
                    //nekointerne bilder werden nach übertragung gelöscht '
                   pAndroidTargetPath.delete();
               }
            }
        }
    }

    // endregion

    // region Download
    private void performDownloadWithSetPermissions() {
        if (hasPermissionsForAction(FileAction.DOWNLOAD)) {
            performDownloadCore();
            return;
        }

        if (shouldDisplayRationaleForAction(FileAction.DOWNLOAD)) {
            new AlertDialog.Builder(mMainActivity)
                    .setMessage("This app requires storage access to download and upload files.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermissionsForAction(FileAction.DOWNLOAD);
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .create()
                    .show();
        } else {
            requestPermissionsForAction(FileAction.DOWNLOAD);
        }
    }
    private void performDownloadCore() {
        // ein neuer Thread wird gestartet: dieser arbeitet unabhängig vom MainUIThread
        new Thread(new DownloadRunnable(responseHandler)){
            @Override
            public synchronized void start() {
                super.start();
                responseHandler.post(dr);
            }
        }.start();
    }
    /* Runnable wird mit mit Handler (Looper)
    aus dem MainUIThread versehen.
    Diese technik ermöglicht die Kommunikation mit dem MainUIThread*/
    class DownloadRunnable implements Runnable{
         Handler uiHandler;

        public DownloadRunnable(Handler uiHandler){
            this.uiHandler=uiHandler;
        }
        public void run(){
            try {
                boolean status = false;
//              alle verzeichnispaare werden durchlaufen
                for(int j = 0; j < downloadPaths.size(); j++){
//                  verbinden mit FTP Laufwerk
                    if (ftpHelper.ftpConnect("eneko.zapto.org",uname,haslo,port)){
//                      Pfad auf dem android-Gerät
                        File androidPath = new File(downloadPaths.get(j).getAndroidTargetPath());

//                      Liste der Dateien auf dem entsprechenden FTP-Pfad
                        String[] fileList = null;
                        Boolean b = ftpHelper.ftpChangeDirectory("/" + downloadPaths.get(j).getFTPPath());
                        fileList = ftpHelper.ftpPrintFilesList(ftpHelper.ftpGetCurrentWorkingDirectory());

//                      alle Dateien auf dem FTP-Laufwerk werden überprüft
                        for (int i = 0; i< fileList.length; i++)

                        {
//                          Datei auf dem FTP-Laufwerk
                            String loopfile = fileList[i];
                            if (loopfile.startsWith("Directory")) continue;

                            //falls die Datei nicht existiert dann wird sie heruntergeladen
                            File checkFile = new File(androidPath, loopfile.replace("File :: ",""));
                            if (!checkFile.exists() && loopfile.toString().startsWith("File :: "))
                            {
                                try {
                                    if (!androidPath.exists()) {androidPath.mkdir();}
                                    if (tryDownloadFile(loopfile.replace("File :: ",""), androidPath)){
                                        Message msg = new Message();
                                        msg.obj = loopfile.replace("File :: ","") + " wurde heruntergeladen";
                                        /* Send result back to UI Thread Handler */
                                        uiHandler.sendMessage(msg);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
        private boolean tryDownloadFile(String filename, File pAndroidTargetPath) throws IOException {
            if (filename.contains("XXX_DONE_XXX")) {
                File fi = new File(pAndroidTargetPath.getAbsolutePath() + "/" + filename.replace("XXX_DONE_XXX_",""));
                if (fi.exists()) {
                    archiveFile(pAndroidTargetPath.getAbsolutePath() + "/archive",fi);
                }
                return false;
            }

            if(ftpHelper.ftpDownload(filename, pAndroidTargetPath.getAbsolutePath())){
                currentlyDownloadedFiles.add(filename);
            }
            return true;
        }

        private void archiveFile(String archivePath, File file) {
            File folder = new File(archivePath);
            if (!folder.exists()) {folder.mkdir();}
            SimpleDateFormat zeitformat = new SimpleDateFormat("dd.MM.yyyy_HH");
            String lastModifiedString = zeitformat.format(new Date(file.lastModified()));
            file.renameTo(new File(archivePath,lastModifiedString + "_" + file.getName()));
            file.delete();
        }
    }

    // endregion

    // region "Permissions"
    private boolean hasPermissionsForAction(FTPManager.FileAction action) {
        for (String permission : action.getPermissions()) {
            int result = ContextCompat.checkSelfPermission(mMainActivity, permission);
            if (result == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }
    private boolean shouldDisplayRationaleForAction(FTPManager.FileAction action) {
        for (String permission : action.getPermissions()) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(mMainActivity, permission)) {
                return true;
            }
        }
        return false;
    }
    private enum FileAction {
        DOWNLOAD(Manifest.permission.WRITE_EXTERNAL_STORAGE),
        UPLOAD(Manifest.permission.READ_EXTERNAL_STORAGE);

        private static final FTPManager.FileAction[] values = values();

        private final String [] permissions;

        FileAction(String ... permissions) {
            this.permissions = permissions;
        }

        public int getCode() {
            return ordinal();
        }

        public String [] getPermissions() {
            return permissions;
        }

        public static FTPManager.FileAction fromCode(int code) {
            if (code < 0 || code >= values.length) {
                throw new IllegalArgumentException("Invalid FileAction code: " + code);
            }
            return values[code];
        }
    }
    private void requestPermissionsForAction(FTPManager.FileAction action) {
        ActivityCompat.requestPermissions(
                mMainActivity,
                action.getPermissions(),
                action.getCode()
        );
    }
    // endregion

    // region Helper

    class PathPair
    {
        private String mFTPPath;
        private String mAndroidTargetPath;

        public PathPair(String pFTPPath, String pAndroidTargetPath){
            this.mFTPPath = pFTPPath;
            this.mAndroidTargetPath = pAndroidTargetPath;
        }

        public String getFTPPath() {
            return mFTPPath;
        }

        public void setFTPPath(String FTPPath) {
            mFTPPath = FTPPath;
        }

        public String getAndroidTargetPath() {
            return mAndroidTargetPath;
        }

        public void setAndroidTargetPath(String androidTargetPath) {
            mAndroidTargetPath = androidTargetPath;
        }
    }
    // endregion
    public void setFtpHost(String ftpHost) {
        this.ftpHost = ftpHost;
    }
    public String getFtpHost() {
        return this.ftpHost;
    }
}






