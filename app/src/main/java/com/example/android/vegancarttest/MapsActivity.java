package com.example.android.vegancarttest;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String locationProvider = LocationManager.GPS_PROVIDER;
    private String TAG = "Location Listener";
    private Location mLocation;
    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS;

    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            // Called when a new location is found by the network location provider.
            mLocation = location;
            Log.i(TAG, "onLocationChanged");
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.i(TAG, "onStatusChanged");
        }

        public void onProviderEnabled(String provider) {
            Log.i(TAG, "onProviderEnabled");
        }

        public void onProviderDisabled(String provider) {
            Log.i(TAG, "onProviderDisabled");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        try {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                Log.i(TAG, "Location Access no granted");
            }
            else{
                locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);
            }
        } catch (SecurityException e) {
            Log.i(TAG, "SecurityException thrown", e);
        }

        if(mLocation != null){
            LatLng curLocation = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());
            mMap.addMarker(new MarkerOptions().position(curLocation).title("Current Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(curLocation));
        }

    }

}
