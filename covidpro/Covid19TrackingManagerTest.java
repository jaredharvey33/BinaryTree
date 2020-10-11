import java.util.ArrayList;
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ren Robinson (rarobin98), Jared Harvey (jharvey33)

/**
 * @author Ren Robinson (rarobin98), Jared Harvey (jharvey33)
 * @version 2020.09.22
 *
 */
public class Covid19TrackingManagerTest extends student.TestCase {

    private Record r1;
    private Record r1Copy;
    private Record r2;
    private Record r3;
    private Record r4;
    private Record r5;
    private Record r4Copy;
    private Record r5Copy;
    private Record r6;
    private Record r7;
    private Record r8;

    String states;
    String[] stateArray;
    ArrayList<Record> sortDate;
    ArrayList<Record> sortState;

    /**
     * sets up the tests classes.
     */

    public void setUp() {

        states = "American Samoa - AS\r\n" + "\r\n"
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

        r1 = new Record(20200815, "AK", -1, 1, -1, 1, 1, 1, "A", 1);
        r1Copy = new Record(20200815, "AK", 1, 1, 1, 1, 1, 1, "A", 1);
        r2 = new Record(20200814, "CO", 1, 1, 1, 1, 1, 1, "A", 1);
        r3 = new Record(20200810, "WY", 1, 1, 1, 1, 1, 1, "A", 1);
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
        r8 = new Record(20200719, "KS", 1, 1, 1, 1, 1, 1, "A", 1);

    }// end setUp


    /**
     * tests the printSumArray method
     */
    public void testPrintSumArray() {
        Covid19TrackingManager.printSumArray(sortState);
        assertEquals(sortState.size(), 3);

    }


    /**
     * tests the printSummary method
     */
    public void testPrintSummary() {
        Covid19TrackingManager.printSummary(sortState, stateArray);
        assertEquals(sortState.size(), 3);

    }


    /**
     * tests the printTopDate method
     */
    public void printTopDate() {
        Covid19TrackingManager.printTopDate(sortState, 20200815);
        assertEquals(sortState.size(), 4);

    }


    /**
     * tests the isDuplicate method
     */
    public void testIsDuplicate() {
        // case 1 returns record
        assertEquals(Covid19TrackingManager.isDuplicate(r1, sortState), r1);
        // case 2 returns null
        assertEquals(Covid19TrackingManager.isDuplicate(r6, sortState), null); // different
                                                                               // date
        assertEquals(Covid19TrackingManager.isDuplicate(r7, sortState), null); // different
                                                                               // state
        assertEquals(Covid19TrackingManager.isDuplicate(r8, sortState), null); // different
                                                                               // state
                                                                               // and
                                                                               // date

    }


    /**
     * tests the greaterGrade method
     */
    public void testGreaterGrade() {
        // case 1 if
        assertFalse(Covid19TrackingManager.greaterGrade(r1, r1Copy));
        // case 2 else if
        assertTrue(Covid19TrackingManager.greaterGrade(r4, r1));
        assertTrue(Covid19TrackingManager.greaterGrade(r1, r5));
        // else false case under else if
        assertFalse(Covid19TrackingManager.greaterGrade(r1, r4));
        assertFalse(Covid19TrackingManager.greaterGrade(r5, r1));
        // last return false else
        assertFalse(Covid19TrackingManager.greaterGrade(r5Copy, r5));
        // last boolean test
        assertTrue(Covid19TrackingManager.greaterGrade(r5Copy, r4Copy));
        assertFalse(Covid19TrackingManager.greaterGrade(r4Copy, r5Copy));

    }


    /**
     * tests the abConversion method
     */
    public void testAbConversion() {
        // main case
        assertEquals(Covid19TrackingManager.abConversion("AK", stateArray),
            "Alaska");
        assertEquals(Covid19TrackingManager.abConversion("WY", stateArray),
            "Wyoming");
        assertEquals(Covid19TrackingManager.abConversion("FM", stateArray),
            "Federated States of Micronesia");
        // return null case
        assertEquals(Covid19TrackingManager.abConversion("alksjdf", stateArray),
            null);
        assertNull(Covid19TrackingManager.abConversion("", stateArray));

    }


    /**
     * tests the replacementCheck method
     */
    public void testReplacementCheck() {
        assertTrue(Covid19TrackingManager.replacementCheck(r1, r4));

    }


    /**
     * tests the dateToInt method
     */
    public void testDateToInt() {
        assertEquals(Covid19TrackingManager.dateToString(20200815),
            "08/15/2020");
        assertEquals(Covid19TrackingManager.dateToString(20200810),
            "08/10/2020");

    }


    /**
     * tests the dateToString method
     */
    public void testDateToString() {
        assertEquals(Covid19TrackingManager.dateToString(20200815),
            "08/15/2020");
        assertEquals(Covid19TrackingManager.dateToString(20200810),
            "08/10/2020");
    }


    /**
     * tests the ContainsState method
     */
    public void testContainsDate() {
        assertTrue(Covid19TrackingManager.containsDate("08/15/2020", sortDate));
        assertFalse(Covid19TrackingManager.containsDate("08/25/2020",
            sortDate));

    }


    /**
     * tests the ContainsState method
     */
    public void testContainsState() {
        assertTrue(Covid19TrackingManager.containsState("AK", sortDate,
            stateArray));
        assertTrue(Covid19TrackingManager.containsState("CO", sortDate,
            stateArray));
        assertFalse(Covid19TrackingManager.containsState("WI", sortDate,
            stateArray));
    }


    /**
     * tests the sortByState method
     */
    public void testSortByState() {
        sortDate = Covid19TrackingManager.sortByState(sortState, stateArray);
        assertEquals(sortDate.get(0).getState(), "AK");
        assertEquals(sortDate.get(2).getState(), "WY");

    }


    /**
     * tests the sortByDate method
     */
    public void testSortByDate() {
        sortDate = Covid19TrackingManager.sortByDate(sortDate);
        assertEquals(sortDate.get(0).getDate(), 20200815);
        assertEquals(sortDate.get(2).getDate(), 20200810);

    }

}
