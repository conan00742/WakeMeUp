package com.example.khiemichigo.wakemeup.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.khiemichigo.wakemeup.R;
import com.example.khiemichigo.wakemeup.model.Alarm;

import java.util.ArrayList;

/**
 * Created by Khiem Ichigo on 1/30/2017.
 */

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {
    LayoutInflater mInflater;
    ArrayList<Alarm> mData;

    public AlarmAdapter(Context context, ArrayList<Alarm> mData) {
        mInflater = LayoutInflater.from(context);
        this.mData = mData;
    }

    @Override
    public AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.alarm_list_item, parent, false);
        AlarmViewHolder holder = new AlarmViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AlarmViewHolder holder, int position) {
        Alarm currentObj = mData.get(position);

        holder.tvTime.setText(currentObj.getTime());
        holder.tvContent.setText(currentObj.getContent());
        holder.tvFrequency.setText(currentObj.getFrequencies());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class AlarmViewHolder extends RecyclerView.ViewHolder{
        TextView tvTime, tvContent, tvFrequency;
        Switch switch1;

        public AlarmViewHolder(View itemView) {
            super(itemView);

            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            tvFrequency = (TextView) itemView.findViewById(R.id.tvFrequency);
            switch1 = (Switch) itemView.findViewById(R.id.switch1);

        }


    }
}
