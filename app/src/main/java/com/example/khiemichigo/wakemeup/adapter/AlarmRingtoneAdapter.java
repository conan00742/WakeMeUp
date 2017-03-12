package com.example.khiemichigo.wakemeup.adapter;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.khiemichigo.wakemeup.R;
import com.example.khiemichigo.wakemeup.model.RingtoneObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by USER on 3/11/2017.
 */

public class AlarmRingtoneAdapter extends RecyclerView.Adapter<AlarmRingtoneAdapter.AlarmRingtoneViewHolder> {

    private LayoutInflater mInflater;
    private List<RingtoneObject> alarmRingtones;
    private Context mContext;
    private Ringtone currentAlarmRingtone;


    public AlarmRingtoneAdapter(Context context, List<RingtoneObject> data) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        alarmRingtones = data;
    }

    @Override
    public AlarmRingtoneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.ringtone_list_item, parent, false);
        AlarmRingtoneViewHolder holder = new AlarmRingtoneViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AlarmRingtoneViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return alarmRingtones.size(); //36
    }

    class AlarmRingtoneViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvSongName)
        TextView name;
        @BindView(R.id.imgPlay)
        ImageView play;
        @BindView(R.id.song)
        RelativeLayout song;

        public AlarmRingtoneViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(int position) {
            final RingtoneObject ringtone = alarmRingtones.get(position);
            name.setText(ringtone.getRingtoneName());


            song.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(currentAlarmRingtone != null){
                        currentAlarmRingtone.stop();
                        currentAlarmRingtone = null;
                    }else{
                        currentAlarmRingtone = RingtoneManager
                                .getRingtone(mContext, ringtone.getRingtoneUri());

                        currentAlarmRingtone.play();
                    }

                }
            });
        }
    }
}
