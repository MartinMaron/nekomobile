package de.eneko.nekomobile.activities.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.RauchmelderWartungListActivity;
import de.eneko.nekomobile.activities.detail.Rwm.RwmActivity_Austausch;
import de.eneko.nekomobile.activities.detail.Rwm.RwmActivity_Info;
import de.eneko.nekomobile.activities.wrapper.RauchmelderWartungListViewItemWrapper;
import de.eneko.nekomobile.beans.Rauchwarnmelder;
import de.eneko.nekomobile.controllers.FileHandler;

public class RauchmelderWartungListViewAdapter extends ArrayAdapter<Rauchwarnmelder>
{
    private final Context context;
    private final ArrayList<Rauchwarnmelder> values;
    private RauchmelderWartungListActivity activity;

    public RauchmelderWartungListViewAdapter(Context context, ArrayList<Rauchwarnmelder> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
        this.activity = (RauchmelderWartungListActivity) context;
    }

    @Override
    public int getCount()
    {
        return values.size();
    }

    @Override
    public Rauchwarnmelder getItem(int index)
    {
        return values.get(index);
    }

    @Override
    public long getItemId(int id)
    {
        return id;
    }

    @Override
    public View getView(int index, View currentView, ViewGroup parent)
    {


        if (currentView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            currentView = inflater.inflate(R.layout.rwm_wartung_list_item, parent, false);
        }

        //Extrahieren der NoteBean zum nutzen der Werte
        Rauchwarnmelder rwm = getItem(index);
        RauchmelderWartungListViewItemWrapper wrapper = new RauchmelderWartungListViewItemWrapper();

        wrapper.setIvAustausch(currentView.findViewById(R.id.ivAustausch));
        wrapper.setIvRwm(currentView.findViewById(R.id.ivRwm));
        wrapper.setIvPhoto(currentView.findViewById(R.id.ivPhoto));
        wrapper.setIvInfo(currentView.findViewById(R.id.ivInfo));
        wrapper.setTxtvDescription(currentView.findViewById(R.id.txtvRaum));

        wrapper.getIvRwm().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rwm.setDone(!rwm.getDone());
                setRwmImage(wrapper.getIvRwm(),rwm);
                wrapper.getIvAustausch().setVisibility( rwm.getDone() || rwm.getNekoId().contains("new")? View.INVISIBLE:View.VISIBLE);
            }
        });
        setRwmImage(wrapper.getIvRwm(),rwm);
        wrapper.getIvPhoto().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String relativePath = rwm.getTodo().getNutzer().getLiegenschaft().getAdresseOneLine();
                relativePath = relativePath + "/" + rwm.getTodo().getNutzer().getWohnungsnummerMitLage();
                String filename = "RWM@" + rwm.getNummer()+ "@" + rwm.getTodo().getNutzer().getNekoId()+ "@";

                if (!rwm.getNekoId().equals("")){
                    filename = filename  + rwm.getNekoId()+ "@";
                }

                activity.openCameraIntent(relativePath,filename);
            }
        });
        wrapper.getIvInfo().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileHandler.getInstance().setRauchwarnmelder(rwm);
                Intent intent = new Intent(context, RwmActivity_Info.class);
                context.startActivity(intent);
            }
        });
        wrapper.getIvAustausch().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileHandler.getInstance().setRauchwarnmelder(rwm);
                Intent intent = new Intent(context, RwmActivity_Austausch.class);
                context.startActivity(intent);
            }
        });
        wrapper.getIvAustausch().setVisibility( rwm.getDone() || rwm.getNekoId().contains("new")? View.INVISIBLE:View.VISIBLE);
        wrapper.getTxtvDescription().setText((!rwm.getNeueNummer().equals("") ? rwm.getNummer() + " (" + rwm.getNeueNummer() + ")": rwm.getNummer()) + (!rwm.getRaum().equals("") ? " - " + rwm.getRaum():""));
        currentView.setTag(wrapper);

        //Rueckgabe der genierten View
        return currentView;
    }

    private void setRwmImage(ImageView iv, Rauchwarnmelder pRauchmelder){
        if (pRauchmelder.getDone()){
            iv.setImageResource(R.drawable.icon_smoke_detector_green_ok);
        }else {
            if (pRauchmelder.getNekoId().contains("new")) {
                iv.setImageResource(R.drawable.icon_smoke_detector_new);
                iv.setClickable(false);
            }else {
               if (pRauchmelder.getWithError()) {
                   iv.setImageResource(R.drawable.icon_smoke_detector_changed);
               }else{
                   iv.setImageResource(R.drawable.icon_smoke_detector_b);
               }
            }
        }
    }
}



