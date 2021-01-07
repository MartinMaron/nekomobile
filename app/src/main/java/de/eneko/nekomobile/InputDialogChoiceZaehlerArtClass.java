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


public class InputDialogChoiceZaehlerArtClass {
    private String values;
    private Activity mActivity;

    private RadioButton rbWMZ;
    private RadioButton rbHKV;
    private RadioButton rbKWZ;
    private RadioButton rbWWZ;

    private RadioGroup mRadioGroup;
    private AlertDialog dialog;

    public InputDialogChoiceZaehlerArtClass(Activity pActivity) {
        this.mActivity = pActivity;
    }


    public void show() {

        LayoutInflater inflater = this.mActivity.getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_input_zeahlerart, null);

        rbWMZ = alertLayout.findViewById(R.id.rbWMZ);
        rbHKV = alertLayout.findViewById(R.id.rbHKV);
        rbKWZ = alertLayout.findViewById(R.id.rbKWZ);
        rbWWZ = alertLayout.findViewById(R.id.rbWMZ);
        mRadioGroup = alertLayout.findViewById(R.id.radiogroup);
        mRadioGroup.setTag("");

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadioGroup.setTag(v.getTag());
            }
        };

        rbWMZ.setOnClickListener(ocl);
        rbHKV.setOnClickListener(ocl);
        rbKWZ.setOnClickListener(ocl);
        rbWWZ.setOnClickListener(ocl);

        rbWMZ.setSelected(false);
        rbHKV.setSelected(false);
        rbKWZ.setSelected(false);
        rbWWZ.setSelected(false);

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
