package com.example.covidapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Entity implements Serializable {
    public final double hot;
    public final String label, url, enwiki, baidu, zhwiki;
    public final HashMap<String, String> properties;
    public final ArrayList<Relation> relations;
    public final byte[] drawable;

    public Entity(double hot, String label, String url, String enwiki, String baidu, String zhwiki, HashMap<String, String> properties, ArrayList<Relation> relations, byte[] drawable) {
        this.hot = hot;
        this.label = label;
        this.url = url;
        this.enwiki = enwiki;
        this.baidu = baidu;
        this.zhwiki = zhwiki;
        this.properties = properties;
        this.relations = relations;
        this.drawable = drawable;
    }
}
