package ca.ubc.cs.cpsc210.mindthegap.parsers;


import ca.ubc.cs.cpsc210.mindthegap.model.Arrival;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;

/**
 * Parser for route strings in TfL line data
 */
public class BranchStringParser {

    /**
     * Parse a branch string obtained from TFL
     *
     * @param branch  branch string
     * @return       list of lat/lon points parsed from branch string
     */
    public static List<LatLon> parseBranch(String branch) {
        if (branch.equals(""))
            return new ArrayList<LatLon>();
        try {
            /**
             * called JSONArray twice because the line looks like a JSONArray but have 2 extra square braces outside.
             * In order to use it, call it twice to remove the extra square braces.
             */
            //String lol = "[.[.[0.093493,51.6037],[0.091015,51.5956],[0.088596,51.5857],[0.090015,51.5757],[0.066195,51.5765],[0.045369,51.5762],[0.028537,51.5755],[0.008202,51.5683],[-0.005515,51.5566],[-0.00345,51.5418],[-0.033633,51.5251],[-0.0555,51.5272],[-0.083176,51.5174],[-0.088948,51.5134],[-0.097562,51.5149],[-0.111578,51.5183],[-0.12047,51.5176],[-0.130406,51.5164],[-0.141899,51.5152],[-0.149719,51.5143],[-0.15895,51.5134],[-0.175491,51.5117],[-0.187149,51.5103],[-0.196102,51.5091],[-0.205677,51.5071],[-0.218812,51.5044],[-0.224295,51.512],[-0.247248,51.5166],[-0.259754,51.5235],[-0.292704,51.5302],[-0.323447,51.5367],[-0.346052,51.5424],[-0.368702,51.5482],[-0.398904,51.5569],[-0.410699,51.5607],[-0.437875,51.5697]]]";
            JSONArray test = new JSONArray(branch);
            JSONArray secondArray = new JSONArray(test.getJSONArray(0).toString());

            List<LatLon> latlons = new ArrayList<LatLon>();
            for (int i=0; i < secondArray.length(); i++){
                JSONArray latlon = new JSONArray(secondArray.get(i).toString());
                // google parse string to double java
                double lng = Double.parseDouble(latlon.get(0).toString());
                double lat = Double.parseDouble(latlon.get(1).toString());

                LatLon to_add = new LatLon(lat, lng);

                latlons.add(to_add);
            }
            //System.out.println(latlons.size());
            return latlons;


        }
        catch (JSONException e){
            System.out.println(e.toString());
            return null;
        }


    }


  /*  public static void main(String[] args)
    {
        BranchStringParser.parseBranch("");
    }
    */
}
