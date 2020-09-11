package com.example.covidapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;

public class AvatarTask extends AsyncTask<Void, Void, Void> {

    WeakReference<ImageView> mView;
    WeakReference<String> mUrl;
    Drawable mDrawable;
    public AvatarTask(ImageView view, String url) {
        super();
        mView = new WeakReference<>(view);
        mUrl = new WeakReference<>(url);
    }

    @Override
    protected void onPostExecute(Void unused) {
        mView.get().setImageDrawable(mDrawable);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL(mUrl.get());
            InputStream content = (InputStream)url.getContent();
            mDrawable = Drawable.createFromStream(content, "src");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
