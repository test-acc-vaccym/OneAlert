package com.xiazhiri.oneAlert.feature.alarm;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiazhiri.oneAlert.R;
import com.xiazhiri.oneAlert.model.Alarm;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by liuwencai on 16/4/2.
 */
public class AlarmListAdapter extends RecyclerView.Adapter<AlarmListAdapter.ViewHolder> {
    LayoutInflater inflater;
    Alarm alarm;
    Context context;

    public AlarmListAdapter(Context context, Alarm alarm) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.alarm = alarm;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_alarm_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Alarm.DataEntity data = alarm.getData().get(position);
        holder.serverName.setText(data.getAppService().getDescription());
        holder.message.setText(data.getAlarmName());
        holder.time.setText(data.getCreationTime());

        int tintColor;
        switch (data.getPriority()) {
            case 3:
                tintColor = Color.parseColor("#FF3837");
                break;
            case 2:
                tintColor = Color.parseColor("#EF773E");
                break;
            case 1:
                tintColor = Color.parseColor("#48A6F1");
                break;
            default:
                tintColor = Color.parseColor("#48A6F1");
                break;
        }
        holder.alarmLevelTint.setBackgroundColor(tintColor);
    }

    @Override
    public int getItemCount() {
        return (alarm == null || alarm.getData() == null) ? 0 : alarm.getData().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.alarmLevelTint)
        View alarmLevelTint;
        @Bind(R.id.serverName)
        TextView serverName;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.message)
        TextView message;
        @Bind(R.id.item)
        RelativeLayout item;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Alarm.DataEntity data = alarm.getData().get(getAdapterPosition());
            Intent intent = new Intent(AlarmListAdapter.this.context, AlarmDetailActivity.class);
            intent.putExtra("data",data);
            AlarmListAdapter.this.context.startActivity(intent);
        }
    }

}
