package com.example.khiemichigo.wakemeup.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.TimePicker;

import com.example.khiemichigo.wakemeup.AddAlarmClock;
import com.example.khiemichigo.wakemeup.R;
import com.example.khiemichigo.wakemeup.adapter.AlarmAdapter;
import com.example.khiemichigo.wakemeup.model.Alarm;

import java.util.Calendar;

/**
 * Created by Khiem Ichigo on 1/29/2017.
 */

public class AlarmFragment extends Fragment {
    private View alarmFragmentView;
    private FloatingActionButton fab;
    private AnalogClock analogClock;


    public AlarmFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate fragment_alarm.xml layout
        alarmFragmentView = inflater.inflate(R.layout.fragment_alarm,container,false);

        fab = (FloatingActionButton) alarmFragmentView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AddAlarmClock.class));
            }
        });

        analogClock = (AnalogClock) alarmFragmentView.findViewById(R.id.analogClock);

        setUpRecyclerView();

        return alarmFragmentView;
    }

    private void setUpRecyclerView(){
        RecyclerView recyclerView = (RecyclerView) alarmFragmentView.findViewById(R.id.recyclerView);
        AlarmAdapter adapter = new AlarmAdapter(getActivity(), Alarm.initData());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

}
