package de.eneko.nekomobile.listener;

import android.content.Intent;
import android.view.View;

import de.eneko.nekomobile.MainActivity;
import de.eneko.nekomobile.R;

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
        switch (v.getId())
        {
            case R.id.fab:
                break;

            case R.id.btCmdGetRoutes:
                // Fenster mit Liste der Dateien
//                Intent intent = new Intent(mainActivity, FileListActivity.class);
//                mainActivity.startActivity(intent);
                break;
        }
    }
}