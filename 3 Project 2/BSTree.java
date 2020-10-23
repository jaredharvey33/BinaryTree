
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
public class BSTree<K extends KeyVector<?, ?, ?>, E> {
    private BSTNode<K, E> root; // Root of BST
    int nodecount; // Size of BST

    /** Constructor */
    BSTree() {
        root = null;
        nodecount = 0;
    }


    /**
     * @return the root
     */
    public BSTNode<K, E> getRoot() {
        return root;
    }


    /**
     * @param root
     *            the root to set
     */
    public void setRoot(BSTNode<K, E> root) {
        this.root = root;
    }


    /** Reinitialize tree */
    public void clear() {
        root = null;
        nodecount = 0;
    }


    /**
     * Insert a record into the tree. @param k Key value of the record. @param e
     * The record to insert.
     */
    public void insert(K k, E e) {
        root = inserthelp(root, k, e);
        nodecount++;
    }


    /**
     * Insert a record into the tree. @param k Key value of the record. @param e
     * The record to insert.
     */
    public void insertIntDesc(K k, E e) {
        root = insertIntDescHelp(root, k, e);
        nodecount++;
    }


    /**
     * Remove a record from the tree.
     * 
     * @param k
     *            Key value of record to remove.
     * @return Record removed, or null if
     *         there is none.
     */
    public E remove(K k) {
        E temp = findhelp(root, k); // find it
        if (temp != null) {
            root = removehelp(root, k); // remove it
            nodecount--;
        }
        return temp;
    }


    /**
     * @return Record with key k, null if none. @param k The key value to find.
     */
    public E find(K k) {
        return findhelp(root, k);
    }


    /** @return Number of records in dictionary. */
    public int size() {
        return nodecount;
    }


    private E findhelp(BSTNode<K, E> rt, K k) {
        if (rt == null)
            return null;
        if (rt.key().compareTo(k) > 0)
            return findhelp(rt.left(), k);
        else if (rt.key().compareTo(k) == 0)
            return rt.element();
        else
            return findhelp(rt.right(), k);
    }


    private BSTNode<K, E> inserthelp(BSTNode<K, E> rt, K k, E e) {
        if (rt == null)
            return new BSTNode<K, E>(k, e);
        if (rt.key().compareTo(k) < 0)
            rt.setLeft(inserthelp(rt.left(), k, e));
        else
            rt.setRight(inserthelp(rt.right(), k, e));
        return rt;
    }


    private BSTNode<K, E> getmin(BSTNode<K, E> rt) {
        if (rt.left() == null)
            return rt;
        else
            return getmin(rt.left());
    }


    private BSTNode<K, E> insertIntDescHelp(BSTNode<K, E> rt, K k, E e) {
        if (rt == null)
            return new BSTNode<K, E>(k, e);
        if (rt.key().compareToIntDesc(k) < 0)
            rt.setLeft(insertIntDescHelp(rt.left(), k, e));
        else
            rt.setRight(insertIntDescHelp(rt.right(), k, e));
        return rt;
    }


    private BSTNode<K, E> deletemin(BSTNode<K, E> rt) {
        if (rt.left() == null)
            return rt.right();
        else {
            rt.setLeft(deletemin(rt.left()));
            return rt;
        }
    }


    /**
     * Remove a node with key value k
     * 
     * @return The tree with the node removed
     */
    private BSTNode<K, E> removehelp(BSTNode<K, E> rt, K k) {
        if (rt == null)
            return null;
        if (rt.key().compareTo(k) > 0)
            rt.setLeft(removehelp(rt.left(), k));
        else if (rt.key().compareTo(k) < 0)
            rt.setRight(removehelp(rt.right(), k));
        else { // Found it, remove it
            if (rt.left() == null)
                return rt.right();
            else if (rt.right() == null)
                return rt.left();
            else { // Two children
                BSTNode<K, E> temp = getmin(rt.right());
                rt.setElement(temp.element());
                rt.setKey(temp.key());
                rt.setRight(deletemin(rt.right()));
            }
        }
        return rt;
    }

}
