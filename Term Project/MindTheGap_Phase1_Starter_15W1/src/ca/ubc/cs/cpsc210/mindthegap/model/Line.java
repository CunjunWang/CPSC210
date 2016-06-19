package ca.ubc.cs.cpsc210.mindthegap.model;

import ca.ubc.cs.cpsc210.mindthegap.parsers.BranchStringParser;

import java.util.*;

/**
 * Represents a line on the underground with a name, id, list of stations and list of branches.
 *
 * Invariants:
 * - no duplicates in list of stations
 * - iterator iterates over stations in the order in which they were added to the line
 */
public class Line implements Iterable<Station> {
    private List<Station> stns;
    private LineResourceData lmd;
    private String id;
    private String name;
    private Set<Branch> bchs;

    /**
     * Constructs a line with given resource data, id and name.
     * List of stations and list of branches are empty.
     *
     * @param lmd     the line meta-data
     * @param id      the line id
     * @param name    the name of the line
     */
    public Line(LineResourceData lmd, String id, String name) {
        this.lmd = lmd;
        this.id = id;
        this.name = name;
        stns = new ArrayList<Station>();
        bchs = new HashSet<Branch>();
        // stub
    }



    public String getName() {
        return name;   // stub
    }

    public String getId() {
        return id;   // stub
    }

    /**
     * Get colour specified by line resource data
     *
     * @return  colour in which to plot this line
     */
    public int getColour() {
        return lmd.getColour();   // stub
    }

    /**
     * Add station to line.
     *
     * @param stn  the station to add to this line
     */
    public void addStation(Station stn) {
        for (Station ss : stns)
        {
            if (ss.equals(stn))
                return;
        }
        // Adds the station if not found.
        stns.add(stn);
        stn.addLine(this);

        // stub
    }

    /**
     * Remove station from line
     *
     * @param stn  the station to remove from this line
     */
    public void removeStation(Station stn) {
        if (stns.contains(stn)){
            stns.remove(stn);
            stn.removeLine(this);
        }
        // stub
    }

    /**
     * Clear all stations from this line
     */
    public void clearStations() {
        for (Station stn : stns){
            stn.removeLine(this);
        }
        stns.clear();

        // stub
    }

    public List<Station> getStations() {
        return stns;   // stub
    }

    /**
     * Determines if this line has a stop at a given station
     *
     * @param stn  the station
     * @return  true if line has a stop at given station
     */
    public boolean hasStation(Station stn) {

        return (stns.contains(stn));   // stub
    }

    /**
     * Add a branch to this line
     *
     * @param b  the branch to add to line
     */
    public void addBranch(Branch b) {
        bchs.add(b);
        // stub
    }

    public Set<Branch> getBranches() {
        return bchs;   // stub
    }

    /**
     * Two lines are equal if their ids are equal
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line stations = (Line) o;

        return id.equals(stations.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * Two lines are equal if their ids are equal
     */


    @Override
    public Iterator<Station> iterator() {
        // Do not modify the implementation of this method!
        return stns.iterator();
    }
}
