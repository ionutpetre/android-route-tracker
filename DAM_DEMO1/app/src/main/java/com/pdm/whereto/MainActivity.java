package com.pdm.whereto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_LOCATION_TYPE = "com.pdm.whereto.LOCATION_TYPE";
    private static final String EXTRA_NEW_LOCATION = "NEW_LOCATION" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitialDumpTask task = new InitialDumpTask(this);
        task.execute();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final AppCompatButton btnSleep = (AppCompatButton) findViewById(R.id.btnSleep);
        final AppCompatButton btnDance = (AppCompatButton) findViewById(R.id.btnDance);
        final AppCompatButton btnMovie = (AppCompatButton) findViewById(R.id.btnMovie);
        final AppCompatButton btnEat = (AppCompatButton) findViewById(R.id.btnEat);
        final AppCompatButton btnShowAll = (AppCompatButton) findViewById(R.id.btnShowAll);

        btnEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DisplayLocationsActivity.class);
                intent.putExtra(EXTRA_LOCATION_TYPE, LocationType.Restaurant.get_name());
                startActivity(intent);
            }
        });

        btnSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DisplayLocationsActivity.class);
                intent.putExtra(EXTRA_LOCATION_TYPE, LocationType.Hotel.get_name());
                startActivity(intent);
            }
        });

        btnDance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DisplayLocationsActivity.class);
                intent.putExtra(EXTRA_LOCATION_TYPE, LocationType.Club.get_name());
                startActivity(intent);
            }
        });

        btnMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DisplayLocationsActivity.class);
                intent.putExtra(EXTRA_LOCATION_TYPE, LocationType.Cinema.get_name());
                startActivity(intent);
            }
        });

        btnShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), MapsActivity.class);
                // TODO change location type to : NewLocation
//                intent.putExtra("name", EXTRA_NEW_LOCATION);
//                intent.putExtra("lat", 44.4300449);
//                intent.putExtra("lng", 26.0981623);
                Intent intent = new Intent(v.getContext(), DisplayAllLocationsActivity.class);
                startActivity(intent);
            }
        });
    }
}