package com.example.covidapplication;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.text.Html;
import android.text.SpannableString;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Map;

public class EntityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entity);
        Intent intent = getIntent();
        Entity entity = (Entity) intent.getSerializableExtra("entity");
        TextView nameView = findViewById(R.id.entity_name);
        nameView.setText(entity.label);
        TextView hotView = findViewById(R.id.entity_hot);
        hotView.setText(Html.fromHtml("<b>热度: </b>" + entity.hot, Html.FROM_HTML_MODE_COMPACT));
        TextView urlView = findViewById(R.id.entity_url);
        urlView.setText(Html.fromHtml("<b>Url: </b>" + entity.url, Html.FROM_HTML_MODE_COMPACT));
        TextView wikiView = findViewById(R.id.entity_wiki);
        if (entity.enwiki != null) {
            wikiView.setText(Html.fromHtml("<b>百科: </b>" + entity.enwiki,Html.FROM_HTML_MODE_COMPACT));
        }
        else if (entity.baidu != null) {
            wikiView.setText(Html.fromHtml("<b>百科: </b>" + entity.baidu, Html.FROM_HTML_MODE_COMPACT));
        }
        else if (entity.zhwiki != null) {
            wikiView.setText(Html.fromHtml("<b>百科: </b>" + entity.zhwiki, Html.FROM_HTML_MODE_COMPACT));
        }
        else {
            wikiView.setText("No wiki available");
        }
        ImageView imageView = findViewById(R.id.entity_picture);
        if (entity.drawable != null) {
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(entity.drawable, 0, entity.drawable.length));

        }
        TextView propertyView = findViewById(R.id.entity_properties);
        StringBuilder properties = new StringBuilder();
        for (Map.Entry<String, String> entry: entity.properties.entrySet()) {
            properties.append("<b>").append(entry.getKey()).append("</b> ").append(entry.getValue()).append("<br>");
        }
        propertyView.setText(Html.fromHtml(properties.toString(),Html.FROM_HTML_MODE_COMPACT));
        TextView relationView = findViewById(R.id.entity_relations);
        StringBuilder relations = new StringBuilder();
        for (Relation relation : entity.relations) {
            relations.append("<b>名称：</b>").append(relation.label).append("<br><b>关系：</b>").append(relation.relation).append("<br><b>url: </b>").append(entity.url).append("<br><b>方向：</b>").append(relation.forward ? "向前" : "向后").append("<br><br>");
        }
        relationView.setText(Html.fromHtml(relations.toString(),Html.FROM_HTML_MODE_COMPACT));
    }
}