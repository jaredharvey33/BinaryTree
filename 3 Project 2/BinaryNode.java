import binarySearchTree.BinaryNode;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who do.
// -- Ren Robinson (rarobin98)

/**
 * @author Ren Robinson (rarobin98)
 * @version 2020.10.06
 *
 */
public class BinaryNode<Record>
{
    //~ Instance/static variables .............................................

    private Record element;
    private BinaryNode<Record> left;
    private BinaryNode<T> right;


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a node with no children.
     * @param theElement the element to store in this node.
     */
    BinaryNode(T theElement)
    {
        element = theElement;
        left = null;
        right = null;
    }


    //~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Get the current data value stored in this node.
     * @return the element
     */
    public T getElement()
    {
        return element;
    }


    // ----------------------------------------------------------
    /**
     * Set the data value stored in this node.
     * @param value the new data value to set
     */
    public void setElement(T value)
    {
        element = value;
    }


    // ----------------------------------------------------------
    /**
     * Get the left child of this node.
     * @return a reference to the left child.
     */
    public BinaryNode<T> getLeft()
    {
        return left;
    }


    // ----------------------------------------------------------
    /**
     * Set this node's left child.
     * @param value the node to point to as the left child.
     */
    public void setLeft(BinaryNode<T> value)
    {
        left = value;
    }


    // ----------------------------------------------------------
    /**
     * Get the right child of this node.
     * @return a reference to the right child.
     */
    public BinaryNode<T> getRight()
    {
        return right;
    }


    // ----------------------------------------------------------
    /**
     * Set this node's right child.
     * @param value the node to point to as the right child.
     */
    public void setRight(BinaryNode<T> value)
    {
        right = value;
    }

    /**
     * Provides an in-order representation of the node
     * @return a string representation of the node
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (left != null)
        {
            builder.append(left.toString() + ", ");
        }
        builder.append(element.toString());
        if (right != null)
        {
            builder.append(", " + right.toString());
        }
        return builder.toString();
    }
}
