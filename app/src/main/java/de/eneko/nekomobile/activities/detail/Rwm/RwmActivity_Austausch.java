package de.eneko.nekomobile.activities.detail.Rwm;

import android.view.View;

public class RwmActivity_Austausch extends RwmActivity {

    @Override
    protected void onResume() {
        super.onResume();

        viewHolder.getLbNummer().setVisibility(View.GONE);
        viewHolder.getEtNummer().setVisibility(View.GONE);
        viewHolder.getIvBarcode1().setVisibility(View.GONE);

        viewHolder.getLbRaum().setVisibility(View.GONE);
        viewHolder.getTvRaum().setVisibility(View.GONE);

    }
}
