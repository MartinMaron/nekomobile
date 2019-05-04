package de.eneko.nekomobile.activities.detail.Rwm;

import android.view.View;

public class RwmActivity_Austausch extends RwmActivity {

    @Override
    protected void onResume() {
        super.onResume();

        lbNummer.setVisibility(View.GONE);
        etNummer.setVisibility(View.GONE);
        ivBarcode1.setVisibility(View.GONE);

        lbRaum.setVisibility(View.GONE);
        tvRaum.setVisibility(View.GONE);

    }
}
