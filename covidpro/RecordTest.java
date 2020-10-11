
/**
 * This class tests the methods in the Record class.
 * 
 * @author Jared Harvey (jharvey33), Ren Robinson (rarobin98)
 * @version 2020.09.24
 *
 */
public class RecordTest extends student.TestCase {

    private Record r1;
    private Record r2;
    private Record r3;
    private Record r4;
    private Record r5;

    /**
     * sets up the tests classes.
     */

    public void setUp() {
        r1 = new Record(1, "VA", 1, 1, 1, 1, 1, 1, "A", 1);
        r2 = new Record(1, "VA", 1, 1, 1, 1, 1, 1, "A", 1);
        r3 = new Record(2, "VA", 1, 1, 1, 1, 1, 1, "A", 1);
        r4 = new Record(1, "MD", 1, 1, 1, 1, 1, 1, "A", 1);
        r5 = new Record(1, "MD", -1, -1, -1, -1, -1, -1, "A", -1);

    }// end setUp


    /**
     * tests the toString method
     */
    public void testToString() {
        assertEquals(r1.toString(), "1,VA,1,1,1,1,1,A,1");

    }


    /**
     * tests the intsArray method
     */
    public void testIntsArray() {
        assertTrue(r1.intsArray().get(0) == 1);
        assertTrue(r1.intsArray().get(1) == 1);
        assertTrue(r1.intsArray().get(2) == 1);
        assertTrue(r1.intsArray().get(3) == 1);
        assertTrue(r1.intsArray().get(4) == 1);
        assertTrue(r1.intsArray().get(5) == 1);
        assertTrue(r1.intsArray().get(6) == 1);

    }


    /**
     * tests the getDate method
     */
    public void testGetDate() {
        assertEquals(r1.getDate(), 1);
    }


    /**
     * tests the setDate method
     */
    public void testSetDate() {
        assertEquals(r1.getDate(), 1);
        r1.setDate(2);
        assertEquals(r1.getDate(), 2);

    }


    /**
     * tests the getState method
     */
    public void testGetState() {
        assertEquals(r1.getState(), "VA");

    }


    /**
     * tests the setState method
     */
    public void testSetState() {
        assertEquals(r1.getState(), "VA");
        r1.setState("WA");
        assertEquals(r1.getState(), "WA");
    }


    /**
     * tests the getPositive method
     */
    public void testGetPositive() {
        assertEquals(r1.getPositive(), 1);
        assertEquals(r5.getPositive(), 0);

    }


    /**
     * tests the setPositive method
     */
    public void testSetPositive() {
        assertEquals(r1.getPositive(), 1);
        r1.setPositive(2);
        assertEquals(r1.getPositive(), 2);
    }


    /**
     * tests the getNegative method
     */
    public void testGetNegative() {
        assertEquals(r1.getNegative(), 1);
        assertEquals(r5.getNegative(), 0);

    }


    /**
     * tests the setNegative method
     */
    public void testSetNegative() {
        assertEquals(r1.getNegative(), 1);
        r1.setNegative(2);
        assertEquals(r1.getNegative(), 2);
    }


    /**
     * tests the getHospitalized method
     */
    public void testGetHospitalized() {
        assertEquals(r1.getHospitalized(), 1);
        assertEquals(r5.getHospitalized(), 0);

    }


    /**
     * tests the setHospitalized method
     */
    public void testSetHospitalized() {
        assertEquals(r1.getHospitalized(), 1);
        r1.setHospitalized(2);
        assertEquals(r1.getHospitalized(), 2);
    }


    /**
     * tests the getOnVentilatorCummulative method
     */
    public void testGetOnVentilatorCummulative() {
        assertEquals(r1.getOnVentilatorCumulative(), 1);
        assertEquals(r5.getOnVentilatorCumulative(), 0);

    }


    /**
     * tests the setOnVentilatorCummulative method
     */
    public void testSetOnVentilatorCummulative() {
        assertEquals(r1.getOnVentilatorCumulative(), 1);
        r1.setOnVentilatorCumulative(2);
        assertEquals(r1.getOnVentilatorCumulative(), 2);
    }


    /**
     * tests the getOnVentilatorCurrently method
     */
    public void testGetOnVentilatorCurrently() {
        assertEquals(r1.getOnVentilatorCurrently(), 1);
        assertEquals(r5.getOnVentilatorCurrently(), 0);

    }


    /**
     * tests the setOnVentilatorCurrently method
     */
    public void testSetOnVentilatorCurrently() {
        assertEquals(r1.getOnVentilatorCurrently(), 1);
        r1.setOnVentilatorCurrently(2);
        assertEquals(r1.getOnVentilatorCurrently(), 2);
    }


    /**
     * tests the getRecovered method
     */
    public void testGetRecovered() {
        assertEquals(r1.getRecovered(), 1);
        assertEquals(r5.getRecovered(), 0);

    }


    /**
     * tests the setRecovered method
     */
    public void testSetRecovered() {
        assertEquals(r1.getRecovered(), 1);
        r1.setRecovered(2);
        assertEquals(r1.getRecovered(), 2);
    }


    /**
     * tests the getDataQualityGrade method
     */
    public void testGetDataQualityGrade() {
        assertEquals(r1.getDataQualityGrade(), "A");
    }


    /**
     * tests the setDataQualityGrade method
     */
    public void testSetDataQualityGrade() {
        assertEquals(r1.getDataQualityGrade(), "A");
        r1.setDataQualityGrade("B");
        assertEquals(r1.getDataQualityGrade(), "B");
    }


    /**
     * tests the getDeath method
     */
    public void testGetDeath() {
        assertEquals(r1.getDeath(), 1);
        assertEquals(r5.getDeath(), 0);

    }


    /**
     * tests the setDeath method
     */
    public void testSetDeath() {
        assertEquals(r1.getDeath(), 1);
        r1.setDeath(2);
        assertEquals(r1.getDeath(), 2);
    }


    /**
     * tests the compareToState method
     */
    public void testCompareTo() {
        assertEquals(r1.compareTo(r2), 0);
        assertEquals(r1.compareTo(r4), 1);
        assertEquals(r4.compareTo(r1), -1);

    }


    /**
     * tests the compareTo method
     */
    public void testCompareToDate() {
        assertEquals(r1.compareToDate(r2), 0);
        assertEquals(r3.compareToDate(r1), -1);
        assertEquals(r1.compareToDate(r3), 1);

    }

}
