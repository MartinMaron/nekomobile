package de.eneko.nekomobile;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

import de.eneko.nekomobile.activities.models.MessgeraetModel;
import de.eneko.nekomobile.framework.NumberCustomKeyboard;

public class InputDialogClass {
    protected static final String TAG = InputDialogClass.class.getSimpleName();

    private final Activity activity;
    private String value;
    private MessgeraetModel mMessgeraetModel;

    private EditText etStichtag;
    private EditText etAktuell;
    private TextView tvNummer;
    private TextView tvPreviousStan;
    protected View alertLayout;
    protected NumberCustomKeyboard mCustomKeyboard;
    protected KeyboardView mKeyBoardview;

    public InputDialogClass(Activity activity, MessgeraetModel messgeraetModel) {
        this.activity = activity;
        this.mMessgeraetModel = messgeraetModel;
    }


    public void show() {

        LayoutInflater inflater = activity.getLayoutInflater();
        alertLayout = inflater.inflate(R.layout.dialog_input_value, null);
        etAktuell = alertLayout.findViewById(R.id.etInputAktuell);
        etAktuell.setSelection(etAktuell.getText().length());
        etStichtag = alertLayout.findViewById(R.id.etInputStichtag);
        etStichtag.setSelection(etStichtag.getText().length());
        tvNummer = alertLayout.findViewById(R.id.lbNummer);
        tvNummer.setText(mMessgeraetModel.getNummer());
        tvPreviousStan = alertLayout.findViewById(R.id.lbPrevStan);
        tvPreviousStan.setText(mMessgeraetModel.getLetzterWertText());

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        mKeyBoardview = alertLayout.findViewById(R.id.keyboardview);
        mKeyBoardview.setKeyboard(new Keyboard(alertLayout.getContext(), R.xml.keyboard));
        mCustomKeyboard= new NumberCustomKeyboard(activity, mKeyBoardview);
        mCustomKeyboard.registerEditText(etAktuell);
        mCustomKeyboard.registerEditText(etStichtag);
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etAktuell.getWindowToken(), 0);
            }
        });
        alert.setPositiveButton("Übernehmen", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etAktuell.getWindowToken(), 0);
                double val_akt = -1.0 ; double val_st = -1.0 ;


                try{
                    val_akt = NumberFormat.getInstance(Locale.GERMANY).parse(etAktuell.getText().toString()).doubleValue();
                }catch (Exception e)
                {
                    Log.e(TAG,"keine convertierung vom Procente: " + etAktuell.getText().toString() + " zu Double möglich");
                }

                try{
                    val_st = NumberFormat.getInstance(Locale.GERMANY).parse(etStichtag.getText().toString()).doubleValue();
                }catch (Exception e)
                {
                    Log.e(TAG,"keine convertierung vom Procente: " + etStichtag.getText().toString() + " zu Double möglich");
                }

                OnDialogSubmit(val_akt,val_st);
            }

        });
        AlertDialog dialog = alert.create();

        etAktuell.setText(mMessgeraetModel.getAktuellValue() == -1.0 ? "" : NumberFormat.getInstance(Locale.GERMANY).format(mMessgeraetModel.getAktuellValue()));
        etStichtag.setText(mMessgeraetModel.getStichtagValue() == -1.0 ? "" : NumberFormat.getInstance(Locale.GERMANY).format(mMessgeraetModel.getStichtagValue()));
        tvNummer.setBackgroundResource(mMessgeraetModel.getArtColor());




        if(mMessgeraetModel.getArt().equals("HKV") || mMessgeraetModel.getArt().equals("WMZ")) {
            etStichtag.setFocusable(true);
            etStichtag.setFocusableInTouchMode(true);
            etStichtag.requestFocus();
        }else {
            etAktuell.setFocusable(true);
            etAktuell.setFocusableInTouchMode(true);
            etAktuell.requestFocus();
        }




        dialog.show();
        // Get the alert dialog buttons reference
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);

        // Change the alert dialog buttons text and background color
        negativeButton.setTextColor(ContextCompat.getColor(activity, R.color.blue));
        positiveButton.setTextColor(ContextCompat.getColor(activity, R.color.blue));

    }

    public void OnDialogSubmit(double pValueAktuell, double pValueStichtag){
        if( mCustomKeyboard.isCustomKeyboardVisible() ) mCustomKeyboard.hideCustomKeyboard(); else activity.finish();
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
