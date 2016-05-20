package com.bit.alan.googleprac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    private SupportMapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(new MapCallBack());
    }

    public class MapCallBack implements OnMapReadyCallback{
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng dunedinLatLng = new LatLng(-45.86067841225702, 170.52040815386135);
            GoogleMap gMap = googleMap;
            gMap.addMarker(new MarkerOptions().position(dunedinLatLng).title("Botanic Gardens!"));
            gMap.moveCamera(CameraUpdateFactory.newLatLng(dunedinLatLng));
        }
    }

}
