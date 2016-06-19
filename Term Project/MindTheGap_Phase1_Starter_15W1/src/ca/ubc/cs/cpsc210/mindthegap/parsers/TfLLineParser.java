package ca.ubc.cs.cpsc210.mindthegap.parsers;

import ca.ubc.cs.cpsc210.mindthegap.model.*;
import ca.ubc.cs.cpsc210.mindthegap.parsers.exception.TfLLineDataMissingException;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A parser for the data returned by TFL line route query
 */
public class TfLLineParser extends TfLAbstractParser {

    /**
     * Parse line from JSON response produced by TfL.  No stations added to line if TfLLineDataMissingException
     * is thrown.
     *
     * @param lmd              line meta-data
     * @return                 line parsed from TfL data
     * @throws JSONException   when JSON data does not have expected format
     * @throws TfLLineDataMissingException when
     * <ul>
     *  <li> JSON data is missing lineName, lineId or stopPointSequences elements </li>
     *  <li> for a given sequence: </li>
     *    <ul>
     *      <li> the stopPoint array is missing </li>
     *      <li> all station elements are missing one of name, lat, lon or stationId elements </li>
     *    </ul>
     * </ul>
     */
    public static Line parseLine(LineResourceData lmd, String jsonResponse)
            throws JSONException, TfLLineDataMissingException {

        JSONObject test = new JSONObject(jsonResponse);
        Line to_return = null;

            if (test.has("lineName") && test.has("lineId") && test.has("stopPointSequences")) {


                String lineName = test.get("lineName").toString();
                String lineId = test.get("lineId").toString();

                to_return = new Line(lmd, lineId, lineName);

                //  parse branches
                JSONArray all_lineStrings = new JSONArray(test.get("lineStrings").toString());
                JSONArray all_stations_array = new JSONArray(test.get("stopPointSequences").toString());

                // line strings
                // parse the branches and add them to the line
                for (int y = 0; y < all_lineStrings.length(); y++) {
                    to_return.addBranch(new Branch(all_lineStrings.get(y).toString()));
                }

                // Stations
                for (int i = 0; i < all_stations_array.length(); i++) {
                    // whether the stopPoint array is missing
                    if (all_stations_array.getJSONObject(i).has("stopPoint")) {
                        JSONArray all_stations = new JSONArray(all_stations_array.getJSONObject(i).get("stopPoint").toString());
                        // if they have all of the elements return false
                        boolean all_stations_screwed = true;

                        for (int x = 0; x < all_stations.length(); x++) {
                            JSONObject obj = all_stations.getJSONObject(x);
                            if (obj.has("name") && obj.has("stationId") && obj.has("lat") && obj.has("lon")) {

                                String stationName = parseName(obj.get("name").toString());
                                String stationId = obj.get("stationId").toString();

                                double stationLat = Double.parseDouble(obj.get("lat").toString());
                                double stationLon = Double.parseDouble(obj.get("lon").toString());

                                LatLon to_add = new LatLon(stationLat, stationLon);


                                Station stn = new Station(stationId, stationName, to_add);
                                Station from_manager = StationManager.getInstance().getStationWithId(stationId);
                                all_stations_screwed = false;
                                if (from_manager != null) {
                                    to_return.addStation(from_manager);
                                }
                                else {
                                    to_return.addStation(stn);
                                    StationManager.getInstance().addStationsOnLine(to_return);

                                }
                            }



                        }
                        if (all_stations_screwed)
                            throw new TfLLineDataMissingException();
                    }
                    else
                        throw new TfLLineDataMissingException();


                }
            }

            else
                throw new TfLLineDataMissingException();





        //to_return.addBranch(new Branch())
        return to_return;   // stub
    }
}
