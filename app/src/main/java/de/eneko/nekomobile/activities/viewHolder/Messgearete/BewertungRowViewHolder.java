package de.eneko.nekomobile.activities.viewHolder.Messgearete;


import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.models.MessgeraetModel;
import de.eneko.nekomobile.beans.Messgeraet;

public class BewertungRowViewHolder extends MessgeraetBaseViewHolder {
    TextView tvDisplayBewertung = null;
    TextView tvNutzer1 = null;
    TextView tvNutzer2 = null;
    TextView tvNutzer3 = null;


    public BewertungRowViewHolder(View pView, MessgeraetModel pModel) {
        this(pView, pModel,null);
    }

    public BewertungRowViewHolder(View pView, MessgeraetModel pModel, Activity pActivity) {
        super(pView, pModel, pActivity);
    }



    @Override
    public void updateView() {
        setTvDisplayBewertung(getView().findViewById(R.id.tvDisplayBewertung));
        getTvDisplayBewertung().setText(getBasemodel().getDisplayBewertung());

        setTvNutzer1(getView().findViewById(R.id.tvNutzer_1));
        setTvNutzer2(getView().findViewById(R.id.tvNutzer_2));
        setTvNutzer3(getView().findViewById(R.id.tvNutzer_3));

        List<Messgeraet> qHKVs = getBasemodel().getTodo().getNutzer().getLiegenschaft().getBaseModel().getNutzerMessgaereteByArt("HKV");
        List<Messgeraet> qSameBewertung = qHKVs.stream()
                .filter(r -> r.getDatum() != null && r.isBewertungEquals(getBean()))
                .sorted(Comparator.comparing(Messgeraet::getDatum).reversed())
                .limit(3)
                .collect(Collectors.toList());

        if (qSameBewertung.size() > 0) {
            getTvNutzer1().setText(qSameBewertung.get(0).getTodo().getNutzer().getNutzerName()+ ": " + qSameBewertung.get(0).getRaum());
        }else {
            getTvNutzer1().setVisibility(View.GONE);
        }

        if (qSameBewertung.size() > 1) {
            getTvNutzer2().setText(qSameBewertung.get(1).getTodo().getNutzer().getNutzerName()+ ": " + qSameBewertung.get(1).getRaum());
        }else {
        getTvNutzer2().setVisibility(View.GONE);
        }

        if (qSameBewertung.size() > 2) {
            getTvNutzer3().setText(qSameBewertung.get(2).getTodo().getNutzer().getNutzerName()+ ": " + qSameBewertung.get(2).getRaum());
        }else {
        getTvNutzer3().setVisibility(View.GONE);
        }



//
//        ArrayList<String> items = new ArrayList<String>();
//
//        items.addAll(qSameBewertung.stream()
//                .map(r -> r.getTodo().getNutzer().getNutzerName() + ": " + r.getRaum()) // + " + " + r.getDatum().toString())
//                .collect(Collectors.toList()));
//
//        ArrayAdapter<String> itemsAdapter =
//                new ArrayAdapter<String>(getActivity(),
//                        android.R.layout.simple_list_item_1,items){
//                    @Override
//                    public View getView(int position, View convertView, ViewGroup parent) {
//                        View view = super.getView(position, convertView, parent);
//                        TextView textView = ((TextView) view.findViewById(android.R.id.text1));
//                        textView.setMinHeight(0); // Min Height
//                        textView.setMinimumHeight(0); // Min Height
//                        textView.setHeight(60); // Height
//                        textView.setTextSize(12);
//                        return view;
//                    }
//                };
//
//        getLvNutzer().setAdapter(itemsAdapter);



    }

    @Override
    protected void createLayout() {

    }


    public TextView getTvDisplayBewertung() {
        return tvDisplayBewertung;
    }

    public void setTvDisplayBewertung(TextView tvDisplayBewertung) {
        this.tvDisplayBewertung = tvDisplayBewertung;
    }

    public TextView getTvNutzer1() {
        return tvNutzer1;
    }

    public void setTvNutzer1(TextView tvNutzer1) {
        this.tvNutzer1 = tvNutzer1;
    }

    public TextView getTvNutzer2() {
        return tvNutzer2;
    }

    public void setTvNutzer2(TextView tvNutzer2) {
        this.tvNutzer2 = tvNutzer2;
    }

    public TextView getTvNutzer3() {
        return tvNutzer3;
    }

    public void setTvNutzer3(TextView tvNutzer3) {
        this.tvNutzer3 = tvNutzer3;
    }
}
