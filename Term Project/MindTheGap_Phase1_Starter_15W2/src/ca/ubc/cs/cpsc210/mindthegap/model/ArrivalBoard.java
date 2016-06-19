package ca.ubc.cs.cpsc210.mindthegap.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Represents an arrivals board for a particular station, on a particular line,
 * for trains traveling in a particular direction (as indicated by platform prefix).
 *
 * Invariant: iterator provides arrivals in order of time to station
 * (first train to arrive will be listed first).
 */
public class ArrivalBoard implements Iterable<Arrival> {
    private List<Arrival> arrivals;

    // add field
    private Line line;
    private String travelDirn;

    /**
     * Constructs an arrival board for the given line with an empty list of arrivals
     * and given travel direction.
     *
     * @param line        line on which arrivals listed on this board operate (cannot be null)
     * @param travelDirn  the direction of travel
     */
    public ArrivalBoard(Line line, String travelDirn) {
        arrivals = new ArrayList<>();
        this.line = line;
        this.travelDirn = travelDirn;
    }

    public Line getLine() {
        return line;
    }

    public String getTravelDirn() {
        return travelDirn;
    }

    /**
     * Get total number of arrivals posted on this arrival board
     *
     * @return  total number of arrivals
     */
    public int getNumArrivals() {
        return arrivals.size();
    }

    /**
     * Add a train arrival to this arrivals board.
     *
     * @param arrival  the arrival to add to this arrivals board
     */
    public void addArrival(Arrival arrival) {

//        for(int i = 0; i < arrivals.size(); i++){
//            if(arrival.compareTo(arrivals.get(i)) <= 0){
//                arrivals.add(i,arrival);
//                break;
//            }
//        }
//        if(!arrivals.contains(arrival)){
//            arrivals.add(arrival);
//        }
//        Collections.sort(arrivals);

        arrivals.add(arrival);
        Collections.sort(arrivals);
    }

    /**
     * Clear all arrivals from this arrival board
     */
    public void clearArrivals() {
        arrivals.clear();
    }

    /**
     * Two ArrivalBoards are equal if their lines are equal and travel directions are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrivalBoard that = (ArrivalBoard) o;

        if (!line.equals(that.line)) return false;
        return travelDirn.equals(that.travelDirn);
    }

    /**
     * Two ArrivalBoards are equal if their lines are equal and travel directions are equal
     */
    @Override
    public int hashCode() {
        int result = line.hashCode();
        result = 31 * result + travelDirn.hashCode();
        return result;
    }

    /**
     * Produces an iterator over the arrivals on this arrival board
     * ordered by time to arrival (first train to arrive is produced
     * first).
     */
    @Override
    public Iterator<Arrival> iterator() {
        return arrivals.iterator();
    }
}
