package com.example.khiemichigo.wakemeup;

import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.khiemichigo.wakemeup.adapter.AlarmRingtoneAdapter;
import com.example.khiemichigo.wakemeup.model.RingtoneObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayMusicList extends AppCompatActivity {

    @BindView(R.id.recyclerAlarmRingtone)
    RecyclerView alarmRingtoneRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_music_list);
        ButterKnife.bind(this);

        AlarmRingtoneAdapter adapter = new AlarmRingtoneAdapter(this, getAlarmRingtone());
        alarmRingtoneRecyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        alarmRingtoneRecyclerView.setLayoutManager(linearLayoutManager);

    }

    public List<RingtoneObject> getAlarmRingtone(){
        RingtoneManager manager = new RingtoneManager(this);
        manager.setType(RingtoneManager.TYPE_ALARM);
        Cursor cursor = manager.getCursor();
        List<RingtoneObject> ringtoneList = new ArrayList<>();
        while(cursor.moveToNext()){
            String title = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX);
            Uri uri = manager.getRingtoneUri(cursor.getPosition());

            RingtoneObject ringtone = new RingtoneObject(title, uri);
            ringtoneList.add(ringtone);
        }

        return ringtoneList;
    }
}
