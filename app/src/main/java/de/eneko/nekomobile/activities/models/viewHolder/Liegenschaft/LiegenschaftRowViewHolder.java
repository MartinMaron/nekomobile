package de.eneko.nekomobile.activities.models.viewHolder.Liegenschaft;


import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.util.Locale;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.activities.models.LiegenschaftModel;
import de.eneko.nekomobile.activities.models.viewHolder.TodoRowItemViewHolder;
import de.eneko.nekomobile.beans.Liegenschaft;

public class LiegenschaftRowViewHolder extends LiegenschaftBaseViewHolder {



    public LiegenschaftRowViewHolder(View pView, Basemodel pModel) {
        super(pView, pModel);
    }

    @Override
    public LiegenschaftModel getBasemodel() {
        return (LiegenschaftModel) super.getBasemodel();
    }

    @Override
    public void updateView() {
        setTxtvLiegenschaft(mView.findViewById(R.id.tvDisplay));
        setTxtvTermin(mView.findViewById(R.id.tvLastPlace_1));
        setTxtvBemerkungen(mView.findViewById(R.id.txtvBemerkung));
        setIvListItemButtonRight(mView.findViewById(R.id.ivListItemButtonRight));
        View svToDoRow = (View) mView.findViewById(R.id.todorow);

        setTodoRow(new TodoRowItemViewHolder(svToDoRow,getBasemodel()));
        getTodoRow().setIvAblesung(mView.findViewById(R.id.ivAblesung));
        getTodoRow().setIvFunkCheck(mView.findViewById(R.id.ivFunkCheck));
        getTodoRow().setIvMontage(mView.findViewById(R.id.ivMontage));
        getTodoRow().setIvRwmMontage(mView.findViewById(R.id.ivRwmMontage));
        getTodoRow().setIvRwmWartung(mView.findViewById(R.id.ivRwmWartung));

        //Befuellen der einzelen Widgets
        getTxtvLiegenschaft().setText(getBasemodel().getAdresseDisplay());
        getTxtvTermin().setText(getBasemodel().getTerminDisplay());
        if (!getBasemodel().getBemerkung().equals("")){
            getTxtvBemerkungen().setText(getBasemodel().getBemerkung());
        }else {
            getTxtvBemerkungen().setVisibility(View.GONE);
        }


        if (getBasemodel().isCompleted("RWM") && getBasemodel().isCompleted("GER")){
            getTodoRow().getIvAblesung().setVisibility(View.GONE);
            getTodoRow().getIvMontage().setVisibility(View.GONE);
            getTodoRow().getIvRwmMontage().setVisibility(View.GONE);
            getTodoRow().getIvRwmWartung().setVisibility(View.GONE);
            getTodoRow().getIvFunkCheck().setVisibility(View.GONE);

            getTxtvLiegenschaft().setTextColor(ContextCompat.getColor(mView.getContext(), R.color.dark_green));

        } else {
            // sichtbarkei der Icons
            getTodoRow().getIvAblesung().setVisibility(getBasemodel().hasAblesung() ? View.VISIBLE : View.GONE);
            getTodoRow().getIvMontage().setVisibility(getBasemodel().hasMontage() ? View.VISIBLE : View.GONE);
            getTodoRow().getIvRwmMontage().setVisibility(getBasemodel().hasRwmMontage() ? View.VISIBLE : View.GONE);
            getTodoRow().getIvRwmWartung().setVisibility(getBasemodel().hasRwmWartung() ? View.VISIBLE : View.GONE);
            getTodoRow().getIvFunkCheck().setVisibility(getBasemodel().hasFunkcheck() ? View.VISIBLE : View.GONE);
        }

//        if (getTodoRow().getIvRwmMontage().getVisibility() == View.VISIBLE || getTodoRow().getIvRwmWartung().getVisibility() == View.VISIBLE ||) {
//            if (getBasemodel().isCompleted("RWM")) {
//                getTodoRow().getIvRwmMontage().setImageResource(R.drawable.icon_smoke_detector_green_ok);
//                getTodoRow().getIvRwmWartung().setImageResource(R.drawable.icon_smoke_detector_green_ok);
//            }else {
//                getTodoRow().getIvRwmMontage().setImageResource(R.drawable.icon_smoke_detector_b);
//                getTodoRow().getIvRwmWartung().setImageResource(R.drawable.icon_smoke_detector_b);
//            }
//       }


        getIvListItemButtonRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Liegenschaft lieg =  (Liegenschaft) getBasemodel().getBean();
                String uri = String.format(Locale.GERMANY, "google.navigation:q=" + lieg.getAdresseOneLine());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(mView.getContext().getPackageManager()) != null) {
                    mView.getContext().startActivity(mapIntent);
                }
}
        });





    }

    // endregion properties
}
