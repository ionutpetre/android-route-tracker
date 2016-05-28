package com.pdm.whereto;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DisplayLocationsActivity extends AppCompatActivity {

    private LocationAdapter lAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_locations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        final String locationType = intent.getStringExtra(MainActivity.EXTRA_LOCATION_TYPE);
        List<Location> locations = DatabaseHandler.getInstance(this).getAllLocationsByType(locationType);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddLocationDialog(locationType);
            }
        });
        RecyclerView lRecyclerView = (RecyclerView) findViewById(R.id.location_recycler);
        lRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager lLayoutManager = new LinearLayoutManager(this);
        lRecyclerView.setLayoutManager(lLayoutManager);

        lAdapter = new LocationAdapter(locations, this);
        lRecyclerView.setAdapter(lAdapter);
    }

    private void showAddLocationDialog(final String locationType){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.add_location);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        int paddingInDp = getSizeInDp(20);

        TextInputLayout nameInputLayout = new TextInputLayout(this);
        final EditText nameInput = new EditText(this);
        nameInput.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        nameInputLayout.setPadding(paddingInDp / 2, paddingInDp, paddingInDp / 2, paddingInDp);
        nameInputLayout.addView(nameInput);
        nameInputLayout.setHint(getResources().getString(R.string.name));
        nameInput.setError(getResources().getString(R.string.required));
        layout.addView(nameInputLayout);

        TextInputLayout addressInputLayout = new TextInputLayout(this);
        final EditText addressInput = new EditText(this);
        addressInput.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        addressInputLayout.setPadding(paddingInDp / 2, paddingInDp, paddingInDp / 2, paddingInDp);
        addressInputLayout.addView(addressInput);
        addressInputLayout.setHint(getResources().getString(R.string.address));
        addressInput.setError(getResources().getString(R.string.required));
        layout.addView(addressInputLayout);

        TextInputLayout latInputLayout = new TextInputLayout(this);
        final EditText latInput = new EditText(this);
        latInput.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        latInputLayout.setPadding(paddingInDp / 2, paddingInDp, paddingInDp / 2, paddingInDp);
        latInputLayout.addView(latInput);
        latInputLayout.setHint(getResources().getString(R.string.lat));
        latInput.setError(getResources().getString(R.string.required));
        layout.addView(latInputLayout);

        TextInputLayout lngInputLayout = new TextInputLayout(this);
        final EditText lngInput = new EditText(this);
        lngInput.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        lngInputLayout.setPadding(paddingInDp / 2, paddingInDp, paddingInDp / 2, paddingInDp);
        lngInputLayout.addView(lngInput);
        lngInputLayout.setHint(getResources().getString(R.string.lng));
        lngInput.setError(getResources().getString(R.string.required));
        layout.addView(lngInputLayout);

        builder.setView(layout);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String name = nameInput.getText().toString();
                String address = addressInput.getText().toString();
                double lat = Double.valueOf(latInput.getText().toString());
                double lng = Double.valueOf(lngInput.getText().toString());
                DatabaseHandler handler = DatabaseHandler.getInstance(DisplayLocationsActivity.this);
                List<Location> locations = new ArrayList<>();
                for(LocationType type : LocationType.values())
                    locations.addAll(handler.getAllLocationsByType(type.get_name()));
                Location location = new Location(locations.size()+1,locationType,address,name,lat,lng);
                lAdapter.addItem(location);
                handler.addLocation(location);
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    public int getSizeInDp(int size) {

        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, size, getResources()
                        .getDisplayMetrics());
    }
}