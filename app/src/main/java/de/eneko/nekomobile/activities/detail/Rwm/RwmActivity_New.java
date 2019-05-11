package de.eneko.nekomobile.activities.detail.Rwm;

import android.view.View;

public class RwmActivity_New extends RwmActivity {

    @Override
    protected void onResume() {
        super.onResume();
        viewHolder.getLbAustauschgrund().setVisibility(View.GONE);
        viewHolder.getSpAustauschgrunde().setVisibility(View.GONE);
        viewHolder.getIvBarcode2().setVisibility(View.GONE);
        viewHolder.getLbNewNummer().setVisibility(View.GONE);
        viewHolder.getEtNeueNummer().setVisibility(View.GONE);
    }
}
