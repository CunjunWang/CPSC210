package ca.ubc.cs.cpsc210.mindthegap.parsers;

import ca.ubc.cs.cpsc210.mindthegap.model.*;
import ca.ubc.cs.cpsc210.mindthegap.parsers.exception.TfLArrivalsDataMissingException;
import ca.ubc.cs.cpsc210.mindthegap.parsers.exception.TfLLineDataMissingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.Set;

/**
 * A parser for the data returned by the TfL station arrivals query
 */
public class TfLArrivalsParser extends TfLAbstractParser {

    /**
     * Parse arrivals from JSON response produced by TfL query.  All parsed arrivals are
     * added to given station assuming that corresponding JSON object as all of:
     * timeToStation, platformName, lineID and one of destinationName or towards.  If
     * any of the aforementioned elements is missing, the arrival is not added to the station.
     *
     * @param stn             station to which parsed arrivals are to be added
     * @param jsonResponse    the JSON response produced by TfL
     * @throws JSONException  when JSON response does not have expected format
     * @throws TfLArrivalsDataMissingException  when all arrivals are missing at least one of the following:
     * <ul>
     *     <li>timeToStation</li>
     *     <li>platformName</li>
     *     <li>lineId</li>
     *     <li>destinationName and towards</li>
     * </ul>
     */
    public static void parseArrivals(Station stn, String jsonResponse)
            throws JSONException, TfLArrivalsDataMissingException {
        JSONArray all_arrivals = new JSONArray(jsonResponse);
        boolean added_arrival = false;
        for (int i = 0; i < all_arrivals.length(); i++)
        {
            try {
                JSONObject temp_obj = new JSONObject(all_arrivals.get(i).toString());
                //System.out.println(temp_obj.get("stationName"));
                String destinationName;
                int timeToDestination = Integer.parseInt(temp_obj.get("timeToStation").toString());
                try {
                    destinationName = parseName(temp_obj.get("destinationName").toString());
                } catch (JSONException e) {
                    destinationName = temp_obj.get("towards").toString();
                }
                String platform = temp_obj.getString("platformName");
                Arrival arrival = new Arrival(timeToDestination, destinationName, platform);
                String lineId = temp_obj.get("lineId").toString();
                // Does not run if exception was thrown.
                // create a new arrival with the parsed stuff
                // Compare every line and try matches the given line, then if it matches,
                // add it to the station
                for (Line line : stn.getLines()) {
                    if (line.getId().equals(lineId)) {
                        stn.addArrival(line, arrival);
                        added_arrival = true;
                       // System.out.println("Added arrival lol");
                    }
                }
            }
            catch (JSONException e) {
                System.out.println("Hmmmm we found one error in the iteration.");
            }
        }
        if (!added_arrival)
            throw new TfLArrivalsDataMissingException();
        // stub
    }
}
