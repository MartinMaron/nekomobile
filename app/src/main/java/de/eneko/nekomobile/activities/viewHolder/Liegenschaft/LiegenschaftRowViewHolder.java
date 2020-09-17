package de.eneko.nekomobile.activities.viewHolder.Liegenschaft;


import android.view.View;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.activities.models.LiegenschaftModel;
import de.eneko.nekomobile.activities.viewHolder.TodoRowItemViewHolder;

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

        // sichtbarkei der Icons
        getTodoRow().getIvAblesung().setVisibility(getBasemodel().hasAblesung() ? View.VISIBLE: View.GONE);
        getTodoRow().getIvMontage().setVisibility(getBasemodel().hasMontage() ? View.VISIBLE: View.GONE);
        getTodoRow().getIvRwmMontage().setVisibility(getBasemodel().hasRwmMontage() ? View.VISIBLE: View.GONE);
        getTodoRow().getIvRwmWartung().setVisibility(getBasemodel().hasRwmWartung() ? View.VISIBLE: View.GONE);
        getTodoRow().getIvFunkCheck().setVisibility(getBasemodel().hasFunkcheck() ? View.VISIBLE: View.GONE);

       if (getTodoRow().getIvAblesung().getVisibility() == View.VISIBLE) {




       }


    }

    // endregion properties
}
