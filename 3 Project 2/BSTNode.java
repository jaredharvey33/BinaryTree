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
 * @param <K>
 *            node key value
 * @param <E>
 *            node element value
 */
public class BSTNode<K, E> {
    private K key;
    private E element;
    private BSTNode<K, E> left;
    private BSTNode<K, E> right;

    /**
     * Empty Constructor
     */
    public BSTNode() {
        left = null;
        right = null;
    }


    /**
     * Constructor for 1 BSTNode
     * 
     * @param k
     *            Node key value
     * @param val
     *            Node element value
     */
    public BSTNode(K k, E val) {
        left = null;
        right = null;
        key = k;
        element = val;
    }


    /**
     * Constructor for 2 BSTNode
     * 
     * @param k
     *            Key value for root
     * @param val
     *            Element value for root
     * @param l
     *            Left node
     * @param r
     *            Right node
     */
    public BSTNode(K k, E val, BSTNode<K, E> l, BSTNode<K, E> r) {
        left = l;
        right = r;
        key = k;
        element = val;
    }


    /**
     * Gets the key of a node
     * 
     * @return Key value for node
     */
    public K key() {
        return key;
    }


    /**
     * Sets the key of a node
     * 
     * @param k
     *            Key value
     */
    public void setKey(K k) {
        this.key = k;
    }


    /**
     * Gets the element of a node
     * 
     * @return the element
     */
    public E element() {
        return element;
    }


    /**
     * Sets the element of a node
     * 
     * @param v
     *            Element to set
     */
    public void setElement(E v) {
        this.element = v;
    }


    /**
     * Gets the left node
     * 
     * @return the left node
     */
    public BSTNode<K, E> left() {
        return left;
    }


    /**
     * Sets the left node
     * 
     * @param p
     *            Node to set
     */
    public void setLeft(BSTNode<K, E> p) {
        this.left = p;
    }


    /**
     * Gets the right node
     * 
     * @return the new right node
     */
    public BSTNode<K, E> right() {
        return right;
    }


    /**
     * Sets the right node
     * 
     * @param p
     *            Node to set
     */
    public void setRight(BSTNode<K, E> p) {
        this.right = p;
    }


    /**
     * checks if a node is a leaf
     * 
     * @return boolean indicating if it is a leaf or not
     */
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

        if (rightHeight > leftHeight) {
            return rightHeight + 1;
        }
        else {
            return leftHeight + 1;
        }
    }


    /**
     * Gets the level of a certain node on the tree
     * 
     * @param rt
     *            root of the tree
     * @param e
     *            element of the node
     * @param l
     *            level of the node
     * @return level of the node
     */
    public int getLevel(BSTNode<K, E> rt, E e, int l) {
        if (rt == null) {
            return 0;
        }
        if (rt.element().equals(e)) {
            return l;
        }

        int result = getLevel(rt.left, e, l + 1);
        if (result != 0) {
            return result;
        }
        result = getLevel(rt.right, e, l + 1);

        return result;
    }
}
