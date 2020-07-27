package de.eneko.nekomobile;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;



public class InputDialogChoiceListModeClass {
    private String values;
    private Activity mActivity;

    private RadioButton rbTodo;
    private RadioButton rbTodos;
    private RadioButton rbWohnung;
    private RadioButton rbAlle;
    private RadioButton rbLiegenschaft;

    private RadioGroup mRadioGroup;
    private AlertDialog dialog;



    public InputDialogChoiceListModeClass(Activity pActivity, String pButtonsString) {
        this.mActivity = pActivity;
        values = pButtonsString;
    }


    public void show() {

        LayoutInflater inflater = this.mActivity.getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_input_listmode, null);

        rbLiegenschaft = alertLayout.findViewById(R.id.rbLiegenschaft);
        rbWohnung = alertLayout.findViewById(R.id.rbWohnung);
        rbTodo = alertLayout.findViewById(R.id.rbTodo);
        rbTodos = alertLayout.findViewById(R.id.rbTodos);
        rbAlle = alertLayout.findViewById(R.id.rbLiegenschaftAlle);
        mRadioGroup = alertLayout.findViewById(R.id.radiogroup);
        mRadioGroup.setTag("");

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadioGroup.setTag(v.getTag());
            }
        };

        rbAlle.setOnClickListener(ocl);
        rbLiegenschaft.setOnClickListener(ocl);
        rbWohnung.setOnClickListener(ocl);
        rbTodo.setOnClickListener(ocl);
        rbTodos.setOnClickListener(ocl);

        rbWohnung.setSelected(false);
        rbAlle.setSelected(false);
        rbTodo.setSelected(false);
        rbTodos.setSelected(false);
        rbLiegenschaft.setSelected(false);

        if (!values.contains(rbWohnung.getTag().toString())) {rbWohnung.setVisibility(View.GONE);}
        if (!values.contains(rbLiegenschaft.getTag().toString())) {rbLiegenschaft.setVisibility(View.GONE);}
        if (!values.contains(rbTodo.getTag().toString())) {rbTodo.setVisibility(View.GONE);}
        if (!values.contains(rbTodos.getTag().toString())) {rbTodos.setVisibility(View.GONE);}
        if (!values.contains(rbAlle.getTag().toString())) {rbAlle.setVisibility(View.GONE);}



        AlertDialog.Builder alert = new AlertDialog.Builder(mActivity);

        alert.setView(alertLayout);
        alert.setCancelable(true);
        alert.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.setPositiveButton("Übernehmen", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                OnDialogSubmit(mRadioGroup.getTag().toString());
            }
        });

        dialog = alert.create();
        dialog.show();
        // Get the alert dialog buttons reference
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);

        // Change the alert dialog buttons text and background color
        negativeButton.setTextColor(ContextCompat.getColor(mActivity, R.color.blue));
        positiveButton.setTextColor(ContextCompat.getColor(mActivity, R.color.blue));



    }
    protected void OnDialogSubmit(String selItem){

        dialog.dismiss();
    }

}
