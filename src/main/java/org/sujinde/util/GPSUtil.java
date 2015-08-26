/*
 * Copyright (c) 2015 SuJinde , Licensed under the Apache License, Version 2.0.
 * Email : sujinde168@foxmail.com
 */

package org.sujinde.util;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by sujinde on 2015/8/5. 09:48
 * Email : sujinde168@foxmail.com
 */
public class GPSUtil {
    private LocationManager locationManager;
    private Location location;
    Context context;

    public GPSUtil(Context context) {
        this.context=context;
        locationManager=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 5, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });

    }

    public Location getLocation() {
        return location;
    }
}
