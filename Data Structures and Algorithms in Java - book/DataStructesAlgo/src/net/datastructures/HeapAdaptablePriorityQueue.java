package net.datastructures;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 15-Aug-2010
 * Time: 18:29:52
 * To change this template use File | Settings | File Templates.
 */
public class HeapAdaptablePriorityQueue<K, V> implements AdaptablePriorityQueue<K, V> {

    public HeapAdaptablePriorityQueue() {

    }

    public HeapAdaptablePriorityQueue(Comparator dc) {

    }


    public V replaceValue(Entry<K, V> e, V value) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public K replaceKey(Entry<K, V> kvEntry, K k) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Entry<K, V> remove(Entry<K, V> kvEntry) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int size() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isEmpty() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Entry<K, V> min() throws EmptyPriorityQueueException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
