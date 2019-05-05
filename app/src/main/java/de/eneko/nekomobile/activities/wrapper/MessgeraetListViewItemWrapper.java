package de.eneko.nekomobile.activities.wrapper;


import android.widget.ImageView;
import android.widget.TextView;

public class MessgeraetListViewItemWrapper {
    /*
     * Objekt Attribute Decl. and init
     */
    private TextView txtvDescription = null;
    private TextView txtvNutzerLage = null;
    private ImageView ivStatus = null;
    private TodoRowtemWrapper todoRow = null;

    public TodoRowtemWrapper getTodoRow() {
        return todoRow;
    }

    public MessgeraetListViewItemWrapper() {
        todoRow = new TodoRowtemWrapper();
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
