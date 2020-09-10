package com.example.covidapplication.itemtouchhelperdemo.demochannel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.covidapplication.Entity;
import com.example.covidapplication.R;
import com.example.covidapplication.itemtouchhelperdemo.helper.ItemDragHelperCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 频道 增删改查 排序
 * Created by YoKeyword on 15/12/29.
 */
public class EditTabActivity extends AppCompatActivity {

    private RecyclerView mRecy;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        mRecy = (RecyclerView) findViewById(R.id.recy);
        mButton = findViewById(R.id.finish_edit_button);
        init();
    }

    private void init() {
        final List<ChannelEntity> items = new ArrayList<>();
        final List<ChannelEntity> otherItems = new ArrayList<>();
        final Intent intent = getIntent();
        ChannelEntity entityAll = new ChannelEntity();
        entityAll.setName("all");
        if (intent.getBooleanExtra("all", true)) {
            items.add(entityAll);
        }
        else {
            otherItems.add(entityAll);
        }
        ChannelEntity entityNews = new ChannelEntity();
        entityNews.setName("news");
        if (intent.getBooleanExtra("news", true)) {
            items.add(entityNews);
        }
        else {
            otherItems.add(entityNews);
        }
        ChannelEntity entityPaper = new ChannelEntity();
        entityPaper.setName("paper");
        if (intent.getBooleanExtra("paper", true)) {
            items.add(entityPaper);
        }
        else {
            otherItems.add(entityPaper);
        }

        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecy.setLayoutManager(manager);

        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecy);

        final ChannelAdapter adapter = new ChannelAdapter(this, helper, items, otherItems);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = adapter.getItemViewType(position);
                return viewType == ChannelAdapter.TYPE_MY || viewType == ChannelAdapter.TYPE_OTHER ? 1 : 4;
            }
        });
        mRecy.setAdapter(adapter);

        adapter.setOnMyChannelItemClickListener(new ChannelAdapter.OnMyChannelItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(EditTabActivity.this, items.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                for (ChannelEntity entity : items) {
                    intent.putExtra(entity.getName(), true);
                }
                for (ChannelEntity entity: otherItems) {
                    intent.putExtra(entity.getName(), false);
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
