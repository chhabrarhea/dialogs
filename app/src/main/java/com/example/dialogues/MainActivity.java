package com.example.dialogues;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity
        implements custom_dialogue.NoticeDialogListener {
String [] names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names= new String[]{"abc", "pqr", "xyz"};


        //Alert Dialog with Custom Layout
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater inflater=this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialogue_inflator,null))
        .setPositiveButton("Sign In", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // sign in the user ...
            }
        })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).show();



        //Alert Dialog with simple List
        AlertDialog.Builder builder1=new AlertDialog.Builder(this);
        builder1.setItems(names, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // The 'which' argument contains the index position
                // of the selected item
            }
        });



        //Multi Choice List
        boolean [] checked=new boolean[3];
        checked[0]=false;
        checked[1]=true;
        checked[2]=false;
        final ArrayList<Integer> selectedItems = new ArrayList();

        AlertDialog.Builder builder2= new AlertDialog.Builder(this);
        builder.setMultiChoiceItems(names, checked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    // If the user checked the item, add it to the selected items
                    selectedItems.add(which);
                } else if (selectedItems.contains(which)) {
                    // Else, if the item is already in the array, remove it
                    selectedItems.remove(Integer.valueOf(which));
                }
            }
            }
        ).show();

         //Date Picker Dialog
        final TextView txtDate=new TextView(this);
        final TextView txtTime=new TextView(this);
        int mYear, mMonth, mDay, mHour, mMinute;
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();


        //Time Picker Dialog
        final Calendar calendar = Calendar.getInstance();
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        txtTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();





    }
    //Passing event back to Activity
    //creating a custom adapter and implementing an interface with a method for each type of click event.
    // Then implement that interface in the host component that will receive the action events from the dialog.
    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new custom_dialogue();
        dialog.show(getSupportFragmentManager(), "custom_dialog");
    }

    //Methods for interface of custom adapter
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Log.i("Button clicked","Positive");
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Log.i("Button clicked","Negative");
    }
}
