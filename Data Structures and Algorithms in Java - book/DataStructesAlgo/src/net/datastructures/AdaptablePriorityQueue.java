package net.datastructures;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 15-Aug-2010
 * Time: 18:27:46
 * To change this template use File | Settings | File Templates.
 */
public interface AdaptablePriorityQueue<K, V> extends PriorityQueue<K, V> {
    /**
     * Replaces the value of the given entry
     *
     * @param e
     * @param value
     * @return
     */
    public V replaceValue(Entry<K, V> e, V value);

    /**
     * Replaces the key of the given entry
     *
     * @param entry
     * @param k
     * @return
     */
    public K replaceKey(Entry<K, V> entry, K k);

    /**
     * Removes and returns the given entry
     *
     * @param entry
     * @return
     */
    public Entry<K, V> remove(Entry<K, V> entry);
}