package com.example.khiemichigo.wakemeup;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddAlarmClock extends AppCompatActivity {
    @BindView(R.id.time)
    TextView tvTime;
    @BindView(R.id.repeat)
    TextView tvFrequency;
    @BindView(R.id.ringtone)
    TextView tvRingtoneTitle;
    @BindView(R.id.edtContent)
    EditText edtContent;
    @BindView(R.id.repeatTime)
    TextView tvRepeatTime;
    private AlertDialog alertDialog;
    int selectedItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm_clock);
        ButterKnife.bind(this);

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
                openRingtoneList();
            }
        });


        //set alarm repeat time
        LinearLayout repeatLayout = (LinearLayout) findViewById(R.id.repeatLayout);
        repeatLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRepeatTimeDialog();
            }
        });


        //vibration
        final CheckBox isVibration = (CheckBox) findViewById(R.id.isVibration);
        LinearLayout vibrationLayout = (LinearLayout) findViewById(R.id.vibrationLayout);
        vibrationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isVibration.isChecked()) {
                    isVibration.setChecked(true);
                    /*Vibrator vibrator = (Vibrator) AddAlarmClock.this.getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(500);*/
                    Toast.makeText(AddAlarmClock.this, "checked", Toast.LENGTH_SHORT).show();
                } else {
                    isVibration.setChecked(false);
                    Toast.makeText(AddAlarmClock.this, "unchecked", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    //repeat time dialog
    private void openRepeatTimeDialog() {

        final String[] times = new String[]{"5 minutes", "10 minutes", "15 minutes", "20 minutes", "25 minutes", "30 minutes"};
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this)
                .setTitle("Repeat after")
                .setCancelable(false)
                .setSingleChoiceItems(times, selectedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setRepeatTime(times[i]);
                        selectedItem = i;
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                });

        alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

    //ringtone list
    private void openRingtoneList() {
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select alarm ringtone:");
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI,
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));
        startActivityForResult(intent,1);
    }

    //timePickerDialog
    private void openTimePickerDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_pick_time, null);
        alertDialogBuilder.setView(dialogView);

        final TimePicker timePicker = (TimePicker) dialogView.findViewById(R.id.timePicker);

        alertDialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(Build.VERSION.SDK_INT < 23){
                    int hour = timePicker.getCurrentHour();
                    int minute = timePicker.getCurrentMinute();
                    setTime(hour, minute);
                }else{
                    int hour = timePicker.getHour();
                    int minute = timePicker.getMinute();
                    setTime(hour, minute);
                }
                alertDialog.dismiss();
            }
        });


        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    //pickDaysDialog
    private void openDaysPickDialog() {
        String[] weekDays = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        final boolean[] checkedDays = new boolean[]{false, false, false, false, false, false, false};

        final List<String> data = Arrays.asList(weekDays);
        final StringBuilder stringBuilder = new StringBuilder();


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this)
                .setTitle("Frequency")
                .setCancelable(false)
                .setMultiChoiceItems(weekDays, checkedDays, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                        checkedDays[which] = isChecked;

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String prefix = "";
                        for (int i = 0; i < checkedDays.length; i++) {
                            boolean checked = checkedDays[i];
                            if (checked) {
                                stringBuilder.append(prefix);
                                prefix = ", ";
                                stringBuilder.append(""+data.get(i));
                            }
                        }
                        setFrequency(stringBuilder.toString());
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
    private void setAlarmContent() {
        String content = edtContent.getText().toString();
    }


    //setTime
    private void setTime(int hour, int minute) {
        tvTime.setText(String.valueOf(hour) + ":" + String.valueOf(minute));
    }

    //set frequency
    private void setFrequency(String day){
        tvFrequency.setText(day);
    }

    //set content
    private void setContent(String content){
        //when press ADD
    }

    //set repeat time
    private void setRepeatTime(String time){
        tvRepeatTime.setText(time);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case 1:
                    Uri ringtoneUri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
                    Ringtone ringtone = RingtoneManager.getRingtone(this,ringtoneUri);
                    tvRingtoneTitle.setText(ringtone.getTitle(this));
                    Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}
