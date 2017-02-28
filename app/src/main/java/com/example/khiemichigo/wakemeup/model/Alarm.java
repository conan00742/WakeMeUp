package com.example.khiemichigo.wakemeup.model;

import java.util.ArrayList;

/**
 * Created by Khiem Ichigo on 1/30/2017.
 */

public class Alarm {
    private String time;
    private String frequencies;
    private String content;
    private String ringtone;
    private String repeatAfter;
    private boolean vibration;

    public Alarm() {
    }

    public Alarm(String time, String frequencies, String content, String ringtone, String repeatAfter, boolean vibration) {
        this.time = time;
        this.frequencies = frequencies;
        this.content = content;
        this.ringtone = ringtone;
        this.repeatAfter = repeatAfter;
        this.vibration = vibration;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFrequencies() {
        return frequencies;
    }

    public void setFrequencies(String frequencies) {
        this.frequencies = frequencies;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRingtone() {
        return ringtone;
    }

    public void setRingtone(String ringtone) {
        this.ringtone = ringtone;
    }

    public String getRepeatAfter() {
        return repeatAfter;
    }

    public void setRepeatAfter(String repeatAfter) {
        this.repeatAfter = repeatAfter;
    }

    public boolean isVibration() {
        return vibration;
    }

    public void setVibration(boolean vibration) {
        this.vibration = vibration;
    }

    public static ArrayList<Alarm> initData(){
        ArrayList<Alarm> mData = new ArrayList<>();

        Alarm alarm1 = new Alarm();
        alarm1.setTime("10:30");
        alarm1.setContent("Cafe");
        alarm1.setFrequencies("Everyday");
        mData.add(alarm1);

        Alarm alarm2 = new Alarm();
        alarm2.setTime("19:00");
        alarm2.setContent("Studying");
        alarm2.setFrequencies("Monday, Tuesday, Wednesday, Thursday, Friday");
        mData.add(alarm2);

        Alarm alarm3 = new Alarm();
        alarm3.setTime("6:00");
        alarm3.setContent("Wake up");
        alarm3.setFrequencies("Everyday");
        mData.add(alarm3);

        Alarm alarm4 = new Alarm();
        alarm4.setTime("15:00");
        alarm4.setContent("Gym");
        alarm4.setFrequencies("Everyday");
        mData.add(alarm4);

        return mData;
    }
}
