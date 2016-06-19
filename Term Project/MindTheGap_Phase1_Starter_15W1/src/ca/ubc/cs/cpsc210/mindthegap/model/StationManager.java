package ca.ubc.cs.cpsc210.mindthegap.model;

import ca.ubc.cs.cpsc210.mindthegap.model.exception.StationException;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import ca.ubc.cs.cpsc210.mindthegap.util.SphericalGeometry;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Manages all tube stations on network.
 *
 * Singleton pattern applied to ensure only a single instance of this class that
 * is globally accessible throughout application.
 */
public class StationManager implements Iterable<Station> {
    public static final int RADIUS = 10000;
    private static StationManager instance;
    private Set<Station> stns;
    private Station selected;

    /**
     * Constructs station manager with empty set of stations and null as the selected station
     */
    private StationManager() {
        stns = new HashSet<Station>();
        this.selected = null;
        // stub
    }

    /**
     * Gets one and only instance of this class
     *
     * @return  instance of class
     */
    public static StationManager getInstance() {
        // Do not modify the implementation of this method!
        if(instance == null) {
            instance = new StationManager();
        }

        return instance;
    }

    public Station getSelected() {
        return selected;  // stub
    }

    /**
     * Get station with given id or null if no such station is found in this manager
     *
     * @param id  the id of this station
     *
     * @return  station with given id or null if no such station is found
     */
    public Station getStationWithId(String id) {
        for (Station stn : stns)
        {
            if (stn.getID().equals(id)) { // meowmeow!!!
                return stn;
            }
        }
           // stub
        return null;
    }

    /**
     * Set the station selected by user
     *
     * @param selected   station selected by user
     * @throws StationException when station manager doesn't contain selected station
     */
    public void setSelected(Station selected) throws StationException {
        if (stns.contains(selected)){
            this.selected = selected;
        }
        else
            throw new StationException("Station not in the list for selected.");
        // stub
    }

    /**
     * Clear selected station (selected station is null)
     */
    public void clearSelectedStation() {
        selected = null;
        // stub
    }

    /**
     * Add all stations on given line. Station added only if it is not already in the collection.
     *
     * @param line  the line from which stations are to be added
     */
    public void addStationsOnLine(Line line) {
        stns.addAll(line.getStations());

        //for (Station stn : stns)
            //System.out.println("Added " + stn.getID());
        // stub
    }

    /**
     * Get number of stations managed
     *
     * @return  number of stations added to manager
     */
    public int getNumStations() {
        return (stns.size());   // stub
    }

    /**
     * Remove all stations from station manager
     */
    public void clearStations() {
        stns.clear();
        clearSelectedStation();
        // stub
    }

    /**
     * Find nearest station to given point.  Returns null if no station is closer than RADIUS metres.
     *
     * @param pt  point to which nearest station is sought
     * @return    station closest to pt but less than 10,000m away; null if no station is within RADIUS metres of pt
     */
    public Station findNearestTo(LatLon pt) {
        double indist = RADIUS;
        Station stat = null;
        for (Station stn : stns) {
            double dist = distFrom(pt.getLatitude(), pt.getLongitude(), stn.getLocn().getLatitude(), stn.getLocn().getLongitude());
            if (dist <= indist) {
                indist = dist;
                stat = stn;
            }
        }
        return stat;   // stub
    }


    /**
     * from http://stackoverflow.com/questions/837872/calculate-distance-in-meters-when-you-know-longitude-and-latitude-in-java
     * calculate the distance between the two given points
     */
    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = (double) (earthRadius * c);

        return dist;
    }

    @Override
    public Iterator<Station> iterator() {
        // Do not modify the implementation of this method!
        return stns.iterator();
    }
}
