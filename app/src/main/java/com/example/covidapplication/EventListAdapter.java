package com.example.covidapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder> {


    public EventListAdapter(Context context, ArrayList<Event> EventList) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        this.mEventList = EventList;
    }

    class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView titleView, sourceView, timeView;
        final EventListAdapter mAdapter;
        public EventViewHolder(@NonNull View itemView, EventListAdapter adapter) {
            super(itemView);
            titleView = itemView.findViewById(R.id.item_title);
            sourceView = itemView.findViewById(R.id.item_source);
            timeView = itemView.findViewById(R.id.item_time);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("onClick", "onClick");
            int mPosition = getLayoutPosition();
            Event event = mEventList.get(mPosition);
            if (mContext instanceof MainActivity) {
                ((MainActivity) mContext).launchContentActivity(view, event);
            }
        }
    }
    private final ArrayList<Event> mEventList;
    private final LayoutInflater mInflater;
    private final Context mContext;

    @NonNull
    @Override
    public EventListAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventViewHolder(mInflater.inflate(R.layout.event_item, parent, false), this);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListAdapter.EventViewHolder holder, int position) {
        Event mCurrent = mEventList.get(position);
        holder.titleView.setText(mCurrent.title);
        holder.timeView.setText(mCurrent.time);
        holder.sourceView.setText(mCurrent.source);
        if (mCurrent.hasRead) {
            holder.titleView.setTextColor(Color.GRAY);
            holder.timeView.setTextColor(Color.GRAY);
            holder.sourceView.setTextColor(Color.GRAY);
        }
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }
}
