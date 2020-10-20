
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ren Robinson (rarobin98)

/**
 * @author Ren Robinson (rarobin98)
 * @version 2020.10.20
 *
 */
public class BSTNode<K extends KeyVector<?, ?, ?>, E> {
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
        int height = 1;

        // Base case: current node has 0 children
        if (right == null && left == null) {
            return height;
        }

        if (right != null && left != null) {
            height = height + Math.max(left.getHeight(), right.getHeight());
        }

        // Recursive case 1: current node has 1 child on the left
        else if (left != null) {
            height = height + left.getHeight();
        }

        // Recursive Case 2: current node has 1 child on the right
        else {
            height = height + right.getHeight();
        }

        return height;
    }
}
