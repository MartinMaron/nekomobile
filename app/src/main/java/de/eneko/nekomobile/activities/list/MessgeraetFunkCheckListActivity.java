package de.eneko.nekomobile.activities.list;

import android.view.View;

import de.eneko.nekomobile.activities.adapter.MessgeraetListViewAdapter;

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
        loadDatasourceCore();
    }

    @Override
    protected void loadDatasourceCore() {
        super.loadDatasourceCore();
        setAdapterCurrent(new MessgeraetListViewAdapter (this,datasource));
    }
}
