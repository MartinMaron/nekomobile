package de.eneko.nekomobile.framework.dropbox;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import de.eneko.nekomobile.GlobalConst;
import de.eneko.nekomobile.MainActivity;
import de.eneko.nekomobile.services.DropBoxService;


public class NekoDropBox {
    private static final String TAG = NekoDropBox.class.getName();
    public static final String ACCESS_TOKEN = "KEiL-5TaoSMAAAAAAACM2uj3dM6ScpYJ6CnnTWIqX-DOcqpwiK5MMxMA73j8TXwD";
    public static final String NEKOMOBILE_PATH = "/apps/" + GlobalConst.NEKOMOBILE_USER + "/nekomobile";
    public static final String SONTEX_PATH = "/apps/" + GlobalConst.NEKOMOBILE_USER + "/sontex";
    public static final String EXIM_PATH = "/apps/" + GlobalConst.NEKOMOBILE_USER + "/exim";
    public static final String BILDER_PATH = "/apps/" + GlobalConst.NEKOMOBILE_USER + "/bilder";
    public static final String APPS_PATH = "/apps/";

    private MainActivity mMainActivity = null;
    protected String email = null;
    protected String username = null;
    protected String accountType = null;

    protected List<Metadata> currentlyDownloadedFiles = null;



    public NekoDropBox(MainActivity mainActivity) {
        mMainActivity = mainActivity;
        SharedPreferences prefs = mMainActivity.getSharedPreferences("nekomobile",mMainActivity.MODE_PRIVATE);
        String accessToken = prefs.getString("dropbox-access-token", ACCESS_TOKEN);
        prefs.edit().putString("dropbox-access-token", accessToken).apply();
        DropboxClientFactory.init(accessToken);
        PicassoClient.init(mainActivity.getApplicationContext(), DropboxClientFactory.getClient());

        //TODO: einschalten wenn produktiv (so alnge nur störend)
        Intent i = new Intent(mMainActivity, DropBoxService.class);
        mMainActivity.startService(i);
    }


    public boolean hasToken() {
        SharedPreferences prefs = mMainActivity.getSharedPreferences("nekomobile", mMainActivity.MODE_PRIVATE);
        String accessToken = prefs.getString("dropbox-access-token", null);
        return accessToken != null;
    }

    public void synchronize(Boolean pWithPictures) {
        new GetCurrentAccountTask(DropboxClientFactory.getClient(), new GetCurrentAccountTask.Callback() {
            @Override
            public void onComplete(FullAccount result) {
                email = result.getEmail();
                username = result.getName().getDisplayName();
                accountType = result.getAccountType().name();
                //aktuell heruntergeladene Datein sollen nicht wieder synchroniziert werden
                currentlyDownloadedFiles = new ArrayList<Metadata>();

                //Toast.makeText(mMainActivity,"Verbindung zu Dropbox erfogreich",Toast.LENGTH_LONG).show();
                syncDownloadPath(NEKOMOBILE_PATH, GlobalConst.PATH_NEKOMOBILE);
                syncDownloadPath(SONTEX_PATH, GlobalConst.PATH_SONTEX);
                syncDownloadPath(EXIM_PATH, GlobalConst.PATH_EXIM);
                syncUploadPath(BILDER_PATH,GlobalConst.PATH_NEKOMOBILE_PICTURES);
                syncUploadPath(NEKOMOBILE_PATH,GlobalConst.PATH_NEKOMOBILE);
                syncUploadPath(SONTEX_PATH,GlobalConst.PATH_SONTEX);
                syncUploadPath(EXIM_PATH,GlobalConst.PATH_EXIM);
                moveFilesToArchive(GlobalConst.PATH_SONTEX);
                moveFilesToArchive(GlobalConst.PATH_NEKOMOBILE);
                moveFilesToArchive(GlobalConst.PATH_EXIM);
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(mMainActivity.getBaseContext(),"Keine verbindung zu Dropbox",Toast.LENGTH_LONG).show();
            }
        }).execute();
    }


    // region moveFiles
    public void moveFilesToArchive(String pHomeDevicePath)
    {
        //new java.util.Date(result.lastModified())
        String archivePath = pHomeDevicePath + "/archive";
        File dir = new File(pHomeDevicePath);
        if (dir != null && dir.listFiles() != null && dir.listFiles().length > 0) {
            for (File loopfile : Arrays.stream(dir.listFiles()).collect(Collectors.toList())) {
                if (loopfile.isFile() && loopfile.exists()) {
                    moveFile(archivePath, loopfile,false);
                }
            }
        }

    }

    private void moveFile(String archivePath, File file, boolean force) {
        Date lastModified = new Date(file.lastModified());
        Calendar validDate = Calendar.getInstance();
        validDate.add(Calendar.DATE, GlobalConst.DAYS_TO_ARCHIVE);
        if (! lastModified.after(validDate.getTime())){
            File folder = new File(archivePath);
            if (!folder.exists()) {folder.mkdir();}
            SimpleDateFormat zeitformat = new SimpleDateFormat("dd.MM.yyyy_HH");
            String lastModifiedString = zeitformat.format(new Date(file.lastModified()));
            file.renameTo(new File(archivePath,lastModifiedString + "_" + file.getName()));
            file.delete();
        }
    }

// endregion moveFiles

    // region download

    private void syncDownloadPath(String downloadPath, String targetPath){
        File folder = new File(targetPath);
        if (!folder.exists()) {folder.mkdir();}

        new ListFolderTask(DropboxClientFactory.getClient(),downloadPath , new ListFolderTask.Callback() {
            @Override
            public void onDataLoaded(ListFolderResult result) {
                performDownloadWithPermissions(Collections.unmodifiableList(result.getEntries()), targetPath);
            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG, "Failed to list folder.", e);
                Toast.makeText(mMainActivity,"An error has occurred",Toast.LENGTH_SHORT).show();
            }
        }).execute(NEKOMOBILE_PATH);
    }
    private void downloadFile(Metadata file, File pAndroidTargetPath) {
        final ProgressDialog dialog = new ProgressDialog(mMainActivity);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.setMessage("Downloading: " + file.getName());
        dialog.show();
        currentlyDownloadedFiles.add(file);

        new DownloadFileTask(mMainActivity, DropboxClientFactory.getClient(),pAndroidTargetPath , new DownloadFileTask.Callback() {
            @Override
            public void onDownloadComplete(File result) {
                dialog.dismiss();
                if (result != null) {
                    Log.e(TAG, "download file success.");
                    if (result.exists()) {
                        Date nDate = ((FileMetadata) file).getServerModified();
                        result.setLastModified(nDate.getTime()-60000*60*24*15);
                        Log.e(TAG, new java.util.Date(result.lastModified()).toString());
                    }
                }
            }

            @Override
            public void onError(Exception e) {
                dialog.dismiss();
                Log.e(TAG, "Failed to download file.", e);
            }


        }).execute((FileMetadata) file);
    }

    private void performDownloadAction(List<Metadata> pFiles, String pTargetPath) {
        File androidPath = new File(pTargetPath);
        for(Metadata loopfile: pFiles)
        {
            if (loopfile.getName().startsWith("XXX_DONE_XXX")) continue;

            File checkFile = new File(androidPath, loopfile.getName());
            if (!checkFile.exists() && loopfile.toString().startsWith("{\".tag\":\"file\""))
            {
                downloadFile(loopfile, androidPath);
            }
        }
    }

    // endregion download

    // region upload
    private void syncUploadPath(String pDropBoxPath, String pHomeDevicePath){
        File folder = new File(pHomeDevicePath);
        if (!folder.exists()) {folder.mkdir();}

        new ListFolderTask(DropboxClientFactory.getClient(), pDropBoxPath , new ListFolderTask.Callback() {
            @Override
            public void onDataLoaded(ListFolderResult result) {
                performUploadWithPermissions (Collections.unmodifiableList(result.getEntries()),pHomeDevicePath);
            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG, "Failed to list folder.", e);
                Toast.makeText(mMainActivity,"An error has occurred",Toast.LENGTH_SHORT).show();
            }
        }).execute(pDropBoxPath);
    }
    private void uploadFile(File fileUri) {

        final ProgressDialog dialog = new ProgressDialog(mMainActivity);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.setMessage("Uploading: " + fileUri.getName());
        dialog.show();

        new UploadFileTask(mMainActivity, DropboxClientFactory.getClient(), new UploadFileTask.Callback() {
            @Override
            public void onUploadComplete(FileMetadata result) {
                if (fileUri.getName().endsWith("neko.jpg")) fileUri.delete();
                dialog.dismiss();
               // Toast.makeText(mMainActivity,fileUri.getName() + " wpisana na Dropbox.",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Exception e) {
                dialog.dismiss();

                Log.e(TAG, "Failed to upload file.", e);
                Toast.makeText(mMainActivity,"An error has occurred",Toast.LENGTH_SHORT).show();
            }
        }).execute(fileUri.getAbsolutePath());
    }
    private void performUploadAction(List<Metadata> pFiles, String pHomeDevicePath) {
        File dir = new File(pHomeDevicePath);
        if (dir.listFiles().length > 0){
            for(File loopfile: Arrays.stream(dir.listFiles()).collect(Collectors.toList()))
            {
                // die Dateien mit gleichen Namen werden synchronisiert
                // neue Dateien werden zu dropBox gesendet
                if (loopfile.isFile() && loopfile.exists()) {
                    List q = pFiles.stream().filter(r -> r.getName().equals(loopfile.getName())).collect(Collectors.toList());
                    if (q.size() > 0)
                    {
                        FileMetadata fileMetadata = (FileMetadata) q.get(0);
                        if (!currentlyDownloadedFiles.contains(q.get(0)) && fileMetadata.getServerModified().compareTo(new java.util.Date(loopfile.lastModified())) < 0)
                        {
                            uploadFile(loopfile);
                        }
                    }
                    else
                    {
                        List qDel = pFiles.stream().filter(r -> (r.getName()).equals("XXX_DONE_XXX_" + loopfile.getName())).collect(Collectors.toList());
                        if (qDel.size() > 0)
                        {
                            //Tue nichts
                        }else
                        {
                            uploadFile(loopfile);
                        }
                    }
                }
            }
        }

    }
    // endregion upload

    // region helpers
    private void performDownloadWithPermissions(List<Metadata> pFiles, String targetPath) {
        if (hasPermissionsForAction(FileAction.DOWNLOAD)) {
            performDownloadAction(pFiles, targetPath);
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
    private void performUploadWithPermissions(List<Metadata> pFiles, String pHomeDevicePath) {
        if (hasPermissionsForAction(FileAction.UPLOAD)) {
            performUploadAction(pFiles, pHomeDevicePath);
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

    private boolean hasPermissionsForAction(NekoDropBox.FileAction action) {
        for (String permission : action.getPermissions()) {
            int result = ContextCompat.checkSelfPermission(mMainActivity, permission);
            if (result == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }
    private boolean shouldDisplayRationaleForAction(NekoDropBox.FileAction action) {
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

        private static final NekoDropBox.FileAction[] values = values();

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

        public static NekoDropBox.FileAction fromCode(int code) {
            if (code < 0 || code >= values.length) {
                throw new IllegalArgumentException("Invalid FileAction code: " + code);
            }
            return values[code];
        }
    }
    private void requestPermissionsForAction(NekoDropBox.FileAction action) {
        ActivityCompat.requestPermissions(
                mMainActivity,
                action.getPermissions(),
                action.getCode()
        );
    }

    // endregion helpers




}






