/*
 * Copyright (c) 2015 SuJinde , Licensed under the Apache License, Version 2.0.
 * Email : sujinde168@foxmail.com
 */

package org.sujinde.activity;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.sujinde.util.GPSUtil;

import caffcoo.com.myweatherdemo.R;

public class TestGPSActivity extends ActionBarActivity {

    private TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_gps);
        initialize();

        GPSUtil gpsUtil=new GPSUtil(this);
        Location location=gpsUtil.getLocation();
        updateView(location);

//        final LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        updateView(location);
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 8, new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//                updateView(location);
//
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//                updateView(locationManager.getLastKnownLocation(provider));
//
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//                updateView(null);
//
//            }
//        });


    }

    private void updateView(Location location) {

        if (location != null) {
            String strLocation = location.getLongitude() + "   " + location.getLatitude();
            hello.setText(strLocation);
        } else {
            hello.setText("location is null");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_g, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initialize() {

        hello = (TextView) findViewById(R.id.hello);
    }
}
