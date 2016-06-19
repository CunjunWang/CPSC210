package ca.ubc.cs.cpsc210.mindthegap.model;

import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;

import java.util.*;

/**
 * Represents a station on the underground with an id, name, location (lat/lon)
 * set of lines with stops at this station and a list of arrival boards.
 */
public class Station implements Iterable<ArrivalBoard> {
    private List<ArrivalBoard> arrivalBoards;
    private String id;
    private String name;
    private LatLon locn;
    private Set<Line> sln;


    /**
     * Constructs a station with given id, name and location.
     * Set of lines and list of arrival boards are empty.
     *
     * @param id    the id of this station (cannot by null)
     * @param name  name of this station
     * @param locn  location of this station
     */
    public Station(String id, String name, LatLon locn) {
        this.id = id;
        this.name = name;
        this.locn = locn;
        sln = new HashSet<Line>();
        arrivalBoards = new ArrayList<ArrivalBoard>();
        // stub
    }

    public String getName() {
        return name;   // stub
    }

    public LatLon getLocn() {
        return locn;   // stub
    }

    public String getID() {
        return id;   // stub
    }

    public Set<Line> getLines() {
        return sln;   // stub
    }

    public int getNumArrivalBoards() {
        return arrivalBoards.size();   // stub
    }

    /**
     * Add line to set of lines with stops at this station.
     *
     * @param line  the line to add
     */
    public void addLine(Line line) {
        sln.add(line);
        line.addStation(this);
        // stub
    }

    /**
     * Remove line from set of lines with stops at this station
     *
     * @param line the line to remove
     */
    public void removeLine(Line line) {
        if (sln.contains(line)) {
            sln.remove(line);
            line.removeStation(this);
        }
        // stub
    }

    /**
     * Determine if this station is on a given line
     * @param line  the line
     * @return  true if this station is on given line
     */
    public boolean hasLine(Line line) {
        return (line.hasStation(this));
           // stub
    }

    /**
     * Add train arrival travelling on a particular line in a particular direction to this station.
     * Arrival is added to corresponding arrival board based on the line on which it is
     * operating and the direction of travel (as indicated by platform prefix).  If the arrival
     * board for given line and travel direction does not exist, it is created and added to
     * arrival boards for this station.
     *
     * @param line    line on which train is travelling
     * @param arrival the train arrival to add to station
     */
    public void addArrival(Line line, Arrival arrival) {
        for (ArrivalBoard ab : arrivalBoards)
        {
            // get particular line from the arrivalBoard
            if (ab.getLine().equals(line))
            {
                // whether the direction is the same
                if (ab.getTravelDirn().equals(arrival.getTravelDirn()))
                {
                    ab.addArrival(arrival);
                    return;
                }
            }
        }

        // At this point we couldn't find line and travel direction so we maek a new arrival board.

        ArrivalBoard new_ab = new ArrivalBoard(line, arrival.getTravelDirn());
        new_ab.addArrival(arrival);
        arrivalBoards.add(new_ab);;

        // stub
    }

    /**
     * Remove all arrival boards from this station
     */
    public void clearArrivalBoards() {
        arrivalBoards.clear();
        // stub
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Station that = (Station) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * Two stations are equal if their ids are equal
     */





    @Override
    public Iterator<ArrivalBoard> iterator() {
        // Do not modify the implementation of this method!
        return arrivalBoards.iterator();
    }
}
