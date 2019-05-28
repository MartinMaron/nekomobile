package de.eneko.nekomobile.activities.list;

import android.view.View;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.adapter.MessgeraetListViewAdapter;
import de.eneko.nekomobile.activities.detail.Messgeraete.MessgaeretAblesungActivity;
import de.eneko.nekomobile.controllers.MessgeraeteListViewActivityConroller;

public class MessgeraetFunkCheckListActivity extends MessgeraetListActivity {

    @Override
    protected void onResume() {
        super.onResume();
        ivExim.setBackground(null);
        ivSontex.setBackground(null);
        ivManuell.setBackground(null);
        ivExim.setVisibility(View.GONE);
        ivSontex.setVisibility(View.GONE);
        ivManuell.setVisibility(View.GONE);

        setAdapterCurrent( new MessgeraetListViewAdapter (this,datasource));

    }


}
