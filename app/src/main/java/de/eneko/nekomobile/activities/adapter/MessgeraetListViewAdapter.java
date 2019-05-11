package de.eneko.nekomobile.activities.adapter;


import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.Locale;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.MessgeraetListActivity;
import de.eneko.nekomobile.activities.rowViewHolder.MessgeraetListViewItemWrapper;
import de.eneko.nekomobile.beans.Messgeraet;
import de.eneko.nekomobile.controllers.FileHandler;
import de.eneko.nekomobile.controllers.FormatHelper;
import de.eneko.nekomobile.controllers.MessgeraeteListViewActivityConroller;


public class MessgeraetListViewAdapter extends ArrayAdapter<Messgeraet> implements Filterable
{
    public final static Integer REQUEST_BT_STICHTAG = 1001;
    public final static Integer REQUEST_BT_AKTUELL = 1002;
    private final Context context;
    private final ArrayList<Messgeraet> values;
    private ArrayList<Messgeraet> valuesFiltered;

    private MessgeraetListActivity activity;
    private customFilter mFilter;

    public MessgeraetListViewAdapter(Context context, ArrayList<Messgeraet> values) {
        super(context, -1, values);
        this.context = context;
        this.activity = (MessgeraetListActivity) context;
        this.values = values;
        this.valuesFiltered = values;
        getFilter();
    }

    @Override
    public int getCount()
    {
        return valuesFiltered.size();
    }

    @Override
    public Messgeraet getItem(int index)
    {
        return valuesFiltered.get(index);
    }

    @Override
    public long getItemId(int id)
    {
        return id;
    }

    @Override
    public View getView(int index, View currentView, ViewGroup parent)
    {

        //Extrahieren der NoteBean zum nutzen der Werte
        Messgeraet obj = getItem(index);

        MessgeraetListViewItemWrapper wrapper = new MessgeraetListViewItemWrapper();

        if(currentView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            currentView = inflater.inflate(R.layout.messgeraet_list_item, parent, false);

        }

        wrapper.setIvStatus(currentView.findViewById(R.id.ivStatus));
        wrapper.setIvPhoto(currentView.findViewById(R.id.ivPhoto));
//        wrapper.getIvStatus().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!nutzer.isCompleted()){
//                    nutzer.setAbwesend(!nutzer.getAbwesend());
//                }
//                wrapper.getIvStatus().setImageResource(nutzer.getStatusImageResourceId());
//            }
//        });



        wrapper.setTvNummer(currentView.findViewById(R.id.tvNummer));
        wrapper.setTvRaum(currentView.findViewById(R.id.tvRaum));
        wrapper.setLbAktuell(currentView.findViewById(R.id.lbAktuell));
        wrapper.setLbStichtag(currentView.findViewById(R.id.lbStichtag));
        wrapper.setTvAktuell(currentView.findViewById(R.id.tvAktuell));
        wrapper.setTvLetzterWert(currentView.findViewById(R.id.tvLetzterWert));
        wrapper.setTvStichtag(currentView.findViewById(R.id.tvStichtag));
        wrapper.setIvStatus(currentView.findViewById(R.id.ivStatus));


        wrapper.getTvNummer().setText(obj.getNummer());
        wrapper.getTvRaum().setText(obj.getRaum());
        wrapper.getLbAktuell().setText("Aktuell");
        wrapper.getLbStichtag().setText("Stichtag");
        wrapper.getTvLetzterWert().setText(obj.getLetzterWertText());
        wrapper.getTvAktuell().setText(obj.getAktuellValue() == -1.0 ? "" : FormatHelper.formatDouble(obj.getAktuellValue()));
        wrapper.getTvStichtag().setText(obj.getStichtagValue() == -1.0 ? "" : FormatHelper.formatDouble(obj.getStichtagValue()));
        currentView.setBackgroundResource(obj.getArtColor());
        wrapper.getTvAktuell().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileHandler.getInstance().setMessgeraet(obj);
                if(MessgeraeteListViewActivityConroller.getInstance().getEingabeArt() == MessgeraeteListViewActivityConroller.EingabeArt.SPRACHE){
                    Intent launchIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    launchIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    launchIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.GERMAN);
                    if (launchIntent.resolveActivity(activity.getPackageManager()) != null) {
                        activity.startActivityForResult(launchIntent, REQUEST_BT_AKTUELL);
                    }
                } else {
                    activity.showInputDialog("aktuell",null);
                }
            }
        });
        wrapper.getTvStichtag().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {
                FileHandler.getInstance().setMessgeraet(obj);
                if(MessgeraeteListViewActivityConroller.getInstance().getEingabeArt() == MessgeraeteListViewActivityConroller.EingabeArt.SPRACHE) {
                    Intent launchIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    launchIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    launchIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.GERMAN);
                    if (launchIntent.resolveActivity(activity.getPackageManager()) != null) {
                        activity.startActivityForResult(launchIntent, REQUEST_BT_STICHTAG);
                    }
                }else {
                    activity.showInputDialog("stichtag", null);
                }
            }
        });


        validate(obj,wrapper);


        currentView.setTag(wrapper);
        return currentView;
    }


    private void validate(Messgeraet valObj, MessgeraetListViewItemWrapper wrapper)
    {
        Boolean hasError = false;

        // falls gerät fortlaufend und aktueller Wert gefüllt muss der aktuelle Wert grösser sein als der letzte Wert.
        if (valObj.getFortlaufend() && valObj.getAktuellValue() != -1.0 && valObj.getLetzter_wert() > valObj.getAktuellValue()){
            wrapper.getIvStatus().setImageResource(R.drawable.icon_alert);
            wrapper.getTvAktuell().setTextColor(ContextCompat.getColor(activity, R.color.red));
            wrapper.getTvLetzterWert().setTextColor(ContextCompat.getColor(activity, R.color.red));
            hasError = true;
        }


        if (!hasError){
            wrapper.getTvAktuell().setTextColor(ContextCompat.getColor(activity, R.color.black));
            wrapper.getTvLetzterWert().setTextColor(ContextCompat.getColor(activity, R.color.navy));
            wrapper.getIvStatus().setImageDrawable(null);
        }
    }



    // region filter


    private class customFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint!=null && constraint.length()>0) {
                ArrayList<Messgeraet> tempList = new ArrayList<Messgeraet>();
                // search content in friend list
                for (Messgeraet messgeraet : values) {
                    if (messgeraet.getNummer().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(messgeraet);
                    }
                }
                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = values.size();
                filterResults.values = values;
            }
            return filterResults;
        }
        /**
         * Notify about filtered list to ui
         * @param constraint text
         * @param results filtered result
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            valuesFiltered = (ArrayList<Messgeraet>) results.values;
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new customFilter();
        }

        return mFilter;
    }

    // endregion filter








}



