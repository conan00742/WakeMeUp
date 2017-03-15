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
import android.widget.TextClock;

import com.example.khiemichigo.wakemeup.AddAlarmClock;
import com.example.khiemichigo.wakemeup.R;
import com.example.khiemichigo.wakemeup.adapter.AlarmAdapter;
import com.example.khiemichigo.wakemeup.model.Alarm;

/**
 * Created by Khiem Ichigo on 1/29/2017.
 */

public class AlarmFragment extends Fragment {
    private View alarmFragmentView;
    private FloatingActionButton fab;
    private TextClock textClock;


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

        textClock = (TextClock) alarmFragmentView.findViewById(R.id.textClock);

        setUpRecyclerView();

        return alarmFragmentView;
    }

    private void setUpRecyclerView(){
        RecyclerView recyclerView = (RecyclerView) alarmFragmentView.findViewById(R.id.recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    fab.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0 || dy < 0 && fab.isShown()){
                    fab.hide();
                }

            }
        });
        AlarmAdapter adapter = new AlarmAdapter(getActivity(), Alarm.initData());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

}
