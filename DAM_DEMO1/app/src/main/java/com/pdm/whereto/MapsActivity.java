package com.pdm.whereto;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback/*, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener*/ {

    private static final float DEFAULT_ZOOM = 15;
    private String _locationName;
    private double _lat;
    private double _lng;
    private LatLng _latLng;
    private MarkerOptions _marker;
    private Location mLastLocation;
    private LocationManager locationManager;
    private final Criteria criteria = new Criteria();
    private String bestAvailableProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        _locationName = getIntent().getStringExtra("name");
        _lat = getIntent().getDoubleExtra("lat", 0);
        _lng = getIntent().getDoubleExtra("lng", 0);
        _latLng = new LatLng(_lat, _lng);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a _marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        break;
                    }
                    case 1: {
                        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        break;
                    }
                    case 2: {
                        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                        break;
                    }
                    case 3: {
                        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });

        googleMap.setMyLocationEnabled(true);
        _marker = new MarkerOptions()
                .position(_latLng)
                .title(_locationName)
                .draggable(true);

        googleMap.addMarker(_marker);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(_latLng, DEFAULT_ZOOM));

//        criteria.setAccuracy(Criteria.ACCURACY_FINE);
//        criteria.setPowerRequirement(Criteria.POWER_LOW);
//
//        String bbb = locationManager.getBestProvider(criteria, true);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        Location myLocation = locationManager.getLastKnownLocation(bbb);
//
//        double lat= myLocation.getLatitude();
//        double lng = myLocation.getLongitude();
//        _latLng = new LatLng(lat, lng);
//
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(_latLng, DEFAULT_ZOOM));

        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                MarkerOptions mark = new MarkerOptions()
                        .position(latLng)
                        .title(getString(R.string.NEW_LOCATION))
                        .draggable(true);

                googleMap.addMarker(mark);

            }
        });
    }
}
