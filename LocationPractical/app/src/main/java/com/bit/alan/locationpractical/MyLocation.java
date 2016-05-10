package com.bit.alan.locationpractical;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;

/**
 * Created by Alan on 10/05/2016.
 */
public class MyLocation {
    private double lat;
    private double lng;
    MyLocation(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String providerName = lm.getBestProvider(criteria, false);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return TODO;
            lat = 0;
            lng = 0;
        }
        Location currentLocation = lm.getLastKnownLocation(providerName);
        lat = currentLocation.getLatitude();
        lng = currentLocation.getLongitude();
    }

    public double getLat(){return lat;};
    public double getLng(){return lng;};



}
