package ca.ubc.cs.cpsc210.mindthegap.tests.parsers;

import ca.ubc.cs.cpsc210.mindthegap.TfL.DataProvider;
import ca.ubc.cs.cpsc210.mindthegap.TfL.FileDataProvider;
import ca.ubc.cs.cpsc210.mindthegap.model.LineResourceData;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;
import ca.ubc.cs.cpsc210.mindthegap.model.StationManager;
import ca.ubc.cs.cpsc210.mindthegap.parsers.TfLLineParser;
import ca.ubc.cs.cpsc210.mindthegap.parsers.exception.TfLLineDataMissingException;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import ca.ubc.cs.cpsc210.mindthegap.model.Line;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


/**
 * Unit test for TfLLineParser
 */
public class TfLLineParserBasicTest {
    private String lineData;

    @Before
    public void setUp() throws Exception {
        DataProvider dataProvider = new FileDataProvider("./res/raw/central_inbound.json");
        lineData = dataProvider.dataSourceToString();
    }

    @Test
    public void testBasicLineParsing() {
        try {
            Line test = TfLLineParser.parseLine(LineResourceData.CENTRAL, lineData);
            assertTrue(test.getBranches().size() == 6);
            System.out.println(test.getStations().size());
            System.out.println(StationManager.getInstance().getNumStations());

            for (Station stn : test.getStations())
            {
                System.out.println(stn.getName());
            }
        } catch (JSONException e) {
            System.out.println(e.getMessage());
            fail("JSONException should not have been thrown while parsing data in central_inbound.json");
        } catch (TfLLineDataMissingException e) {
            System.out.println(e.getMessage());
            fail("TfLLineDataMissingException should not have been thrown while parsing data in central_inbound.json");
        }
    }

    // The single test above is very basic - it simply checks that your parser
    // doesn't throw an exception.
}