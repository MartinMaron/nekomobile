package de.eneko.nekomobile.activities.viewHolder.Nutzer;


import android.view.View;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.activities.viewHolder.TodoRowItemViewHolder;
import de.eneko.nekomobile.controllers.Dict;

public class NutzerListRowViewHolder extends NutzerBaseViewHolder {
    private TodoRowItemViewHolder todoRow = null;
    private View svToDoRow = null;

    public NutzerListRowViewHolder(View pView, Basemodel pModel) {
        super(pView, pModel);
    }

    public TodoRowItemViewHolder getTodoRow() {
        return todoRow;
    }

    @Override
    public void updateView() {
        super.updateView();

        View svToDoRow = (View) mView.findViewById(R.id.todorow);
        todoRow = new TodoRowItemViewHolder(svToDoRow, getBasemodel());

        getTodoRow().setIvAblesung(mView.findViewById(R.id.ivAblesung));
        getTodoRow().setIvFunkCheck(mView.findViewById(R.id.ivFunkCheck));
        getTodoRow().setIvMontage(mView.findViewById(R.id.ivMontage));
        getTodoRow().setIvRwmMontage(mView.findViewById(R.id.ivRwmMontage));
        getTodoRow().setIvRwmWartung(mView.findViewById(R.id.ivRwmWartung));

        // sichtbarkei der Icons
        if (getBasemodel().hasAblesung()){
            getTodoRow().getIvAblesung().setVisibility(getBasemodel().hasAblesung() ? View.VISIBLE: View.GONE);
            getTodoRow().getIvAblesung().setImageResource(getBasemodel().getProgressStatusImageResourceId(Dict.TODO_ABLESUNG));
        }else if (getBasemodel().hasZwischenAblesung()){
            getTodoRow().getIvAblesung().setVisibility(getBasemodel().hasZwischenAblesung() ? View.VISIBLE: View.GONE);
            getTodoRow().getIvAblesung().setImageResource(getBasemodel().getProgressStatusImageResourceId(Dict.TODO_ZWISCHENABLESUNG));
        }else
        {
            getTodoRow().getIvAblesung().setVisibility(View.GONE);
        }



        getTodoRow().getIvMontage().setVisibility(getBasemodel().hasMontage() ? View.VISIBLE: View.GONE);
        getTodoRow().getIvRwmMontage().setVisibility(getBasemodel().hasRwmMontage() ? View.VISIBLE: View.GONE);
        getTodoRow().getIvRwmWartung().setVisibility(getBasemodel().hasRwmWartung() ? View.VISIBLE: View.GONE);
        getTodoRow().getIvRwmWartung().setImageResource(getBasemodel().getProgressStatusImageResourceId(Dict.TODO_WARTUNG_RWM));
        getTodoRow().getIvFunkCheck().setVisibility(getBasemodel().hasFunkcheck() ? View.VISIBLE: View.GONE);
        getTodoRow().getIvFunkCheck().setImageResource(getBasemodel().getProgressStatusImageResourceId(Dict.TODO_FUNK_CHECK));

    }

}
