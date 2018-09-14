package com.example.android.getmylocation;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.location.LocationListener;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity  {

    TextView result;
    String s, latide, longde;
    double latitude,  longitude;
    long i, before, after ;
    private GpsTracker gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.coordinates);
        latide = getString(R.string.latitude);
        longde = getString(R.string.longitude);

    }


    public void get_coordinates (View view) {

        //вариант с использованием текущего времени (работает в java проекте, но не здесь)
        before = System.currentTimeMillis();
        i = before;
        while (true) {

            after = System.currentTimeMillis();
            if (after - before > 1000) {

                before = System.currentTimeMillis();

                gpsTracker = new GpsTracker(MainActivity.this);
                if (gpsTracker.canGetLocation()) {
                    latitude = gpsTracker.getLatitude();
                    longitude = gpsTracker.getLongitude();
                    s += latide + latitude + "\n" + longde + longitude + "\n\n";
                    display(s);

                } else {
                    gpsTracker.showSettingsAlert();
                    s = getString(R.string.failed);
                }
            }

            if (before - i > 10000) break;
        }
    }


    public void display(String str) {
        result.setText(str);
    }
}

        /*
        //вариант с Thread
        try {
            Thread.sleep(1000); //Приостанавливает поток на 1 секунду
        }
        catch (Exception e) {}
        */


/*
*
*       //вариант без фонового отображения (без цикла)
        gpsTracker = new GpsTracker(MainActivity.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            s += "latitude: " + latitude + "\n" + "longitude: " + longitude + "\n\n";

        }
        display(s);
* */

        /*
        //просто тестовый вариант, без высчитывания реальных координат (работает в java проекте, но не здесь)
        int timeToWait = 10; //second
        int a=0, b=0;

        for (int i=0; i<timeToWait ; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
                double latitude = a++;
                double longitude = b++;
                s += "latitude: " + latitude + "\n" + "longitude: " + longitude + "\n\n";
                display(s);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        */