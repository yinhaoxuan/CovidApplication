package com.example.covidapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class GetContentTask extends AsyncTask<Void, Void, String> {

    private WeakReference<MainActivity> mContext;
    private WeakReference<Event> mEvent;

    public GetContentTask(MainActivity context, Event event) {
        super();
        mContext = new WeakReference<>(context);
        mEvent = new WeakReference<>(event);
    }

    @Override
    protected String doInBackground(Void... voids) {
        return (mContext.get()).eventManager.getContent(mEvent.get().id);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Intent intent = new Intent(mContext.get(), ContentActivity.class);
        intent.putExtra("title", mEvent.get().title);
        intent.putExtra("time", mEvent.get().time);
        intent.putExtra("source", mEvent.get().source);
        intent.putExtra("content", s);
        mContext.get().startActivity(intent);
    }
}
