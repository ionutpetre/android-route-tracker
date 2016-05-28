package com.pdm.whereto;

public class Location {

    private Integer _id;
    private String _type;
    private String _address;
    private String _name;
    private Double _lat;
    private Double _lng;

    public Location(Integer id, String type, String address, String name, double lat, double lng) {

        _id = id;
        _type = type;
        _address = address;
        _name = name;
        _lat = lat;
        _lng = lng;
    }

    public Integer get_id() {

        return _id;
    }

    public String get_type() {

        return _type;
    }

    public String get_address() {

        return _address;
    }

    public String get_name() {

        return _name;
    }

    public Double get_lat() {

        return _lat;
    }

    public Double get_lng() {

        return _lng;
    }
}