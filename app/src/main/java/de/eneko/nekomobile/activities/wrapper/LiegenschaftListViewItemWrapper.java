package de.eneko.nekomobile.activities.wrapper;


import android.widget.TextView;

public class LiegenschaftListViewItemWrapper {
    /*
     * Objekt Attribute Decl. and init
     */
    private TextView txtvLiegenschaft = null;
    private TextView txtvTermin = null;
    private TodoRowtemWrapper todoRow = null;

    public LiegenschaftListViewItemWrapper() {
        todoRow = new TodoRowtemWrapper();
    }

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

    public TodoRowtemWrapper getTodoRow() {
        return todoRow;
    }

    public void setTodoRow(TodoRowtemWrapper todoRow) {
        this.todoRow = todoRow;
    }
}
