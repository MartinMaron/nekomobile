package de.eneko.nekomobile.framework.dropbox;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.CreateFolderErrorException;
import com.dropbox.core.v2.files.CreateFolderResult;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.WriteMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import de.eneko.nekomobile.GlobalConst;

/**
 * Async task to upload a file to a directory
 */
public class UploadFileTask extends AsyncTask<String, Void, FileMetadata> {
    private static final String TAG = UploadFileTask.class.getName();
    private final Context mContext;
    private final DbxClientV2 mDbxClient;
    private final Callback mCallback;
    private Exception mException;

    public interface Callback {
        void onUploadComplete(FileMetadata result);
        void onError(Exception e);
    }

    public UploadFileTask(Context context, DbxClientV2 dbxClient, Callback callback) {
        mContext = context;
        mDbxClient = dbxClient;
        mCallback = callback;
    }

    @Override
    protected void onPostExecute(FileMetadata result) {
        super.onPostExecute(result);
        if (mException != null) {
            mCallback.onError(mException);
        } else if (result == null) {
            mCallback.onError(null);
        } else {
            mCallback.onUploadComplete(result);
        }
    }

    @Override
    protected FileMetadata doInBackground(String... params) {
        File localFile = new File(params[0]);
        String remoteFolderPath = NekoDropBox.BILDER_PATH;
        String remoteFileName = localFile.getName();

        if (localFile.getName().endsWith(".neko.jpg")) {
            String[] dropBoxPathCore = localFile.getName().split("#");
            remoteFolderPath = NekoDropBox.BILDER_PATH + "/" + dropBoxPathCore[0].replace("@","/");
            remoteFileName = dropBoxPathCore[1].replace("#","");
        }

        if (localFile.getName().endsWith(".neko.xml")) {
            remoteFolderPath = NekoDropBox.NEKOMOBILE_PATH;
        }
        if (localFile.getName().endsWith(".son.xml")) {
            remoteFolderPath = NekoDropBox.SONTEX_PATH;
        }
        if (localFile.getName().endsWith(".droid.xml")) {
            remoteFolderPath = NekoDropBox.EXIM_PATH;
        }

        if (localFile.exists()) {
            try {
                mDbxClient.files().createFolderV2(remoteFolderPath);
            } catch (CreateFolderErrorException err) {
                if (err.errorValue.isPath() && err.errorValue.getPathValue().isConflict()) {
                    Log.e(TAG,"Something already exists at the path.");
                } else {
                    Log.e(TAG,"Some other CreateFolderErrorException occurred...");
                }
            } catch (Exception err) {
                Log.e(TAG,"Some other Exception occurred...", err);
                return null;
            }

            try (InputStream inputStream = new FileInputStream(localFile)) {
                FileMetadata ret_val =  mDbxClient.files().uploadBuilder(remoteFolderPath + "/" + remoteFileName)
                        .withMode(WriteMode.OVERWRITE)
                        .uploadAndFinish(inputStream);
                        return ret_val;
            } catch (DbxException | IOException e) {
                mException = e;
            }
        }

        return null;
    }
}
