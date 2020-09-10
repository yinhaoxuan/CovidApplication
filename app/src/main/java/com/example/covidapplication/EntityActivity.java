package com.example.covidapplication;

import android.content.Intent;
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
        hotView.setText("热度: " + entity.hot);
        TextView urlView = findViewById(R.id.entity_url);
        urlView.setText("Url: " + entity.url);
        TextView wikiView = findViewById(R.id.entity_wiki);
        if (entity.enwiki != null) {
            wikiView.setText("百科: " + entity.enwiki);
        }
        else if (entity.baidu != null) {
            wikiView.setText("百科: " + entity.baidu);
        }
        else if (entity.zhwiki != null) {
            wikiView.setText("百科: " + entity.zhwiki);
        }
        else {
            wikiView.setText("No wiki available");
        }
        ImageView imageView = findViewById(R.id.entity_picture);
        imageView.setImageDrawable(entity.drawable);
        TextView propertyView = findViewById(R.id.entity_properties);
        StringBuilder properties = new StringBuilder();
        for (Map.Entry<String, String> entry: entity.properties.entrySet()) {
            properties.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        propertyView.setText(properties.toString());
        TextView relationView = findViewById(R.id.entity_relations);
        StringBuilder relations = new StringBuilder();
        for (Relation relation : entity.relations) {
            relations.append("名称：").append(relation.label).append("\n关系：").append(relation.relation).append("\nurl: ").append(entity.url).append("\n方向：").append(relation.forward ? "向前\n" : "向后\n");
        }
        relationView.setText(relations.toString());
    }
}