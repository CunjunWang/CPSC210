package ca.ubc.cs.cpsc210.mindthegap.tests.parsers;

import ca.ubc.cs.cpsc210.mindthegap.TfL.DataProvider;
import ca.ubc.cs.cpsc210.mindthegap.TfL.FileDataProvider;
import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.LineResourceData;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;
import ca.ubc.cs.cpsc210.mindthegap.parsers.TfLArrivalsParser;
import ca.ubc.cs.cpsc210.mindthegap.parsers.exception.TfLArrivalsDataMissingException;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests to check basic parsing for TfL Arrivals data
 */
public class TfLArrivalsParserBasicTest extends AbstractTfLArrivalsParserTest {
    private String arrivalsJsonData;

    @Before
    public void setUp() throws Exception {
        DataProvider dataProvider = new FileDataProvider("./res/raw/arrivals_oxc.json");
        arrivalsJsonData = dataProvider.dataSourceToString();
        stn = new Station("id", "Oxford Circus", new LatLon(51.5, -0.1));
        stn.addLine(new Line(LineResourceData.CENTRAL, "central", "Central"));
        stn.addLine(new Line(LineResourceData.VICTORIA, "victoria", "Victoria"));
    }

    @Test
    public void testArrivalsBasicParsing() {
        try {
            TfLArrivalsParser.parseArrivals(stn, arrivalsJsonData);

            System.out.println(stn.getNumArrivalBoards());
        } catch (JSONException e) {
            fail("JSONException should not have been thrown while parsing data in arrivals_oxc.json");
        } catch (TfLArrivalsDataMissingException e) {
            fail("TfLArrivalsDataMissingException should not have been thrown while parsing data in arrivals_oxc.json");
        }
    }

    // The single test above is very basic - it simply checks that your parser
    // doesn't throw an exception.
}