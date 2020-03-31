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

public class MessgaeretBewertungActivity extends AppCompatActivity {

    protected BewertungViewHolder viewHolder = null;
    protected MenuItem showBookPictureMenuItem = null;

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
        showBookPictureMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int pictureId = -1;

//                if (viewHolder.getEtGrundparameter().getText().toString().equals(""))
//                {
//                    Toast.makeText(this,"kein Grundparameter ausgesucht",Toast.LENGTH_SHORT);
//                    return false;
//                }
                switch (viewHolder.getEtGrundparameter().getText().toString().substring(0,2)) {
                    case "11":pictureId = R.drawable.bew_11_gp;break;
                    case "12":pictureId = R.drawable.bew_12_gp;break;
                    case "13":pictureId = R.drawable.bew_13_gp;break;
                    case "14":pictureId = R.drawable.bew_14_gp;break;
                    case "21":pictureId = R.drawable.bew_21_gp;break;
                    case "22":pictureId = R.drawable.bew_22_gp;break;
                    case "23":pictureId = R.drawable.bew_23_gp;break;
                    case "24":pictureId = R.drawable.bew_24_gp;break;
                    case "31":pictureId = R.drawable.bew_31_gp;break;
                    case "32":pictureId = R.drawable.bew_32_gp;break;
                    case "33":pictureId = R.drawable.bew_33_gp;break;
                    case "41":pictureId = R.drawable.bew_41_gp;break;
                    case "42":pictureId = R.drawable.bew_42_gp;break;
                    case "43":pictureId = R.drawable.bew_43_gp;break;
                    case "51":pictureId = R.drawable.bew_51_gp;break;
                    case "52":pictureId = R.drawable.bew_52_gp;break;
                    case "61":pictureId = R.drawable.bew_61_gp;break;
                    case "71":pictureId = R.drawable.bew_71_gp;break;
                    case "72":pictureId = R.drawable.bew_72_gp;break;
                    case "73":pictureId = R.drawable.bew_73_gp;break;
                    case "74":pictureId = R.drawable.bew_74_gp;break;
                    case "75":pictureId = R.drawable.bew_75_gp;break;
                    case "76":pictureId = R.drawable.bew_76_gp;break;
                    case "81":pictureId = R.drawable.bew_81_gp;break;
                    case "82":pictureId = R.drawable.bew_82_gp;break;
                    case "83":pictureId = R.drawable.bew_83_gp;break;
                    default:
                        Toast.makeText(viewHolder.getActivity(),"Grundparameter  unbekannt",Toast.LENGTH_SHORT);
                        return false;
                }
                Intent intent;
                intent = new Intent(viewHolder.getActivity(), PictureViewActivity.class);
                intent.putExtra("PICTURE_ID",pictureId);
                viewHolder.getActivity().startActivity(intent);
                return true;
            }
        });
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_save:
                viewHolder.save();
                exit();
                return true;
            case R.id.showPicture:
                int pictureId = -1;

                if (viewHolder.getEtGrundparameter().getText().toString().equals(""))
                {
                    Toast.makeText(viewHolder.getActivity(),"kein Grundparameter ausgesucht",Toast.LENGTH_SHORT);
                    return false;
                }
                switch (viewHolder.getEtGrundparameter().getText().toString().substring(0,2)) {
                    case "11":
                        Toast.makeText(viewHolder.getActivity(),"Grundparameter 11",Toast.LENGTH_SHORT);
                        pictureId = R.drawable.bew_11_gp;
                        break;

                    default:
                        Toast.makeText(viewHolder.getActivity(),"Grundparameter  unbekannt",Toast.LENGTH_SHORT);
                        return false;
                }
                Intent intent;
                intent = new Intent(viewHolder.getActivity(), PictureViewActivity.class);
                intent.putExtra("PICTURE_ID",pictureId);
                viewHolder.getActivity().startActivity(intent);

                return true;
        }
        return false;
    }
}
