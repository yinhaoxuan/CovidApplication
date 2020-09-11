package com.example.covidapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScholarListAdapter extends RecyclerView.Adapter<ScholarListAdapter.ScholarViewHolder> {


    public ScholarListAdapter(Context context, ArrayList<Scholar> ScholarList) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mScholarList = ScholarList;
    }

    class ScholarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView mNameView, mPositionView, mIndexView;
        public final ImageView mAvatar;
        final ScholarListAdapter mAdapter;
        public ScholarViewHolder(@NonNull View itemView, ScholarListAdapter adapter) {
            super(itemView);
            mNameView = itemView.findViewById(R.id.scholar_name);
            mPositionView = itemView.findViewById(R.id.scholar_position);
            mIndexView = itemView.findViewById(R.id.scholar_index);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("onClick", "onClick");
            int mPosition = getLayoutPosition();
            Scholar scholar = mScholarList.get(mPosition);
            Intent intent = new Intent(mContext, ScholarActivity.class);
            intent.putExtra("scholar", scholar);
            mContext.startActivity(intent);
        }
    }
    public ArrayList<Scholar> mScholarList;
    private final LayoutInflater mInflater;
    private final Context mContext;

    @NonNull
    @Override
    public ScholarListAdapter.ScholarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ScholarViewHolder(mInflater.inflate(R.layout.scholar_item, parent, false), this);
    }

    @Override
    public void onBindViewHolder(@NonNull ScholarListAdapter.ScholarViewHolder holder, int position) {
        Scholar mCurrent = mScholarList.get(position);
        holder.mNameView.setText(mCurrent.name);
        holder.mPositionView.setText(mCurrent.posisition);
        String index = "Citations: " + mCurrent.citations +
                "G-index: " + mCurrent.gindex +
                "H-index: " + mCurrent.hindex;
        holder.mIndexView.setText(index);
        holder.mAvatar.setImageDrawable(mCurrent.avatar);
    }

    @Override
    public int getItemCount() {
        return mScholarList.size();
    }


}
