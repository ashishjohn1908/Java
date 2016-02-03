// Demonstrate HashSet. 

import java.util.*;

class HashSetDemo {
    public static void main(String args[]) {
        // Create a hash set.
        HashSet<String> hs = new HashSet<String>();

        // Add elements to the hash set.
        hs.add("B");
        hs.add("A");
        hs.add("D");
        hs.add("E");
        hs.add("C");
        hs.add("F");

        System.out.println(hs);

        // Create a hash set.
        Set<String> hs1 = new HashSet<String>();

        // Add elements to the hash set.
        hs1.add("B");
        hs1.add("A");
        hs1.add("D");
        hs1.add("E");
        hs1.add("C");
        hs1.add("F");

        Iterator<String> itor = hs1.iterator();
        SortedSet ss = new TreeSet();
        while (itor.hasNext()) {
            ss.add(itor.next());
        }

        System.out.println(ss);
    }
}
