package com.example.khiemichigo.wakemeup;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.util.Arrays;
import java.util.List;


public class AddAlarmClock extends AppCompatActivity{
    /*private CheckBox cbMonday, cbTuesday, cbWednesday, cbThursday, cbFriday, cbSaturday, cbSunday;*/
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm_clock);

        //setup Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.inflateMenu(R.menu.alarm_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.mnSave) {
                    Toast.makeText(AddAlarmClock.this, "Added", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //pick time
        LinearLayout timeLayout = (LinearLayout) findViewById(R.id.timeLayout);
        timeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open timePickerDialog
                openTimePickerDialog();
            }
        });


        //pick days in week to setup Alarm
        LinearLayout frequencyLayout = (LinearLayout) findViewById(R.id.frequencyLayout);
        frequencyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDaysPickDialog();
            }
        });


        //alarm content
        setAlarmContent();


        //set alarm ringtone
        LinearLayout ringtoneLayout = (LinearLayout) findViewById(R.id.ringtoneLayout);
        ringtoneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRingtoneIntentFilter();
            }
        });




        //vibration
        final CheckBox isVibration = (CheckBox) findViewById(R.id.isVibration);
        LinearLayout vibrationLayout = (LinearLayout) findViewById(R.id.vibrationLayout);
        vibrationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isVibration.isChecked()){
                    isVibration.setChecked(true);
                }
                else{
                    isVibration.setChecked(false);
                }
                Toast.makeText(AddAlarmClock.this, "Vibration", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openRingtoneIntentFilter() {
        startActivity(new Intent(AddAlarmClock.this, DisplayMusicList.class));
    }

    //timePickerDialog
    private void openTimePickerDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_pick_time, null);
        alertDialogBuilder.setView(dialogView);

        alertDialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(AddAlarmClock.this, "OK", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });


        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    //pickDaysDialog
    private void openDaysPickDialog(){
        String[] weekDays = new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        final boolean[] checkedDays = new boolean[]{false,false,false,false,false,false,false};

        final List<String> data = Arrays.asList(weekDays);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this)
                .setTitle("Frequency")
                .setCancelable(false)
                .setMultiChoiceItems(weekDays, checkedDays, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                        checkedDays[which] = isChecked;
                        Toast.makeText(AddAlarmClock.this, "You chose "+data.get(which), Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i = 0; i < checkedDays.length; i++) {
                            boolean checked = checkedDays[i];
                            if(checked){
                                Toast.makeText(AddAlarmClock.this, " "+data.get(i), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                });

        alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }


    //set alarm content
    private void setAlarmContent(){
        EditText edtContent = (EditText) findViewById(R.id.edtContent);
        String content = edtContent.getText().toString();
    }

}
