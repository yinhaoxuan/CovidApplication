package com.example.covidapplication;

import android.content.Intent;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;


import androidx.room.Room;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static EntityManager entityManager = new AllEntity();
    public static EventManager eventManager;
    public static PlaceManager placeManager = new MyPlaceManager();
    public static ScholarManager scholarManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Appdata db= Room.databaseBuilder(this, Appdata.class, "covid-db").allowMainThreadQueries().build();
        setContentView(R.layout.activity_main);


        eventManager = AllData.get_AllData(this);
//        scholarManager = AllScholar.get_AllScholar(this);

//        placeManager = new AllPlace();
        new GetDataTask(this).execute();


        BottomNavigationView view = findViewById(R.id.bottom_navigation);
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_events:
                        fragment = new EventFragment();
                        break;
                    case R.id.nav_data:
                        fragment = new DataFragment();
                        break;
                    case R.id.nav_relation:
                        fragment = new EntityFragment();
                        break;
                    case R.id.nav_cluster:
                        fragment = new ClusterFragment();
                        break;
                    case R.id.nav_scholar:
                        fragment = new ScholarFragment();
                        break;
                    default:
                        return false;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                return true;
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EventFragment()).commit();

//        eventManager = new MyEventManager();

    }

    public void launchSearchActivity(String query, String type) {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("query", query);
        intent.putExtra("type", type);
        startActivity(intent);
    }

}