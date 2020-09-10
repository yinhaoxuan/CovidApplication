package com.example.covidapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EntityListAdapter extends RecyclerView.Adapter<EntityListAdapter.EntityViewHolder> {


    public EntityListAdapter(Context context, ArrayList<Entity> EntityList) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        this.mEntityList = EntityList;
    }

    class EntityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView mNameView;
        final EntityListAdapter mAdapter;
        public EntityViewHolder(@NonNull View itemView, EntityListAdapter adapter) {
            super(itemView);
            mNameView = itemView.findViewById(R.id.search_string);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("onClick", "onClick");
            int mPosition = getLayoutPosition();
            Entity entity = mEntityList.get(mPosition);
            Intent intent = new Intent(mContext, EntityActivity.class);
            intent.putExtra("entity", (Parcelable) entity);
            mContext.startActivity(intent);
        }
    }
    public ArrayList<Entity> mEntityList;
    private final LayoutInflater mInflater;
    private final Context mContext;

    @NonNull
    @Override
    public EntityListAdapter.EntityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EntityViewHolder(mInflater.inflate(R.layout.string_item, parent, false), this);
    }

    @Override
    public void onBindViewHolder(@NonNull EntityListAdapter.EntityViewHolder holder, int position) {
        Entity mCurrent = mEntityList.get(position);
        holder.mNameView.setText(mCurrent.label);
    }

    @Override
    public int getItemCount() {
        return mEntityList.size();
    }


}
