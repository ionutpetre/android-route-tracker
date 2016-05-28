package com.pdm.whereto;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DisplayAllLocationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_locations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        LocationType[] locTypes = LocationType.values();
        List<Location> locations = new ArrayList<>();
        for (LocationType type : locTypes)
            locations.addAll(DatabaseHandler.getInstance(this).getAllLocationsByType(type.get_name()));


        RecyclerView lRecyclerView = (RecyclerView) findViewById(R.id.location_recycler);
        lRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager lLayoutManager = new LinearLayoutManager(this);
        lRecyclerView.setLayoutManager(lLayoutManager);

        RecyclerView.Adapter lAdapter = new LocationAdapter(locations, this);
        lRecyclerView.setAdapter(lAdapter);
    }
}