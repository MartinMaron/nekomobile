package de.eneko.nekomobile.activities.list;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.controllers.MessgeraeteListViewActivityConroller;

public class MessgeraetAblesungListActivity extends MessgeraetListActivity {

    @Override
    protected void onResume() {
        super.onResume();
        ivExim.setBackground(null);
        ivSontex.setBackground(null);
        ivManuell.setBackground(null);

        if (MessgeraeteListViewActivityConroller.getInstance().getGereteart() == MessgeraeteListViewActivityConroller.GeraeteArt.EXIM){
            setAdapterCurrent(mAdapter_exm);
            ivExim.setBackground(getDrawable(R.drawable.textview_border));
        }
        if (MessgeraeteListViewActivityConroller.getInstance().getGereteart() == MessgeraeteListViewActivityConroller.GeraeteArt.SONTEX){
            setAdapterCurrent(mAdapter_son);
            ivSontex.setBackground(getDrawable(R.drawable.textview_border));
        }
        if (MessgeraeteListViewActivityConroller.getInstance().getGereteart() == MessgeraeteListViewActivityConroller.GeraeteArt.MANUELL){
            setAdapterCurrent(mAdapter_man);
            ivManuell.setBackground(getDrawable(R.drawable.textview_border));
        }
    }



}
