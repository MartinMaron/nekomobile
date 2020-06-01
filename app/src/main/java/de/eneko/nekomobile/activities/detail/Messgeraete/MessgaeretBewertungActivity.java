package de.eneko.nekomobile.activities.detail.Messgeraete;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.PictureViewActivity;
import de.eneko.nekomobile.activities.viewHolder.Messgearete.BewertungViewHolder;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.controllers.CurrentObjectNavigation;
import de.eneko.nekomobile.controllers.Dict;

public class MessgaeretBewertungActivity extends AppCompatActivity implements MenuItem.OnMenuItemClickListener {

    protected BewertungViewHolder viewHolder = null;
    protected MenuItem showBookPictureMenuItem = null;
    protected MenuItem showBookPicture2MenuItem = null;

    public BewertungViewHolder getViewHolder() {
        return viewHolder;
    }

    public void setViewHolder(BewertungViewHolder viewHolder) {
        this.viewHolder = viewHolder;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_bewertung);
        createViewHolder();
    }

    protected void createViewHolder(){
        Messgeraet obj = CurrentObjectNavigation.getInstance().getMessgeraet();
        viewHolder = new BewertungViewHolder( null,obj.getBaseModel(), this){

            @Override
            protected void loadData(){
                super.loadData();
                createLayout();
            }

            @Override
            protected void createLayout() {

                if (getBasemodel().getTodo().getArt().equals(Dict.TODO_MONTAGE_HKV)
                        || getBasemodel().getBean().getTodo().getArt().equals(Dict.TODO_FUNK_CHECK))
                {
                    if (getBasemodel().getArt().equals("HKV"))
                    {
                        getLbGrundparameter().setVisibility(View.VISIBLE);
                        getEtGrundparameter().setVisibility(View.VISIBLE);
                    }
                }
            }
        };
        viewHolder.updateView();
    }

    protected void exit() {
        Intent intent = new Intent(this, MessgaeretAustauschActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        exit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewHolder.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bewertung_detail_menu, menu);
        showBookPictureMenuItem = menu.findItem(R.id.showPicture);
        showBookPictureMenuItem.setOnMenuItemClickListener(this);
        showBookPicture2MenuItem = menu.findItem(R.id.showPictureBook);
        showBookPicture2MenuItem.setOnMenuItemClickListener(this);
        return true;
    }

    @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            int pictureId = -1;
            int pictureId2 = -1;

            if (viewHolder.getEtGrundparameter().getText().toString().equals(""))
            {
                return false;
            }
            switch (viewHolder.getEtGrundparameter().getText().toString().substring(0,2)) {
                case "11":pictureId = R.drawable.webesfront11;pictureId2 = R.drawable.abmessung11;break;
                case "12":pictureId = R.drawable.webesfront12;pictureId2 = R.drawable.abmessung12;break;
                case "13":pictureId = R.drawable.webesfront13;pictureId2 = R.drawable.abmessung13;break;
                case "14":pictureId = R.drawable.webesfront14;pictureId2 = R.drawable.abmessung14;break;
                case "21":pictureId = R.drawable.webesfront21;pictureId2 = R.drawable.abmessung21;break;
                case "22":pictureId = R.drawable.webesfront22;pictureId2 = R.drawable.abmessung22;break;
                case "23":pictureId = R.drawable.webesfront23;pictureId2 = R.drawable.abmessung23;break;
                case "24":pictureId = R.drawable.webesfront24;pictureId2 = R.drawable.abmessung24;break;
                case "31":pictureId = R.drawable.webesfront31;pictureId2 = R.drawable.abmessung31;break;
                case "32":pictureId = R.drawable.webesfront32;pictureId2 = R.drawable.abmessung32;break;
                case "33":pictureId = R.drawable.webesfront33;pictureId2 = R.drawable.abmessung33;break;
                case "41":pictureId = R.drawable.webesfront41;pictureId2 = R.drawable.abmessung42;break;
                case "42":pictureId = R.drawable.webesfront42;pictureId2 = R.drawable.abmessung42;break;
                case "43":pictureId = R.drawable.webesfront43;pictureId2 = R.drawable.abmessung43;break;
                case "51":pictureId = R.drawable.webesfront51;pictureId2 = R.drawable.abmessung51;break;
                case "52":pictureId = R.drawable.webesfront52;pictureId2 = R.drawable.abmessung52;break;
                case "61":pictureId = R.drawable.webesfront61;pictureId2 = R.drawable.abmessung61;break;
                case "71":pictureId = R.drawable.webesfront71;pictureId2 = R.drawable.abmessung71;break;
                case "72":pictureId = R.drawable.webesfront72;pictureId2 = R.drawable.abmessung72;break;
                case "73":pictureId = R.drawable.webesfront73;pictureId2 = R.drawable.abmessung73;break;
                case "74":pictureId = R.drawable.webesfront74;pictureId2 = R.drawable.abmessung74;break;
                case "75":pictureId = R.drawable.webesfront75;pictureId2 = R.drawable.abmessung75;break;
                case "76":pictureId = R.drawable.webesfront76;pictureId2 = R.drawable.abmessung76;break;
                case "81":pictureId = R.drawable.webesfront81;pictureId2 = R.drawable.abmessung81;break;
                case "82":pictureId = R.drawable.webesfront82;pictureId2 = R.drawable.abmessung82;break;
                case "83":pictureId = R.drawable.webesfront83;pictureId2 = R.drawable.abmessung83;break;
                default:
                    Toast.makeText(viewHolder.getActivity(),"Grundparameter  unbekannt",Toast.LENGTH_SHORT);
                    return false;
            }
            Intent intent;
            intent = new Intent(viewHolder.getActivity(), PictureViewActivity.class);

            if (menuItem.getItemId() == R.id.showPicture) {intent.putExtra("PICTURE_ID",pictureId);}
            if (menuItem.getItemId() == R.id.showPictureBook) {intent.putExtra("PICTURE_ID",pictureId2);}

            viewHolder.getActivity().startActivity(intent);
            return true;
        }



    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_save:
                viewHolder.save();
                exit();
                return true;
            case R.id.showPicture:
//                int pictureId = -1;
//
//                if (viewHolder.getEtGrundparameter().getText().toString().equals(""))
//                {
//                    Toast.makeText(viewHolder.getActivity(),"kein Grundparameter ausgesucht",Toast.LENGTH_SHORT);
//                    return false;
//                }
//                switch (viewHolder.getEtGrundparameter().getText().toString().substring(0,2)) {
//                    case "11":
//                        Toast.makeText(viewHolder.getActivity(),"Grundparameter 11",Toast.LENGTH_SHORT);
//                        pictureId = R.drawable.bew_11_gp;
//                        break;
//
//                    default:
//                        Toast.makeText(viewHolder.getActivity(),"Grundparameter  unbekannt",Toast.LENGTH_SHORT);
//                        return false;
//                }
//                Intent intent;
//                intent = new Intent(viewHolder.getActivity(), PictureViewActivity.class);
//                intent.putExtra("PICTURE_ID",pictureId);
//                viewHolder.getActivity().startActivity(intent);

                return true;
        }
        return false;
    }
}
