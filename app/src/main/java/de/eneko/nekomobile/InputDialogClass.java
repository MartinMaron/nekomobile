package de.eneko.nekomobile;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputDialogClass {
    private final Activity activity;
    private final String title;
    private final String value;

    public InputDialogClass(Activity activity, String title, String value) {
        this.activity = activity;
        this.title = title;
        this.value = value;
    }

    public void show() {

        LayoutInflater inflater = activity.getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_input_value, null);
        final EditText etInput = alertLayout.findViewById(R.id.etInput);

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
     //   alert.setTitle(title);


        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);

        alert.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(activity.getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etInput.getWindowToken(), 0);
            }
        });

        alert.setPositiveButton("Übernehmen", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etInput.getWindowToken(), 0);
                OnDialogSubmit(etInput.getText().toString());
            }

        });
        AlertDialog dialog = alert.create();

        etInput.setText(value);
        etInput.setBackgroundColor(activity.getResources().getColor(R.color._light_green));

        etInput.requestFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        dialog.show();
        // Get the alert dialog buttons reference
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);

        // Change the alert dialog buttons text and background color
        negativeButton.setTextColor(ContextCompat.getColor(activity, R.color.blue));
        positiveButton.setTextColor(ContextCompat.getColor(activity, R.color.blue));

    }

    public void OnDialogSubmit(String pValue){
    }

    public static boolean isDouble(String s)
    {
        try {
            double d = Double.parseDouble(s.replace(",","."));
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }

}
