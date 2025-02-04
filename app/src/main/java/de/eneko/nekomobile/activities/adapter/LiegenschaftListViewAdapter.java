package de.eneko.nekomobile.activities.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.models.viewHolder.Liegenschaft.LiegenschaftRowViewHolder;
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

        Liegenschaft obj = getItem(index);
        LiegenschaftRowViewHolder viewHolder = new LiegenschaftRowViewHolder(rowView, obj.getBaseModel());
        viewHolder.updateView();
        rowView.setTag(viewHolder);
        return rowView;
    }



}

