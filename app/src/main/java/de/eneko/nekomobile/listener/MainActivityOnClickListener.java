package de.eneko.nekomobile.listener;

import android.content.Intent;
import android.view.View;

import de.eneko.nekomobile.MainActivity;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.DropboxFilesActivity;
import de.eneko.nekomobile.activities.list.FileListActivity;
import de.eneko.nekomobile.framework.dropbox.NekoDropBox;

public class MainActivityOnClickListener implements View.OnClickListener
{
    //MainActivity
    private static MainActivity mainActivity     = null;


    public MainActivityOnClickListener(MainActivity pMainActivity)
    {
        mainActivity = pMainActivity;
    }

    @Override
    public void onClick(View v)
    {
        Intent intent;
        switch (v.getId())
        {
            case R.id.fab:
                mainActivity.startActivity(DropboxFilesActivity.getIntent(mainActivity, NekoDropBox.APPS_PATH));
                break;

            case R.id.btCmdGetRoutes:
                intent = new Intent(mainActivity, FileListActivity.class);
                mainActivity.startActivity(intent);
                break;

            default:


        }
    }
}