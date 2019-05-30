package de.eneko.nekomobile.activities.viewHolder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import de.eneko.nekomobile.R;
import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.activities.models.NutzerModel;
import de.eneko.nekomobile.beans.Nutzer;
import de.eneko.nekomobile.controllers.Dict;

public class NutzerListRowViewHolder extends BaseViewHolder{
    /*
     * Objekt Attribute Decl. and init
     */
    private TextView txtvDescription = null;
    private TextView txtvNutzerLage = null;
    private ImageView ivStatus = null;
    private TodoRowItemViewHolder todoRow = null;
    private View svToDoRow = null;

    public NutzerListRowViewHolder(View pView, Basemodel pModel) {
        super(pView, pModel);
    }

    @Override
    public NutzerModel getBasemodel() {
        return (NutzerModel) super.getBasemodel();
    }

    public TodoRowItemViewHolder getTodoRow() {
        return todoRow;
    }

    @Override
    public void updateView() {
        setIvStatus(mView.findViewById(R.id.ivStatus));
        getIvStatus().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!getBasemodel().isCompleted()){
                    getBasemodel().getBean().setAbwesend(!getBasemodel().getBean().getAbwesend());
                }
                getIvStatus().setImageResource(getBasemodel().getStatusImageResourceId());
            }
        });

        setTxtvDescription(mView.findViewById(R.id.txtvDescription));
        setTxtvNutzerLage(mView.findViewById(R.id.txtvNutzerLage));

        View svToDoRow = (View) mView.findViewById(R.id.todorow);
        todoRow = new TodoRowItemViewHolder(svToDoRow, getBasemodel());

        getTodoRow().setIvAblesung(mView.findViewById(R.id.ivAblesung));
        getTodoRow().setIvFunkCheck(mView.findViewById(R.id.ivFunkCheck));
        getTodoRow().setIvMontage(mView.findViewById(R.id.ivMontage));
        getTodoRow().setIvRwmMontage(mView.findViewById(R.id.ivRwmMontage));
        getTodoRow().setIvRwmWartung(mView.findViewById(R.id.ivRwmWartung));

        //Befuellen der einzelen Widgets
        getTxtvDescription().setText(getBasemodel().getNutzerName());
        getTxtvNutzerLage().setText(getBasemodel().getWohnungsnummerMitLage());

        // sichtbarkei der Icons
        getTodoRow().getIvAblesung().setVisibility(getBasemodel().hasAblesung() ? View.VISIBLE: View.GONE);
        getTodoRow().getIvAblesung().setImageResource(getBasemodel().getProgressStatusImageResourceId(Dict.TODO_ABLESUNG));

        getTodoRow().getIvMontage().setVisibility(getBasemodel().hasMontage() ? View.VISIBLE: View.GONE);
        getTodoRow().getIvRwmMontage().setVisibility(getBasemodel().hasRwmMontage() ? View.VISIBLE: View.GONE);
        getTodoRow().getIvRwmWartung().setVisibility(getBasemodel().hasRwmWartung() ? View.VISIBLE: View.GONE);
        getTodoRow().getIvRwmWartung().setImageResource(getBasemodel().getProgressStatusImageResourceId(Dict.TODO_WARTUNG_RWM));
        getTodoRow().getIvFunkCheck().setVisibility(getBasemodel().hasFunkcheck() ? View.VISIBLE: View.GONE);

        setStatusImage(getIvStatus(),getBasemodel().getBean());

    }

    private void setStatusImage( ImageView iv, Nutzer pNutzer){
        iv.setImageResource(pNutzer.getBaseModel().getStatusImageResourceId());
    }

    public TextView getTxtvDescription() {
        return txtvDescription;
    }

    public void setTxtvDescription(TextView txtvDescription) {
        this.txtvDescription = txtvDescription;
    }

    public TextView getTxtvNutzerLage() {
        return txtvNutzerLage;
    }

    public void setTxtvNutzerLage(TextView txtvNutzerLage) {
        this.txtvNutzerLage = txtvNutzerLage;
    }

    public ImageView getIvStatus() {
        return ivStatus;
    }

    public void setIvStatus(ImageView ivStatus) {
        this.ivStatus = ivStatus;
    }



}
