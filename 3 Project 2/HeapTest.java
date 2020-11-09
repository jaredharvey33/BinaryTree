// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ren Robinson (rarobin98)

/**
 * @author Ren Robinson (rarobin98)
 * @version 2020.11.09
 *
 */
public class HeapTest extends student.TestCase {
    
    private static final int MAX = 100;
    private Heap<String> h1;

    public void setUp() {
        String[] ints = new String[MAX];
        h1 = new Heap<>(ints, 0, MAX);

    }


    public void testToString() {
//        System.out.println(h1.toString());
//        System.out.println(h1.removeMin());
//        System.out.println(h1.removeMin());


    }
    
    
    public void testInsert() {
        h1.insert("S");
        h1.insert("C");
        h1.insert("K");
        h1.insert("A");
        h1.insert("Z");
        h1.insert("B");
        System.out.println(h1.toString());
    }

}
