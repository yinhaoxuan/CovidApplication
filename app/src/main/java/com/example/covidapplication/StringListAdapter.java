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

public class StringListAdapter extends RecyclerView.Adapter<StringListAdapter.StringViewHolder> {


    public StringListAdapter(Context context, ArrayList<String> StringList, String type) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mStringList = StringList;
        mType = type;
    }

    class StringViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView view;
        final StringListAdapter mAdapter;
        public StringViewHolder(@NonNull View itemView, StringListAdapter adapter) {
            super(itemView);
            view = itemView.findViewById(R.id.search_string);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("onClick", "onClick");
            int mPosition = getLayoutPosition();
            String string = mStringList.get(mPosition);
            if (mContext instanceof MainActivity) {
                ((MainActivity) mContext).launchSearchActivity(string, mType);
            }
        }
    }
    private final ArrayList<String> mStringList;
    private final LayoutInflater mInflater;
    private final Context mContext;
    private final String mType;

    @NonNull
    @Override
    public StringListAdapter.StringViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StringViewHolder(mInflater.inflate(R.layout.string_item, parent, false), this);
    }

    @Override
    public void onBindViewHolder(@NonNull StringListAdapter.StringViewHolder holder, int position) {
        String mCurrent = mStringList.get(position);
        holder.view.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mStringList.size();
    }
}
