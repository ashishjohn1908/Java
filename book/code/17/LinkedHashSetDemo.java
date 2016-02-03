// Demonstrate HashSet. 

import java.util.*;

class LinkedHashSetDemo {
    public static void main(String args[]) {
        // Create a hash set.
        LinkedHashSet<String> hs = new LinkedHashSet<String>();

        // Add elements to the hash set.
        hs.add("B");
        hs.add("A");
        hs.add("D");
        hs.add("E");
        hs.add("C");
        hs.add("F");

        System.out.println(hs);
    }
}
