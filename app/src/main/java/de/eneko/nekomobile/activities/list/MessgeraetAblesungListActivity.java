package de.eneko.nekomobile.activities.list;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.controllers.MessgeraeteConroller;

public class MessgeraetAblesungListActivity extends MessgeraetListActivity {

    @Override
    protected void onResume() {
        super.onResume();
        ivExim.setBackground(null);
        ivSontex.setBackground(null);
        ivManuell.setBackground(null);

        if (MessgeraeteConroller.getInstance().getGereteart() == MessgeraeteConroller.GeraeteArt.EXIM){
            setAdapterCurrent(mAdapter_exm);
            ivExim.setBackground(getDrawable(R.drawable.textview_border));
        }
        if (MessgeraeteConroller.getInstance().getGereteart() == MessgeraeteConroller.GeraeteArt.SONTEX){
            setAdapterCurrent(mAdapter_son);
            ivSontex.setBackground(getDrawable(R.drawable.textview_border));
        }
        if (MessgeraeteConroller.getInstance().getGereteart() == MessgeraeteConroller.GeraeteArt.MANUELL){
            setAdapterCurrent(mAdapter_man);
            ivManuell.setBackground(getDrawable(R.drawable.textview_border));
        }
    }



}
