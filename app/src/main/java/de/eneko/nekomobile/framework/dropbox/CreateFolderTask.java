package de.eneko.nekomobile.framework.dropbox;

import android.os.AsyncTask;
import android.util.Log;

import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.CreateFolderErrorException;
import com.dropbox.core.v2.files.CreateFolderResult;

public class CreateFolderTask extends AsyncTask<String, Void, CreateFolderResult> {
    private static final String TAG = CreateFolderTask.class.getName();
    private final DbxClientV2 mDbxClient;
    private final CreateFolderTask.Callback mCallback;
    private Exception mException;
    private final String mFolderPath;


    public interface Callback {
        void onCreatedFolder(CreateFolderResult result);

        void onError(Exception e);
    }

    public CreateFolderTask(DbxClientV2 dbxClient, String dropboxFolderPath, CreateFolderTask.Callback callback) {
        mDbxClient = dbxClient;
        mCallback = callback;
        mFolderPath = dropboxFolderPath;
    }

    @Override
    protected void onPostExecute(CreateFolderResult result) {
        super.onPostExecute(result);

        if (mException != null) {
            mCallback.onError(mException);
        } else {
            mCallback.onCreatedFolder(result);
        }
    }

    @Override
    protected CreateFolderResult doInBackground(String... params) {
        try {
            CreateFolderResult result = mDbxClient.files().createFolderV2(mFolderPath);
            System.out.println(result.getMetadata().getName());
            return result;
        } catch (CreateFolderErrorException err) {
            if (err.errorValue.isPath() && err.errorValue.getPathValue().isConflict()) {
                Log.e(TAG,"Something already exists at the path.", err);
            } else {
                Log.e(TAG,"Some other CreateFolderErrorException occurred...", err);
            }
        } catch (Exception err) {
            Log.e(TAG,"Some other Exception occurred...", err);
        }
        return null;
    }
}
