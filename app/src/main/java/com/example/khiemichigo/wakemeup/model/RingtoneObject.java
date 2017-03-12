package com.example.khiemichigo.wakemeup.model;

import android.net.Uri;

/**
 * Created by USER on 3/11/2017.
 */

public class RingtoneObject {
    String ringtoneName;
    Uri ringtoneUri;

    public RingtoneObject() {
    }

    public RingtoneObject(String ringtoneName, Uri ringtoneUri) {
        this.ringtoneName = ringtoneName;
        this.ringtoneUri = ringtoneUri;
    }

    public String getRingtoneName() {
        return ringtoneName;
    }

    public void setRingtoneName(String ringtoneName) {
        this.ringtoneName = ringtoneName;
    }

    public Uri getRingtoneUri() {
        return ringtoneUri;
    }

    public void setRingtoneUri(Uri ringtoneUri) {
        this.ringtoneUri = ringtoneUri;
    }
}
