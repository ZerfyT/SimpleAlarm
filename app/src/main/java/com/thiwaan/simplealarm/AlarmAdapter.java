package com.thiwaan.simplealarm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {
    private List<Alarm> alarmList;
    private OnItemClickListener listener;

    public AlarmAdapter(List<Alarm> alarmList, OnItemClickListener listener) {
        this.alarmList = alarmList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm, parent, false);
        return new AlarmViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
        Alarm alarm = alarmList.get(position);

        holder.timeTextView.setText(alarm.getTime());

        holder.editButton.setOnClickListener(v -> listener.onEditClick(alarm));
        holder.deleteButton.setOnClickListener(v -> listener.onDeleteClick(alarm));
    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    static class AlarmViewHolder extends RecyclerView.ViewHolder {
        TextView timeTextView;
        ImageButton editButton;
        ImageButton deleteButton;

        public AlarmViewHolder(@NonNull View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    public interface OnItemClickListener {
        void onEditClick(Alarm alarm);
        void onDeleteClick(Alarm alarm);
    }
}
