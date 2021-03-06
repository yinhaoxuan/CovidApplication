package com.example.covidapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class GetContentTask extends AsyncTask<Void, Void, String> {

    private WeakReference<Context> mContext;
    private WeakReference<Event> mEvent;
    private WeakReference<TabFragment> mFragment;

    public GetContentTask(Context context, TabFragment fragment, Event event) {
        super();
        mContext = new WeakReference<>(context);
        mFragment = new WeakReference<>(fragment);
        mEvent = new WeakReference<>(event);
    }

    @Override
    protected String doInBackground(Void... voids) {
        return MainActivity.eventManager.getContent(mEvent.get().id);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Intent intent = new Intent(mContext.get(), ContentActivity.class);
        intent.putExtra("title", mEvent.get().title);
        intent.putExtra("time", mEvent.get().time);
        intent.putExtra("source", mEvent.get().source);
        intent.putExtra("content", s);
        if (mFragment.get() != null) {
            mFragment.get().updateList();
        }
        mContext.get().startActivity(intent);
    }
}
