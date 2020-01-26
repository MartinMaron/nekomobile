package de.eneko.nekomobile.activities.viewHolder;


import android.view.View;
import android.widget.TextView;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.activities.models.LiegenschaftModel;

public class LiegenschaftRowViewHolder extends BaseViewHolder{
    /*
     * Objekt Attribute Decl. and init
     */
    private TextView txtvLiegenschaft = null;
    private TextView txtvTermin = null;
    private TextView txtvBemerkungen = null;
    private TodoRowItemViewHolder todoRow = null;


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

        todoRow = new TodoRowItemViewHolder(svToDoRow,getBasemodel());

        todoRow.setIvAblesung(mView.findViewById(R.id.ivAblesung));
        todoRow.setIvFunkCheck(mView.findViewById(R.id.ivFunkCheck));
        todoRow.setIvMontage(mView.findViewById(R.id.ivMontage));
        todoRow.setIvRwmMontage(mView.findViewById(R.id.ivRwmMontage));
        todoRow.setIvRwmWartung(mView.findViewById(R.id.ivRwmWartung));

        //Befuellen der einzelen Widgets
        getTxtvLiegenschaft().setText(getBasemodel().getAdresseDisplay());
        getTxtvTermin().setText(getBasemodel().getTerminDisplay());
        if (!getBasemodel().getBemerkung().equals("")){
            getTxtvBemerkungen().setText(getBasemodel().getBemerkung());
        }else {
            getTxtvBemerkungen().setVisibility(View.GONE);
        }

        // sichtbarkei der Icons
        todoRow.getIvAblesung().setVisibility(getBasemodel().hasAblesung() ? View.VISIBLE: View.GONE);
        todoRow.getIvMontage().setVisibility(getBasemodel().hasMontage() ? View.VISIBLE: View.GONE);
        todoRow.getIvRwmMontage().setVisibility(getBasemodel().hasRwmMontage() ? View.VISIBLE: View.GONE);
        todoRow.getIvRwmWartung().setVisibility(getBasemodel().hasRwmWartung() ? View.VISIBLE: View.GONE);
        todoRow.getIvFunkCheck().setVisibility(getBasemodel().hasFunkcheck() ? View.VISIBLE: View.GONE);
    }

    // region properties

    public TextView getTxtvLiegenschaft() {
        return txtvLiegenschaft;
    }

    public void setTxtvLiegenschaft(TextView txtvLiegenschaft) {
        this.txtvLiegenschaft = txtvLiegenschaft;
    }

    public TextView getTxtvTermin() {
        return txtvTermin;
    }

    public void setTxtvTermin(TextView txtvTermin) {
        this.txtvTermin = txtvTermin;
    }

    public TodoRowItemViewHolder getTodoRow() {
        return todoRow;
    }

    public void setTodoRow(TodoRowItemViewHolder todoRow) {
        this.todoRow = todoRow;
    }


    public TextView getTxtvBemerkungen() {
        return txtvBemerkungen;
    }

    public void setTxtvBemerkungen(TextView txtvBemerkungen) {
        this.txtvBemerkungen = txtvBemerkungen;
    }

    // endregion properties
}
