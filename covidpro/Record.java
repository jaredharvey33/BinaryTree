
import java.util.ArrayList;

/**
 * This class holds the information for creating a Record instance
 * 
 * @author Ren Robinson (rarobin98), Jared Harvey (jharvey33)
 * @version 2020.09.02
 *
 */

public class Record implements Comparable<Record> {

    private int date;
    private String state;
    private int positive;
    private int negative;
    private int hospitalized;
    private int onVentilatorCurrently;
    private int onVentilatorCumulative;
    private int recovered;
    private String dataQualityGrade;
    private int death;

    /**
     * Empty constructor for Record
     */
    public Record() {
        super();
    }


    /**
     * Constructor to create instance of the Record class
     * 
     * @param date
     *            The date for the Record
     * @param state
     *            The state for the Record
     * @param positive
     *            The Positive cases for the state
     * @param negative
     *            The Negative cases for the state
     * @param hospitalized
     *            The Hospitalized cases for the state
     * @param onVentilatorCurrently
     *            The COVID patients currently on a ventilator for the state
     * @param onVentilatorCumulative
     *            The total COIVD patients that have been on a ventilator for
     *            the state
     * @param recovered
     *            The patients that have recovered for the state
     * @param dataQualityGrade
     *            The quality grade for the data in the record
     * @param death
     *            The amount of deaths according to the record
     */
    public Record(
        int date,
        String state,
        int positive,
        int negative,
        int hospitalized,
        int onVentilatorCurrently,
        int onVentilatorCumulative,
        int recovered,
        String dataQualityGrade,
        int death) {

        this.date = date;
        this.state = state;
        this.positive = positive;
        this.negative = negative;
        this.hospitalized = hospitalized;
        this.onVentilatorCurrently = onVentilatorCurrently;
        this.onVentilatorCumulative = onVentilatorCumulative;
        this.recovered = recovered;
        this.dataQualityGrade = dataQualityGrade;
        this.death = death;
    }


    /**
     * Prints the information for a record
     * 
     * @return The formatted information for a record
     */
    @Override
    public String toString() {
        return date + "," + state + "," + positive + "," + negative + ","
            + hospitalized + "," + onVentilatorCurrently + "," + recovered + ","
            + dataQualityGrade + "," + death;

    }


    /**
     * Method to get field values that possibly need to be updated by more
     * recent data
     * 
     * @return arr of field values
     */
    public ArrayList<Integer> intsArray() {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(positive);
        arr.add(negative);
        arr.add(hospitalized);
        arr.add(onVentilatorCurrently);
        arr.add(onVentilatorCumulative);
        arr.add(recovered);
        arr.add(death);

        return arr;
    }


    /**
     * Gets the date of a Record
     * 
     * @return the date
     */
    public int getDate() {
        return date;
    }


    /**
     * Sets the date of a Record
     * 
     * @param date
     *            the date to set
     */
    public void setDate(int date) {
        this.date = date;
    }


    /**
     * Gets the state of a Record
     * 
     * @return the state
     */
    public String getState() {
        return state;
    }


    /**
     * Sets the state of a Record
     * 
     * @param state
     *            the state to set
     */
    public void setState(String state) {
        this.state = state;
    }


    /**
     * Gets the positive cases for a Record
     * 
     * @return the positive
     */
    public int getPositive() {
        if (positive == -1) {
            return 0;
        }
        return positive;
    }


    /**
     * Sets the positive cases for a Record
     * 
     * @param positive
     *            the positive to set
     */
    public void setPositive(int positive) {
        this.positive = positive;
    }


    /**
     * Gets the negative cases for a Record
     * 
     * @return the negative
     */
    public int getNegative() {
        if (negative == -1) {
            return 0;
        }
        return negative;
    }


    /**
     * Sets the negative cases for a Record
     * 
     * @param negative
     *            the negative to set
     */
    public void setNegative(int negative) {
        this.negative = negative;
    }


    /**
     * Gets the hospitalized cases for a Record
     * 
     * @return the hospitalized
     */
    public int getHospitalized() {
        if (hospitalized == -1) {
            return 0;
        }
        return hospitalized;
    }


    /**
     * Sets the hospitalized cases for a Record
     * 
     * @param hospitalized
     *            the hospitalized to set
     */
    public void setHospitalized(int hospitalized) {
        this.hospitalized = hospitalized;
    }


    /**
     * Gets the patients on a ventilator currently for a Record
     * 
     * @return the onVentilatorCurrently
     */
    public int getOnVentilatorCurrently() {
        if (onVentilatorCurrently == -1) {
            return 0;
        }
        return onVentilatorCurrently;
    }


    /**
     * Sets the patients on a ventilator currently for a Record
     * 
     * @param onVentilatorCurrently
     *            the onVentilatorCurrently to set
     */
    public void setOnVentilatorCurrently(int onVentilatorCurrently) {
        this.onVentilatorCurrently = onVentilatorCurrently;
    }


    /**
     * Gets the total number of patients that have been on a ventilator for a
     * Record
     * 
     * @return the onVentilatorCumulative
     */
    public int getOnVentilatorCumulative() {
        if (onVentilatorCumulative == -1) {
            return 0;
        }
        return onVentilatorCumulative;
    }


    /**
     * Sets the total number of patients that have been on a ventilator for a
     * Record
     * 
     * @param onVentilatorCumulative
     *            the onVentilatorCumulative to set
     */
    public void setOnVentilatorCumulative(int onVentilatorCumulative) {
        this.onVentilatorCumulative = onVentilatorCumulative;
    }


    /**
     * Gets the recovered cases for a Record
     * 
     * @return the recovered
     */
    public int getRecovered() {
        if (recovered == -1) {
            return 0;
        }
        return recovered;
    }


    /**
     * Sets the recovered cases for a Record
     * 
     * @param recovered
     *            the recovered to set
     */
    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }


    /**
     * Gets the data quality grade for a Record
     * 
     * @return the dataQualityGrade
     */
    public String getDataQualityGrade() {
        return dataQualityGrade;
    }


    /**
     * Sets the data quality grade for a Record
     * 
     * @param dataQualityGrade
     *            the dataQualityGrade to set
     */
    public void setDataQualityGrade(String dataQualityGrade) {
        this.dataQualityGrade = dataQualityGrade;
    }


    /**
     * Gets the number of deaths for a Record
     * 
     * @return the death
     */
    public int getDeath() {
        if (death == -1) {
            return 0;
        }
        return death;
    }


    /**
     * Sets the number of deaths for a Record
     * 
     * @param death
     *            the death to set
     */
    public void setDeath(int death) {
        this.death = death;
    }


    /**
     * 
     * Method to compare two records by their state
     * 
     * @param o
     *            record to compare
     * @return integer representing the outcome of comparing records by state
     */
    public int compareTo(Record o) {
        if (this.state.compareToIgnoreCase(o.state) > 0) {
            return 1;
        }
        else if (this.state.compareToIgnoreCase(o.state) < 0) {
            return -1;
        }
        else {
            return 0;
        }
    }


    /**
     * 
     * Method to compare two records by their date
     * 
     * @param o
     *            record to compare
     * @return integer representing the outcome of comparing records by date
     */
    public int compareToDate(Record o) {
        if (this.date > o.date) {
            return -1;
        }
        else if (this.date < o.date) {
            return 1;
        }
        else {
            return 0;
        }
    }

}
