package de.eneko.nekomobile.activities.detail.Rwm;

import android.view.View;

public class RwmActivity_Info extends RwmActivity {

    @Override
    protected void onResume() {
        super.onResume();
        this.lbAustauschgrund.setVisibility(View.GONE);
        this.spAustauschgrunde.setVisibility(View.GONE);
        this.ivBarcode2.setVisibility(View.GONE);
        this.lbNewNummer.setVisibility(View.GONE);
        this.etNeueNummer.setVisibility(View.GONE);
        this.ivBarcode1.setVisibility(View.GONE);
    }
}
