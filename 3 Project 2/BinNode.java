// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Ren Robinson (rarobin98)

/**
 * @author Ren Robinson (rarobin98)
 * @version 2020.10.11
 *
 */
public interface BinNode<K extends Comparable<K>, E> {

    public K getKey();


    public E getElement();


    public BinNode<K,E> getLeft();


    public BinNode<K, E> getRight();


    public BinNode<K, E> insert(K k, E e);


    public BinNode<K, E> remove(K k);

}
