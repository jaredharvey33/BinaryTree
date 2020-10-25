import java.util.ArrayList;

/**
 * This class tests the methods in the runner class
 * 
 * @author Ren Robinson (rarobin98) Jared Harvey (jharvey33)
 * @version 2020.09.30
 *
 */
public class Covid19TrackingManager2Test extends student.TestCase {

    // Virginia Tech Honor Code Pledge:
    //
    // As a Hokie, I will conduct myself with honor and integrity at all times.
    // I will not lie, cheat, or steal, nor will I accept the actions of those
    // who
    // do.
    // -- Ren Robinson (rarobin98), Jared Harvey (jharvey33)

    /**
     * @author Ren Robinson (rarobin98), Jared Harvey (jharvey33)
     * @version 2020.09.22
     *
     */

    private Record r1;
    private Record r1Copy;
    private Record r2;
    private Record r4;
    private Record r5;
    private Record r4Copy;
    private Record r5Copy;
    private Record r6;
    private Record r7;

    private String[] stateArray;
    private ArrayList<Record> sortDate;
    private ArrayList<Record> sortState;
    private ArrayList<Record> records;

    /**
     * sets up the tests classes.
     */

    public void setUp() {

        String states = "American Samoa - AS\r\n" + "\r\n"
            + "District of Columbia - DC\r\n" + "\r\n"
            + "Federated States of Micronesia - FM\r\n" + "\r\n"
            + "Guam - GU\r\n" + "\r\n" + "Marshall Islands - MH\r\n" + "\r\n"
            + "Northern Mariana Islands - MP\r\n" + "\r\n" + "Palau - PW\r\n"
            + "\r\n" + "Puerto Rico - PR\r\n" + "\r\n"
            + "Virgin Islands - VI\r\n" + "\r\n" + "Alabama - AL\r\n" + "\r\n"
            + "Alaska - AK\r\n" + "\r\n" + "Arizona - AZ\r\n" + "\r\n"
            + "Arkansas - AR\r\n" + "\r\n" + "California - CA\r\n" + "\r\n"
            + "Colorado - CO\r\n" + "\r\n" + "Connecticut - CT\r\n" + "\r\n"
            + "Delaware - DE\r\n" + "\r\n" + "Florida - FL\r\n" + "\r\n"
            + "Georgia - GA\r\n" + "\r\n" + "Hawaii - HI\r\n" + "\r\n"
            + "Idaho - ID\r\n" + "\r\n" + "Illinois - IL\r\n" + "\r\n"
            + "Indiana - IN\r\n" + "\r\n" + "Iowa - IA\r\n" + "\r\n"
            + "Kansas - KS\r\n" + "\r\n" + "Kentucky - KY\r\n" + "\r\n"
            + "Louisiana - LA\r\n" + "\r\n" + "Maine - ME\r\n" + "\r\n"
            + "Maryland - MD\r\n" + "\r\n" + "Massachusetts - MA\r\n" + "\r\n"
            + "Michigan - MI\r\n" + "\r\n" + "Minnesota - MN\r\n" + "\r\n"
            + "Mississippi - MS\r\n" + "\r\n" + "Missouri - MO\r\n" + "\r\n"
            + "Montana - MT\r\n" + "\r\n" + "Nebraska - NE\r\n" + "\r\n"
            + "Nevada - NV\r\n" + "\r\n" + "New Hampshire - NH\r\n" + "\r\n"
            + "New Jersey - NJ\r\n" + "\r\n" + "New Mexico - NM\r\n" + "\r\n"
            + "New York - NY\r\n" + "\r\n" + "North Carolina - NC\r\n" + "\r\n"
            + "North Dakota - ND\r\n" + "\r\n" + "Ohio - OH\r\n" + "\r\n"
            + "Oklahoma - OK\r\n" + "\r\n" + "Oregon - OR\r\n" + "\r\n"
            + "Pennsylvania - PA\r\n" + "\r\n" + "Rhode Island - RI\r\n"
            + "\r\n" + "South Carolina - SC\r\n" + "\r\n"
            + "South Dakota - SD\r\n" + "\r\n" + "Tennessee - TN\r\n" + "\r\n"
            + "Texas - TX\r\n" + "\r\n" + "Utah - UT\r\n" + "\r\n"
            + "Vermont - VT\r\n" + "\r\n" + "Virginia - VA\r\n" + "\r\n"
            + "Washington - WA\r\n" + "\r\n" + "West Virginia - WV\r\n" + "\r\n"
            + "Wisconsin - WI\r\n" + "\r\n" + "Wyoming - WY";
        stateArray = states.split("\r\n\r\n");

        r1 = new Record(20200815, "AK", -1, -1, -1, -1, -1, -1, "A", -1);
        r1Copy = new Record(20200815, "AK", -1, -1, 1, 1, 1, 1, "A", 1);
        r2 = new Record(20200814, "CO", -1, 1, 1, 1, 1, 1, "A", 1);
        Record r3 = new Record(20200810, "WY", 1, 1, 1, 1, 1, 1, "A", 1);
        sortDate = new ArrayList<Record>();
        sortDate.add(r2);
        sortDate.add(r3);
        sortDate.add(r1);
        sortState = new ArrayList<Record>();
        sortState.add(r2);
        sortState.add(r3);
        sortState.add(r1);
        // Testing Greater Grade
        r4 = new Record(20200815, "AK", 5, 1, 6, 1, 1, 1, "A+", 1);
        r5 = new Record(20200815, "AK", 1, 1, 1, 1, 1, 1, "A-", 1);
        r4Copy = new Record(20200815, "AK", 1, 1, 1, 1, 1, 1, "C+", 1);
        r5Copy = new Record(20200815, "AK", 1, 1, 1, 1, 1, 1, "A-", 1);
        // testing isDuplicate
        r6 = new Record(20200810, "AK", 1, 1, 1, 1, 1, 1, "A", 1);
        r7 = new Record(20200810, "WI", 1, 1, 1, 1, 1, 1, "A", 1);
        records = new ArrayList<>();

    }


    /**
     * tests the
     * load method
     */

    public void testLoad() {
        assertTrue(Covid19TrackingManager2.load("Sample_input2.csv", stateArray,
            records));
    }


    /**
     * tests the isDuplicate method
     */
    public void testIsDuplicate() {
        // case 1 returns record
        assertEquals(Covid19TrackingManager2.isDuplicate(r1, sortState), r1);
        // case 2 returns null
        assertEquals(Covid19TrackingManager2.isDuplicate(r6, sortState), null);

        assertEquals(Covid19TrackingManager2.isDuplicate(r7, sortState), null);

    }


    /**
     * tests the greaterGrade method
     */
    public void testGreaterGrade() {
        // case 1 if
        assertFalse(Covid19TrackingManager2.greaterGrade("A", "A+"));
        assertFalse(Covid19TrackingManager2.greaterGrade("A", "A"));
        assertTrue(Covid19TrackingManager2.greaterGrade("A", "B"));
        assertTrue(Covid19TrackingManager2.greaterGrade("A+", "A"));
        assertFalse(Covid19TrackingManager2.greaterGrade("B", "A"));
    }


    /**
     * tests the abConversion method
     */
    public void testAbConversion() {
        // main case
        assertEquals(Covid19TrackingManager2.abConversion("VI", stateArray),
            "virginislands");
        assertEquals(Covid19TrackingManager2.abConversion("Rhode Island",
            stateArray), "rhodeisland");
        assertEquals(Covid19TrackingManager2.abConversion("districtofcolumbia",
            stateArray), "districtofcolumbia");

    }


    /**
     * tests the replacementCheck method
     */
    public void testReplacementCheck() {
        assertTrue(Covid19TrackingManager2.replacementCheck(r1, r4));
        assertFalse(Covid19TrackingManager2.replacementCheck(r2, r1Copy));
    }


    /**
     * tests the dateToString method
     */
    public void testDateToString() {
        assertEquals(Covid19TrackingManager2.dateToString(20200815),
            "08/15/2020");
        assertEquals(Covid19TrackingManager2.dateToString(20200810),
            "08/10/2020");
    }


    /**
     * tests the ContainsState method
     */
    public void testContainsDate() {
        assertTrue(Covid19TrackingManager2.containsDate("08/15/2020",
            sortDate));
        assertFalse(Covid19TrackingManager2.containsDate("08/25/2020",
            sortDate));

    }


    /**
     * tests the ContainsState method
     */
    public void testContainsState() {
        assertTrue(Covid19TrackingManager2.containsState("AK", sortDate,
            stateArray));
        assertTrue(Covid19TrackingManager2.containsState("CO", sortDate,
            stateArray));
        assertFalse(Covid19TrackingManager2.containsState("WI", sortDate,
            stateArray));
    }

}
