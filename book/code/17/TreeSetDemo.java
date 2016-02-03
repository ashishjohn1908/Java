// Demonstrate TreeSet. 

import java.util.*;
import java.util.concurrent.*;

class TreeSetDemo {
    public static void main(String args[]) {
        // Create a tree set.
        TreeSet<String> ts = new TreeSet<String>();

        // Add elements to the tree set.
        ts.add("C");
        ts.add("D");
        ts.add("A");
        ts.add("B");
        ts.add("E");
        ts.add("F");
        ts.add("D");
        ts.add("D");

        System.out.println(ts);
        System.out.println(ts.subSet("B", "E"));

        // Create a tree set.
        SortedSet<String> ss = new TreeSet<String>();

        // Add elements to the tree set.
        ss.add("C");
        ss.add("D");
        ss.add("A");
        ss.add("B");
        ss.add("E");
        ss.add("F");
        ss.add("D");
        ss.add("D");

        System.out.println(ss);
        System.out.println(ss.subSet("B", "F"));

        // Create a priority queue.
        PriorityQueue<String> pq = new PriorityQueue<String>();

        // Add elements to the priority queue.
        pq.add("C");
        pq.add("D");
        pq.add("A");
        pq.add("B");
        pq.add("E");
        pq.add("F");
        pq.add("D");
        pq.add("D");

        System.out.println(pq);


        // Create a linked blocking queue.
        LinkedBlockingQueue<String> lbq = new LinkedBlockingQueue();

        // Add elements to the linked blocked queue
        lbq.add("C");
        lbq.add("D");
        lbq.add("A");
        lbq.add("B");
        lbq.add("E");
        lbq.add("F");
        lbq.add("D");
        lbq.add("D");

        System.out.println(lbq);

    }
}