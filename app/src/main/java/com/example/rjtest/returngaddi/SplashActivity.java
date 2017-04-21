package com.example.rjtest.returngaddi;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.put_image);
        Thread mythread = new Thread(){
               public void run(){
                   try {
                       sleep(3000);
                       Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                       startActivity(intent);
                       finish();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
        };

          mythread.start();
    }

 
}
