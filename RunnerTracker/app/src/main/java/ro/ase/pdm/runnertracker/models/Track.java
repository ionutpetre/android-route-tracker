package ro.ase.pdm.runnertracker.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Track {

    private String name;
    private List<Coordinate> coordinates;
    private Date startDate;
    private Date endDate;

    public Track() {
        this("Undefined", new Date());
    }

    public Track(String name, Date startDate) {
        this.name = name;
        this.coordinates = new ArrayList<Coordinate>();
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public void addCoordinate(double latitude, double longitude) {
        coordinates.add(new Coordinate(latitude, longitude));
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "{'track':'" + name + "','start':'" + startDate + "','end':'" + endDate + "'}";
    }
}
