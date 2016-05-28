package com.pdm.whereto;

import android.graphics.Color;

public enum LocationType {

    Restaurant("restaurant",R.color.colorRestaurant),
    Club("club",R.color.colorClub),
    Hotel("hotel",R.color.colorHotel),
    Cinema("cinema",R.color.colorCinema);

    private String _name;
    private int _color;

    LocationType(String name, int color) {

        _name = name;
        _color = color;
    }

    public String get_name() {

        return _name;
    }

    public int get_color(){

        return _color;
    }

    public static LocationType getLocationTypeByName(String name){

        for(LocationType type : values())
            if(type.get_name().equals(name))
                return type;

        return null;
    }
}