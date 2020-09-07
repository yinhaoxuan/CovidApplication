package com.example.covidapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import androidx.room.Room;

import androidx.viewpager.widget.ViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

public class MainActivity extends AppCompatActivity {
    public static EntityManager entityManager;
    public static EventManager eventManager;
    public static PlaceManager placeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Appdata db= Room.databaseBuilder(this, Appdata.class, "covid-db").allowMainThreadQueries().build();
        setContentView(R.layout.activity_main);

        BottomNavigationView view = findViewById(R.id.bottom_navigation);
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_events:
                        fragment = new FragmentEvent();
                        break;
                    case R.id.nav_data:
                        fragment = new FragmentData();
                        break;
                    case R.id.nav_relation:
                        fragment = new FragmentRelation();
                        break;
                    default:
                        return false;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                return true;
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentEvent()).commit();

//        eventManager = AllData.get_AllData(this);
//        eventManager.refresh();

        eventManager = new myEventManager();
    }

    public void launchContentActivity(View view, final Event event) {
        Log.d("Main", "launch content");
        new GetContentTask(this, event).execute();
    }

    public void launchSearchActivity(String query) {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("query", query);
        startActivity(intent);
    }

    public void refresh(RefreshLayout mRefreshLayout) {
        new RefreshTask(this, mRefreshLayout).execute();
    }

    public void getMore(RefreshLayout mRefreshLayout) {
        new getMoreTask(this, mRefreshLayout).execute();
    }
}