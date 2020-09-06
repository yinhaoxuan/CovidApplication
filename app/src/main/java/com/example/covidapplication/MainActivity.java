package com.example.covidapplication;

import android.view.MenuItem;
import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;


import androidx.room.Room;

import androidx.viewpager.widget.ViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

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
    }
}