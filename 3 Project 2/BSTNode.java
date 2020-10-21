// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ren Robinson (rarobin98), Jared Harvey (jharvey33)

/**
 * @author Ren Robinson (rarobin98), Jared Harvey (jharvey33)
 * @version 2020.10.20
 *
 */
public class BSTNode<K, E> {
    private K key;
    private E element;
    private BSTNode<K, E> left;
    private BSTNode<K, E> right;

    public BSTNode() {
        left = right = null;
    }


    public BSTNode(K k, E val) {
        left = right = null;
        key = k;
        element = val;
    }


    public BSTNode(K k, E val, BSTNode<K, E> l, BSTNode<K, E> r) {
        left = l;
        right = r;
        key = k;
        element = val;
    }


    public K key() {
        return key;
    }


    public K setKey(K k) {
        return key = k;
    }


    public E element() {
        return element;
    }


    public E setElement(E v) {
        return element = v;
    }


    public BSTNode<K, E> left() {
        return left;
    }


    public BSTNode<K, E> setLeft(BSTNode<K, E> p) {
        return left = p;
    }


    public BSTNode<K, E> right() {
        return right;
    }


    public BSTNode<K, E> setRight(BSTNode<K, E> p) {
        return right = p;
    }


    public boolean isLeaf() {
        return (left == null) && (right == null);
    }


    /**
     * 
     * @return The height of this tree.
     */
    public int getHeight() {
        // Base case
        if (left == null && right == null) {
            return 0;
        }

        int leftHeight = 0;
        int rightHeight = 0;
        if (left != null) {
            leftHeight = left.getHeight();
        }
        if (right != null) {
            rightHeight = right.getHeight();
        }

        if (leftHeight > rightHeight) {
            return leftHeight + 1;
        }
        else {
            return rightHeight + 1;
        }
    }
}
