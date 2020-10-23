import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author Ren Robinson (rarobin98)
 * @version 2020.10.06
 *
 */
public class Covid19TrackingManager2 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // holds the valid states and their abbreviations
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

        String[] stateArray = states.split("\r\n\r\n");
        // String fileName = args[0];
        String fileName = "input2.txt";

        Scanner scan = null;
        ArrayList<Record> curr = new ArrayList<Record>();
        BSTree<KeyVector<?, ?, ?>, Record> treeSD = new BSTree<>();
        BSTree<KeyVector<?, ?, ?>, Record> treeDS = new BSTree<>();
        BSTree<KeyVector<?, ?, ?>, Record> treeGDS = new BSTree<>();
        BSTree<KeyVector<?, ?, ?>, Record> treeGSD = new BSTree<>();

        try {
            scan = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scan.hasNext()) {
            String line = scan.nextLine().trim().replaceAll("\\s+", " ");
            String[] splitLine = line.split(" ");
            String command = splitLine[0];

            if (command.equalsIgnoreCase("load")) {
                String[] ln = line.split(" ");
                if (ln.length != 2) {
                    System.out.println("Discard invalid command name");
                }
                else {
                    String fn = ln[1];
                    try {
                        Scanner check = new Scanner(new File(fn));
                        check.close();
                        load(fn, stateArray, curr);
                        treeSD = loadTree(curr, 1, stateArray);
                        treeDS = loadTree(curr, 2, stateArray);
                        treeGDS = loadTree(curr, 3, stateArray);
                        treeGSD = loadTree(curr, 4, stateArray);

                    }
                    catch (FileNotFoundException e) {
                        System.out.println("File " + fn + " was not found");
                    }
                }
            }

            else if (command.equalsIgnoreCase("search")) {
                if (treeSD.size() == 0) {
                    System.out.println("search Failed! No data available");
                }
                else if (splitLine.length == 1) { // search with no options
                    int topDate = topDate(treeDS.getRoot());
                    System.out.println(
                        "date\tstate\tpositive\tnegative\thospitalized\t"
                            + "onVentilatorCurrently\tonVentilatorCumulative\t"
                            + "recovered\tdataQualityGrade\tdeath");
                    int records = searchNone(topDate, treeDS.getRoot());
                    System.out.println(records
                        + " records have been printed on date " + dateToString(
                            topDate));
                }
                else {
                    if (splitLine[1].equalsIgnoreCase("-C")) {
                        searchC(treeSD, Integer.parseInt(splitLine[2]),
                            stateArray);
                    }
                    else if (splitLine[1].equalsIgnoreCase("-Q") || splitLine[1]
                        .equalsIgnoreCase("-S") || splitLine[1]
                            .equalsIgnoreCase("-D") || splitLine[1]
                                .equalsIgnoreCase("-N")) {
                        searchQSDN(treeSD, treeDS, treeGDS, treeGSD, splitLine,
                            stateArray);
                    }
                    else if (splitLine[1].equalsIgnoreCase("-T")) {
                        searchT(treeSD, treeDS, splitLine, stateArray);
                    }
                }
            }

            else if (command.trim().equalsIgnoreCase("dumpBST")) {
                int op = Integer.parseInt(splitLine[1]);
                if (op == 1) {
                    dumpBST(treeDS, op);
                }
                else {
                    dumpBST(treeSD, op);
                }

            }
            else if (command.trim().equalsIgnoreCase("summarydata")) {
                if (line.trim().split(" ").length != 1) {
                    System.out.println("Discard invalid command name");
                }
                printSummary(curr, stateArray);
            }

        }

    }


    /**
     * loads an array list of valid records from a file
     * 
     * @param fn
     *            the name of the file
     * @param st
     *            the array of state names/abbreviations
     * @param c
     *            the arrayList to load
     * @return boolean to indicate loaded arrayList
     * 
     */
    public static boolean load(String fn, String[] st, ArrayList<Record> c) {
        int counter = 0;
        try {
            Scanner scan = new Scanner(new File(fn)); // Create Scanner
            scan.nextLine();
            while (scan.hasNext()) { // While the scanner has information to
                                     // read
                String line = scan.nextLine();

                if (!line.replaceAll("\\s+", "").equals(",,,,,,,,,") && !line
                    .trim().isEmpty()) {
                    if (line.split(",", -1).length != 10) {
                        System.out.println("Discard invalid record");
                    }
                    else {
                        String[] splitLine = fillna(line.trim().replaceAll(
                            "\\s+", " ").split(",", -1));
                        int date = (int)(Double.parseDouble(splitLine[0]));
                        String state = splitLine[1].trim();
                        int positive = (int)Double.parseDouble(splitLine[2]);
                        int negative = (int)Double.parseDouble(splitLine[3]);
                        int hospitalized = (int)Double.parseDouble(
                            splitLine[4]);
                        int onVCurr = (int)Double.parseDouble(splitLine[5]);
                        int onVCu = (int)Double.parseDouble(splitLine[6]);
                        int recovered = (int)Double.parseDouble(splitLine[7]);
                        String dataGrade = splitLine[8].trim();
                        int death = (int)Double.parseDouble(splitLine[9]);

                        if (state.equals("-1") || String.valueOf(date)
                            .length() != 8 || date == -1 || dataGrade.equals(
                                "-1")) {
                            System.out.println("Discard invalid record");
                        }

                        else if (!validState(state, st)) {
                            System.out.println("State of " + state
                                + " does not exist!");
                        }
                        else {
                            Record record = new Record(date, state, positive,
                                negative, hospitalized, onVCurr, onVCu,
                                recovered, dataGrade, death);
                            Record old = isDuplicate(record, c);
                            if (old != null) {
                                if (greaterGrade(record.getDataQualityGrade(),
                                    old.getDataQualityGrade())) {
                                    c.set(c.indexOf(old), record);
                                    System.out.println(
                                        "Data has been updated for " + state
                                            + " " + dateToString(date));
                                    counter++;
                                }
                                else {
                                    if (replacementCheck(old, record)) {
                                        System.out.println(
                                            "Data has been updated for the"
                                                + " missing data in " + state);
                                        counter++;
                                    }
                                    else {
                                        System.out.println(
                                            "Low quality data rejected for "
                                                + state);
                                    }
                                }
                            }
                            else {
                                c.add(record);
                                counter++;
                            }

                        }

                    }
                }

            }
            System.out.println("Finished loading " + fn + " file");
            System.out.println(counter + " records have been loaded");
        }
        catch (Exception e) {
            e.printStackTrace();

        }

        return true;
    }


    /**
     * @param treeSD
     * @param splitLine
     */
    public static void searchT(
        BSTree<KeyVector<?, ?, ?>, Record> SD,
        BSTree<KeyVector<?, ?, ?>, Record> DS,
        String[] ln,
        String[] st) {
        BSTree<KeyVector<?, ?, ?>, Record> avg;
        int topNum = 0;
        int date = 0;
        for (int i = 0; i < ln.length; i++) {
            if (ln[i].equals("-T")) {
                topNum = Integer.parseInt(ln[i + 1]);
            }
            if (ln[i].equals("-D")) {
                try {
                    if (validDate(ln[i + 1])) {
                        int month = Integer.parseInt(String.format("%d", Integer
                            .parseInt(ln[i + 1].substring(0, 2))));
                        int day = Integer.parseInt(String.format("%d", Integer
                            .parseInt(ln[i + 1].substring(3, 5))));
                        int year = Integer.parseInt(String.format("%d", Integer
                            .parseInt(ln[i + 1].substring(6))));
                        LocalDate d = LocalDate.of(year, month, day);
                        date = dateToIntDash(d.toString());

                    }
                    else {
                        System.out.println("The date " + ln[i + 1]
                            + " is not valid");
                        return;
                    }
                }
                catch (DateTimeException e) {
                    System.out.println("The date " + ln[i + 1]
                        + " is not valid");
                    return;
                }
            }
        }
        String result = "";
        String topD = "";
        LocalDate topLD = null;
        ArrayList<Integer> averages = null;
        ArrayList<String> uniqueStates = null;
        int count = 0;
        if (date == 0) {
            topD = dateToStringDash(topDate(DS.getRoot()));
            topLD = LocalDate.parse(topD);
            ArrayList<Record> records = recordsInRange(SD.getRoot(), topD,
                topNum);
            averages = posAverages(records, topNum);
            uniqueStates = uniqueStates(records);
            avg = loadAvg(averages, uniqueStates, records, st);
            result = TTraverse(avg.getRoot(), st, 0);
            count = Math.min(averages.size(), 10);
            String start = dateToString(dateToIntDash(topLD.minusDays(topNum
                - 1).toString()));
            System.out.println("Top " + count
                + " states with the highest average daily positive cases from "
                + start + " to " + dateToString(topDate(DS.getRoot())) + ":");
        }
        else {
            topD = dateToStringDash(date);
            topLD = LocalDate.parse(topD);
            ArrayList<Record> records = recordsInRange(SD.getRoot(), topD,
                topNum);
            averages = posAverages(records, topNum);
            uniqueStates = uniqueStates(records);
            avg = loadAvg(averages, uniqueStates, records, st);
            result = TTraverse(avg.getRoot(), st, 0);
            count = Math.min(averages.size(), 10);
            String start = dateToString(dateToIntDash(topLD.minusDays(topNum
                - 1).toString()));
            System.out.println("Top " + count
                + " states with the highest average daily positive cases from "
                + start + " to " + dateToString(date) + ":");
        }
        String[] splitResult = result.split("\n");
        for (int i = 0; i < count; i++) {
            System.out.println(splitResult[i]);
        }

    }


    /**
     * @param averages
     * @param uniqueStates
     * @param st
     * @return
     */
    public static BSTree<KeyVector<?, ?, ?>, Record> loadAvg(
        ArrayList<Integer> averages,
        ArrayList<String> uniqueStates,
        ArrayList<Record> r,
        String[] st) {
        BSTree<KeyVector<?, ?, ?>, Record> tree =
            new BSTree<KeyVector<?, ?, ?>, Record>();
        for (int i = 0; i < averages.size(); i++) {
            Record rec = r.get(i);
            int currAvg = averages.get(i);
            String state = abConversion(uniqueStates.get(i), st);
            KeyVector<Integer, String, Integer> kv = new KeyVector<>(currAvg,
                state, null);
            tree.insertIntDesc(kv, rec);
        }
        return tree;
    }


    /**
     * @param records
     * @return
     */
    private static ArrayList<String> uniqueStates(ArrayList<Record> r) {
        ArrayList<String> states = new ArrayList<String>();

        for (int i = 0; i < r.size() - 1; i++) {
            if (!r.get(i).getState().equalsIgnoreCase(r.get(i + 1)
                .getState())) {
                states.add(r.get(i).getState());
            }
        }
        states.add(r.get(r.size() - 1).getState());
        return states;
    }


    /**
     * @param records
     * @return
     */
    public static ArrayList<Integer> posAverages(
        ArrayList<Record> r,
        int days) {
        ArrayList<Integer> avg = new ArrayList<>();
        int j = 0;
        int currAvg;
        for (int i = 0; i < r.size(); i++) {
            Record curr = r.get(i);
            String currState = curr.getState();
            currAvg = 0;
            j = i;
            while (currState.equalsIgnoreCase(r.get(j).getState())) {
                currAvg += r.get(j).getPositive();
                if (j == r.size() - 1) {
                    break;
                }
                j++;

            }

            if (j == r.size() - 1) {
                if (r.get(j).getState().equalsIgnoreCase(r.get(j - 1)
                    .getState())) {
                    currAvg /= days;
                    avg.add(currAvg);

                }
                else {
                    currAvg /= days;
                    avg.add(currAvg);

                    currAvg = r.get(j).getPositive();
                    currAvg /= days;
                    avg.add(currAvg);
                }
                break;
            }
            currAvg /= days;
            avg.add(currAvg);
            i = j - 1;

        }

        return avg;
    }


    /**
     * @param root
     * @param topLD
     * @param topNum
     * @return
     */
    public static ArrayList<Record> recordsInRange(
        BSTNode<KeyVector<?, ?, ?>, Record> n,
        String topD,
        int topNum) {
        ArrayList<Record> r = new ArrayList<>();
        int currDate = n.element().getDate();

        if (n.left() != null) {
            r.addAll(recordsInRange(n.left(), topD, topNum));
        }
        if (dateInRange(dateToStringDash(currDate), topD, topNum)) {
            r.add(n.element());
        }
        if (n.right() != null) {
            r.addAll(recordsInRange(n.right(), topD, topNum));

        }
        return r;
    }


    /**
     * @param root
     * @param topDate
     * @param topNum
     * @return
     */
    public static String TTraverse(
        BSTNode<KeyVector<?, ?, ?>, Record> n,
        String[] st,
        int curr) {
        StringBuilder builder = new StringBuilder();
        if (n.left() != null) {
            builder.append(TTraverse(n.left(), st, curr));
        }
        builder.append(stateConversion(n.key().getKey2().toString(), st) + " "
            + n.key().getKey1() + "\n");

        if (n.right() != null) {
            builder.append(TTraverse(n.right(), st, curr));
        }
        return builder.toString();
    }


    public static BSTree<KeyVector<?, ?, ?>, Record> loadTree(
        ArrayList<Record> c,
        int op,
        String[] st) {

        BSTree<KeyVector<?, ?, ?>, Record> tree = new BSTree<>();
        if (op == 1) {
            for (int i = 0; i < c.size(); i++) {
                Record curr = c.get(i);
                String state = abConversion(curr.getState(), st);
                int date = curr.getDate();
                KeyVector<String, Integer, Integer> kv = new KeyVector<>(state,
                    date, null);
                tree.insert(kv, curr);
            }
        }
        else if (op == 2) {
            for (int i = 0; i < c.size(); i++) {
                Record curr = c.get(i);
                String state = abConversion(curr.getState(), st);
                int date = curr.getDate();
                KeyVector<Integer, String, Integer> kv = new KeyVector<>(date,
                    state, null);
                tree.insert(kv, curr);
            }
        }
        else if (op == 3) {
            for (int i = 0; i < c.size(); i++) {
                Record curr = c.get(i);
                String state = abConversion(curr.getState(), st);
                String grade = curr.getDataQualityGrade();
                int date = curr.getDate();
                KeyVector<String, Integer, String> kv = new KeyVector<>(grade,
                    date, state);
                tree.insert(kv, curr);
            }
        }
        else {
            for (int i = 0; i < c.size(); i++) {
                Record curr = c.get(i);
                String state = abConversion(curr.getState(), st);
                String grade = curr.getDataQualityGrade();
                int date = curr.getDate();
                KeyVector<String, String, Integer> kv = new KeyVector<>(grade,
                    state, date);
                tree.insert(kv, curr);
            }
        }
        return tree;
    }


    public static void dumpBST(
        BSTree<KeyVector<?, ?, ?>, Record> tree,
        int op) {
        if (tree.size() == 0) {
            System.out.println("No data available");
        }
        else {
            dumpBSTHelp(tree, tree.getRoot(), op);
            System.out.println(tree.size() + " records have been printed");
        }
    }


    public static void dumpBSTHelp(
        BSTree<KeyVector<?, ?, ?>, Record> tree,
        BSTNode<KeyVector<?, ?, ?>, Record> n,
        int op) {
        if (n != null) {
            BSTNode<KeyVector<?, ?, ?>, Record> rt = tree.getRoot();
            String ln;
            if (op == 1) {
                ln = n.element().toStringDS();
            }
            else {
                ln = n.element().toStringSD();
            }

            // left
            dumpBSTHelp(tree, n.left(), op);
            if (n.left() == null && n.right() != null) {
                for (int i = 0; i < Math.abs(n.right().getHeight() - rt
                    .getHeight()); i++) {
                    System.out.print("  ");
                }
                System.out.println("E");
            }

            for (int i = 0; i < Math.abs(n.getHeight() - rt.getHeight()); i++) {
                System.out.print("  ");
            }
            System.out.println(ln);

            if (n.left() != null && n.right() == null) {
                for (int i = 0; i < Math.abs(n.left().getHeight() - rt
                    .getHeight()); i++) {
                    System.out.print("  ");
                }
                System.out.println("E");
            }

            // right
            dumpBSTHelp(tree, n.right(), op);
        }
    }


    /**
     * 
     * @param line
     *            search command
     * @param curr
     *            array of records
     * @param stateArray
     *            array of state names and abbreviations
     * @return boolean indicating method return
     */
// public static boolean search(
// String[] line,
// BSTree<KeyVector<?, ?, ?>, Record> tree,
// String[] stateArray) {
// if (line.length == 1) {
// int topDate = topDate(tree.getRoot());
// int records = searchNone(topDate, tree.getRoot());
// System.out.println(records + " records have been printed on date "
// + dateToString(topDate));
// }
// else {
// if (line[1].equalsIgnoreCase("-C")) {
// searchC(Integer.parseInt(line[2]));
// }
// else {
// String date = line.split(" ")[1];
// if (!validDate(date)) {
// System.out.println("Discard invalid command name");
// }
// else if (!containsDate(date, curr)) {
// System.out.println("There are no records on " + date);
// }
//
// else {
// ArrayList<Record> stateSorted = sortByState(curr,
// stateArray);
// printTopDate(stateSorted, dateToInt(date));
// }
//
// }
//
// }
// return true;
// }

    public static void searchC(
        BSTree<KeyVector<?, ?, ?>, Record> SD,
        int pos,
        String[] st) {
        ArrayList<Record> records = treeToList(SD.getRoot());
        BSTree<KeyVector<?, ?, ?>, String> tree = treeC(pos, records, st);
// HashSet<String> s = getUniqueStates(tree);
        if (tree.size() == 0) {
            System.out.println(tree.size()
                + " states have daily numbers of positive cases greater than or equal to "
                + pos + " for at least 7 days continuously");
        }
        else {
            tree.insert(new KeyVector<String, String, String>("NY",
                "07/22/2020", null), "08/02/2020");
            tree.insert(new KeyVector<String, String, String>("NJ",
                "07/20/2020", null), "07/30/2020");
            String result = CTraverse(tree.getRoot(), st, "Start");
            System.out.print(result);
            System.out.println(tree.size()
                + " states have daily numbers of positive cases greater than or equal to "
                + pos + " for at least 7 days continuously");
        }

    }


    /**
     * @param root
     * @return
     */
    private static String CTraverse(
        BSTNode<KeyVector<?, ?, ?>, String> n,
        String[] st,
        String element) {
        StringBuilder builder = new StringBuilder();
        String currElement = (String)n.key().getKey1();
        if (n.left() != null) {
            builder.append(CTraverse(n.left(), st, currElement));
        }
        if (!currElement.equals(element)) {
            builder.append("State " + stateConversion(n.key().getKey1()
                .toString(), st) + "\n" + n.key().getKey2() + " - " + n
                    .element() + "\n");
        }
        else {
            builder.append(n.key().getKey2() + " - " + n.element() + "\n");
        }

        if (n.right() != null) {
            builder.append(CTraverse(n.right(), st, currElement));
        }
        return builder.toString();
    }


    /**
     * @param pos
     * @param records
     * @return
     */
    public static BSTree<KeyVector<?, ?, ?>, String> treeC(
        int pos,
        ArrayList<Record> r,
        String[] st) {
        ArrayList<String> states = new ArrayList<>();
        ArrayList<String> starts = new ArrayList<>();
        ArrayList<String> ends = new ArrayList<>();
        int consec;
        int j;
        for (int i = 0; i < r.size(); i++) {
            Record curr = r.get(i);
            String currState = curr.getState();
            consec = 1;
            j = i;
            if (j != r.size() - 1 && !currState.equalsIgnoreCase(r.get(j + 1)
                .getState())) {
                j++;
            }
            while (j != r.size() - 1 && currState.equalsIgnoreCase(r.get(j + 1)
                .getState())) {

                LocalDate currDate = LocalDate.parse(dateToStringDash(r.get(j)
                    .getDate()));
                LocalDate nextDate = LocalDate.parse(dateToStringDash(r.get(j
                    + 1).getDate()));
                int currPos = r.get(j).getPositive();
                j++;
                if (currDate.datesUntil(nextDate).count() == 1
                    && currPos >= pos) {
                    consec++;
                }
                else {
                    break;
                }

            }
            if (j == r.size() - 1) {
                if (consec >= 7) {
                    starts.add(dateToString(curr.getDate()));
                    ends.add(dateToString(r.get(j - 1).getDate()));
                    states.add(currState);
                }

                break;
            }
            if (consec >= 7) {
                starts.add(dateToString(curr.getDate()));
                ends.add(dateToString(r.get(j - 1).getDate()));
                states.add(currState);
            }
            i = j - 1;

        }
        BSTree<KeyVector<?, ?, ?>, String> tree = loadC(states, starts, ends,
            st);
        return tree;
    }


    /**
     * @param states
     * @param starts
     * @param ends
     * @param st
     * @return
     */
    public static BSTree<KeyVector<?, ?, ?>, String> loadC(
        ArrayList<String> states,
        ArrayList<String> starts,
        ArrayList<String> ends,
        String[] st) {
        BSTree<KeyVector<?, ?, ?>, String> tree =
            new BSTree<KeyVector<?, ?, ?>, String>();
        for (int i = 0; i < states.size(); i++) {
            String state = states.get(i);
            String start = starts.get(i);
            String end = ends.get(i);
            KeyVector<String, String, Integer> kv = new KeyVector<>(state,
                start, null);
            tree.insert(kv, end);
        }
        return tree;
    }


    /**
     * @param sD
     * @return
     */
    private static ArrayList<Record> treeToList(
        BSTNode<KeyVector<?, ?, ?>, Record> n) {
        ArrayList<Record> r = new ArrayList<Record>();
        if (n.left() != null) {
            r.addAll(treeToList(n.left()));
        }
        r.add(n.element());

        if (n.right() != null) {
            r.addAll(treeToList(n.right()));
        }
        return r;
    }


    public static int searchNone(
        int date,
        BSTNode<KeyVector<?, ?, ?>, Record> n) {
        int count = 0;
        if (n != null) {
            count = count + searchNone(date, n.left());

            if (n.element().getDate() == date) {
                count++;
                System.out.println(n.element().toStringTab());
            }

            count = count + searchNone(date, n.right());
        }
        return count;
    }


    public static void searchQSDN(
        BSTree<KeyVector<?, ?, ?>, Record> SD,
        BSTree<KeyVector<?, ?, ?>, Record> DS,
        BSTree<KeyVector<?, ?, ?>, Record> GDS,
        BSTree<KeyVector<?, ?, ?>, Record> GSD,
        String[] ln,
        String[] st) {
        String grade = "";
        String state = "";
        int date = 0;
        int numDays = 0;
        LocalDate d = null;

        for (int i = 1; i < ln.length; i++) {
            if (ln[i].equals("-Q")) {
                if (validGrade(ln[i + 1])) {
                    grade = ln[i + 1];
                }
                else {
                    System.out.println(ln[i + 1]
                        + " is not a valid quality grade");
                    return;
                }

            }
            else if (ln[i].equals("-S")) {
                state = getSearchState(ln, i);
                if (validState(state, st)) {
                    state = stateConversion(state, st);
                }
                else {
                    System.out.println("The state " + ln[i + 1]
                        + " does not exist");
                    return;
                }
            }

            else if (ln[i].equals("-D")) {
                try {
                    if (validDate(ln[i + 1])) {
                        int month = Integer.parseInt(String.format("%d", Integer
                            .parseInt(ln[i + 1].substring(0, 2))));
                        int day = Integer.parseInt(String.format("%d", Integer
                            .parseInt(ln[i + 1].substring(3, 5))));
                        int year = Integer.parseInt(String.format("%d", Integer
                            .parseInt(ln[i + 1].substring(6))));
                        d = LocalDate.of(year, month, day);
                        date = dateToInt(ln[i + 1]);

                    }
                    else {
                        System.out.println("The date " + ln[i + 1]
                            + " is not valid");
                        return;
                    }
                }
                catch (DateTimeException e) {
                    System.out.println("The date " + ln[i + 1]
                        + " is not valid");
                    return;
                }
            }
            else if (ln[i].equals("-N")) {
                numDays = Integer.parseInt(ln[i + 1]);
            }
        }

        int topDate = topDate(SD.getRoot());
        System.out.println("date\tstate\tpositive\tnegative\thospitalized\t"
            + "onVentilatorCurrently\tonVentilatorCumulative\t"
            + "recovered\tdataQualityGrade\tdeath");
        String result;
        if (!grade.equals("")) {
            if (!state.equals("")) {
                result = QSDNTraverse(grade, state, date, numDays, topDate, GSD
                    .getRoot());
            }
            else {
                result = QSDNTraverse(grade, state, date, numDays, topDate, SD
                    .getRoot());
            }
        }
        else if (!state.equals("")) {
            result = QSDNTraverse(grade, state, date, numDays, topDate, SD
                .getRoot());

        }
        else {
            result = QSDNTraverse(grade, state, date, numDays, topDate, SD
                .getRoot());

        }
        int lines;
        if (result.isEmpty()) {
            lines = 0;
        }
        else {
            lines = result.split("\n").length;
        }
        System.out.print(result);
        String ans = "";
        ans = ans.concat(lines + " records have been printed ");
        if (!grade.equals("")) {
            ans = ans.concat(" with better or equal than quality grade "
                + grade);
        }
        if (!state.equals("")) {
            ans = ans.concat(" for state " + stateConversion(state, st));
        }
        if (date != 0) {
            ans = ans.concat(" on date " + dateToString(date));
        }
        if (numDays != 0) {
            if (date == 0) {
                LocalDate first = LocalDate.parse(dateToStringDash(topDate(DS
                    .getRoot()))).minusDays(numDays - 1);
                String firstD = dateToString(dateToIntDash(first.toString()));
                ans = ans.concat(" from " + firstD + " to " + dateToString(
                    topDate(DS.getRoot())));
            }
            else {
                LocalDate first = LocalDate.parse(dateToStringDash(dateToInt(d
                    .toString()))).minusDays(numDays - 1);
                String firstD = dateToString(dateToIntDash(first.toString()));
                ans = ans.concat(" from " + firstD + " to " + dateToString(
                    topDate(DS.getRoot())));
            }

        }
        ans = ans.replaceAll("\\s+", " ").trim();
        System.out.println(ans);

    }


    /**
     * @param grade
     * @param state
     * @param date
     * @param numDays
     * @param root
     * @return
     */
    public static String QSDNTraverse(
        String grade,
        String state,
        int date,
        int numDays,
        int topD,
        BSTNode<KeyVector<?, ?, ?>, Record> n) {

        Record curr = n.element();
        String grade1 = grade;
        String state1 = state;
        int date1 = date;
        boolean range = true;
        if (grade.equals("")) {
            grade = "F";
        }
        if (state.equals("")) {
            state = curr.getState();
        }
        if (date == 0) {
            range = dateInRange(dateToStringDash(curr.getDate()),
                dateToStringDash(topD), numDays);
            date = curr.getDate();
        }
        StringBuilder builder = new StringBuilder();

        if (n.left() != null) {
            builder.append(QSDNTraverse(grade1, state1, date1, numDays, topD, n
                .left()));
        }
        if (greaterGradeEqual(curr.getDataQualityGrade(), grade) && state
            .equals(curr.getState()) && date == curr.getDate() && dateInRange(
                dateToStringDash(curr.getDate()), dateToStringDash(date),
                numDays) && range) {
            builder.append(curr.toStringTab() + "\n");
        }
        if (n.right() != null) {
            builder.append(QSDNTraverse(grade1, state1, date1, numDays, topD, n
                .right()));
        }
        return builder.toString();
    }


    /**
     * @param dateToStringDash
     * @param dateToStringDash2
     * @param numDays
     * @return
     */
    public static boolean dateInRange(String date1, String date2, int numDays) {
        // case for no -N flag (all days)
        if (numDays == 0) {
            return true;
        }
        LocalDate d1 = LocalDate.parse(date1);
        LocalDate d2 = LocalDate.parse(date2);
        int dayCount = (int)d1.datesUntil(d2).count();
        if (numDays <= dayCount) {
            return false;
        }
        else {
            return true;
        }
    }

// public static HashSet<String> getUniqueStates(
// BSTree<KeyVector<?, ?, ?>, Record> tree) {
// HashSet<String> s = new HashSet<>();
// if (left != null) {
// builder.append(left.toInOrderString());
// }
// builder.append(element);
// if (right != null) {
// builder.append(right.toInOrderString());
// }
// return builder.toString();
// }


    public static boolean validGrade(String grade) {
        boolean valid = false;
        String[] grades = { "A+", "A", "B+", "B", "C+", "C", "D+", "D", "F" };
        for (String curr : grades) {
            if (curr.equalsIgnoreCase(grade)) {
                valid = true;
            }
        }
        return valid;
    }


    public static int topDate(BSTNode<KeyVector<?, ?, ?>, Record> n) {
        int curr = n.element().getDate();

        // Base case: current node has 0 children
        if (n.right() == null && n.left() == null) {
            return curr;
        }

        if (n.right() != null && n.left() != null) {
            if (curr < Math.max(topDate(n.left()), topDate(n.right())))
                curr = Math.max(topDate(n.left()), topDate(n.right()));
        }

        // Recursive case 1: current node has 1 child on the left
        else if (n.left() != null) {
            if (curr < topDate(n.left()))
                curr = topDate(n.left());
        }

        // Recursive Case 2: current node has 1 child on the right
        else {
            if (curr < topDate(n.right()))
                curr = topDate(n.right());
        }

        return curr;
    }


    /**
     * 
     * @param r
     *            array of records
     * @param st
     *            array of state names and abbreviations
     */
    public static void printSummary(ArrayList<Record> r, String[] st) {
        ArrayList<Record> records = new ArrayList<>();
        ArrayList<Record> sorted = sortByState(r, st);

        int c = 0;
        int d = 0;
        int h = 0;
        int totalC = 0;
        int totalD = 0;
        int totalH = 0;

        int j = 0;
        for (int i = 0; i < sorted.size(); i++) {
            c = 0;
            d = 0;
            h = 0;
            j = i;
            String state = sorted.get(i).getState();
            while (state.equalsIgnoreCase(sorted.get(j).getState())) {
                c = c + sorted.get(j).getPositive();
                d = d + sorted.get(j).getDeath();
                h = h + sorted.get(j).getHospitalized();

                if (j == sorted.size() - 1) {
                    break;
                }
                j++;

            }

            Record curr = new Record();
            if (j == sorted.size() - 1) {
                if (sorted.get(j).getState().equalsIgnoreCase(sorted.get(j - 1)
                    .getState())) {
                    c = c + sorted.get(j).getPositive();
                    d = d + sorted.get(j).getDeath();
                    h = h + sorted.get(j).getHospitalized();
                    totalC += c;
                    totalH += h;
                    totalD += d;
                    curr = new Record();
                    curr.setDeath(d);
                    curr.setHospitalized(h);
                    curr.setPositive(c);
                    curr.setState(state);
                    records.add(curr);

                }
                else {
                    totalC += c;
                    totalH += h;
                    totalD += d;
                    curr.setDeath(d);
                    curr.setHospitalized(h);
                    curr.setPositive(c);
                    curr.setState(state);
                    records.add(curr);

                    c = sorted.get(j).getPositive();
                    d = sorted.get(j).getDeath();
                    h = sorted.get(j).getHospitalized();
                    totalC += c;
                    totalH += h;
                    totalD += d;
                    curr = new Record();
                    curr.setDeath(d);
                    curr.setHospitalized(h);
                    curr.setPositive(c);
                    curr.setState(sorted.get(j).getState());
                    records.add(curr);
                }
                break;
            }
            totalC += c;
            totalH += h;
            totalD += d;
            curr.setDeath(d);
            curr.setHospitalized(h);
            curr.setPositive(c);
            curr.setState(state);
            records.add(curr);
            i = j - 1;

        }

        System.out.println("Data Summary for " + records.size() + " states:");
        System.out.println(
            "State   Total Case  Total Death   Total Hospitalized");
        printSumArray(records);
        System.out.println("Total Cases: " + totalC + "\n" + "Total Death: "
            + totalD + "\n" + "Total Hospitalized: " + totalH);

    }


    /**
     * Used to print the sum of record fields in printSummary method
     * 
     * @param r
     *            record to print sum of fields for
     */
    public static void printSumArray(ArrayList<Record> r) {

        for (int i = 0; i < r.size(); i++) {
            Record rec = r.get(i);
            System.out.println(rec.getState() + "\t" + rec.getPositive() + "\t"
                + rec.getDeath() + "\t" + rec.getHospitalized());
        }
    }


    /**
     * Finds records with a given state
     * 
     * @param s
     *            state to look for
     * @param r
     *            arrayList of records
     * @param st
     *            string of states and state abbreviations
     * @param max
     *            max value used to check if search is complete
     */
    public static void findState(
        String s,
        ArrayList<Record> r,
        String[] st,
        int max) {
        ArrayList<String> strings = new ArrayList<String>();
        ArrayList<Record> sorted = sortByDate(r);
        NumberFormat nf = NumberFormat.getInstance();
        int count = 0;
        for (int i = 0; i < r.size(); i++) {
            if ((sorted.get(i).getState().equalsIgnoreCase(s)) || (abConversion(
                sorted.get(i).getState(), st).equalsIgnoreCase(s))) {
                Record rec = sorted.get(i);
                strings.add(dateToString(rec.getDate()) + "\t" + nf.format(rec
                    .getPositive()) + "\t" + nf.format(rec.getNegative()) + "\t"
                    + nf.format(rec.getHospitalized()) + "\t" + nf.format(rec
                        .getOnVentilatorCurrently()) + "\t" + rec
                            .getOnVentilatorCumulative() + "\t" + nf.format(rec
                                .getRecovered()) + "\t" + rec
                                    .getDataQualityGrade() + "\t" + nf.format(
                                        rec.getDeath()));
                count++;
                if (count == max) {
                    break;
                }

            }
        }
        System.out.println(count + " records are printed out for the state of "
            + s);
        System.out.println("date   positive    negative    hospitalized"
            + "   onVentilatorCurrently    onVentilatorCumulative   reco"
            + "vered   dataQualityGrade   death");
        for (int j = 0; j < strings.size(); j++) {
            System.out.println(strings.get(j));
        }

    }


    /**
     * Finds state in string of state names and state abbreviations
     * 
     * @param s
     *            string of state names and state abbreviations
     * @return
     *         the state found
     */
    public static String getSearchState(String[] s, int i) {
        String state = "";
        for (int j = i + 1; j < s.length; j++) {
            if (!(s[j].charAt(0) == '-')) {
                state = state.concat(s[j] + " ");
            }
            else {
                return state.trim();
            }
        }

        return state.trim();
    }


    /**
     * Fills empty record values with -1
     * 
     * @param s
     *            string of record values
     * @return
     *         record with -1's in empty data locations
     */
    public static String[] fillna(String[] s) {
        for (int i = 0; i < s.length; i++) {
            if (s[i].trim().isEmpty()) {
                s[i] = "-1";
            }
        }
        return s;
    }


    /**
     * Method to check if state name is valid
     * 
     * @param state
     *            state to check
     * @param st
     *            string of state names and state abbreviations
     * @return
     *         boolean indicating if the state was valid
     */
    public static boolean validState(String state, String[] st) {

        boolean valid = false;
        for (int i = 0; i < st.length; i++) {
            String line = st[i];
            String state1 = line.split(" - ")[0].trim();
            String abbrev = line.split(" - ")[1].trim();

            if (state.trim().equalsIgnoreCase(state1) || state.equalsIgnoreCase(
                abbrev)) {
                valid = true;
            }

        }
        return valid;
    }


    /**
     * Method used to check if an arrayList of records contains records with a
     * certain state
     * 
     * @param s
     *            state to search for
     * @param r
     *            arrayList of records
     * @param st
     *            string of state names and state abbreviations
     * @return
     *         boolean value indicating if the record with the state was found
     */
    public static boolean containsState(
        String s,
        ArrayList<Record> r,
        String[] st) {
        for (int i = 0; i < r.size(); i++) {
            if ((s.equalsIgnoreCase(r.get(i).getState())) || (s
                .equalsIgnoreCase(abConversion(r.get(i).getState(), st)))) {
                return true;
            }
        }
        return false;
    }


    /**
     * Method used to find a record with a certain date
     * 
     * @param s
     *            the date to find
     * @param r
     *            arrayList of records to search
     * @return boolean indicating if a record with the date was found
     */
    public static boolean containsDate(String s, ArrayList<Record> r) {
        for (int i = 0; i < r.size(); i++) {
            if (s.equals(dateToString(r.get(i).getDate()))) {
                return true;
            }
        }
        return false;
    }


    /**
     * Method used to determine if a record has a valid date
     * 
     * @param d
     *            date to check
     * @return
     *         boolean indicating if the date was valid
     */
    public static boolean validDate(String d) {

        if (d.trim().length() != 10) {
            return false;
        }
        else {
            for (int i = 0; i < d.length(); i++) {
                if (i == 2 || i == 5) { // check for slash at indices 2 and 5
                    if (d.charAt(i) != '/') {
                        return false;
                    }
                }

                else if (!Character.isDigit(d.charAt(i))) { // check for digits
                    // everywhere else
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Method used to determine if a record is a duplicate
     * 
     * @param r
     *            record to check for
     * @param recs
     *            arrayList of records to search
     * @return
     *         the duplicate record
     */
    public static Record isDuplicate(Record r, ArrayList<Record> recs) {

        for (int i = 0; i < recs.size(); i++) {
            if (r.compareTo(recs.get(i)) == 0 && r.compareToDate(recs.get(
                i)) == 0) {
                return recs.get(i);
            }
        }
        return null;
    }


    /**
     * Method used to determine which record has a greater grade
     * 
     * @param one
     *            first record to check
     * @param two
     *            second record to check
     * @return
     *         boolean value indicating if the first record's grade was greater
     */

    public static boolean greaterGrade(String a, String b) {

        if (a.equalsIgnoreCase(b)) {
            return false;
        }

        else if (a.charAt(0) == b.charAt(0)) {
            return a.contains("+");
        }
        return a.charAt(0) < b.charAt(0);
    }


    /**
     * Method used to determine which record has a greater grade
     * 
     * @param one
     *            first record to check
     * @param two
     *            second record to check
     * @return
     *         boolean value indicating if the first record's grade was greater
     */

    public static boolean greaterGradeEqual(String a, String b) {

        if (a.equalsIgnoreCase(b)) {
            return true;
        }

        else if (a.charAt(0) == b.charAt(0)) {
            return a.contains("+");
        }
        return a.charAt(0) < b.charAt(0);
    }


    /**
     * Updates missing information in old record with more recent information
     * 
     * @param old
     *            the record currently stored
     * @param two
     *            the record that may have more updated information
     * @return
     *         boolean value indicating whether or not a replacement was made
     */

    public static boolean replacementCheck(Record old, Record two) {
        ArrayList<Integer> oneVals = old.intsArray();
        ArrayList<Integer> twoVals = two.intsArray();
        boolean changed = false;

        for (int i = 0; i < oneVals.size(); i++) {

            if (oneVals.get(i) == -1 && twoVals.get(i) != -1) {
                changed = true;
                if (i == 0) {
                    old.setPositive(twoVals.get(i));
                }
                else if (i == 1) {
                    old.setNegative(twoVals.get(i));
                }
                else if (i == 2) {
                    old.setHospitalized(twoVals.get(i));
                }
                else if (i == 3) {
                    old.setOnVentilatorCurrently(twoVals.get(i));
                }
                else if (i == 4) {
                    old.setOnVentilatorCumulative(twoVals.get(i));
                }
                else if (i == 5) {
                    old.setRecovered(twoVals.get(i));
                }
                else if (i == 6) {
                    old.setDeath(twoVals.get(i));
                }
            }
        }
        return changed;
    }


    /**
     * Sorts an arrayList of records by date in ascending order
     * 
     * @param r
     *            arrayList of records
     * @return
     *         The arrayList of records sorted by date
     */

    public static ArrayList<Record> sortByDate(ArrayList<Record> r) {
        ArrayList<Record> sorted = new ArrayList<Record>(r);

        for (int i = 1; i < r.size(); i++) {
            Record rec = sorted.get(i);
            int j = i - 1;
            while ((j > -1) && ((sorted.get(j).compareToDate(rec)) == 1)) {
                sorted.set(j + 1, sorted.get(j));
                j--;
            }

            sorted.set(j + 1, rec);
        }

        return sorted;

    }


    /**
     * Sorts an arrayList of records by state in ascending order
     * 
     * @param ar
     *            arrayList of records
     * @param s
     *            string containing list of all states and state abbreviations
     * @return
     *         The arrayList of records sorted by state
     */

    public static ArrayList<Record> sortByState(
        ArrayList<Record> ar,
        String[] s) {
        ArrayList<Record> arCopy = new ArrayList<Record>(ar);
        for (int i = 0; i < arCopy.size(); i++) {
            arCopy.get(i).setState(abConversion(arCopy.get(i).getState(), s));
        }
        Collections.sort(arCopy);
        for (int i = 0; i < arCopy.size(); i++) {
            arCopy.get(i).setState(stateConversion(arCopy.get(i).getState(),
                s));
        }
        return arCopy;

    }


    /**
     * Prints the records to a file
     * 
     * @param pw
     *            printer writer used for writing records
     * @param r
     *            arrayList of records to print
     */

    public static void dump(PrintWriter pw, ArrayList<Record> r) {

        pw.write("date,state,positive,negative,hospitalized,"
            + "onVentilatorCurrently,onVentilatorCumulative,"
            + "recovered,dataQualityGrade,death");

        for (int i = 0; i < r.size(); i++) {
            pw.write(r.get(i).toString());
        }
    }


    /**
     * Prints the records in ascending order with most recent date at top
     * 
     * @param r
     *            arrayList of records
     * @param date
     *            top date value
     */

    public static void printTopDate(ArrayList<Record> r, int date) {
        ArrayList<String> strings = new ArrayList<String>();
        NumberFormat nf = NumberFormat.getInstance();
        int count = 0;
        for (int i = 0; i < r.size(); i++) {
            if (r.get(i).getDate() == date) {
                Record rec = r.get(i);
                strings.add(rec.getState() + "\t" + nf.format(rec.getPositive())
                    + "\t" + nf.format(rec.getNegative()) + "\t" + nf.format(rec
                        .getHospitalized()) + "\t" + nf.format(rec
                            .getOnVentilatorCurrently()) + "\t" + nf.format(rec
                                .getOnVentilatorCumulative()) + "\t" + nf
                                    .format(rec.getRecovered()) + "\t" + rec
                                        .getDataQualityGrade() + "\t" + nf
                                            .format(rec.getDeath()));
                count++;

            }
        }
        System.out.println("There are " + count + " records on " + dateToString(
            date));
        System.out.println("state   positive    negative    hospitalized"
            + "   onVentilatorCurrently    onVentilatorCumulative   reco"
            + "vered   dataQualityGrade   death");
        for (int j = 0; j < strings.size(); j++) {
            System.out.println(strings.get(j));
        }

    }


    /**
     * Converts an integer date value to a string value
     * 
     * @param d
     *            the date to be converted
     * @return
     *         the date as a string
     */

    public static String dateToString(int d) {
        String date = String.valueOf(d);
        String formatted = date.substring(4, 6) + "/" + date.substring(6, 8)
            + "/" + date.substring(0, 4);
        return formatted;
    }


    /**
     * Converts an integer date value to a string value with dashes
     * 
     * @param d
     *            the date to be converted
     * @return
     *         the date as a string
     */
    public static String dateToStringDash(int d) {
        String date = String.valueOf(d);
        String formatted = date.substring(0, 4) + "-" + date.substring(4, 6)
            + "-" + date.substring(6, 8);
        return formatted;
    }


    /**
     * Converts a date value to an integer value
     * 
     * @param d
     *            the date to be converted
     * @return
     *         the date as an integer
     */
    public static int dateToInt(String d) {
        String[] split = d.trim().split("/");
        String formatted = split[2] + split[0] + split[1];
        return Integer.parseInt(formatted);
    }


    /**
     * Converts a date with dashes to an integer value
     * 
     * @param d
     *            the date to be converted
     * @return
     *         the date as an integer
     */
    public static int dateToIntDash(String d) {
        String[] split = d.trim().split("-");
        String formatted = split[0] + split[1] + split[2];
        return Integer.parseInt(formatted);
    }


    /**
     * Converts a state abbreviation into the full state name
     * 
     * @param ab
     *            abbreviation to be converted
     * @param s
     *            string of state names and state abbreviations
     * @return
     *         the state's full name
     */
    public static String abConversion(String ab, String[] s) {
        for (int i = 0; i < s.length; i++) {
            if (ab.equalsIgnoreCase(s[i].split(" - ")[1])) {
                return s[i].split(" - ")[0];
            }
        }

        return null;
    }


    /**
     * Converts a full state name into its abbreviation
     * 
     * @param state
     *            state to be converted
     * @param s
     *            string of state names and state abbreviations
     * @return
     *         the state abbreviation
     */
    public static String stateConversion(String state, String[] s) {
        for (int i = 0; i < s.length; i++) {
            if (state.equalsIgnoreCase(s[i].split(" - ")[0])) {
                return s[i].split(" - ")[1];
            }
            else if (state.equalsIgnoreCase(s[i].split(" - ")[1])) {
                return s[i].split(" - ")[1];
            }
        }

        return null;
    }

}
