package ca.ubc.cs.cpsc210.mindthegap.model;

import ca.ubc.cs.cpsc210.mindthegap.model.exception.StationException;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import ca.ubc.cs.cpsc210.mindthegap.util.SphericalGeometry;

import java.util.*;

/**
 * Manages all tube stations on network.
 *
 * Singleton pattern applied to ensure only a single instance of this class
 * is globally accessible throughout application.
 */
public class StationManager implements Iterable<Station> {
    public static final int RADIUS = 10000;
    private static StationManager instance;
    private Set<Station> stations;

    // add fields
    private Station selected;

    /**
     * Constructs station manager with empty set of stations and null as the selected station
     */
    private StationManager() {
        stations = new HashSet<>();
        selected = null;
    }

    /**
     * Gets one and only instance of this class
     *
     * @return  instance of class
     */
    public static StationManager getInstance() {
        if(instance == null) {
            instance = new StationManager();
        }
        return instance;
    }

    public Station getSelected() {
        return selected;
    }

    /**
     * Get station with given id or null if no such station is found in this manager
     *
     * @param id  the id of this station
     *
     * @return  station with given id or null if no such station is found
     */
    public Station getStationWithId(String id) {
        Station station = null;
        for (Station this_station : stations){
            if(this_station.getID().equals(id)){
                station = this_station;
            }
        }
        return station;
    }

    /**
     * Set the station selected by user
     *
     * @param selected   station selected by user
     * @throws StationException when station manager doesn't contain selected station
     */
    public void setSelected(Station selected) throws StationException {
        if(!stations.contains(selected))
            throw new StationException("Doesn't contain selected station!");
        else
            this.selected = selected;
    }

    /**
     * Clear selected station (selected station is null)
     */
    public void clearSelectedStation() {
        selected = null;
    }

    /**
     * Add all stations on given line. Station added only if it is not already in the collection.
     *
     * @param line  the line from which stations are to be added
     */
    public void addStationsOnLine(Line line) {
        for(Station station : line){
            if(!stations.contains(station))
                stations.add(station);
        }
        //stations.addAll(line.getStations());
    }

    /**
     * Get number of stations managed
     *
     * @return  number of stations added to manager
     */
    public int getNumStations() {
        return stations.size();
    }

    /**
     * Remove all stations from station manager and
     * clear selected station.
     */
    public void clearStations() {
        stations.clear();
        this.clearSelectedStation();
    }

    /**
     * Find nearest station to given point.  Returns null if no station is closer than RADIUS metres. If
     * two or more stations are at an equal distance from the given point, any one of those stations is
     * returned.
     *
     * @param pt  point to which nearest station is sought
     * @return    station closest to pt but less than 10,000m away; null if no station is within RADIUS metres of pt
     */
    public Station findNearestTo(LatLon pt) {
        Station NearestStation = null;
        double NearestDistance = RADIUS;
        for(Station next_staion : stations){
            double distance = SphericalGeometry.distanceBetween(next_staion.getLocn(),pt);
            if(distance <= RADIUS && distance < NearestDistance){
                NearestDistance = distance;
                NearestStation = next_staion;
            }
        }
        return NearestStation;
    }

    @Override
    public Iterator<Station> iterator() {
        return stations.iterator();
    }
}
