package ca.ubc.cs.cpsc210.mindthegap.tests.model;

import ca.ubc.cs.cpsc210.mindthegap.TfL.DataProvider;
import ca.ubc.cs.cpsc210.mindthegap.TfL.FileDataProvider;
import ca.ubc.cs.cpsc210.mindthegap.model.*;
import ca.ubc.cs.cpsc210.mindthegap.model.exception.StationException;
import ca.ubc.cs.cpsc210.mindthegap.parsers.TfLLineParser;
import ca.ubc.cs.cpsc210.mindthegap.parsers.exception.TfLLineDataMissingException;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.io.IOException;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;
import static org.junit.Assert.*;

/**
 * Unit tests for StationManager
 */
public class StationManagerTest {
    private StationManager stnManager;

    @Before
    public void setUp() {
        stnManager = StationManager.getInstance();
        stnManager.clearSelectedStation();
        stnManager.clearStations();
    }

    @Test
    public void testStationManagerConstructor() {
        assertEquals(stnManager.getSelected(), null);
    }

    @Test
    public void someTest(){
        DataProvider dataProvider = new FileDataProvider("./res/raw/central_inbound.json");
        DataProvider dataProvider2 = new FileDataProvider("./res/raw/district_inbound.json");

        try {
            String data_central = dataProvider.dataSourceToString();
            String data_district = dataProvider2.dataSourceToString();

            Line central = TfLLineParser.parseLine(LineResourceData.CENTRAL, data_central);
            stnManager.addStationsOnLine(central);
            Line district = TfLLineParser.parseLine(LineResourceData.DISTRICT, data_district);
            stnManager.addStationsOnLine(district);
            Station test = new Station("haha", "lolcat", new LatLon(1, 1));
            assertFalse(central.hasStation(test));
            central.addStation(test);
            assertTrue(central.hasStation(test));

            for (Station ss : central.getStations()) {
                System.out.println(ss.getName());
            }
            System.out.println("Stations " + stnManager.getNumStations());
        }
        catch(IOException e)
        {
            fail("IOexception");
        }

        catch(TfLLineDataMissingException e){
            fail("Bad exception. data missing");
        }
        catch (JSONException e){
            fail("JSSOn....");
        }
    }

    @Test
    public void testArrival()
    {
        Arrival arr = new Arrival(165, "Testing lolcat", "Westbound - Platform 1");
        Arrival arr2 = new Arrival(178, "Testing2", "Westbound - Platform 6");
;
        Line line1 = new Line(LineResourceData.CENTRAL, "first", "test1");
        ArrivalBoard lol = new ArrivalBoard(line1, "westbound");

        lol.addArrival(arr2);
        lol.addArrival(arr);

        for( Arrival i : lol){
            System.out.println(i.getPlatformName());
        }

        System.out.println(arr.getTravelDirn());
        System.out.println(arr.getTimeToStationInMins());
    }

    @Test
    public void testBranch(){
        DataProvider dataProvider = new FileDataProvider("./res/raw/central_inbound.json");

    }


    @Test
    public void testAddLines()
    {
        Line line1 = new Line(LineResourceData.CENTRAL, "first", "test1");
        Line line2 = new Line(LineResourceData.CENTRAL, "firsrt", "tefst1");

        Station s = new Station("s1", "name", new LatLon(1, 1));
        s.addLine(line1);
        s.addLine(line2);

        assertTrue(s.getLines().size() == 2);
    }

    @Test
    public void testDuplicatStation()
    {
        Line line = new Line(LineResourceData.CENTRAL, "test", "testName");
        line.addStation(new Station("1", "name1", new LatLon(0, 0)));
        stnManager.addStationsOnLine(line);

        Station dup = new Station("1", "name1", new LatLon(0, 0));
        System.out.println(stnManager.getStationWithId(dup.getID()).getName());
    }

    // You will need to add more tests!
}