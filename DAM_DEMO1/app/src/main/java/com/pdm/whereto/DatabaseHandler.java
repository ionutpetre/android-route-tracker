package com.pdm.whereto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "main_db";

    private static final String TABLE_LOCATIONS = "locations";

    private static final String KEY_ID = "id";
    private static final String KEY_TYPE = "type";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_LAT = "lat";
    private static final String KEY_LNG = "lng";

    private static DatabaseHandler _instance;

    public static synchronized DatabaseHandler getInstance(Context context) {

        if (_instance == null)
            _instance = new DatabaseHandler(context.getApplicationContext());

        return _instance;
    }

    public DatabaseHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query =
                String.format("CREATE TABLE %s (%s INTEGER primary key, %s VARCHAR(50), %s VARCHAR(50), %s VARCHAR(50), %s VARCHAR(50), %s VARCHAR(50));",
                        TABLE_LOCATIONS, KEY_ID, KEY_TYPE, KEY_ADDRESS, KEY_NAME, KEY_LAT, KEY_LNG);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);
        onCreate(db);
    }

    public List<Location> getAllLocationsByType(String type) {

        List<Location> locations = new ArrayList<>();
        String getAllLocationsQuery = String.format("SELECT * FROM %s WHERE %s = '%s';", TABLE_LOCATIONS, KEY_TYPE, type);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(getAllLocationsQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Location location = new Location(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), Double.valueOf(cursor.getString(4)), Double.valueOf(cursor.getString(5)));
                locations.add(location);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return locations;
    }

    public void addLocation(Location location){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID,location.get_id());
        values.put(KEY_NAME, location.get_name());
        values.put(KEY_TYPE, location.get_type());
        values.put(KEY_ADDRESS, location.get_address());
        values.put(KEY_LAT, location.get_lat().toString());
        values.put(KEY_LNG, location.get_lng().toString());
        db.insertWithOnConflict(TABLE_LOCATIONS,null,values,SQLiteDatabase.CONFLICT_REPLACE);

        db.close();
    }

    public void addLocations(List<Location> locations) {

        SQLiteDatabase db = this.getWritableDatabase();

        for (Location location : locations) {
            ContentValues values = new ContentValues();
            values.put(KEY_ID,location.get_id());
            values.put(KEY_NAME, location.get_name());
            values.put(KEY_TYPE, location.get_type());
            values.put(KEY_ADDRESS, location.get_address());
            values.put(KEY_LAT, location.get_lat().toString());
            values.put(KEY_LNG, location.get_lng().toString());
            db.insertWithOnConflict(TABLE_LOCATIONS,null,values,SQLiteDatabase.CONFLICT_REPLACE);
        }

        db.close();
    }
}