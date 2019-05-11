package de.eneko.nekomobile.activities.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.viewHolder.LiegenschaftListViewItemWrapper;
import de.eneko.nekomobile.beans.Liegenschaft;

public class LiegenschaftListViewAdapter extends ArrayAdapter<Liegenschaft>
{
    private final Context context;
    private final ArrayList<Liegenschaft> values;

    public LiegenschaftListViewAdapter(Context context, ArrayList<Liegenschaft> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount()
    {
        return values.size();
    }

    @Override
    public Liegenschaft getItem(int index)
    {
        return values.get(index);
    }

    @Override
    public long getItemId(int id)
    {
        return id;
    }

    @Override
    public View getView(int index, View rowView, ViewGroup parent)
    {

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item_liegenschaft, parent, false);
        }

        Liegenschaft liegenschaft = getItem(index);
        LiegenschaftListViewItemWrapper wrapper = new LiegenschaftListViewItemWrapper();

        wrapper.setTxtvLiegenschaft(rowView.findViewById(R.id.txtvDescription));
        wrapper.setTxtvTermin(rowView.findViewById(R.id.txtvDescription2));

        View todo_row = (View) rowView.findViewById(R.id.todorow);

        wrapper.getTodoRow().setIvAblesung(rowView.findViewById(R.id.ivAblesung));
        wrapper.getTodoRow().setIvFunkCheck(rowView.findViewById(R.id.ivFunkCheck));
        wrapper.getTodoRow().setIvMontage(rowView.findViewById(R.id.ivMontage));
        wrapper.getTodoRow().setIvRwmMontage(rowView.findViewById(R.id.ivRwmMontage));
        wrapper.getTodoRow().setIvRwmWartung(rowView.findViewById(R.id.ivRwmWartung));

        //Befuellen der einzelen Widgets
        wrapper.getTxtvLiegenschaft().setText(liegenschaft.getAdresse().replace("/n",","));
        wrapper.getTxtvTermin().setText(new SimpleDateFormat("HH:mm").format(liegenschaft.getStart())
                + "-" + new SimpleDateFormat("HH:mm").format(liegenschaft.getEnde()));

        // sichtbarkei der Icons
        wrapper.getTodoRow().getIvAblesung().setVisibility(liegenschaft.hasAblesung() ? View.VISIBLE: View.GONE);
        wrapper.getTodoRow().getIvMontage().setVisibility(liegenschaft.hasMontage() ? View.VISIBLE: View.GONE);
        wrapper.getTodoRow().getIvRwmMontage().setVisibility(liegenschaft.hasRwmMontage() ? View.VISIBLE: View.GONE);
        wrapper.getTodoRow().getIvRwmWartung().setVisibility(liegenschaft.hasRwmWartung() ? View.VISIBLE: View.GONE);
        wrapper.getTodoRow().getIvFunkCheck().setVisibility(liegenschaft.hasFunkcheck() ? View.VISIBLE: View.GONE);




        rowView.setTag(wrapper);

        //Rueckgabe der genierten View
        return rowView;
    }



}

