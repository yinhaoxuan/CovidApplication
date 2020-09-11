package com.example.covidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread myThread=new Thread(){
           public void run()
           {
               try {
                   sleep(2000);
                   Intent it=new Intent(getApplicationContext(),MainActivity.class);
                   startActivity(it);
                   finish();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

           }
        };
        myThread.start();
    }
}