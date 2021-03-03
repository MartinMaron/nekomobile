package de.eneko.nekomobile.framework.dropbox;

import android.os.AsyncTask;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;

/**
 * Async task to list items in a folder
 */
public class ListFolderTask extends AsyncTask<String, Void, ListFolderResult> {

    private final DbxClientV2 mDbxClient;
    private final Callback mCallback;
    private Exception mException;
    private final String mFolderPath;

    public interface Callback {
        void onDataLoaded(ListFolderResult result);

        void onError(Exception e);
    }

    public ListFolderTask(DbxClientV2 dbxClient, String dropboxFolderPath, Callback callback) {
        mDbxClient = dbxClient;
        mCallback = callback;
        mFolderPath = dropboxFolderPath;
    }

    @Override
    protected void onPostExecute(ListFolderResult result) {
        super.onPostExecute(result);

        if (mException != null) {
            mCallback.onError(mException);
        } else {
            mCallback.onDataLoaded(result);
        }
    }

    @Override
    protected ListFolderResult doInBackground(String... params) {
        try {
            ListFolderResult myListFolderResult = mDbxClient.files().listFolder(mFolderPath);
            return mDbxClient.files().listFolder(mFolderPath);
        } catch (DbxException e) {
            mException = e;
        }

        return null;
    }
}
