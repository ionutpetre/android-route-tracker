package ro.ase.pdm.runnertracker.models;

public class Coordinate {

    private double latitude;
    private double longitude;

    public Coordinate() {
        this(0.0, 0.0);
    }

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "{'lat':" + latitude + ",'long':" + longitude + "}";
    }
}
