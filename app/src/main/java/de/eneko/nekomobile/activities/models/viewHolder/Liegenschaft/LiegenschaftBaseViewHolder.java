package de.eneko.nekomobile.activities.models.viewHolder.Liegenschaft;


import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import de.eneko.nekomobile.activities.models.Basemodel;
import de.eneko.nekomobile.activities.models.LiegenschaftModel;
import de.eneko.nekomobile.activities.models.viewHolder.BaseViewHolder;
import de.eneko.nekomobile.activities.models.viewHolder.TodoRowItemViewHolder;

public class LiegenschaftBaseViewHolder extends BaseViewHolder {
    /*
     * Objekt Attribute Decl. and init
     */
    private TextView txtvLiegenschaft = null;
    private TextView txtvTermin = null;
    private TextView txtvBemerkungen = null;
    private TodoRowItemViewHolder todoRow = null;
    private ImageView ivPhoto = null;
    private EditText etNotizMitarbeiter = null;



    private ImageView ivListItemButtonRight = null;


    public LiegenschaftBaseViewHolder(View pView, Basemodel pModel) {
        super(pView, pModel);
    }

    public LiegenschaftBaseViewHolder(View pView, Basemodel pModel, Activity pActivity) {
        super(pView, pModel, pActivity);
    }


    @Override
    public LiegenschaftModel getBasemodel() {
        return (LiegenschaftModel) super.getBasemodel();
    }

    public void updateView() {
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

    public ImageView getIvPhoto() {
        return ivPhoto;
    }

    public void setIvPhoto(ImageView ivPhoto) {
        this.ivPhoto = ivPhoto;
    }

    public EditText getEtNotizMitarbeiter() {
        return etNotizMitarbeiter;
    }

    public void setEtNotizMitarbeiter(EditText etNotizMitarbeiter) {
        this.etNotizMitarbeiter = etNotizMitarbeiter;
    }

    public ImageView getIvListItemButtonRight() {
        return ivListItemButtonRight;
    }

    public void setIvListItemButtonRight(ImageView ivListItemButtonRight) {
        this.ivListItemButtonRight = ivListItemButtonRight;
    }

// endregion properties
}
